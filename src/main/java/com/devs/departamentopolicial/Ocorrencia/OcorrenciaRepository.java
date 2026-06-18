package com.devs.departamentopolicial.Ocorrencia;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OcorrenciaRepository extends JpaRepository<OcorrenciaEntity, UUID> {
}
