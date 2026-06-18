package com.devs.departamentopolicial.Policial;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_policiais")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PolicialEntity {

    @Id
    private UUID id;

    private String nome;

    private String cargo;

    private String unidade;

    private PolicialStatus status;
}
