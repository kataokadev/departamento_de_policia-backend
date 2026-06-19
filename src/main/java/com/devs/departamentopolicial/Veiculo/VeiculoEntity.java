package com.devs.departamentopolicial.Veiculo;

import com.devs.departamentopolicial.Ocorrencia.OcorrenciaEntity;
import com.devs.departamentopolicial.PessoasEnvolvidas.PessoasEnvolvidasEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_veiculo")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VeiculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false,  unique = true)
    private String placa;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String cor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VeiculoSituacao situacao;

    @OneToMany(mappedBy = "veiculo")
    private List<PessoasEnvolvidasEntity> pessoasEnvolvidas;

    @ManyToMany(mappedBy = "veiculos")
    private List<OcorrenciaEntity> ocorrencias;
}
