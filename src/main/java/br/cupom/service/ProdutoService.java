package br.cupom.service;

import br.cupom.dto.ProdutoDTO;
import br.cupom.entity.ProdutoEntity;
import br.cupom.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public ProdutoEntity cadastra(ProdutoDTO dto) {
        validaProduto(dto);
        ProdutoEntity produto = dto.toEntity();
        return repository.save(produto);
    }

    public ProdutoEntity atualizar(ProdutoDTO dto, Long id) {
        validaProduto(dto);
        ProdutoEntity produto = dto.toEntity();
        produto.setId(id);
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Produto não encontrado para atualização.");
        }
        return repository.save(produto);
    }

    public void delete(Long id) {
        ProdutoEntity produto = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        repository.delete(produto);
    }

    private void validaProduto(ProdutoDTO dto) {
        if (dto.nome() == null || dto.nome().isEmpty()) {
            throw new IllegalArgumentException("Nome do Produto não pode ser vazio");
        }
        if (dto.descricao() == null || dto.descricao().isEmpty()) {
            throw new IllegalArgumentException("Descrição do Produto não pode ser vazia");
        }
        if (dto.quantidade() < 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior ou igual a zero.");
        }
        if (dto.preco() < 0) {
            throw new IllegalArgumentException("O preço deve ser maior ou igual a zero.");
        }

        repository.findByNome(dto.nome())
                .ifPresent(produto -> {
                    throw new IllegalArgumentException("Já existe um produto cadastrado com este nome.");
                });
    }
}
