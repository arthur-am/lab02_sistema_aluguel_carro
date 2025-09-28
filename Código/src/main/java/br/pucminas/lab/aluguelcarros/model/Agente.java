package br.pucminas.lab.aluguelcarros.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Agente extends Usuario {

    public enum TipoAgente {
        BANCO, EMPRESA
    }

    @Column(nullable = false)
    private String nomeEmpresa;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAgente tipo;
}