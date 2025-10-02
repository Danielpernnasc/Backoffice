package com.test.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String nome;


    @Column(unique = true, nullable = false)
    private String email;

    private String senha;

    private String role;

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
    
        public String getEmail() { 
            return email; 
        }

        public void setEmail(String email) { 
            this.email = email; 
        }
    
        public String getSenha() { 
            return senha; 
        }

        public void setSenha(String senha) { 
            this.senha = senha; 
        }

        public String getRole() { 
            return role; 
        }

        public void setRole(String role) { 
            this.role = role; 
        }
    
}
