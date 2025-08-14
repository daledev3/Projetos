package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

}
