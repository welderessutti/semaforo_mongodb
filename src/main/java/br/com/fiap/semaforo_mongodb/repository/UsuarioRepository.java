package br.com.fiap.semaforo_mongodb.repository;

import br.com.fiap.semaforo_mongodb.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    UserDetails findByEmail(String email);

}
