package com.example.trabalhofinaldaw2.Controller.Usuario;

import DTO.TokenDto;
import com.example.trabalhofinaldaw2.Models.Usuario.Usuario;
import com.example.trabalhofinaldaw2.Service.TokenService.TokenService;
import com.example.trabalhofinaldaw2.Service.Usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins="*", allowedHeaders = "*")
@RestController
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    TokenService tokenService;

    @GetMapping("/api/usuario")
    public List<Usuario> ListarTodosUsers() {
        return usuarioService.ListarTodosUsers();
    }

    @PostMapping("/api/usuario")
    public Usuario UserSalvar(@RequestBody Usuario usuario) {
        return usuarioService.SalvarUser(usuario);
    }

//    @PostMapping("/api/usuario/validarLogin")
//    public ResponseEntity<Boolean> ValidarLogin(@RequestBody Usuario usuario) {
//        return usuarioService.ValidarLogin(usuario);
//    }

    @PostMapping("/api/usuario/validarLogin")
    public ResponseEntity<TokenDto> ValidarLogin(@RequestBody @Valid Usuario usuario) {
        UsernamePasswordAuthenticationToken dadosLogin = usuario.converter();
        try {
            Authentication authentication = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
