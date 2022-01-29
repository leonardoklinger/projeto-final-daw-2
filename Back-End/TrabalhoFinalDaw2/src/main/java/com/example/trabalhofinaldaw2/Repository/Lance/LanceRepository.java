package com.example.trabalhofinaldaw2.Repository.Lance;

import com.example.trabalhofinaldaw2.Models.Lance.Lance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanceRepository extends JpaRepository<Lance, Long> {
    @Query(value = "SELECT * FROM lance l WHERE l.id_leilao = ?1", nativeQuery = true)
    public List<Lance> ultimoLance(Long idLeilao);
}