package com.scorsaro.bbas.repository.accounts;

import com.scorsaro.bbas.model.accounts.Account;
import com.scorsaro.bbas.model.others.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT MAX(t.transactionDateTime) FROM Transaction t WHERE sender_account_id = :accountId")
    LocalDateTime findLastTransaction(@Param("accountId") Account id);

    @Query(value = "SELECT SUM(t.amount) FROM transaction t WHERE CAST(t.transaction_date_time AS DATE) != CAST(:currentDate AS DATE) && sender_account_id = :accountId GROUP BY CAST(t.transaction_date_time AS DATE), sender_account_id ORDER BY SUM(t.amount) DESC LIMIT 1",
            nativeQuery = true)
    BigDecimal findHighestDailyTransactionByCustomer(@Param("currentDate") LocalDateTime currentDate, @Param("accountId") Account id);

    @Query(value = "SELECT SUM(t.amount), sender_account_id FROM transaction t where CAST(t.transaction_date_time AS DATE) = CAST(:currentDate AS DATE) AND sender_account_id = :accountId ORDER BY SUM(t.amount) DESC LIMIT 1",
            nativeQuery = true)
    BigDecimal findTodayTotalTransactions(@Param("currentDate") LocalDateTime currentDate, @Param("accountId") Account id);
}
