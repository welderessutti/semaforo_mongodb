package br.com.fiap.semaforo_mongodb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class Evento {

    @Id
    private String id;

    private LocalDateTime dataHoraEvento;

    private String tipoEvento;

    private String localEvento;

    private String impactoTransito;

    private String acaoTomada;

    @DBRef
    private LeituraSensor leituraSensor;

    @Version
    private Long version;
}
