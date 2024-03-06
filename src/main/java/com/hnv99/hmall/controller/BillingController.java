package com.hnv99.hmall.controller;

import com.hnv99.hmall.model.entity.Billing;
import com.hnv99.hmall.serivce.BillingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Billing", description = "Billing Management")
@AllArgsConstructor
@RestController
@RequestMapping("/billings")
public class BillingController {
    private final BillingService billingService;

    @Operation(summary = "Create a billing")
    @PostMapping("/")
    public void createBilling(@RequestBody Billing billing) {
        billingService.createBilling(billing);
    }

    @Operation(summary = "Get a billing")
    @GetMapping("/{id}")
    public Billing getBilling(@PathVariable Long id) {
        return billingService.getBilling(id);
    }

    @Operation(summary = "Delete a billing")
    @DeleteMapping("/{id}")
    public void deleteBilling(@PathVariable Long id) {
        billingService.deleteBilling(id);
    }
}
