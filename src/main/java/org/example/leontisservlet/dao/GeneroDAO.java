package org.example.leontisservlet.dao;

import org.example.leontisservlet.model.Genero;

import java.sql.*;

public class GeneroDAO {
    //<editor-fold desc="Métodos buscar">
    //Método buscar por id.
    public ResultSet buscarPorId(int id){
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try{
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();

            //Preparando o select.
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM genero WHERE id = ?");

            //Definindo o parâmetro com base no objeto recebido.
            pstmt.setInt(1,id);

            //Retornando os dados da consulta SQL.
            return pstmt.executeQuery();

            //Caso der algum erro no banco de dados, vai retornar nulo.
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            //Dando erro ou não, desconectamos.
            conexao.desconectar();
        }
    }

    //Método buscar - geral.
    public ResultSet buscarTodos(){
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try{
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();

            //Preparando o select.
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM genero");

            //Retornando os dados da consulta SQL.
            return pstmt.executeQuery();

            //Caso der algum erro no banco de dados, vai retornar nulo.
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            //Dando erro ou não, desconectamos.
            conexao.desconectar();
        }
    }
    //</editor-fold>
}
