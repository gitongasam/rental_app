package com.tenant_service.tenant_service.Controler;

import com.tenant_service.tenant_service.Entity.Tenant;
import com.tenant_service.tenant_service.Entity.Service.TenantService;
import com.tenant_service.tenant_service.VO.ResponseTemplateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/tenants")
@RestController
@RequiredArgsConstructor

public class TenantController {
    private final TenantService tenantService;

    @PostMapping
    public ResponseEntity<Tenant> addTenant(@RequestBody Tenant tenant) {
        Tenant newTenant = tenantService.addTenant(tenant);
        return new ResponseEntity<>(newTenant, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTenant(@PathVariable Long tenantID) {
        tenantService.deleteTenant(tenantID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{tenantID}")
    public ResponseEntity<Tenant> updateTenant(@PathVariable Long tenantID, @RequestBody Tenant tenant) {
        Tenant updatedTenant = tenantService.updateTenant(tenantID, tenant);
        return new ResponseEntity<>(updatedTenant, HttpStatus.OK);
    }



    @GetMapping
    public ResponseEntity<List<Tenant>> getAllTenants() {
        List<Tenant> tenants = tenantService.getAllTenants();
        return new ResponseEntity<>(tenants, HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseTemplateVo getTenantWithRoom(@PathVariable Long id){
        return tenantService.getTenantWithRoom(id);
    }
}
