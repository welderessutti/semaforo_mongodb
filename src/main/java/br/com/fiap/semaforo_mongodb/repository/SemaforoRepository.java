package br.com.fiap.semaforo_mongodb.repository;

import br.com.fiap.semaforo_mongodb.model.Semaforo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SemaforoRepository extends MongoRepository<Semaforo, String> {
}
