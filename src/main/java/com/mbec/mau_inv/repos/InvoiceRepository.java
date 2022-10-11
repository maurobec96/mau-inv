package com.mbec.mau_inv.repos;

import com.mbec.mau_inv.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
