package com.tripease.controllers;

import com.tripease.dto.request.CabRequest;
import com.tripease.dto.response.CabDetailsResponse;
import com.tripease.dto.response.CabResponse;
import com.tripease.services.CabService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cab")
public class CabController {
    CabService  cabService;
    public CabController(CabService cabService) {
        this.cabService = cabService;
    }

    @GetMapping("/getdetailsofcab")
    public CabDetailsResponse getCabDetails(@RequestParam int cabId) {
        return cabService.getCabDetails(cabId);
    }

    @PostMapping("/register/driver/{driverId}")
    public CabResponse registerCab(@RequestBody CabRequest cabRequest , @PathVariable int driverId) {
        return cabService.registerCab(cabRequest , driverId);
    }
}
