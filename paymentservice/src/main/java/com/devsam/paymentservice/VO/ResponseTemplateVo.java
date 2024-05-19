package com.devsam.paymentservice.VO;
import com.devsam.paymentservice.Entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplateVo {
    private Room room;
    private Payment payment;

}
