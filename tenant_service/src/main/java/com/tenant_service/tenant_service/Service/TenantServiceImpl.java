package com.tenant_service.tenant_service.Service;

import com.tenant_service.tenant_service.Entity.Tenant;
import com.tenant_service.tenant_service.Repository.TenantRepository;
import com.tenant_service.tenant_service.VO.ResponseTemplateVo;
import com.tenant_service.tenant_service.VO.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService{
    private final TenantRepository tenantRepository;

    @Autowired
    private WebClient webClient;

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
        existingTenant.setPhoneNumber(tenant.getPhoneNumber());

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

    @Override
    public ResponseTemplateVo getTenantWithRoom(Long id) {
        ResponseTemplateVo vo = new ResponseTemplateVo();
        Tenant tenant = tenantRepository.findById(id).get();

        Room room = webClient.get()
                .uri("http://localhost:8080/api/v1/rooms/" + tenant.getRoomId()) // Corrected URI
                .retrieve()
                .bodyToMono(Room.class)
                .block();

        vo.setTenant(tenant);
        vo.setRoom(room);

        return vo;
    }
}
