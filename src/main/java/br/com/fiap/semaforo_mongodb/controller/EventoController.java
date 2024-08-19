package br.com.fiap.semaforo_mongodb.controller;

import br.com.fiap.semaforo_mongodb.dto.EventoAtualizacaoDTO;
import br.com.fiap.semaforo_mongodb.dto.EventoCadastroDTO;
import br.com.fiap.semaforo_mongodb.dto.EventoExibicaoDTO;
import br.com.fiap.semaforo_mongodb.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventoExibicaoDTO salvar(@RequestBody @Valid EventoCadastroDTO eventoCadastroDTO) {
        return eventoService.salvar(eventoCadastroDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<EventoExibicaoDTO> listarTodos(@PageableDefault(size = 2) Pageable paginacao) {
        return eventoService.listarTodos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoExibicaoDTO> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(eventoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable String id) {
        eventoService.excluir(id);
    }

    @PutMapping
    public ResponseEntity<EventoExibicaoDTO> atualizar(@RequestBody @Valid EventoAtualizacaoDTO eventoAtualizacaoDTO) {
        return ResponseEntity.ok(eventoService.atualizar(eventoAtualizacaoDTO));
    }
}
