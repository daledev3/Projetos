package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class AlunoRequestDTO {
	
	    @NotBlank(message = "Nome é obrigatório")
	    @Size(min = 3, max = 50, message = "Nome deve ter entre 3 e 50 caracteres")
	    private String nome;
	    
	    @NotBlank(message = "Email é obrigatório")
	    private String email;

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
		
		

	    
	}

