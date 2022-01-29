package com.example.trabalhofinaldaw2.Repository.Leilao;

import com.example.trabalhofinaldaw2.Models.Lance.Lance;
import com.example.trabalhofinaldaw2.Models.Leilao.Leilao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeilaoRepository extends JpaRepository<Leilao, Long> {
    @Query(value = "SELECT * FROM leilao l WHERE l.status = ?1", nativeQuery = true)
    public List<Leilao> findLeilaoByStatus(String status);

    @Query(value = "SELECT * FROM leilao l WHERE l.data = ?1", nativeQuery = true)
    public List<Leilao> leilaoDataExpirada(String status);
}