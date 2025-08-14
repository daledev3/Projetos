package com.example.demo.DTO;

import java.util.List;

public class AlunoResponseDTO {
	

    private Long id;
    private String nome;
    private String email;
    private List<String> cursos; // ou pode ser List<Long> se quiser só os IDs
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<String> getCursos() {
		return cursos;
	}
	public void setCursos(List<String> cursos) {
		this.cursos = cursos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	



}
