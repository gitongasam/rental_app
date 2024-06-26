package com.devsam.paymentservice.VO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Room {
    private Long roomId;
    private String roomNumber;
    private double rentAmount;
    private String paymentStatus;
    private BigDecimal balance;
}