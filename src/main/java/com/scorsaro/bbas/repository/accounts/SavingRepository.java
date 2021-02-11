package com.scorsaro.bbas.repository.accounts;

import com.scorsaro.bbas.model.accounts.Saving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingRepository extends JpaRepository<Saving, Long> {
}
