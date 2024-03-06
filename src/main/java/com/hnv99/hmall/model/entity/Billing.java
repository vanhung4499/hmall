package com.hnv99.hmall.model.entity;


import com.hnv99.hmall.common.core.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "billings")
public class Billing extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "agency_id")
    private Long agencyId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "billing_address")
    private String billingAddress;

    @Column(name = "payment_method")
    private String paymentMethod;
}

