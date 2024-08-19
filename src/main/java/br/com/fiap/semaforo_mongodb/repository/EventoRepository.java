package br.com.fiap.semaforo_mongodb.repository;

import br.com.fiap.semaforo_mongodb.model.Evento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventoRepository extends MongoRepository<Evento, String> {
}
