package br.com.fiap.semaforo_mongodb.service;

import br.com.fiap.semaforo_mongodb.dto.SensorAtualizacaoDTO;
import br.com.fiap.semaforo_mongodb.dto.SensorCadastroDTO;
import br.com.fiap.semaforo_mongodb.dto.SensorExibicaoDTO;
import br.com.fiap.semaforo_mongodb.exception.SemaforoNaoEncontradoException;
import br.com.fiap.semaforo_mongodb.exception.SensorNaoEncontradoException;
import br.com.fiap.semaforo_mongodb.model.Semaforo;
import br.com.fiap.semaforo_mongodb.model.Sensor;
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
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private SemaforoRepository semaforoRepository;

    @Transactional
    public SensorExibicaoDTO salvar(SensorCadastroDTO sensorCadastroDTO) {
        Optional<Semaforo> semaforoOptinal = semaforoRepository.findById(sensorCadastroDTO.semaforo().id());

        if (semaforoOptinal.isPresent()) {
            Sensor sensor = new Sensor();
            BeanUtils.copyProperties(sensorCadastroDTO, sensor);
            sensor.setSemaforo(semaforoOptinal.get());
            return new SensorExibicaoDTO(sensorRepository.save(sensor));
        } else {
            throw new SemaforoNaoEncontradoException("Semáforo não encontrado para cadastrar o sensor!");
        }
    }

    public Page<SensorExibicaoDTO> listarTodos(Pageable paginacao) {
        return sensorRepository
                .findAll(paginacao)
                .map(SensorExibicaoDTO::new);
    }

    public SensorExibicaoDTO buscarPorId(String id) {
        Optional<Sensor> sensorOptional = sensorRepository.findById(id);

        if (sensorOptional.isPresent()) {
            return new SensorExibicaoDTO(sensorOptional.get());
        } else {
            throw new SensorNaoEncontradoException("Sensor não encontrado!");
        }
    }

    @Transactional
    public void excluir(String id) {
        Optional<Sensor> sensorOptional = sensorRepository.findById(id);

        if (sensorOptional.isPresent()) {
            sensorRepository.delete(sensorOptional.get());
        } else {
            throw new SensorNaoEncontradoException("Sensor não encontrado!");
        }
    }

    @Transactional
    public SensorExibicaoDTO atualizar(SensorAtualizacaoDTO sensorAtualizacaoDTO) {
        Optional<Sensor> sensorOptional = sensorRepository.findById(sensorAtualizacaoDTO.id());

        if (sensorOptional.isPresent()) {
            Optional<Semaforo> semaforoOptional = semaforoRepository.findById(sensorAtualizacaoDTO.semaforo().id());

            if (semaforoOptional.isPresent()) {
                BeanUtils.copyProperties(sensorAtualizacaoDTO, sensorOptional.get());
                sensorOptional.get().setSemaforo(semaforoOptional.get());

                return new SensorExibicaoDTO(sensorRepository.save(sensorOptional.get()));
            } else {
                throw new SemaforoNaoEncontradoException("Semáforo não encontrado!");
            }
        } else {
            throw new SensorNaoEncontradoException("Sensor não encontrado!");
        }
    }
}
