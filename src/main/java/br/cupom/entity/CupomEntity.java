package br.cupom.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "cupons")

public class CupomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private LocalDate prazoValidade;
    private int quantidade;
    private Boolean ativo;
    private double desconto;

    public CupomEntity() {}

    public CupomEntity(String nome, LocalDate prazoValidade, int quantidade, Boolean ativo, double desconto) {
        this.nome = nome;
        this.prazoValidade = prazoValidade;
        this.quantidade = quantidade;
        this.ativo = ativo;
        this.desconto = desconto;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getPrazoValidade() { return prazoValidade; }
    public void setPrazoValidade(LocalDate prazoValidade) { this.prazoValidade = prazoValidade; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public double getDesconto() { return desconto; }
    public void setDesconto(double desconto) { this.desconto = desconto; }
}
