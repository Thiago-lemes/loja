package br.cupom.controller;

import br.cupom.dto.CupomDTO;
import br.cupom.entity.CupomEntity;
import br.cupom.service.CupomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cupom")
public class CupomController {

    private final CupomService service;

    public CupomController(CupomService service) {
        this.service = service;
    }

    @PostMapping("/cadastra")
    public ResponseEntity<CupomEntity> cadastrar(@RequestBody CupomDTO dto) {
        CupomEntity cupomCadastrado = service.cadastrar(dto);
        return ResponseEntity.ok(cupomCadastrado);
    }

    @PostMapping("/{id}/resgatar")
    public ResponseEntity<CupomEntity> resgatar(@PathVariable Long id) {
        CupomEntity cupom = service.resgatarCupom(id);
        return ResponseEntity.ok(cupom);
    }
}
