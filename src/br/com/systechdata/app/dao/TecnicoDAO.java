package br.com.systechdata.app.dao;

import br.com.systechdata.app.entity.Tecnico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TecnicoDAO implements ITecnicoDAO {
    private static final String SQL_INSERT =
            "INSERT INTO tecnico (NOME, CPF, ENDERECO, TELEFONE, USUARIO, SENHA) VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE =
            "UPDATE tecnico SET NOME = ?, CPF = ?, ENDERECO = ?, TELEFONE = ?, USUARIO = ?, SENHA = ? WHERE COD_TECNICO =? ";
    private static final String SQL_REMOVE =
            "DELETE FROM tecnico WHERE COD_TECNICO = ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM tecnico";
    @Override
    public int save(Tecnico tecnico) {
        Connection conn = DBconn.getConnection();
        PreparedStatement pstm = null;
        int result = 0;
        try {
            pstm = conn.prepareStatement(SQL_INSERT);
            pstm.setString(1, tecnico.getNome());
            pstm.setString(2, tecnico.getCpf());
            pstm.setString(3, tecnico.getEndereco());
            pstm.setString(4, tecnico.getTelefone());
            pstm.setString(5, tecnico.getUsuario());
            pstm.setString(6, tecnico.getSenha());
            result = pstm.executeUpdate();
        }catch (SQLException e){
            try{
                if (conn != null){
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } finally {
                DBconn.close(conn, pstm, null);
            }
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(Tecnico tecnico) {
        Connection conn = DBconn.getConnection();
        PreparedStatement pstm = null;
        int result = 0;
        try {
            pstm = conn.prepareStatement(SQL_UPDATE);
            pstm.setString(1, tecnico.getNome());
            pstm.setString(2, tecnico.getCpf());
            pstm.setString(3, tecnico.getEndereco());
            pstm.setString(4, tecnico.getTelefone());
            pstm.setString(5, tecnico.getUsuario());
            pstm.setString(6, tecnico.getSenha());
            pstm.setLong(7, tecnico.getCodTecnico());

            result = pstm.executeUpdate();

        }catch (SQLException e){
            try{
                if (conn!= null){
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } finally {
                DBconn.close(conn, pstm, null);
            }
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int remove(Long codtecnico) {
        Connection conn = DBconn.getConnection();
        PreparedStatement pstm = null;
        int result = 0;
        try {
            pstm = conn.prepareStatement(SQL_REMOVE);
            pstm.setLong(1, codtecnico);
            result = pstm.executeUpdate();

        }catch (SQLException e){
            try{
                if (conn!= null){
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } finally {
                DBconn.close(conn, pstm, null);
            }
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Tecnico> findAll() {
        Connection conn = DBconn.getConnection();
        PreparedStatement pstm = null;
        List <Tecnico> tecnicos = new ArrayList<>();
        ResultSet rs = null;
        int result = 0;
        try {

            pstm = conn.prepareStatement(SQL_FIND_ALL);
            rs = pstm.executeQuery();
            while (rs.next()){
                Tecnico tecnico = new Tecnico();
                tecnico.setCodTecnico(rs.getLong("COD_TECNICO"));
                tecnico.setNome(rs.getString("NOME"));
                tecnico.setCpf(rs.getString("CPF"));
                tecnico.setEndereco(rs.getString("ENDERECO"));
                tecnico.setTelefone(rs.getString("TELEFONE"));
                tecnico.setUsuario(rs.getString("USUARIO"));
                tecnico.setSenha(rs.getString("SENHA"));

                tecnicos.add(tecnico);
            }

        }catch (SQLException e){
            try{
                if (conn!= null){
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } finally {
                DBconn.close(conn, pstm, null);
            }
            e.printStackTrace();
        }
        return tecnicos;
    }
}
