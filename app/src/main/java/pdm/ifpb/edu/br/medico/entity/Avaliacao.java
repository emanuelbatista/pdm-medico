package pdm.ifpb.edu.br.medico.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by emanuel on 23/03/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Avaliacao implements Serializable{

    private long id;
    private Cliente cliente;
    private String opiniao;
    private long nota;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getOpiniao() {
        return opiniao;
    }

    public void setOpiniao(String opiniao) {
        this.opiniao = opiniao;
    }

    public long getNota() {
        return nota;
    }

    public void setNota(long nota) {
        this.nota = nota;
    }
}
