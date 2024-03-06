package com.hnv99.hmall.serivce.impl;

import com.hnv99.hmall.model.entity.Agency;
import com.hnv99.hmall.repository.AgencyRepository;
import com.hnv99.hmall.serivce.AgencyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AgencyServiceImpl implements AgencyService {
    private final AgencyRepository agencyRepository;

    @Override
    public Agency createAgency(Agency agency) {
        return agencyRepository.save(agency);
    }

    @Override
    public Agency getAgency(Long id) {
        return agencyRepository.findById(id).orElse(null);
    }

    @Override
    public Agency updateAgency(Agency agency) {
        return agencyRepository.save(agency);
    }

    @Override
    public void deleteAgency(Long id) {
        agencyRepository.deleteById(id);
    }
}
