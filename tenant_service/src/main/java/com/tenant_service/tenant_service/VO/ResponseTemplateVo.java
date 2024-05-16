package com.tenant_service.tenant_service.VO;

import com.tenant_service.tenant_service.Entity.Tenant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplateVo {
    private Tenant tenant;
    private Room room;

}
