package com.tenant_service.tenant_service.Service;

import com.tenant_service.tenant_service.Entity.Tenant;
import com.tenant_service.tenant_service.Repository.TenantRepository;
import com.tenant_service.tenant_service.VO.ResponseTemplateVo;
import com.tenant_service.tenant_service.VO.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {
    private final TenantRepository tenantRepository;

    @Autowired
    private WebClient webClient;
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public Tenant addTenant(Tenant tenant) {
        BigDecimal rentAmount = getRentAmountFromRoomService(tenant.getRoomId());
        tenant.setBalance(rentAmount);
        updateRoomBalance(tenant.getRoomId(), rentAmount); // Update room balance
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

//    get tenant with room from the room service
    @Override
    public ResponseTemplateVo getTenantWithRoom(Long id) {
        ResponseTemplateVo vo = new ResponseTemplateVo();
        Tenant tenant = tenantRepository.findById(id).orElseThrow(() -> new RuntimeException("Tenant not found with ID: " + id));

        Room room = webClient.get()
                .uri("http://localhost:8080/api/v1/rooms/" + tenant.getRoomId())
                .retrieve()
                .bodyToMono(Room.class)
                .block();

        // Update tenant's balance with the current balance from the room service
        tenant.setBalance(room.getBalance());
        tenantRepository.save(tenant); // Optionally save the updated tenant balance to the database

        vo.setTenant(tenant);
        vo.setRoom(room);

        return vo;
    }

//    get rent amount from room service
    private BigDecimal getRentAmountFromRoomService(Long roomId) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/api/v1/rooms/{roomId}/rent-amount", roomId)
                .retrieve()
                .bodyToMono(BigDecimal.class)
                .block();
    }

//     update room balance to get the balance of the t=room in the tenant

    private void updateRoomBalance(Long roomId, BigDecimal balance) {
        webClientBuilder.build()
                .put()
                .uri("http://localhost:8080/api/v1/rooms/{roomId}/update-balance?balance={balance}", roomId, balance)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnError(e -> {
                    // Log the error
                    System.err.println("Error occurred: " + e.getMessage());
                })
                .block();
    }
}
