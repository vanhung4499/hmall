package com.hnv99.hmall.converter;

import com.hnv99.hmall.model.dto.AddressDTO;
import com.hnv99.hmall.model.entity.Address;
import com.hnv99.hmall.model.form.AddressForm;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressConverter {
    AddressDTO entity2Dto(Address entity);

    List<AddressDTO> entity2Dto(List<Address> entities);

    Address form2Entity(AddressForm form);
}
