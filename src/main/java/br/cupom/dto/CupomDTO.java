package br.cupom.dto;

import br.cupom.entity.CupomEntity;
import br.cupom.utils.DateUtils;

public record CupomDTO(String nome, String prazoValidade, int quantidade, Boolean ativo, double desconto) {

    public CupomEntity toEntity() {
        return new CupomEntity(nome, DateUtils.stringToLocalDate(prazoValidade), quantidade, ativo, desconto);
    }

    public String getPrazoValidadeFormatted() {
        return prazoValidade;
    }
}
