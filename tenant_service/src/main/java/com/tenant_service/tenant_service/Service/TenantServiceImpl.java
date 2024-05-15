package com.tenant_service.tenant_service.Service;

import com.tenant_service.tenant_service.Entity.Tenant;
import com.tenant_service.tenant_service.Repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService{
    private TenantRepository tenantRepository;

    @Override
    public Tenant addTenant(Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    @Override
    public void deleteTenant(Long tenantID) {
        tenantRepository.deleteById(tenantID);
    }

    @Override
    public Tenant updateTenant(Long tenantID, Tenant tenant) {
        Tenant existingTenant = tenantRepository.findById(tenantID)
                .orElseThrow(() -> new RuntimeException("Tenant not found with ID: " + tenantID));

        // Update existing tenant with new data
        existingTenant.setLandlordId(tenant.getLandlordId());
        existingTenant.setUsername(tenant.getUsername());
        existingTenant.setPassword(tenant.getPassword());
        existingTenant.setBalance(tenant.getBalance());
        existingTenant.setName(tenant.getName());
        existingTenant.setContactInformation(tenant.getContactInformation());

        return tenantRepository.save(existingTenant);
    }

    @Override
    public Tenant getTenant(Long tenantID) {
        return tenantRepository.findById(tenantID)
                .orElseThrow(() -> new RuntimeException("Tenant not found with ID: " + tenantID));
    }

    @Override
    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }
}
