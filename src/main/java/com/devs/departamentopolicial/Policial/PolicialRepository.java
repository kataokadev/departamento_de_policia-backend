package com.devs.departamentopolicial.Policial;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PolicialRepository extends JpaRepository<PolicialEntity, UUID> {
}
