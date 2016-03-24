package pdm.ifpb.edu.br.medico.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by emanuel on 22/03/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cliente implements Serializable{

    private Long id;
    private String email;
    private String senha;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
