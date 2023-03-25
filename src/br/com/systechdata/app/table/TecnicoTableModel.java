package br.com.systechdata.app.table;

import br.com.systechdata.app.entity.Tecnico;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TecnicoTableModel extends AbstractTableModel {

    private static final int COL_codtecnico = 0;
    private static final int COL_nome = 1;
    private static final int COL_cpf = 2;
    private static final int COL_endereco = 3;
    private static final int COL_telefone = 4;
    private static final int COL_usuario = 5;
    private static final int COL_senha = 6;

    private List<Tecnico> values;

    public TecnicoTableModel(List<Tecnico> values) {
        this.values = values;
    }

    @Override
    public int getRowCount() {
        return values.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tecnico tecnico = values.get(rowIndex);
        if(columnIndex == COL_codtecnico){
            return tecnico.getCodTecnico();
        }else if (columnIndex == COL_nome){
            return tecnico.getNome();
        }else if(columnIndex == COL_cpf){
            return tecnico.getCpf();
        }else if (columnIndex == COL_endereco){
            return tecnico.getEndereco();
        } else if (columnIndex == COL_telefone) {
            return tecnico.getTelefone();
        } else if (columnIndex == COL_usuario) {
            return tecnico.getUsuario();
        } else if (columnIndex == COL_senha) {
            return tecnico.getSenha();
        }
        return null;
    }
    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column){
            case COL_codtecnico:
                coluna = "Código";
                break;
            case COL_nome:
                coluna = "Nome";
                break;
            case COL_cpf:
                coluna = "CPF";
                break;
            case COL_endereco:
                coluna = "Endereço";
                break;
            case COL_telefone:
                coluna = "Telefone";
                break;
            case COL_usuario:
                coluna= "Usuário";
                break;
            case  COL_senha:
                coluna = "Senha";
                break;
            default:
                throw new IllegalArgumentException("Coluna Inválida! Tente novamente :D");

        }
        return coluna;

    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == COL_codtecnico){
            return Long.class;
        }else if (columnIndex == COL_nome){
            return String.class;
        }else if(columnIndex == COL_cpf){
            return Long.class;
        }else if (columnIndex == COL_endereco){
            return String.class;
        } else if (columnIndex == COL_telefone) {
            return Long.class;
        } else if (columnIndex == COL_usuario) {
            return String.class;
        } else if (columnIndex == COL_senha) {
            return String.class;
        }
        return null;
    }
    public Tecnico get(int row){
    return values.get(row);
    }
}
