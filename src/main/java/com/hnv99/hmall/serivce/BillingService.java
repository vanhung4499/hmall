package com.hnv99.hmall.serivce;

import com.hnv99.hmall.model.entity.Billing;

public interface BillingService {
    Billing createBilling(Billing billing);
    Billing getBilling(Long id);
    Billing updateBilling(Billing billing);
    void deleteBilling(Long id);
}
