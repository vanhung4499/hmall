package com.hnv99.hmall.serivce.impl;

import com.hnv99.hmall.common.security.util.SecurityUtils;
import com.hnv99.hmall.converter.AddressConverter;
import com.hnv99.hmall.repository.AddressRepository;
import com.hnv99.hmall.model.dto.AddressDTO;
import com.hnv99.hmall.model.entity.Address;
import com.hnv99.hmall.model.form.AddressForm;
import com.hnv99.hmall.serivce.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;
    private AddressConverter addressConverter;

    @Override
    public List<AddressDTO> listCurrentUserAddresses() {
        Long userId = SecurityUtils.getUserId();
        List<Address> addresses = addressRepository.findAllByUserId(userId);
        return addressConverter.entity2Dto(addresses);
    }

    @Override
    public AddressDTO getAddressById(Long id) {
        Address address = addressRepository.findById(id).orElse(null);
        return addressConverter.entity2Dto(address);
    }

    @Override
    public boolean createAddress(AddressForm formData) {
        Long userId = SecurityUtils.getUserId();
        Address address = addressConverter.form2Entity(formData);
        address.setUserId(userId);
        addressRepository.save(address);
        return true;
    }

    @Override
    public boolean updateAddress(Long id, AddressForm formData) {
        Address address = addressConverter.form2Entity(formData);
        address.setId(id);
        addressRepository.save(address);
        return true;
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}