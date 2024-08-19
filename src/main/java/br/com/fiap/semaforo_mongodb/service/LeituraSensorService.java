package br.com.fiap.semaforo_mongodb.service;

import br.com.fiap.semaforo_mongodb.dto.LeituraSensorAtualizacaoDTO;
import br.com.fiap.semaforo_mongodb.dto.LeituraSensorCadastroDTO;
import br.com.fiap.semaforo_mongodb.dto.LeituraSensorExibicaoDTO;
import br.com.fiap.semaforo_mongodb.exception.LeituraSensorNaoEncontradaException;
import br.com.fiap.semaforo_mongodb.exception.SensorNaoEncontradoException;
import br.com.fiap.semaforo_mongodb.model.LeituraSensor;
import br.com.fiap.semaforo_mongodb.model.Sensor;
import br.com.fiap.semaforo_mongodb.repository.LeituraSensorRepository;
import br.com.fiap.semaforo_mongodb.repository.SensorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LeituraSensorService {

    @Autowired
    private LeituraSensorRepository leituraSensorRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Transactional
    public LeituraSensorExibicaoDTO salvar(LeituraSensorCadastroDTO leituraSensorCadastroDTO) {
        Optional<Sensor> sensorOptinal = sensorRepository.findById(leituraSensorCadastroDTO.sensor().id());

        if (sensorOptinal.isPresent()) {
            LeituraSensor leituraSensor = new LeituraSensor();
            BeanUtils.copyProperties(leituraSensorCadastroDTO, leituraSensor);
            leituraSensor.setSensor(sensorOptinal.get());
            return new LeituraSensorExibicaoDTO(leituraSensorRepository.save(leituraSensor));
        } else {
            throw new SensorNaoEncontradoException("Sensor não encontrado para cadastrar a leitura do sensor!");
        }
    }

    public Page<LeituraSensorExibicaoDTO> listarTodos(Pageable paginacao) {
        return leituraSensorRepository
                .findAll(paginacao)
                .map(LeituraSensorExibicaoDTO::new);
    }

    public LeituraSensorExibicaoDTO buscarPorId(String id) {
        Optional<LeituraSensor> leituraSensorOptional = leituraSensorRepository.findById(id);

        if (leituraSensorOptional.isPresent()) {
            return new LeituraSensorExibicaoDTO(leituraSensorOptional.get());
        } else {
            throw new LeituraSensorNaoEncontradaException("Leitura do sensor não encontrada!");
        }
    }

    @Transactional
    public void excluir(String id) {
        Optional<LeituraSensor> leituraSensorOptional = leituraSensorRepository.findById(id);

        if (leituraSensorOptional.isPresent()) {
            leituraSensorRepository.delete(leituraSensorOptional.get());
        } else {
            throw new LeituraSensorNaoEncontradaException("Leitura do sensor não encontrada!");
        }
    }

    @Transactional
    public LeituraSensorExibicaoDTO atualizar(LeituraSensorAtualizacaoDTO leituraSensorAtualizacaoDTO) {
        Optional<LeituraSensor> leituraSensorOptional = leituraSensorRepository.findById(leituraSensorAtualizacaoDTO.id());

        if (leituraSensorOptional.isPresent()) {
            Optional<Sensor> sensorOptional = sensorRepository.findById(leituraSensorAtualizacaoDTO.sensor().id());

            if (sensorOptional.isPresent()) {
                BeanUtils.copyProperties(leituraSensorAtualizacaoDTO, leituraSensorOptional.get());
                leituraSensorOptional.get().setSensor(sensorOptional.get());

                return new LeituraSensorExibicaoDTO(leituraSensorRepository.save(leituraSensorOptional.get()));
            } else {
                throw new SensorNaoEncontradoException("Sensor não encontrado!");
            }
        } else {
            throw new LeituraSensorNaoEncontradaException("Leitura do sensor não encontrada!");
        }
    }
}
