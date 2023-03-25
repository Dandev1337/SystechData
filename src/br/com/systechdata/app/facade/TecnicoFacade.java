package br.com.systechdata.app.facade;

import br.com.systechdata.app.dao.ITecnicoDAO;
import br.com.systechdata.app.dao.TecnicoDAO;
import br.com.systechdata.app.entity.Tecnico;

import java.util.List;

public class TecnicoFacade {
    private ITecnicoDAO dao;
    public TecnicoFacade (){
        this.dao = new TecnicoDAO();
    }
    public int save(Tecnico tecnico){
        return dao.save(tecnico);
    }
    public int update (Tecnico tecnico){
        return dao.update(tecnico);
    }
    public int remove (long codtecnico){
        return dao.remove(codtecnico);
    }
    public List<Tecnico> findAll(){
        return dao.findAll();
    }

}
