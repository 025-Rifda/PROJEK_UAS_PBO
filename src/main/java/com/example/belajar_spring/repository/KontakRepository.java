package com.example.belajar_spring.repository;

import com.example.belajar_spring.model.Kontak;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KontakRepository extends JpaRepository<Kontak, Long> {

    @Query("SELECT k FROM Kontak k WHERE LOWER(k.nama) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(k.noTelp) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(k.jenisKontak) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Kontak> searchKontak(@Param("keyword") String keyword);

}
