package org.lemanoman.minhastarefas.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tarefa")
public class TarefaModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer idTarefa;
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

    @Column
    private Integer idProjeto;

    @Column
    private Boolean isFinalizado;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFinalizado;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TarefaModel that = (TarefaModel) o;

        return Objects.equals(idTarefa, that.idTarefa);
    }

    @Override
    public int hashCode() {
        return idTarefa != null ? idTarefa.hashCode() : 0;
    }

    public Integer getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(Integer idTarefa) {
        this.idTarefa = idTarefa;
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

    public Integer getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Integer idProjeto) {
        this.idProjeto = idProjeto;
    }

    public Boolean getFinalizado() {
        return isFinalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        isFinalizado = finalizado;
    }

    public Date getDataFinalizado() {
        return dataFinalizado;
    }

    public void setDataFinalizado(Date dataFinalizado) {
        this.dataFinalizado = dataFinalizado;
    }
}
