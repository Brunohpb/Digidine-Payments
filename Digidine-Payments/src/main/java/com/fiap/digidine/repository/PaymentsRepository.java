package com.fiap.digidine.repository;

import com.fiap.digidine.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsRepository extends JpaRepository<Payment, Long> {

    Payment findByOrderNumber(long orderNumber);
}
