package com.devs.departamentopolicial.Ocorrencia;

import com.devs.departamentopolicial.PessoasEnvolvidas.PessoasEnvolvidasEntity;
import com.devs.departamentopolicial.Policial.PolicialEntity;
import com.devs.departamentopolicial.Relatorios.RelatorioEntity;
import com.devs.departamentopolicial.Veiculo.VeiculoEntity;
import com.devs.departamentopolicial.Viatura.ViaturaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_ocorrencia")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OcorrenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OcorrenciaStatus status;

    @ManyToMany
    @JoinTable(
            name = "ocorrencia_policial",
            joinColumns = @JoinColumn(name = "ocorrencia_id"),
            inverseJoinColumns = @JoinColumn(name = "policial_id")
    )
    private List<PolicialEntity> policiais;

    @ManyToMany
    @JoinTable(
            name = "ocorrencia_viatura",
            joinColumns = @JoinColumn(name = "ocorrencia_id"),
            inverseJoinColumns = @JoinColumn(name = "viatura_id")
    )
    private List<ViaturaEntity> viaturas;

    @ManyToMany
    @JoinTable(
            name = "ocorrencia_pessoa",
            joinColumns = @JoinColumn(name = "ocorrencia_id"),
            inverseJoinColumns = @JoinColumn(name = "pessoa_id")
    )
    private List<PessoasEnvolvidasEntity> pessoasEnvolvidas;

    @ManyToMany
    @JoinTable(
            name = "ocorrencia_veiculo",
            joinColumns = @JoinColumn(name = "ocorrencia_id"),
            inverseJoinColumns = @JoinColumn(name = "veiculo_id")
    )
    private List<VeiculoEntity> veiculos;

    @OneToOne(mappedBy = "ocorrencia")
    private RelatorioEntity relatorio;
}
