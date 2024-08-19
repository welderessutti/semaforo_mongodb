package br.com.fiap.semaforo_mongodb.repository;

import br.com.fiap.semaforo_mongodb.model.LeituraSensor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LeituraSensorRepository extends MongoRepository<LeituraSensor, String> {
}
