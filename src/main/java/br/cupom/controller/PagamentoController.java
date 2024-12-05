package br.cupom.controller;

import br.cupom.dto.PagamentoDTO;
import br.cupom.entity.PagamentoEntity;
import br.cupom.service.PagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    private final PagamentoService service;

    public PagamentoController(PagamentoService service) {
        this.service = service;
    }

    @PostMapping("/cria")
    public ResponseEntity<PagamentoEntity> cadastrar(@RequestBody PagamentoDTO dto) {
        PagamentoEntity pagamentos = service.criaPagamento(dto);
        return ResponseEntity.ok(pagamentos);
    }

}
