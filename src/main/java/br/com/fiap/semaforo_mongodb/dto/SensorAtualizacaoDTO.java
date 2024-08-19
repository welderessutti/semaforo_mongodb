package br.com.fiap.semaforo_mongodb.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SensorAtualizacaoDTO(

        @NotBlank(message = "O ID é obrigatório!")
        String id,

        @NotBlank(message = "O endereço de localização é obrigatório!")
        String localInstalacao,

        @NotBlank(message = "O tipo de sensor é obrigatório!")
        String tipoSensor,

        @NotBlank(message = "Os dados técnicos são obrigatórios!")
        String dadosTecnicos,

        @NotNull(message = "A data de instalação é obrigatória!")
        LocalDate dataInstalacao,

        @NotNull(message = "O status da operação é obrigatória!")
        Boolean statusOperacao,

        @Valid()
        SemaforoSensorDTO semaforo,

        @NotNull(message = "A versão do documento é obrigatória!")
        Long version
) {
}
