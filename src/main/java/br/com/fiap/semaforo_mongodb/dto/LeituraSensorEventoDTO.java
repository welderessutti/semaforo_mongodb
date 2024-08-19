package br.com.fiap.semaforo_mongodb.dto;

import jakarta.validation.constraints.NotBlank;

public record LeituraSensorEventoDTO(

        @NotBlank(message = "O ID da leitura do sensor é obrigatória!")
        String id
) {
}
