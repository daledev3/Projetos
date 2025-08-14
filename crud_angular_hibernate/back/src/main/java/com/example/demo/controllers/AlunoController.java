package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.AlunoRequestDTO;
import com.example.demo.DTO.AlunoResponseDTO;
import com.example.demo.entidades.Aluno;
import com.example.demo.entidades.Curso;
import com.example.demo.mappers.AlunoMapper;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.CursoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
	
	 private final AlunoRepository alunoRepo;
	    private final CursoRepository cursoRepo;

	    public AlunoController(AlunoRepository alunoRepo, CursoRepository cursoRepo) {
	        this.alunoRepo = alunoRepo;
	        this.cursoRepo = cursoRepo;
	    }

	    // Criar novo aluno
	    @PostMapping
	    public ResponseEntity<AlunoResponseDTO> criarAluno(@RequestBody @Valid AlunoRequestDTO dto) {
	        Aluno aluno = AlunoMapper.toEntity(dto);
	        Aluno salvo = alunoRepo.save(aluno);
	        return ResponseEntity.ok(AlunoMapper.toResponseDTO(salvo));
	    }

	    // Listar todos os alunos
	    @GetMapping
	    public ResponseEntity<List<AlunoResponseDTO>> listarAlunos() {
	        List<Aluno> alunos = alunoRepo.findAll();
	        List<AlunoResponseDTO> dtos = alunos.stream()
	                .map(AlunoMapper::toResponseDTO)
	                .collect(Collectors.toList());
	        return ResponseEntity.ok(dtos);
	    }

	    // Buscar aluno por ID
	    @GetMapping("/{id}")
	    public ResponseEntity<AlunoResponseDTO> buscarAluno(@PathVariable Long id) {
	        return alunoRepo.findById(id)
	                .map(aluno -> ResponseEntity.ok(AlunoMapper.toResponseDTO(aluno)))
	                .orElse(ResponseEntity.notFound().build());
	    }

	    // Atualizar aluno
	    @PutMapping("/{id}")
	    public ResponseEntity<AlunoResponseDTO> atualizarAluno(
	            @PathVariable Long id,
	            @RequestBody @Valid AlunoRequestDTO dto) {

	        Optional<Aluno> alunoOpt = alunoRepo.findById(id);
	        if (alunoOpt.isEmpty()) {
	            return ResponseEntity.notFound().build();
	        }

	        Aluno aluno = alunoOpt.get();
	        aluno.setNome(dto.getNome());
	        aluno.setEmail(dto.getEmail());

	        Aluno atualizado = alunoRepo.save(aluno);
	        return ResponseEntity.ok(AlunoMapper.toResponseDTO(atualizado));
	    }

	    // Deletar aluno
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Object> deletarAluno(@PathVariable Long id) {
	        return alunoRepo.findById(id).map(aluno -> {
	            alunoRepo.delete(aluno);
	            return ResponseEntity.noContent().build();
	        }).orElse(ResponseEntity.notFound().build());
	    }
	    
	    //matricular aluno em curso
	    @PutMapping("/{alunoId}/adicionar-curso/{cursoId}")
	    public ResponseEntity<AlunoResponseDTO> matricularAlunoEmCurso(
	    		@PathVariable Long alunoId,
	            @PathVariable Long cursoId) {

	        Optional<Aluno> alunoOpt = alunoRepo.findById(alunoId);
	        Optional<Curso> cursoOpt = cursoRepo.findById(cursoId);

	        if (alunoOpt.isEmpty() || cursoOpt.isEmpty()) {
	            return ResponseEntity.notFound().build();
	        }

	        Aluno aluno = alunoOpt.get();
	        Curso curso = cursoOpt.get();

	        if (!aluno.getCursos().contains(curso)) {
	            aluno.getCursos().add(curso);
	            alunoRepo.save(aluno);
	        }

	        return ResponseEntity.ok(AlunoMapper.toResponseDTO(aluno));
	    }

}
