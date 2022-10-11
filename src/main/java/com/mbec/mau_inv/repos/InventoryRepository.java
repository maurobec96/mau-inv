package com.mbec.mau_inv.repos;

import com.mbec.mau_inv.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
