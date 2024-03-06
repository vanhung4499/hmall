package com.hnv99.hmall.serivce;

import com.hnv99.hmall.model.entity.Agency;

public interface AgencyService {
    Agency createAgency(Agency agency);
    Agency getAgency(Long id);
    Agency updateAgency(Agency agency);
    void deleteAgency(Long id);
}