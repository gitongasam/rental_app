package com.tenant_service.tenant_service.Entity;

import com.tenant_service.tenant_service.VO.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long landlordId;
    private String name;
    private String username;
    private String password; // hashed
    private BigDecimal balance;
    private String phoneNumber;

    private Long roomId;

}
