package br.com.systechdata.app.entity;

public class Tecnico extends Pessoa {
    private Long codTecnico;

    public Tecnico() {
        super();
    }

    @Override
    public void deletar(Long CPF) {

    }

    public Long getCodTecnico() {
        return codTecnico;
    }

    public void setCodTecnico(Long codTecnico) {
        this.codTecnico = codTecnico;
    }
}
