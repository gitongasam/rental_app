package com.tenant_service.tenant_service.Repository;

import com.tenant_service.tenant_service.Entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
}
