package br.com.fiap.semaforo_mongodb.config.security;

import br.com.fiap.semaforo_mongodb.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${minha.chave.secreta}")
    private String palavraSecreta;

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(palavraSecreta);

            String token = JWT
                    .create()
                    .withIssuer("semaforo")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(gerarDataDeExpiracao())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Não foi possível gerar o token!");
        }
    }

    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(palavraSecreta);

            return JWT
                    .require(algorithm)
                    .withIssuer("semaforo")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTCreationException e) {
            return "";
        }
    }

    public Instant gerarDataDeExpiracao() {
        return LocalDateTime
                .now()
                .plusHours(1)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
