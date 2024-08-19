package br.com.fiap.semaforo_mongodb.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EventoCadastroDTO(

        String id,

        @NotNull(message = "A data e hora do evento é obrigatória!")
        LocalDateTime dataHoraEvento,

        @NotBlank(message = "O tipo de evento é obrigatório!")
        String tipoEvento,

        @NotBlank(message = "O local do evento é obrigatório!")
        String localEvento,

        @NotBlank(message = "O impacto no trânsito é obrigatório!")
        String impactoTransito,

        @NotBlank(message = "A ação tomada é obrigatória!")
        String acaoTomada,

        @Valid()
        LeituraSensorEventoDTO leituraSensor
) {
}
