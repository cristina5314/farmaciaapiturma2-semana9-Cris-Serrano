package br.com.alura.farmaciaapiturma2.controller;

import br.com.alura.farmaciaapiturma2.dto.DadosAtualizaProdutoDTO;
import br.com.alura.farmaciaapiturma2.dto.DadosCadastroDTO;
import br.com.alura.farmaciaapiturma2.dto.DadosRetornoDTO;
import br.com.alura.farmaciaapiturma2.model.Fabricante;
import br.com.alura.farmaciaapiturma2.model.Produto;
import br.com.alura.farmaciaapiturma2.repository.FabricanteRepository;
import br.com.alura.farmaciaapiturma2.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Pageable;

@RestController
public class ProdutoController {
    @Autowired
    private FabricanteRepository fabricanteRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(UriComponentsBuilder UriBuilder) {
        return cadastrar(null);
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroDTO dadosCadastro, UriComponentsBuilder uriBuilder){
        Fabricante fabricante = fabricanteRepository.findByNome(dadosCadastro.nomeFabricante());
        if (fabricante == null){
            fabricante = new Fabricante(dadosCadastro);
            fabricanteRepository.save(fabricante);
        }
        var produto = new Produto(dadosCadastro, fabricante);
        produtoRepository.save(produto);

       var uri = uriBuilder.path("{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosRetornoDTO(produto));
    }


    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable int id){
        var produto = produtoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosRetornoDTO(produto));
    }


    @GetMapping
    public ResponseEntity<Page<DadosRetornoDTO>> listar(@PageableDefault(size = 2, sort = {"nome"}) Pageable paginacao) {
        Page<Produto> produtos = produtoRepository.findAll(paginacao);
        Page<DadosRetornoDTO> page = produtos.map(DadosRetornoDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizaProdutoDTO produtoDTO) {
        var produto = produtoRepository.getReferenceById(produtoDTO.id());
        produto.atulizar(produtoDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable int id){
        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}






