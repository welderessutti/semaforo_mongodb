package br.com.fiap.semaforo_mongodb.service;

import br.com.fiap.semaforo_mongodb.dto.SemaforoAtualizacaoDTO;
import br.com.fiap.semaforo_mongodb.dto.SemaforoCadastroDTO;
import br.com.fiap.semaforo_mongodb.dto.SemaforoExibicaoDTO;
import br.com.fiap.semaforo_mongodb.exception.SemaforoNaoEncontradoException;
import br.com.fiap.semaforo_mongodb.exception.SemaforoPossuiReferenciaException;
import br.com.fiap.semaforo_mongodb.model.Semaforo;
import br.com.fiap.semaforo_mongodb.repository.SemaforoRepository;
import br.com.fiap.semaforo_mongodb.repository.SensorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SemaforoService {

    @Autowired
    private SemaforoRepository semaforoRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Transactional
    public SemaforoExibicaoDTO salvar(SemaforoCadastroDTO semaforoCadastroDTO) {
        Semaforo semaforo = new Semaforo();
        BeanUtils.copyProperties(semaforoCadastroDTO, semaforo);
        return new SemaforoExibicaoDTO(semaforoRepository.save(semaforo));
    }

    public Page<SemaforoExibicaoDTO> listarTodos(Pageable paginacao) {
        return semaforoRepository
                .findAll(paginacao)
                .map(SemaforoExibicaoDTO::new);
    }

    public SemaforoExibicaoDTO buscarPorId(String id) {
        Optional<Semaforo> semaforoOptional = semaforoRepository.findById(id);

        if (semaforoOptional.isPresent()) {
            return new SemaforoExibicaoDTO(semaforoOptional.get());
        } else {
            throw new SemaforoNaoEncontradoException("Semáforo não encontrado!");
        }
    }

    @Transactional
    public void excluir(String id) {
        Boolean hasReferences = sensorRepository.existsBySemaforo_Id(id);

        if (!hasReferences) {
            Optional<Semaforo> semaforoOptional = semaforoRepository.findById(id);

            if (semaforoOptional.isPresent()) {
                semaforoRepository.delete(semaforoOptional.get());
            } else {
                throw new SemaforoNaoEncontradoException("Semáforo não encontrado!");
            }
        } else {
            throw new SemaforoPossuiReferenciaException(
                    "Não é possível excluir o semáforo pois ele está sendo referenciado na coleção sensor!"
            );
        }
    }

    @Transactional
    public SemaforoExibicaoDTO atualizar(SemaforoAtualizacaoDTO semaforoAtualizacaoDTO) {
        Optional<Semaforo> semaforoOptional = semaforoRepository.findById(semaforoAtualizacaoDTO.id());

        if (semaforoOptional.isPresent()) {
            Semaforo semaforoAtualizar = new Semaforo();
            BeanUtils.copyProperties(semaforoAtualizacaoDTO, semaforoAtualizar);
            return new SemaforoExibicaoDTO(semaforoRepository.save(semaforoAtualizar));
        } else {
            throw new SemaforoNaoEncontradoException("Semáforo não encontrado!");
        }
    }
}
