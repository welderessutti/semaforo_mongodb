package br.com.fiap.semaforo_mongodb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Semaforo {

    @Id
    private String id;

    private String localInstalacao;

    private Integer tempoVerde;

    private Integer tempoAmarelo;

    private Integer tempoVermelho;

    @Version
    private Long version;
}
