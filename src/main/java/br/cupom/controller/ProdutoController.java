package br.cupom.controller;

import br.cupom.dto.ProdutoDTO;
import br.cupom.entity.ProdutoEntity;
import br.cupom.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private  ProdutoService service;

    public ProdutoController(ProdutoService produtoService) {
        this.service = produtoService;
    }

    @PostMapping("/cadastra")
    public ResponseEntity<ProdutoEntity> cadastrar(@RequestBody ProdutoDTO dto) {
        ProdutoEntity produtoCadastrado = service.cadastra(dto);
        return ResponseEntity.ok(produtoCadastrado);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoEntity atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO dto) {

        return service.atualizar(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
