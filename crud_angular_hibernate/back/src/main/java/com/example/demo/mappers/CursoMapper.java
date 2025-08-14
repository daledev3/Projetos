package com.example.demo.mappers;

import com.example.demo.DTO.CursoRequestDTO;
import com.example.demo.DTO.CursoResponseDTO;
import com.example.demo.entidades.Curso;

public class CursoMapper {
	
	  public static Curso toEntity(CursoRequestDTO dto) {
	        Curso curso = new Curso();
	        curso.setNomeCurso(dto.getNome());
	        return curso;
	    }

	    public static CursoResponseDTO toResponseDTO(Curso curso) {
	        CursoResponseDTO dto = new CursoResponseDTO();
	        dto.setId(curso.getId());
	        dto.setNome(curso.getNomeCurso());
	        return dto;
	    }

}
