package com.msandy.loans.repositories;

import com.msandy.loans.entities.LoanEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoansRepository extends JpaRepository<LoanEntity, Long>
{
    Optional <LoanEntity> findBymobileNumber(String mobileNumber);

//    @Transactional
//    @Modifying
//    Optional<LoanEntity> deleteById(String mobileNumber);
}
