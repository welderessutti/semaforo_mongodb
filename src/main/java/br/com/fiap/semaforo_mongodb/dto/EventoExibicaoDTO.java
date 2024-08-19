package br.com.fiap.semaforo_mongodb.dto;

import br.com.fiap.semaforo_mongodb.model.Evento;

import java.time.LocalDateTime;

public record EventoExibicaoDTO(
        String id,
        LocalDateTime dataHoraEvento,
        String tipoEvento,
        String localEvento,
        String impactoTransito,
        String acaoTomada,
        Long version,
        LeituraSensorExibicaoDTO leituraSensor
) {
    public EventoExibicaoDTO(Evento evento) {
        this(
                evento.getId(),
                evento.getDataHoraEvento(),
                evento.getTipoEvento(),
                evento.getLocalEvento(),
                evento.getImpactoTransito(),
                evento.getAcaoTomada(),
                evento.getVersion(),
                new LeituraSensorExibicaoDTO(evento.getLeituraSensor())
        );
    }
}
