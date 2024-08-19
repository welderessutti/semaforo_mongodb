package br.com.fiap.semaforo_mongodb.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record LeituraSensorCadastroDTO(

        String id,

        @NotNull(message = "A data e hora da leitura é obrigatória!")
        LocalDateTime dataHoraLeitura,

        @NotNull(message = "O fluxo de veículos é obrigatório!")
        Integer fluxoVeiculos,

        @NotNull(message = "A velocidade média é obrigatória!")
        Double velocidadeMedia,

        @NotNull(message = "O tempo de fila é obrigatório!")
        Integer tempoFila,

        @NotBlank(message = "A condição climática é obrigatória!")
        String condicaoClimatica,

        @Valid()
        SensorLeituraSensorDTO sensor
) {
}
