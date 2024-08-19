package br.com.fiap.semaforo_mongodb.dto;

import jakarta.validation.constraints.NotBlank;

public record SemaforoSensorDTO(

        @NotBlank(message = "O ID do semáforo é obrigatório!")
        String id
) {
}
