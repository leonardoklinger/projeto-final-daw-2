package com.example.trabalhofinaldaw2.Service.Usuario;

import com.example.trabalhofinaldaw2.Models.Lance.Lance;
import com.example.trabalhofinaldaw2.Models.Usuario.Usuario;
import com.example.trabalhofinaldaw2.Repository.Lance.LanceRepository;
import com.example.trabalhofinaldaw2.Repository.Usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    LanceRepository lanceRepository;

    public List<Usuario> ListarTodosUsers() {
        return usuarioRepository.findAll();
    }

    public Usuario SalvarUser(Usuario usuario) {
        usuario.setSenha(criptografarSenha().encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

//    public ResponseEntity<Boolean> ValidarLogin(Usuario usuario) {
//        Optional<Usuario> optionalUsuario = usuarioRepository.findUsuarioEmail(usuario.getEmail());
//        if(optionalUsuario.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
//        }
//        boolean validar = false;
//        validar = criptografarSenha().matches(usuario.getSenha(), optionalUsuario.get().getSenha());
//
//        HttpStatus status = (validar) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
//        return ResponseEntity.status(status).body(validar);
//    }



    public PasswordEncoder criptografarSenha() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    public Optional<Lance> findOneById(Long id) {
        return lanceRepository.findById(id);
    }
}
