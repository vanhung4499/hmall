package com.hnv99.hmall.controller;

import com.hnv99.hmall.common.core.result.Result;
import com.hnv99.hmall.model.entity.Agency;
import com.hnv99.hmall.repository.AgencyRepository;
import com.hnv99.hmall.serivce.AgencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Agency", description = "Agency Management")
@AllArgsConstructor
@RequestMapping("/agencies")
@RestController
public class AgencyController {
    private final AgencyService agencyService;

    @Operation(summary = "Create an agency")
    @PostMapping("/")
    public Result createAgency(@RequestBody Agency agency) {
        Agency result = agencyService.createAgency(agency);
        return Result.success(result);
    }

    @Operation(summary = "Get an agency")
    @GetMapping("/{id}")
    public Result getAgency(Long id) {
        Agency result = agencyService.getAgency(id);
        return Result.success(result);
    }

    @Operation(summary = "Update an agency")
    @PatchMapping("/")
    public Result updateAgency(@RequestBody Agency agency) {
        Agency result = agencyService.updateAgency(agency);
        return Result.success(result);
    }

    @Operation(summary = "Delete an agency")
    @DeleteMapping("/{id}")
    public Result deleteAgency(@PathVariable Long id) {
        agencyService.deleteAgency(id);
        return Result.success(null);
    }
}
