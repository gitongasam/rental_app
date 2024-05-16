package com.tenant_service.tenant_service.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private Long roomId;
    private String roomNumber;
    private double rentAmount;
    private String paymentStatus;
}
