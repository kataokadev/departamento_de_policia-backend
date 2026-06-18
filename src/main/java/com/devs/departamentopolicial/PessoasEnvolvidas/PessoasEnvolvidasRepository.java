package com.devs.departamentopolicial.PessoasEnvolvidas;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PessoasEnvolvidasRepository extends JpaRepository<PessoasEnvolvidasEntity, UUID> {


}
