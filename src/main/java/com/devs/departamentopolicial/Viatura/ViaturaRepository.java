package com.devs.departamentopolicial.Viatura;

import com.devs.departamentopolicial.Veiculo.VeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ViaturaRepository extends JpaRepository<VeiculoEntity, UUID> {
}
