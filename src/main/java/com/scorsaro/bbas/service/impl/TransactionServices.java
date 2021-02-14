package com.scorsaro.bbas.service.impl;

import com.scorsaro.bbas.dto.others.AdminRequestDTO;
import com.scorsaro.bbas.dto.others.TransactionDTO;
import com.scorsaro.bbas.enums.TransactionType;
import com.scorsaro.bbas.model.accounts.Account;
import com.scorsaro.bbas.model.accounts.Checking;
import com.scorsaro.bbas.model.accounts.CreditCard;
import com.scorsaro.bbas.model.accounts.Saving;
import com.scorsaro.bbas.model.others.Money;
import com.scorsaro.bbas.model.others.Transaction;
import com.scorsaro.bbas.model.users.AccountHolder;
import com.scorsaro.bbas.model.users.User;
import com.scorsaro.bbas.repository.accounts.AccountRepository;
import com.scorsaro.bbas.repository.accounts.TransactionRepository;
import com.scorsaro.bbas.service.interfaces.ITransactionServices;
import com.scorsaro.bbas.service.interfaces.IValidationServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServices implements ITransactionServices {
    private static final Logger LOGGER = LogManager.getLogger(CheckingServices.class);

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    IValidationServices validationServices;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<TransactionDTO> findAll() {
        return transactionRepository.findAll().stream().map(transaction -> TransactionDTO.parseFromTransaction(transaction)).collect(Collectors.toList());
    }

    @Override
    public TransactionDTO transferTransaction(User user, TransactionDTO transactionDTO) {
        if (!(validationServices.validateAccountAreDifferent(transactionDTO.getSenderAccount(), transactionDTO.getReceiverAccount())))
            throw new IllegalArgumentException("Account must be different");
        Account senderAccount = accountRepository.findAccountById(transactionDTO.getSenderAccount());
        Account receiverAccount = accountRepository.findAccountById(transactionDTO.getReceiverAccount());
        checkIsNotThirdPartyUser(senderAccount, receiverAccount);

        if (!(validationServices.validateAccountOwnership(user, senderAccount)))
            throw new IllegalArgumentException("User is not the owner of the senderAccount");
        Transaction transaction = Transaction.parseFromTransactionDTO(senderAccount, receiverAccount, transactionDTO);
        if (!(validationServices.validateTransaction(transaction))) {
            throw new IllegalArgumentException("Validation failed!");
        }
        if (!(validationServices.validateRequestName(transaction))) {
            throw new IllegalArgumentException("Validation failed!");
        }
        updateIfPenaltyFeeHasToBeAppliedAfterTransfer(transaction);
        applyTransferChanges(transactionDTO, senderAccount, receiverAccount);
        transactionRepository.save(transaction);
        return TransactionDTO.parseFromTransaction(transaction);
    }

    private void checkIsNotThirdPartyUser(Account senderAccount, Account receiverAccount) {
        User senderOwner = senderAccount.getPrimaryOwner();
//        if (!(senderOwner instanceof ThirdParty)){
//            senderOwner = senderAccount.getSecondaryOwner();
//            if (!(senderOwner instanceof ThirdParty)){
//                throw new IllegalArgumentException("This transaction is from AccountHolder Users");
//            }
//        }
        User receiverOwner = receiverAccount.getPrimaryOwner();
//        if (!(receiverOwner instanceof ThirdParty)){
//            receiverOwner = receiverAccount.getSecondaryOwner();
//            if (!(receiverOwner instanceof ThirdParty)){
//                throw new IllegalArgumentException("This transaction is versus AccountHolder Users");
//            }
//        }
    }

    @Override
    public TransactionDTO transferTransactionToTPU(User user, TransactionDTO transactionDTO, String hashedKey) {
        if (!(validationServices.validateAccountAreDifferent(transactionDTO.getSenderAccount(), transactionDTO.getReceiverAccount())))
            throw new IllegalArgumentException("Account must be different");
        Account senderAccount = accountRepository.findAccountById(transactionDTO.getSenderAccount());
        Account receiverAccount = accountRepository.findAccountById(transactionDTO.getReceiverAccount());
        User receiverOwner = receiverAccount.getPrimaryOwner();
//        if (!(receiverOwner instanceof ThirdParty)){
//            receiverOwner = receiverAccount.getSecondaryOwner();
//            if (!(receiverOwner instanceof ThirdParty)){
//                throw new IllegalArgumentException("This transaction is versus ThirdParty Users");
//            }
//        }
        if (!(validationServices.validateAccountOwnership(user, senderAccount)))
            throw new IllegalArgumentException("User is not the owner of the senderAccount");
        Transaction transaction = Transaction.parseFromTransactionDTO(senderAccount, receiverAccount, transactionDTO);
        if (!(validationServices.validateTransaction(transaction))) {
            throw new IllegalArgumentException("Validation failed!");
        }
        updateIfPenaltyFeeHasToBeAppliedAfterTransfer(transaction);
        applyTransferChangesTPU(transactionDTO, senderAccount, receiverAccount);
        transactionRepository.save(transaction);
        return TransactionDTO.parseFromTransaction(transaction);
    }

    @Override
    public TransactionDTO transferTransactionFromTPU(User user, TransactionDTO transactionDTO, String hashedKey) {
        if (!(validationServices.validateAccountAreDifferent(transactionDTO.getSenderAccount(), transactionDTO.getReceiverAccount())))
            throw new IllegalArgumentException("Account must be different");
        Account senderAccount = accountRepository.findAccountById(transactionDTO.getSenderAccount());
        Account receiverAccount = accountRepository.findAccountById(transactionDTO.getReceiverAccount());
        User senderOwner = senderAccount.getPrimaryOwner();
//        if (!(senderOwner instanceof ThirdParty)){
//            senderOwner = senderAccount.getSecondaryOwner();
//            if (!(senderOwner instanceof ThirdParty)){
//                throw new IllegalArgumentException("This transaction is versus ThirdParty Users");
//            }
//        }
        if (!(validationServices.validateAccountOwnership(user, senderAccount)))
            throw new IllegalArgumentException("User is not the owner of the senderAccount");
        Transaction transaction = Transaction.parseFromTransactionDTO(senderAccount, receiverAccount, transactionDTO);
        if (!(validationServices.validateTransaction(transaction))) {
            throw new IllegalArgumentException("Validation failed!");
        }
        if (!(validationServices.validateRequestName(transaction))) {
            throw new IllegalArgumentException("Validation failed wrong name in the request!");
        }
        updateIfPenaltyFeeHasToBeAppliedAfterTransfer(transaction);
        applyTransferChangesTPU(transactionDTO, senderAccount, receiverAccount);
        transactionRepository.save(transaction);
        return TransactionDTO.parseFromTransaction(transaction);
    }

    private void updateIfPenaltyFeeHasToBeAppliedAfterTransfer(Transaction transaction) {
        if (transaction.getSenderAccount() instanceof Checking) {
            Checking tChecking = (Checking) transaction.getSenderAccount();
            if (tChecking.getBalance().decreaseAmount(transaction.getAmount()).compareTo(tChecking.getMinimumBalance().getAmount()) < 0) {
                updateWithPenaltyFeeTransfer(transaction, tChecking);
            }
        } else if (transaction.getSenderAccount() instanceof Saving) {
            Saving tSaving = (Saving) transaction.getSenderAccount();
            if (tSaving.getBalance().decreaseAmount(transaction.getAmount()).compareTo(tSaving.getMinimumBalance().getAmount()) < 0) {
                updateWithPenaltyFeeTransfer(transaction, tSaving);
            }
        } else if (transaction.getSenderAccount() instanceof CreditCard) {
            CreditCard tCreditCard = (CreditCard) transaction.getSenderAccount();
            if (tCreditCard.getBalance().decreaseAmount(transaction.getAmount()).compareTo(tCreditCard.getCreditLimit().getAmount().multiply(BigDecimal.valueOf(-1))) < 0) {
                updateWithPenaltyFeeTransfer(transaction, tCreditCard);
            }
        }
    }

    private void updateIfPenaltyFeeHasToBeAppliedAfterMonthlyFee(Transaction transaction) {
        if (transaction.getSenderAccount() instanceof Checking) {
            Checking tChecking = (Checking) transaction.getReceiverAccount();
            if (tChecking.getBalance().decreaseAmount(transaction.getAmount()).compareTo(tChecking.getMinimumBalance().getAmount()) < 0) {
                updateWithPenaltyFeeNotTransfer(transaction, tChecking);
            }
        } else if (transaction.getSenderAccount() instanceof Saving) {
            Saving tSaving = (Saving) transaction.getReceiverAccount();
            if (tSaving.getBalance().decreaseAmount(transaction.getAmount()).compareTo(tSaving.getMinimumBalance().getAmount()) < 0) {
                updateWithPenaltyFeeNotTransfer(transaction, tSaving);
            }
        } else if (transaction.getSenderAccount() instanceof CreditCard) {
            CreditCard tCreditCard = (CreditCard) transaction.getReceiverAccount();
            if (tCreditCard.getBalance().decreaseAmount(transaction.getAmount()).compareTo(tCreditCard.getCreditLimit().getAmount().multiply(BigDecimal.valueOf(-1))) < 0) {
                updateWithPenaltyFeeNotTransfer(transaction, tCreditCard);
            }
        }
    }

    private void updateWithPenaltyFeeTransfer(Transaction transaction, CreditCard tCreditCard) {
        Transaction penaltyFeeTransaction = new Transaction(null,
                transaction.getSenderAccount(),
                null,
                new Money(tCreditCard.getPenaltyFee().getAmount().multiply(BigDecimal.valueOf(-1))),
                null,
                TransactionType.PENALTY_FEE);
        applyPenaltyFeeChanges(penaltyFeeTransaction);
        transactionRepository.save(penaltyFeeTransaction);
    }

    private void updateWithPenaltyFeeNotTransfer(Transaction transaction, Saving tSaving) {
        Transaction penaltyFeeTransaction = new Transaction(null,
                transaction.getSenderAccount(),
                null,
                new Money(tSaving.getPenaltyFee().getAmount().multiply(BigDecimal.valueOf(-1))),
                null,
                TransactionType.PENALTY_FEE);
        applyPenaltyFeeChanges(penaltyFeeTransaction);
        transactionRepository.save(penaltyFeeTransaction);
    }

    private void updateWithPenaltyFeeNotTransfer(Transaction transaction, Checking tChecking) {
        Transaction penaltyFeeTransaction = new Transaction(null,
                transaction.getSenderAccount(),
                null,
                new Money(tChecking.getPenaltyFee().getAmount().multiply(BigDecimal.valueOf(-1))),
                null,
                TransactionType.PENALTY_FEE);
        applyPenaltyFeeChanges(penaltyFeeTransaction);
        transactionRepository.save(penaltyFeeTransaction);
    }

    private void updateWithPenaltyFeeNotTransfer(Transaction transaction, CreditCard tCreditCard) {
        Transaction penaltyFeeTransaction = new Transaction(null,
                transaction.getSenderAccount(),
                null,
                new Money(tCreditCard.getPenaltyFee().getAmount().multiply(BigDecimal.valueOf(-1))),
                null,
                TransactionType.PENALTY_FEE);
        applyPenaltyFeeChanges(penaltyFeeTransaction);
        transactionRepository.save(penaltyFeeTransaction);
    }

    private void updateWithPenaltyFeeTransfer(Transaction transaction, Saving tSaving) {
        Transaction penaltyFeeTransaction = new Transaction(null,
                transaction.getSenderAccount(),
                null,
                new Money(tSaving.getPenaltyFee().getAmount().multiply(BigDecimal.valueOf(-1))),
                null,
                TransactionType.PENALTY_FEE);
        applyPenaltyFeeChanges(penaltyFeeTransaction);
        transactionRepository.save(penaltyFeeTransaction);
    }

    private void updateWithPenaltyFeeTransfer(Transaction transaction, Checking tChecking) {
        Transaction penaltyFeeTransaction = new Transaction(null,
                transaction.getSenderAccount(),
                null,
                new Money(tChecking.getPenaltyFee().getAmount().multiply(BigDecimal.valueOf(-1))),
                null,
                TransactionType.PENALTY_FEE);
        applyPenaltyFeeChanges(penaltyFeeTransaction);
        transactionRepository.save(penaltyFeeTransaction);
    }

    private void applyPenaltyFeeChanges(Transaction penaltyFeeTransaction) {
        BigDecimal newBalance = penaltyFeeTransaction.getReceiverAccount().getBalance().getAmount().subtract(penaltyFeeTransaction.getAmount().getAmount());
        Account account = penaltyFeeTransaction.getReceiverAccount();
        account.setBalance(new Money(newBalance));
        accountRepository.save(account);
    }

    private void applyTransferChanges(TransactionDTO transactionDTO, Account senderAccount, Account receiverAccount) {
        Money newSenderBalance = new Money(senderAccount.getBalance().getAmount().subtract(transactionDTO.getAmount()));
        Money newReceiverBalance = new Money(receiverAccount.getBalance().getAmount().add(transactionDTO.getAmount()));
        senderAccount.setBalance(newSenderBalance);
        receiverAccount.setBalance(newReceiverBalance);
        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);
    }

    private void applyTransferChangesTPU(TransactionDTO transactionDTO, Account senderAccount, Account receiverAccount) {
        Money newSenderBalance = new Money(senderAccount.getBalance().getAmount().subtract(transactionDTO.getAmount()));
        Money newReceiverBalance = new Money(receiverAccount.getBalance().getAmount().add(transactionDTO.getAmount()));
        senderAccount.setBalance(newSenderBalance);
        accountRepository.save(senderAccount);
    }

    @Override
    public AdminRequestDTO modifyAccountBalance(AdminRequestDTO adminRequestDTO) {
        Account receiverAccount = accountRepository.findAccountById(adminRequestDTO.getAccountId());
        if (receiverAccount == null) {
            throw new IllegalArgumentException("account not found");
        }
        Transaction transaction = new Transaction(null,
                receiverAccount,
                null,
                new Money(adminRequestDTO.getBalanceOperationValue()),
                null,
                TransactionType.ADMIN_OPERATION);
        Account account = applyAdminBalanceOperationChanges(adminRequestDTO, receiverAccount);
        transactionRepository.save(transaction);
        return adminRequestDTO;
    }

    @Override
    public void createMonthlyMaintenanceFee(Checking account, BigDecimal monthlyMaintenanceFeeToPay) {
        Transaction transaction = new Transaction(null,
                account,
                null,
                new Money(monthlyMaintenanceFeeToPay.multiply(BigDecimal.valueOf(-1))),
                null,
                TransactionType.MONTHLY_MAINTENANCE_FEE);
        Money newBalance = new Money(account.getBalance().decreaseAmount(monthlyMaintenanceFeeToPay));
        account.setBalance(newBalance);
        accountRepository.save(account);
        transactionRepository.save(transaction);
        updateIfPenaltyFeeHasToBeAppliedAfterMonthlyFee(transaction);
    }

    @Override
    public void createInterestTransaction(Saving account, BigDecimal interestToAdd) {
        Transaction transaction = new Transaction(null,
                account,
                null,
                new Money(interestToAdd),
                null,
                TransactionType.INTEREST);
        Money newBalance = new Money(account.getBalance().increaseAmount(interestToAdd));
        account.setBalance(newBalance);
        accountRepository.save(account);
        transactionRepository.save(transaction);
    }

    @Override
    public void createInterestTransaction(CreditCard account, BigDecimal interestToAdd) {
        Transaction transaction = new Transaction(null,
                account,
                null,
                new Money(interestToAdd),
                null,
                TransactionType.INTEREST);
        Money newBalance = new Money(account.getBalance().increaseAmount(interestToAdd));
        account.setBalance(newBalance);
        accountRepository.save(account);
        transactionRepository.save(transaction);
    }

    private Account applyAdminBalanceOperationChanges(AdminRequestDTO adminRequestDTO, Account receiverAccount) {
        Money newBalance = new Money(receiverAccount.getBalance().getAmount().add(adminRequestDTO.getBalanceOperationValue()));
        receiverAccount.setBalance(newBalance);
        accountRepository.save(receiverAccount);
        return receiverAccount;
    }

    private AccountHolder getAccountHolder(long primaryOwnerId, String s) {
        //TODO DELETE THIS METHOD
        AccountHolder primaryOwner = null;
        if (primaryOwnerId >= 0) {
            LOGGER.info("Searching AccountHolder " + primaryOwnerId);
//            primaryOwner = accountHolderRepository.findById(primaryOwnerId);
        } else {
            throw new IllegalArgumentException(s + primaryOwnerId);
        }
        return primaryOwner;
    }
}
