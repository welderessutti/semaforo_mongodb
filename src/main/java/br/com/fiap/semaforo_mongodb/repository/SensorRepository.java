package br.com.fiap.semaforo_mongodb.repository;

import br.com.fiap.semaforo_mongodb.model.Sensor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SensorRepository extends MongoRepository<Sensor, String> {

    Boolean existsBySemaforo_Id(String semaforoId);
}
