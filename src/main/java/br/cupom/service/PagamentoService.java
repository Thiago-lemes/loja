package br.cupom.service;

import br.cupom.dto.PagamentoDTO;
import br.cupom.entity.CupomEntity;
import br.cupom.entity.PagamentoEntity;
import br.cupom.entity.ProdutoEntity;
import br.cupom.repository.CupomRepository;
import br.cupom.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {
    @Autowired
    private CupomRepository cupomRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MercadoPagoService service;

    public PagamentoEntity criaPagamento(PagamentoDTO dto) {
        ProdutoEntity produto = produtoRepository.findById(dto.produto())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com ID: " + dto.produto()));
        
        var valorFinal = valorDoPagamento(dto, produto);
        service.criarPreferencia(produto.getNome(), dto.quantidade(), valorFinal );
        return null;
    }

    public double valorDoPagamento(PagamentoDTO dto, ProdutoEntity produto) {
        double precoProduto = produto.getPreco();
        double valorFinal = precoProduto * dto.quantidade();

        if (dto.cupom() != null) {
            CupomEntity cupom = cupomRepository.findById(dto.cupom())
                    .orElseThrow(() -> new IllegalArgumentException("Cupom não encontrado com ID: " + dto.cupom()));

            if (cupom.getAtivo() && valorFinal >= cupom.getDesconto()) {

                valorFinal -= valorFinal * (cupom.getDesconto() / 100.0);
            } else {
                System.out.println("Cupom inválido ou não aplicável.");
            }
        }

        return Math.max(valorFinal, 0.0);
    }

}
