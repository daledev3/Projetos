package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.CursoRequestDTO;
import com.example.demo.DTO.CursoResponseDTO;
import com.example.demo.entidades.Curso;
import com.example.demo.mappers.CursoMapper;
import com.example.demo.repository.CursoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
	
	 private final CursoRepository cursoRepo = null;

	// Criar novo curso
	    @PostMapping
	    public ResponseEntity<CursoResponseDTO> criarCurso(@RequestBody @Valid CursoRequestDTO dto) {
	        Curso curso = CursoMapper.toEntity(dto);
	        Curso salvo = cursoRepo.save(curso);
	        return ResponseEntity.status(HttpStatus.CREATED).body(CursoMapper.toResponseDTO(salvo));
	    }

	    // Listar todos os cursos
	    @GetMapping
	    public ResponseEntity<List<CursoResponseDTO>> listarCursos() {
	        List<Curso> cursos = cursoRepo.findAll();
	        List<CursoResponseDTO> dtos = cursos.stream()
	                .map(CursoMapper::toResponseDTO)
	                .collect(Collectors.toList());
	        return ResponseEntity.ok(dtos);
	    }

	    // Buscar curso por ID
	    @GetMapping("/{id}")
	    public ResponseEntity<CursoResponseDTO> buscarCursoPorId(@PathVariable Long id) {
	        return cursoRepo.findById(id)
	                .map(curso -> ResponseEntity.ok(CursoMapper.toResponseDTO(curso)))
	                .orElse(ResponseEntity.notFound().build());
	    }

	    // Atualizar curso
	    @PutMapping("/{id}")
	    public ResponseEntity<CursoResponseDTO> atualizarCurso(
	            @PathVariable Long id,
	            @RequestBody @Valid CursoRequestDTO dto) {

	        return cursoRepo.findById(id).map(curso -> {
	            curso.setNomeCurso(dto.getNome());
	            Curso atualizado = cursoRepo.save(curso);
	            return ResponseEntity.ok(CursoMapper.toResponseDTO(atualizado));
	        }).orElse(ResponseEntity.notFound().build());
	    }

	    // Deletar curso 
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Object> deletarCurso(@PathVariable Long id) {
	        return cursoRepo.findById(id).map(curso -> {
	            cursoRepo.delete(curso);
	            return ResponseEntity.noContent().build();
	        }).orElse(ResponseEntity.notFound().build());
	    }
}
