package br.cupom.controller;

import br.cupom.dto.ProdutoDTO;
import br.cupom.entity.ProdutoEntity;
import br.cupom.service.ProdutoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProdutoEntity> cadastrar(@RequestBody ProdutoDTO dto) {
        ProdutoEntity produtoCadastrado = service.cadastra(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoCadastrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoEntity> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
        ProdutoEntity produtoAtualizado = service.atualizar(dto, id);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoEntity> buscaProdutoPeloId(@PathVariable Long id) {
        ProdutoEntity produto = service.buscaProdutoPeloId(id);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/findByAll")
    public ResponseEntity<Page<ProdutoEntity>> buscaTodosProdutos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort) {
        if (size < 1 || size > 100) {
            throw new IllegalArgumentException("O tamanho da página deve estar entre 1 e 100.");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        Page<ProdutoEntity> produtos = service.buscaTodosProdutos(pageable);
        return ResponseEntity.ok(produtos);
    }
}
