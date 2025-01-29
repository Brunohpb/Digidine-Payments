package com.fiap.digidine.model;

import com.fiap.digidine.model.enums.PaymentMethod;
import com.fiap.digidine.model.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(force = true)
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long paymentNumber;
    private long orderNumber;
    private PaymentStatus paymentStatus;
    private PaymentMethod method;

}
