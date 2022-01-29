package com.example.trabalhofinaldaw2.Repository.Usuario;

import com.example.trabalhofinaldaw2.Models.Usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    @Query(value = "SELECT * FROM usuario l WHERE l.email = ?1", nativeQuery = true)
    public Optional<Usuario> findUsuarioEmail(String email);
}
