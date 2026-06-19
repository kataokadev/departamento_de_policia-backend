package com.devs.departamentopolicial.PessoasEnvolvidas;

import com.devs.departamentopolicial.Ocorrencia.OcorrenciaEntity;
import com.devs.departamentopolicial.Veiculo.VeiculoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_pessoas_envolvidas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PessoasEnvolvidasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 14, nullable = false,  unique = true)
    private String cpf;

    @Column(length = 14, nullable = false, unique = true)
    private String rg;

    @Column(length = 100, nullable = false)
    private String endereco;

    @Enumerated(EnumType.STRING)
    @Column(length = 100, nullable = false)
    private PessoasEnvolvidasTipo tipo;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private VeiculoEntity veiculo;

    @ManyToMany(mappedBy = "pessoasEnvolvidas")
    private List<OcorrenciaEntity> ocorrencias;
}
