package com.devsam.paymentservice.Service;

import com.devsam.paymentservice.Entity.Payment;

import java.util.List;

public interface PaymentService {
    Payment makePayment(Payment payment);

    Payment getPaymentById(Long paymentId);
    List<Payment> getAllPayment();
}
