package com.devs.departamentopolicial.Viatura;

import com.devs.departamentopolicial.Ocorrencia.OcorrenciaEntity;
import com.devs.departamentopolicial.Policial.PolicialEntity;
import com.devs.departamentopolicial.Veiculo.VeiculoSituacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_viatura")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ViaturaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String placa;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ViaturaStatus situacao;

    @OneToMany(mappedBy = "viatura")
    private List<PolicialEntity> policiais;

    @ManyToMany(mappedBy = "viaturas")
    private List<OcorrenciaEntity> ocorrencias;

}
