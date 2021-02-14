package com.scorsaro.bbas.repository.users;

import com.scorsaro.bbas.model.users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {

        AccountHolder findById(long id);

        AccountHolder findByUsername(String username);
}
