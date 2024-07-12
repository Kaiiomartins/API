package com.example.API.Controller;

import com.example.API.Domain.Coupon.Coupon;
import com.example.API.Domain.Coupon.CouponRequestDTO;
import com.example.API.Services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;
    @PostMapping("/event/{eventId}")
    public ResponseEntity<Coupon>addCouponToEvent(@PathVariable UUID eventId, @RequestBody  CouponRequestDTO data) throws IllegalAccessException {

        Coupon coupons = couponService.addCouponToEvent(eventId, data);
        return ResponseEntity.ok(coupons);
    }
}
