package com.mbec.mau_inv.repos;

import com.mbec.mau_inv.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
