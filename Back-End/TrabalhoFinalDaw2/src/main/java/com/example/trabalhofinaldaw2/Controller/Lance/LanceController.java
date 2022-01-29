package com.example.trabalhofinaldaw2.Controller.Lance;

import com.example.trabalhofinaldaw2.Models.Lance.Lance;
import com.example.trabalhofinaldaw2.Service.Lance.LanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@CrossOrigin(origins="*", allowedHeaders = "*")
@RestController
public class LanceController {
    @Autowired
    LanceService lanceService;

    @GetMapping("/api/lance")
    public List<Lance> ListarTodosLances() {
        return lanceService.ListarTodosLances();
    }

    @GetMapping("/api/lance/{id}")
    public List<Lance> ListarLanceEspecifico(@PathVariable(value = "id") long id) {
        return lanceService.ListarLanceEspecifico(id);
    }

    @PostMapping("/api/lance")
    public HttpEntity<? extends Serializable> LeilaoSalvar(@RequestBody Lance lance) {
        return lanceService.DarLance(lance);
    }
}
