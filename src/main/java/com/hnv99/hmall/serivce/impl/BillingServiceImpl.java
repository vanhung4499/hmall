package com.hnv99.hmall.serivce.impl;

import com.hnv99.hmall.model.entity.Billing;
import com.hnv99.hmall.repository.BillingRepository;
import com.hnv99.hmall.serivce.BillingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BillingServiceImpl implements BillingService {
    private final BillingRepository billingRepository;

    @Override
    public Billing createBilling(Billing billing) {
        return billingRepository.save(billing);
    }

    @Override
    public Billing getBilling(Long id) {
        return billingRepository.findById(id).orElse(null);
    }

    @Override
    public Billing updateBilling(Billing billing) {
        return billingRepository.save(billing);
    }

    @Override
    public void deleteBilling(Long id) {
        billingRepository.deleteById(id);
    }
}
