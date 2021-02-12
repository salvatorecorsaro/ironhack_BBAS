package com.scorsaro.bbas.repository.users;

import com.scorsaro.bbas.model.users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {

        //    @Query("SELECT  a FROM Account a WHERE primaryOwner = :id OR secondaryOwner = :id")
//    List<Account> findAllAccountsOwnedByUser(@Param("id") long id);
//    @Query("SELECT a from AccountHolder a WHERE id = :id")
//    AccountHolder findAccountHolderById(@Param("id")Long id);
        AccountHolder findById(long id);
}
