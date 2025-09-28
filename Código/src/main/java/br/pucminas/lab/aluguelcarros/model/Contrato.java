package br.pucminas.lab.aluguelcarros.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDate dataContrato;
    private String termos;

    @OneToOne
    @JoinColumn(name = "pedido_aluguel_id", nullable = false)
    private PedidoAluguel pedidoAluguel;

    @ManyToOne
    @JoinColumn(name = "agente_id", nullable = false)
    private Agente agente;
    
    @ManyToOne
    @JoinColumn(name = "banco_credor_id")
    private Agente bancoCredor; 
    
    private String propriedadeAutomovel; 
}