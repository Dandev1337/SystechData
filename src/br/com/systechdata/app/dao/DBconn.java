package br.com.systechdata.app.dao;
import java.sql.*;

public class DBconn {
    private static final String URL_MYSQL = "jdbc:mysql://localhost/systechdata";
    private static final String DRIVER_CLASS_MYSQL = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASS = "";


    public static Connection getConnection() {
        System.out.println("Conectando ao Banco de Dados");
        try {
            Class.forName(DRIVER_CLASS_MYSQL);
            return DriverManager.getConnection(URL_MYSQL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void close( Connection conn, PreparedStatement pstm, ResultSet rs) {
        try {
            if (conn!= null) {
                conn.close();
            }

            if (pstm!= null) {
                pstm.close();
            }

            if (rs!= null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void createDB() {
        Connection connection = getConnection();
        PreparedStatement stmt = null;
        String sql = "CREATE DATABASE  if not exists systechdata";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Banco de dados criado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, stmt, null);
        }
    }
    public static void selectDB(){
        Connection connection = getConnection();
        PreparedStatement stmt = null;
        String sql = "USE systechdata";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Banco de dados Selecionado com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, stmt, null);
        }

    }
    public static void createTableTecnico() {
        Connection connection = getConnection();
        PreparedStatement stmt = null;
        String sql = "CREATE TABLE if NOT EXISTS tecnico (" +
                "COD_TECNICO bigint PRIMARY KEY AUTO_INCREMENT," +
                "NOME VARCHAR(80) NOT NULL," +
                "CPF VARCHAR(20) NOT NULL," +
                "ENDERECO VARCHAR(80) NOT NULL," +
                "TELEFONE VARCHAR(20) NOT NULL," +
                "USUARIO VARCHAR(80) NOT NULL," +
                "SENHA VARCHAR(20) NOT NULL" +
                ")";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Tabela Tecnico Criada com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, stmt, null);
        }
    }
    public static void createTableCliente() {
        Connection connection = getConnection();
        PreparedStatement stmt = null;
        String sql = "CREATE TABLE if NOT EXISTS cliente (" +
                "CPF VARCHAR(20)PRIMARY KEY NOT NULL," +
                "NOME VARCHAR(80) NOT NULL," +
                "ENDERECO VARCHAR(80) NOT NULL," +
                "TELEFONE VARCHAR(20) NOT NULL" +
                ")";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Tabela Cliente Criada com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, stmt, null);
        }
    }
    public static void createTableOS() {
        Connection connection = getConnection();
        PreparedStatement stmt = null;
        String sql =  "CREATE TABLE if NOT EXISTS tbos ( "+
                "OS INT PRIMARY KEY AUTO_INCREMENT NOT NULL,"+
                "DATA_OS TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"+
                "TIPO VARCHAR (25) NOT NULL,"+
                "SITUACAO VARCHAR (25) NOT NULL,"+
                "EQUIPAMENTO VARCHAR (150) NOT NULL,"+
                "DEFEITO VARCHAR (150) NOT NULL,"+
                "SERVICO VARCHAR (150),"+
                "CODTECNICO bigint (20) NOT NULL,"+
                "VALOR DECIMAL (10,2),"+
                "CPF_CLI VARCHAR (20) NOT NULL,"+
                "foreign key (CPF_CLI) references cliente(CPF),"+
                "foreign key (CODTECNICO) references tecnico(COD_TECNICO)"+
        ")";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Tabela OS Criada com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, stmt, null);
        }
    }
}
