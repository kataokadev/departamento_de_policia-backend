package com.devs.departamentopolicial.Policial;

import com.devs.departamentopolicial.Ocorrencia.OcorrenciaEntity;
import com.devs.departamentopolicial.Relatorios.RelatorioEntity;
import com.devs.departamentopolicial.Viatura.ViaturaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_policiais")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PolicialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 50, nullable = false)
    private String cargo;

    @Column(length = 50, nullable = false)
    private String unidade;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private PolicialStatus status;

    @ManyToOne
    @JoinColumn(name = "viatura_id")
    private ViaturaEntity viatura;

    @ManyToMany(mappedBy = "policiais")
    private List<OcorrenciaEntity> ocorrencias;

    @OneToOne(mappedBy = "policial")
    private RelatorioEntity relatorio;
}
