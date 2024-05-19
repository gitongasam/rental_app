package com.devsam.paymentservice.Service;

import com.devsam.paymentservice.Entity.Payment;
import com.devsam.paymentservice.Repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;


    public Payment makePayment(Payment payment) {
        // Implement logic to record payment
        paymentRepository.save(payment);

        // Update room status
        updateRoomStatus(payment.getRoomId(), "paid");

        // Update balance
        BigDecimal rentAmount = getRentAmountFromRoomService(payment.getRoomId());
        BigDecimal updatedBalance = calculateUpdatedBalance(rentAmount, BigDecimal.valueOf(payment.getAmount()));
        updateBalance(payment.getRoomId(), updatedBalance);

        return payment;
    }
    private BigDecimal calculateUpdatedBalance(BigDecimal rentAmount, BigDecimal amount) {
        return rentAmount.subtract(amount);
    }


    //    function to update room status
    private void updateRoomStatus(Long roomId,String status) {
        webClientBuilder.build()
                .put()
                .uri("http://localhost:8080/api/v1/rooms/{roomId}/payment-status?paymentStatus={status}", roomId, status)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnError(e -> {
                    // Log the error
                    System.err.println("Error occurred: " + e.getMessage());
                })
                .block();
    }
//    function to call the balance from the room service
private void updateBalance(Long roomId, BigDecimal balance) {
    webClientBuilder.build()
            .put()
            .uri("http://localhost:8080/api/v1/rooms/{roomId}/update-balance?balance={balance}", roomId, balance)
            .retrieve()
            .bodyToMono(Void.class)
            .doOnError(e -> {
                // Log the error
                System.err.println("Error occurred while updating balance: " + e.getMessage());
            })
            .subscribe(response -> {
                // Log successful update
                System.out.println("Balance updated successfully");
            }, error -> {
                // Log error in subscription
                System.err.println("Error occurred in subscription: " + error.getMessage());
            });
}

    private BigDecimal getRentAmountFromRoomService(Long roomId) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/api/v1/rooms/{roomId}/rent-amount", roomId)
                .retrieve()
                .bodyToMono(BigDecimal.class)
                .block();
    }





    public Payment getPaymentById(Long paymentId) {
        // Implement logic to retrieve payment by ID
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found with ID: " + paymentId));
    }

    @Override
    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }

}
