package br.cupom.service;

import br.cupom.dto.CupomDTO;
import br.cupom.entity.CupomEntity;
import br.cupom.repository.CupomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CupomService {

    private final CupomRepository repository;

    public CupomService(CupomRepository repository) {
        this.repository = repository;
    }

    public CupomEntity cadastrar(CupomDTO dto) {
        validarDadosDoCupom(dto);
        CupomEntity cupomEntity = dto.toEntity();
        cupomEntity.setAtivo(true);
        return repository.save(cupomEntity);
    }

    private void validarDadosDoCupom(CupomDTO dto) {
        if (dto.quantidade() <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        if (dto.desconto() < 0) {
            throw new IllegalArgumentException("O desconto não pode ser negativo.");
        }
        if (dto.desconto() > 100) {
            throw new IllegalArgumentException("O desconto não pode exceder 100%.");
        }
    }

    public CupomEntity resgatarCupom(Long id) {
        CupomEntity cupom = buscarCupomAtivo(id);
        validarEstadoDoCupom(cupom);

        cupom.setQuantidade(cupom.getQuantidade() - 1);
        return repository.save(cupom);
    }

    private CupomEntity buscarCupomAtivo(Long id) {
        CupomEntity cupom = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cupom não encontrado"));

        if (!cupom.getAtivo()) {
            throw new IllegalStateException("Este cupom não está mais ativo.");
        }
        return cupom;
    }

    private void validarEstadoDoCupom(CupomEntity cupom) {
        if (cupom.getPrazoValidade().isBefore(LocalDate.now())) {
            throw new IllegalStateException("Este cupom está expirado.");
        }
        if (cupom.getQuantidade() <= 0) {
            throw new IllegalStateException("Este cupom não possui mais unidades disponíveis.");
        }
    }

}
