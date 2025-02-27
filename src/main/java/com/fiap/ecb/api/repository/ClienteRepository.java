package com.fiap.ecb.api.repository;

import com.fiap.ecb.api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClienteRepository extends JpaRepository<Cliente, Long> {}