package org.lemanoman.minhastarefas.repository;

import org.lemanoman.minhastarefas.model.TarefaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<TarefaModel, Integer> {
    List<TarefaModel> findAllByIdProjeto(Integer idProjeto);
}
