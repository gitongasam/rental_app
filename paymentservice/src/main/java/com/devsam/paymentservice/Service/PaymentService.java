package com.devsam.paymentservice.Service;

import com.devsam.paymentservice.Entity.Payment;

public interface PaymentService {
    Payment makePayment(Payment payment);

    Payment getPaymentById(Long paymentId);
}
