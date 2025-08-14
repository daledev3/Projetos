package com.example.demo.mappers;

import java.util.stream.Collectors;

import com.example.demo.DTO.AlunoRequestDTO;
import com.example.demo.DTO.AlunoResponseDTO;
import com.example.demo.entidades.Aluno;
import com.example.demo.entidades.Curso;

public class AlunoMapper {
	
	  public static AlunoRequestDTO toDTO(Aluno aluno) {
	        AlunoRequestDTO dto = new AlunoRequestDTO();
	        dto.setNome(aluno.getNome());
	        return dto;
	    }

	    public static Aluno toEntity(AlunoRequestDTO dto) {
	        Aluno aluno = new Aluno();
	        aluno.setNome(dto.getNome());
	        aluno.setEmail(dto.getEmail());
	        return aluno;
	    }
	    
	    public static AlunoResponseDTO toResponseDTO(Aluno aluno) {
	        AlunoResponseDTO dto = new AlunoResponseDTO();
	        dto.setId(aluno.getId());
	        dto.setNome(aluno.getNome());
	        dto.setEmail(aluno.getEmail());

	        if (aluno.getCursos() != null) {
	            dto.setCursos(
	                aluno.getCursos().stream()
	                    .map(Curso::getNomeCurso)
	                    .collect(Collectors.toList())
	            );
	        }

	        return dto;
	    }
	}

