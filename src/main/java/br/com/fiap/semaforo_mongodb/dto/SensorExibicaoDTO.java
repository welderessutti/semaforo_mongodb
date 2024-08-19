package br.com.fiap.semaforo_mongodb.dto;

import br.com.fiap.semaforo_mongodb.model.Sensor;

import java.time.LocalDate;

public record SensorExibicaoDTO(
        String id,
        String localInstalacao,
        String tipoSensor,
        String dadosTecnicos,
        LocalDate dataInstalacao,
        Boolean statusOperacao,
        Long version,
        SemaforoExibicaoDTO semaforo
) {
    public SensorExibicaoDTO(Sensor sensor) {
        this(
                sensor.getId(),
                sensor.getLocalInstalacao(),
                sensor.getTipoSensor(),
                sensor.getDadosTecnicos(),
                sensor.getDataInstalacao(),
                sensor.getStatusOperacao(),
                sensor.getVersion(),
                new SemaforoExibicaoDTO(sensor.getSemaforo())
        );
    }
}
