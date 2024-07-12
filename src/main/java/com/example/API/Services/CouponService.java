package com.example.API.Services;

import com.example.API.Domain.Coupon.Coupon;
import com.example.API.Domain.Coupon.CouponRequestDTO;
import com.example.API.Domain.Eventos.Event;
import com.example.API.Repositorios.CouponRepository;
import com.example.API.Repositorios.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service

public class CouponService {
    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private EventRepository eventRepository;

    public Coupon addCouponToEvent(UUID EventID, CouponRequestDTO couponData) throws IllegalAccessException {
        Event event = eventRepository.findById(EventID ).orElseThrow(()-> new IllegalAccessException("Event not  found"));

        Coupon coupon = new Coupon();

        coupon.setCode(couponData.code());
        coupon.setDiscount(couponData.disccount());
        coupon.setValid(new Date(String.valueOf(couponData.valid())));
        coupon.setEvent(event);
        return couponRepository.save(coupon);

    }
    public List<Coupon> consulCoupons(UUID eventId, Date currentDate){
        return couponRepository.findByEventAndValidAfter(eventId, currentDate);
    }

}
