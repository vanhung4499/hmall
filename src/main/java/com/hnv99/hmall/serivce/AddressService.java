package com.hnv99.hmall.serivce;

import com.hnv99.hmall.model.dto.AddressDTO;
import com.hnv99.hmall.model.form.AddressForm;

import java.util.List;

public interface AddressService {
    List<AddressDTO> listCurrentUserAddresses();
    AddressDTO getAddressById(Long id);
    boolean createAddress(AddressForm formData);
    boolean updateAddress(Long id, AddressForm formData);
    void deleteAddress(Long id);
}