package com.example.API.Repositorios;

import com.example.API.Domain.Coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface CouponRepository extends JpaRepository <Coupon, UUID> {

    List<Coupon> findByEventAndValidAfter(UUID eventId, Date currentDate);
}
