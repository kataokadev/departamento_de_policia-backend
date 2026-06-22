package com.devs.departamentopolicial.Veiculo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VeiculoRepository extends JpaRepository<VeiculoEntity, UUID> {

    VeiculoEntity findByPlaca(String placa);
}
