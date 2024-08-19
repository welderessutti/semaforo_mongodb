package br.com.fiap.semaforo_mongodb.dto;

import jakarta.validation.constraints.NotBlank;

public record SensorLeituraSensorDTO(

        @NotBlank(message = "O ID do sensor é obrigatório!")
        String id
) {
}
