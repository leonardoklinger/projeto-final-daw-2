package com.example.trabalhofinaldaw2.Service.Leilao;

import com.example.trabalhofinaldaw2.Models.Lance.Lance;
import com.example.trabalhofinaldaw2.Models.Leilao.Leilao;
import com.example.trabalhofinaldaw2.Repository.Lance.LanceRepository;
import com.example.trabalhofinaldaw2.Repository.Leilao.LeilaoRepository;
import com.example.trabalhofinaldaw2.Service.Email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Service
public class LeilaoService {
    @Autowired
    private LeilaoRepository leilaoRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    LanceRepository lanceRepository;

    public List<Leilao> ListarTodosLeiloes() {
        return leilaoRepository.findAll();
    }

    public Optional<Leilao> ListarLeilaoEspecifico(Long id) {
        return leilaoRepository.findById(id);
    }

    public Leilao LeilaoSalvar(Leilao leilao) {
        leilao.setStatus("INATIVO");
        return leilaoRepository.save(leilao);
    }

    public HttpEntity<? extends Object> LeilaoEditar(Leilao leilao) {
        List<Lance> list = lanceRepository.ultimoLance(leilao.getId());
        Optional<Leilao> leilaoAntigo = leilaoRepository.findById(leilao.getId());
        if (leilaoAntigo.isPresent()) {
            Leilao leilaoNovo = leilaoAntigo.get();
            leilaoNovo.setNome(leilao.getNome());
            if (LeilaoExpiradoFinalizado(leilao.getStatus(), leilao.getData())) {
                leilaoNovo.setStatus("FINALIZADO");
                if(list.size() != 0) {
                    emailService.EnviadorDeEmail(list.get(list.size() - 1).getEmail(), "Ganhador do Leilão", "Parabéns " + list.get(list.size() - 1).getNomeAuthor() + " Você acaba de arrematar com êxito um " + list.get(list.size() - 1).getNomeLeilao());
                }
            } else {
                leilaoNovo.setStatus(leilao.getStatus());
            }
            leilaoNovo.setValor(leilao.getValor());
            leilaoNovo.setData(leilao.getData());
            leilaoRepository.save(leilaoNovo);
            return new ResponseEntity<Leilao>(leilao, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public HttpEntity<? extends Serializable> LeilaoDeletar(long id) {
        Optional<Leilao> leilaoDeletar = leilaoRepository.findById(id);
        if (leilaoDeletar.isPresent()) {
            leilaoRepository.deleteById(id);
            return new ResponseEntity<String>("Leilão deletado com sucesso", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Leilão não encontrado!", HttpStatus.NOT_FOUND);
        }
    }

    public List<Leilao> BuscarLeilaoStatus(Leilao leilao) {
        return leilaoRepository.findLeilaoByStatus(leilao.getStatus());
    }

    public void LeilaoValidade() {
        List<Leilao> list = leilaoRepository.findAll();
        LocalDate localDate = LocalDate.now();
        for (Leilao l : list) {
            if (l.getData().isBefore(localDate)) {
                if (!l.getStatus().equals("FINALIZADO")) {
                    l.setStatus("EXPIRADO");
                    leilaoRepository.save(l);
                }
            }
        }
    }

    public Boolean LeilaoExpiradoFinalizado(String status, LocalDate localDate) {
        LocalDate localDateAtual = LocalDate.now();
        if (!status.equals("INATIVO") && localDate.isBefore(localDateAtual)) {
            return true;
        }
        return false;
    }
}