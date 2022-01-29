package com.example.trabalhofinaldaw2.Controller.Leilao;

import com.example.trabalhofinaldaw2.Models.Leilao.Leilao;
import com.example.trabalhofinaldaw2.Service.Leilao.LeilaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*", allowedHeaders = "*")
@RestController
public class LeilaoController {

    @Autowired
    LeilaoService leilaoService;

    @GetMapping("/api/leilao")
    public List<Leilao> ListarTodosLeiloes() {
        return leilaoService.ListarTodosLeiloes();
    }

    @GetMapping("/api/leilao/{id}")
    public Optional<Leilao> ListarLeilaoEspecifico(@PathVariable(value = "id") long id) {
        return leilaoService.ListarLeilaoEspecifico(id);
    }

    @PostMapping("/api/leilao")
    public Leilao LeilaoSalvar(@RequestBody Leilao leilao) {
        return leilaoService.LeilaoSalvar(leilao);
    }

    @PostMapping("/api/leilao/status")
    public List<Leilao> BuscarLeilaoStatus(@RequestBody Leilao leilao) {
        return leilaoService.BuscarLeilaoStatus(leilao);
    }

    @PutMapping("/api/leilao")
    public HttpEntity<?> LeilaoEditar(@RequestBody Leilao leilao) {
        return leilaoService.LeilaoEditar(leilao);
    }

    @DeleteMapping("/api/leilao/{id}")
    public ResponseEntity<Leilao> LeilaoDeletar(@PathVariable(value = "id") long id) {
        return (ResponseEntity<Leilao>) leilaoService.LeilaoDeletar(id);
    }
}
