package br.com.fiap.semaforo_mongodb.dto;

import br.com.fiap.semaforo_mongodb.model.Semaforo;

public record SemaforoExibicaoDTO(
        String id,
        String localInstalacao,
        Integer tempoVerde,
        Integer tempoAmarelo,
        Integer tempoVermelho,
        Long version
) {
    public SemaforoExibicaoDTO(Semaforo semaforo) {
        this(
                semaforo.getId(),
                semaforo.getLocalInstalacao(),
                semaforo.getTempoVerde(),
                semaforo.getTempoAmarelo(),
                semaforo.getTempoVermelho(),
                semaforo.getVersion()
        );
    }
}
