package com.example.API.Repositorios;

import com.example.API.Domain.Address.Address;
import com.example.API.Domain.Coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
