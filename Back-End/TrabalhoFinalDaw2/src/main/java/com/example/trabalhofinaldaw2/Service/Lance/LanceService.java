package com.example.trabalhofinaldaw2.Service.Lance;

import com.example.trabalhofinaldaw2.Models.Lance.Lance;
import com.example.trabalhofinaldaw2.Models.Usuario.Usuario;
import com.example.trabalhofinaldaw2.Repository.Lance.LanceRepository;
import com.example.trabalhofinaldaw2.Repository.Usuario.UsuarioRepository;
import com.example.trabalhofinaldaw2.Service.TokenService.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class LanceService {
    @Autowired
    LanceRepository lanceRepository;
    @Autowired
    TokenService tokenService;
    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Lance> ListarTodosLances() {
        return lanceRepository.findAll();
    }

    public List<Lance> ListarLanceEspecifico(Long id) {
        return lanceRepository.ultimoLance(id);
    }

    public HttpEntity<? extends Serializable> DarLance(Lance lance) {
        Usuario user = usuarioRepository.findById(tokenService.getIdUsuario(lance.getToken())).get();
        List<Lance> list = lanceRepository.ultimoLance(lance.getIdLeilao());
        if(list.isEmpty()) {
            if(lance.getStatusLeilao().equals("ABERTO") && lance.getValor() > lance.getValorLeilao()) {
                lance.setEmail(user.getEmail());
                lance.setNomeAuthor(user.getNome());
                lanceRepository.save(lance);
                return ResponseEntity.ok("Lance Efetuado com sucesso");
            } else {
                return new ResponseEntity<>("Erro, lance tem que está aberto e o valor tem que ser maior que do leilão !",HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            if (lance.getStatusLeilao().equals("ABERTO") && lance.getValor() > lance.getValorLeilao()) {
                if (LanceNaoPodeDarLancesSeguidos(user.getEmail(), list.get(list.size() - 1).getEmail())) {
                    if (LanceNaoPodeAssumirValorIgualLeilao(lance.getValor(), list.get(list.size() - 1).getValor())) {
                        lance.setEmail(user.getEmail());
                        lance.setNomeAuthor(user.getNome());
                        lanceRepository.save(lance);
                        return ResponseEntity.ok("Lance Efetuado com sucesso");
                    } else {
                        return new ResponseEntity<>("Valor tem que ser maior que do último lance !",HttpStatus.NOT_ACCEPTABLE);
                    }
                } else {
                    return new ResponseEntity<>("Você não pode dar 2 lances seguidos !",HttpStatus.NOT_ACCEPTABLE);
                }
            } else {
                return new ResponseEntity<>("Erro, lance tem que está aberto e o valor tem que ser maior que do leilão !",HttpStatus.NOT_ACCEPTABLE);
            }
        }
    }

    public Boolean LanceNaoPodeAssumirValorIgualLeilao(Integer valorLance, Integer ultimoLance) {
        if(valorLance > ultimoLance){
            return true;
        }
        return false;
    }

    public Boolean LanceNaoPodeDarLancesSeguidos(String nomeUser, String ultimoLance) {
        if(!nomeUser.equals(ultimoLance)) {
            return true;
        }
        return false;
    }


}
