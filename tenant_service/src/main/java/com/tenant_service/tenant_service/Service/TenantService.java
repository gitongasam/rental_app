package com.tenant_service.tenant_service.Service;

import com.tenant_service.tenant_service.Entity.Tenant;
import com.tenant_service.tenant_service.VO.ResponseTemplateVo;

import java.util.List;

public interface TenantService {
    Tenant addTenant(Tenant tenant);

    void deleteTenant(Long tenantID);

    Tenant updateTenant(Long tenantID, Tenant tenant);

    Tenant getTenant(Long tenantID);

    List<Tenant> getAllTenants();

    ResponseTemplateVo getTenantWithRoom(Long id);
}
