package br.com.systechdata.app.dao;

import br.com.systechdata.app.entity.Tecnico;

import java.util.List;

public interface ITecnicoDAO {
    int save(Tecnico tecnico);
    int update ( Tecnico tecnico);
    int remove(Long id);

    List<Tecnico> findAll();
}
