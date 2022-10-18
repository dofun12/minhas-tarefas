package org.lemanoman.minhastarefas.repository;

import org.lemanoman.minhastarefas.model.TarefaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<TarefaModel, Integer> {
}
