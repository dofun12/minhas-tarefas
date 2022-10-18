package org.lemanoman.minhastarefas.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "projeto")
public class ProjetoModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer idProjeto;
    @Column
    private String titulo;
    @Column
    private String descricao;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPrazo;

    public Integer getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Integer idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataPrazo() {
        return dataPrazo;
    }

    public void setDataPrazo(Date dataPrazo) {
        this.dataPrazo = dataPrazo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjetoModel that = (ProjetoModel) o;

        return Objects.equals(idProjeto, that.idProjeto);
    }

    @Override
    public int hashCode() {
        return idProjeto != null ? idProjeto.hashCode() : 0;
    }
}
