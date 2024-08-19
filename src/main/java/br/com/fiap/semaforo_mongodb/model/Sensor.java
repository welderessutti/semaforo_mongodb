package br.com.fiap.semaforo_mongodb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
public class Sensor {

    @Id
    private String id;

    private String localInstalacao;

    private String tipoSensor;

    private String dadosTecnicos;

    private LocalDate dataInstalacao;

    private Boolean statusOperacao;

    @DBRef
    private Semaforo semaforo;

    @Version
    private Long version;
}
