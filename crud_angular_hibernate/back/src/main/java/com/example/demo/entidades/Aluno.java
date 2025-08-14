package com.example.demo.entidades;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Entity;

@Entity
public class Aluno {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String email;
	
	@Column
	private LocalDate dataNascimento;
	
	@ManyToMany
	@JoinTable(
	        name = "aluno_curso",
	        joinColumns = @JoinColumn(name = "aluno_id"),
	        inverseJoinColumns = @JoinColumn(name = "curso_id"))
    private List<Curso> cursos;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public List<Curso> getCursos() {
		return cursos;
	}
	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		id = id;
	}

	
	
	
	
	
	
	

}
