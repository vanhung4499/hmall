package com.hnv99.hmall.controller;

import com.hnv99.hmall.common.core.result.Result;
import com.hnv99.hmall.converter.AddressConverter;
import com.hnv99.hmall.model.dto.AddressDTO;
import com.hnv99.hmall.model.form.AddressForm;
import com.hnv99.hmall.serivce.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Address", description = "Address Management")
@RestController
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final AddressConverter addressConverter;

    @Operation(summary = "Get current user addresses")
    @GetMapping("/addresses")
    public Result listCurrentUserAddresses() {
        List<AddressDTO> addressList = addressService.listCurrentUserAddresses();
        return Result.success(addressList);
    }

    @Operation(summary = "Get address detail")
    @GetMapping("/addresses/{addressId}")
    public Result getAddressDetail(Long addressId) {
        AddressDTO address = addressService.getAddressById(addressId);
        return Result.success(address);
    }

    @Operation(summary = "Create address")
    @PostMapping("/addresses")
    public Result createAddress(@RequestBody @Validated AddressForm formData) {
        boolean result = addressService.createAddress(formData);
        return Result.judge(result);
    }

    @Operation(summary = "Update address")
    @PatchMapping("/addresses/{addressId}")
    public Result updateAddress(Long addressId, @RequestBody @Validated AddressForm formData) {
        boolean result = addressService.updateAddress(addressId, formData);
        return Result.judge(result);
    }

    @Operation(summary = "Delete address")
    @DeleteMapping("/addresses/{addressId}")
    public Result deleteAddress(Long addressId) {
        addressService.deleteAddress(addressId);
        return Result.success();
    }
}
