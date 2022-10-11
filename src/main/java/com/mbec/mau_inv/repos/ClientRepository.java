package com.mbec.mau_inv.repos;

import com.mbec.mau_inv.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client, Long> {
}
