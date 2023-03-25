package br.com.systechdata.app.controller;

import br.com.systechdata.app.entity.Tecnico;
import br.com.systechdata.app.facade.TecnicoFacade;

import java.util.List;

public class TecnicoController {
    private TecnicoFacade facade;
    public TecnicoController(){
        this.facade = new TecnicoFacade();
    }
    public int addTecnico(Tecnico tecnico){
        return facade.save(tecnico);
    }
    public int updateTecnico(Tecnico tecnico){
        return facade.update(tecnico);
    }
    public int removeTecnico(Long codtecnico){
        return facade.remove(codtecnico);
    }
    public List<Tecnico> findTecnicos() {
        return facade.findAll();
    }
}
