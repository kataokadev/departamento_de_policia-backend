package com.devs.departamentopolicial.Relatorios;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RelatorioRepository extends JpaRepository<RelatorioEntity, UUID> {
}
