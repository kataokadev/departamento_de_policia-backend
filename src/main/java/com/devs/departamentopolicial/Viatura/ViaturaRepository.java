package com.devs.departamentopolicial.Viatura;

import com.devs.departamentopolicial.Veiculo.VeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ViaturaRepository extends JpaRepository<ViaturaEntity, UUID> {

    List<ViaturaEntity> findByModelo(String modelo);
    ViaturaEntity findByPlaca(String placa);
}
