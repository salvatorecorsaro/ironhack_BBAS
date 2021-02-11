package com.scorsaro.bbas.repository.accounts;

import com.scorsaro.bbas.model.accounts.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingRepository extends JpaRepository<Checking, Long> {
}
