package com.devs.departamentopolicial.Relatorios;

import com.devs.departamentopolicial.Ocorrencia.OcorrenciaEntity;
import com.devs.departamentopolicial.Policial.PolicialEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_relatorio")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RelatorioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDateTime data;

    @Column(nullable = false)
    private String descricao;

    @OneToOne
    @JoinColumn(name = "policial_id")
    private PolicialEntity policial;

    @OneToOne
    @JoinColumn(name = "ocorrencia_id")
    private OcorrenciaEntity ocorrencia;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RelatorioSituacao situacao;
}
