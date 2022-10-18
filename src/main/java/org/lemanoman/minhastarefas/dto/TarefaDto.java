package org.lemanoman.minhastarefas.dto;

import org.lemanoman.minhastarefas.model.TarefaModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

public class TarefaDto {
    final private SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    private String idProjeto;
    private String titulo;
    private String descricao;
    private String dataPrazo;

    private String dataCriacao;

    public TarefaDto() {
    }


    public TarefaDto(TarefaModel tarefaModel) {
        this.dataPrazo = getAsDateString(tarefaModel.getDataPrazo());
        this.idProjeto = tarefaModel.getIdProjeto() + "";
        this.titulo = tarefaModel.getTitulo();
        this.descricao = tarefaModel.getDescricao();
        this.dataCriacao = getAsDateString(tarefaModel.getDataCriacao());
    }

    private Date getAsDate(String dateString) {
        if (dateString == null) {
            return null;
        }
        try {
            Date date = inputDateFormat.parse(dateString);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }

    private String getAsDateString(Date date) {
        if (date == null) {
            return null;
        }

        return inputDateFormat.format(date);

    }

    public TarefaModel getTarefaModel() {
        TarefaModel tarefaModel = new TarefaModel();
        tarefaModel.setIdProjeto(Integer.parseInt(this.idProjeto));
        tarefaModel.setDataPrazo(getAsDate(this.dataPrazo));
        tarefaModel.setDataCriacao(getAsDate(this.dataCriacao));

        tarefaModel.setTitulo(this.getTitulo());
        tarefaModel.setDescricao(this.getDescricao());
        return tarefaModel;
    }

    public String getPrazoMessage() {
        if (this.dataPrazo == null) {
            return "Sem prazo";
        }
        Date datePrazo = getAsDate(this.dataPrazo);

        if (datePrazo == null) {
            return "Prazo invalido";
        }

        Calendar calendarPrazo = Calendar.getInstance();
        calendarPrazo.setTime(datePrazo);

        Calendar calendarAgora = Calendar.getInstance();
        long days = Duration.between(calendarAgora.toInstant(), calendarPrazo.toInstant()).toDays();

        return "Faltam " + days + " dias restantes";
    }

    public String getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(String idProjeto) {
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

    public String getDataPrazo() {
        return dataPrazo;
    }

    public void setDataPrazo(String dataPrazo) {
        this.dataPrazo = dataPrazo;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
