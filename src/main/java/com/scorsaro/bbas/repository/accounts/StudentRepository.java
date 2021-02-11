package com.scorsaro.bbas.repository.accounts;

import com.scorsaro.bbas.model.accounts.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
