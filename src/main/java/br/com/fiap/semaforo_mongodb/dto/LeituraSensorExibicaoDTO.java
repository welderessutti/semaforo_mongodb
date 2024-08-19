package br.com.fiap.semaforo_mongodb.dto;

import br.com.fiap.semaforo_mongodb.model.LeituraSensor;

import java.time.LocalDateTime;

public record LeituraSensorExibicaoDTO(
        String id,
        LocalDateTime dataHoraLeitura,
        Integer fluxoVeiculos,
        Double velocidadeMedia,
        Integer tempoFila,
        String condicaoClimatica,
        Long version,
        SensorExibicaoDTO sensor
) {
    public LeituraSensorExibicaoDTO(LeituraSensor leituraSensor) {
        this(
                leituraSensor.getId(),
                leituraSensor.getDataHoraLeitura(),
                leituraSensor.getFluxoVeiculos(),
                leituraSensor.getVelocidadeMedia(),
                leituraSensor.getTempoFila(),
                leituraSensor.getCondicaoClimatica(),
                leituraSensor.getVersion(),
                new SensorExibicaoDTO(leituraSensor.getSensor())
        );
    }
}
