package com.devsam.paymentservice.Service;

import com.devsam.paymentservice.Entity.Payment;
import com.devsam.paymentservice.Repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;


    public Payment makePayment(Payment payment) {
        // Implement logic to record payment
        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(Long paymentId) {
        // Implement logic to retrieve payment by ID
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found with ID: " + paymentId));
    }

    // Implement other methods as needed
}
