package org.lemanoman.minhastarefas.service;

import org.lemanoman.minhastarefas.dto.ProjetoDto;
import org.lemanoman.minhastarefas.dto.TarefaDto;
import org.lemanoman.minhastarefas.model.ProjetoModel;
import org.lemanoman.minhastarefas.model.TarefaModel;
import org.lemanoman.minhastarefas.repository.ProjetoRepository;
import org.lemanoman.minhastarefas.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TarefaService {
    private TarefaRepository tarefaRepository;
    private ProjetoRepository projetoRepository;

    public TarefaService(TarefaRepository tarefaRepository, ProjetoRepository projetoRepository) {
        this.tarefaRepository = tarefaRepository;
        this.projetoRepository = projetoRepository;
    }
    public List<TarefaDto> getListTarefasDto(Integer idProjeto){
        return tarefaRepository.findAllByIdProjeto(idProjeto).stream().map(TarefaDto::new).toList();
    }
    public List<TarefaModel> getListTarefas(Integer idProjeto){
        return tarefaRepository.findAllByIdProjeto(idProjeto);
    }

    public ProjetoModel getProjetoModel(Integer idProjeto){
        return projetoRepository.findById(idProjeto).orElse(null);
    }

    public List<ProjetoModel> getListAll(){
        return projetoRepository.findAll();
    }

    public List<ProjetoDto> getListAllDto(){
        return projetoRepository.findAll().stream().map(ProjetoDto::new).toList();
    }


    public ProjetoModel criarProjeto(String titulo, String descricao, Date dataPrazo){
        ProjetoModel projetoModel = new ProjetoModel();
        projetoModel.setTitulo(titulo);
        projetoModel.setDescricao(descricao);
        projetoModel.setDataPrazo(dataPrazo);
        projetoModel.setDataCriacao(new Date());
        return projetoRepository.save(projetoModel);
    }
    public ProjetoModel saveProjeto(ProjetoModel projetoModel){
        return projetoRepository.save(projetoModel);
    }

    public TarefaModel criarTarefa(Integer idProjeto, String titulo, String descricao, Date dataPrazo){
        TarefaModel tarefaModel = new TarefaModel();
        tarefaModel.setTitulo(titulo);
        tarefaModel.setDescricao(descricao);
        tarefaModel.setDataPrazo(dataPrazo);
        tarefaModel.setDataCriacao(new Date());
        tarefaModel.setIdProjeto(idProjeto);
        return tarefaRepository.save(tarefaModel);
    }
}
