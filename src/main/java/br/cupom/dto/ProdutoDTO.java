package br.cupom.dto;

import br.cupom.entity.ProdutoEntity;

public record ProdutoDTO(String nome, double preco, String descricao, int quantidade) {

    public ProdutoEntity toEntity() {
        return new ProdutoEntity(nome, preco, descricao, quantidade);
    }
}
