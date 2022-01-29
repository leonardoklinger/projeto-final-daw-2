package com.example.trabalhofinaldaw2.Service.Leilao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LeilaoDataValidade {
    @Autowired
    LeilaoService leilaoService;

    @Scheduled(fixedDelay = 2000)
    public void LeilaoValidade() {
        leilaoService.LeilaoValidade();
    }
}
