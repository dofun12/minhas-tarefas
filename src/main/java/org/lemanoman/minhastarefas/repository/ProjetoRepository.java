package org.lemanoman.minhastarefas.repository;

import org.lemanoman.minhastarefas.model.ProjetoModel;
import org.lemanoman.minhastarefas.model.TarefaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<ProjetoModel, Integer> {
}
