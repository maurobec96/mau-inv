package com.mbec.mau_inv.repos;

import com.mbec.mau_inv.domain.InvoiceProduct;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, Long> {
}
