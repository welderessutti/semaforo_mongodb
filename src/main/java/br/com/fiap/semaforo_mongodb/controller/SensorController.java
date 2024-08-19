package br.com.fiap.semaforo_mongodb.controller;

import br.com.fiap.semaforo_mongodb.dto.SensorAtualizacaoDTO;
import br.com.fiap.semaforo_mongodb.dto.SensorCadastroDTO;
import br.com.fiap.semaforo_mongodb.dto.SensorExibicaoDTO;
import br.com.fiap.semaforo_mongodb.service.SensorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensor")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SensorExibicaoDTO salvar(@RequestBody @Valid SensorCadastroDTO sensorCadastroDTO) {
        return sensorService.salvar(sensorCadastroDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<SensorExibicaoDTO> listarTodos(@PageableDefault(size = 2) Pageable paginacao) {
        return sensorService.listarTodos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorExibicaoDTO> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(sensorService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable String id) {
        sensorService.excluir(id);
    }

    @PutMapping
    public ResponseEntity<SensorExibicaoDTO> atualizar(@RequestBody @Valid SensorAtualizacaoDTO sensorAtualizacaoDTO) {
        return ResponseEntity.ok(sensorService.atualizar(sensorAtualizacaoDTO));
    }
}
