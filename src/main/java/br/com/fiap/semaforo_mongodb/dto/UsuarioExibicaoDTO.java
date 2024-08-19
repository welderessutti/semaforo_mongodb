package br.com.fiap.semaforo_mongodb.dto;

import br.com.fiap.semaforo_mongodb.model.Usuario;
import br.com.fiap.semaforo_mongodb.model.UsuarioRole;

public record UsuarioExibicaoDTO(
        String id,
        String nome,
        String email,
        UsuarioRole role,
        Long version
) {
    public UsuarioExibicaoDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole(),
                usuario.getVersion()
        );
    }
}
