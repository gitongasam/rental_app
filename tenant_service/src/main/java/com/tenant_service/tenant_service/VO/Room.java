package com.tenant_service.tenant_service.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private Long roomId;
    private String roomNumber;
    private BigDecimal rentAmount;
    private String paymentStatus;
    private BigDecimal balance;
}
