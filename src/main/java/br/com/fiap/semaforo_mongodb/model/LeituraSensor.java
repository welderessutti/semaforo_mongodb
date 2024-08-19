package br.com.fiap.semaforo_mongodb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class LeituraSensor {

    @Id
    private String id;

    private LocalDateTime dataHoraLeitura;

    private Integer fluxoVeiculos;

    private Double velocidadeMedia;

    private Integer tempoFila;

    private String condicaoClimatica;

    @DBRef
    private Sensor sensor;

    @Version
    private Long version;
}
