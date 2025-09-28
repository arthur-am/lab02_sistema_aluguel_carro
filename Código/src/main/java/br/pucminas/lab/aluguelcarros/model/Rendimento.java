package br.pucminas.lab.aluguelcarros.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Rendimento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String profissao;
    private String entidadeEmpregadora;
    private BigDecimal rendimentoMensal;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}