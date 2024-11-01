package org.example.leontisservlet.dao;

import org.example.leontisservlet.model.DiaFuncionamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DiaFuncionamentoDAO {
    //Método inserir.
    //<editor-fold desc="Método inserir">
    public int inserir(DiaFuncionamento diaFuncionamento) {
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try{
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();

            //Criando a String de insert.
            String insert = "INSERT INTO dia_funcionamento (hr_inicio, hr_termino, pr_dia_funcionamento, dia_semana, id_museu) VALUES (?,?,?,?,?)";

            //Preparando o insert.
            PreparedStatement pstmt = conn.prepareStatement(insert);

            //Definindo os parâmetros com base nos objetos recebidos.
            pstmt.setString(1, diaFuncionamento.getHrInicio());
            pstmt.setString(2, diaFuncionamento.getHrTermino());
            pstmt.setDouble(3,diaFuncionamento.getPrDiaFuncionamento());
            pstmt.setString(4,diaFuncionamento.getDiaSemana());
            pstmt.setInt(5,diaFuncionamento.getIdMuseu());

            //Retorno: se for 1 deu tudo certo,
            //se for 0 não executou, e se for -1, erro na conexão com o banco.
            if(pstmt.executeUpdate() > 0){
                return 1;
            }else {
                return 0;
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return -1;
        }finally {
            //Dando erro ou não, desconectamos.
            conexao.desconectar();
        }
    }
    //</editor-fold>

    //Método alterar com o parâmetro "valor" sendo String.
    //<editor-fold desc="Alterar 1">
    public int alterar(String valor,int id,String tabela){
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try{
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();

            //Criando a String update.
            String update = "UPDATE dia_funcionamento SET "+tabela+" = ? WHERE id = ?";

            //Preparando o update.
            PreparedStatement pstmt = conn.prepareStatement(update);

            //Definindo os parâmetros com base nos objetos recebidos.
            pstmt.setString(1,valor);
            pstmt.setInt(2,id);

            //Retorno: se for 1 deu tudo certo,
            //se for 0 não executou, e se for -1, erro na conexão com o banco.
            if(pstmt.executeUpdate() > 0){
                return 1;
            }else {
                return 0;
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return -1;
        }finally {
            //Dando erro ou não, desconectamos.
            conexao.desconectar();
        }
    }
    //</editor-fold>

    //Método alterar com o parâmetro "valor" sendo int.
    //<editor-fold desc="Alterar 2">
    public int alterar(int valor,int id,String tabela){
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try{
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();

            //Criando a String update.
            String update = "UPDATE dia_funcionamento SET "+tabela+" = ? WHERE id = ?";

            //Preparando o update.
            PreparedStatement pstmt = conn.prepareStatement(update);

            //Definindo os parâmetros com base nos objetos recebidos.
            pstmt.setInt(1,valor);
            pstmt.setInt(2,id);

            //Retorno: se for 1 deu tudo certo,
            //se for 0 não executou, e se for -1, erro na conexão com o banco.
            if(pstmt.executeUpdate() > 0){
                return 1;
            }else {
                return 0;
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return -1;
        }finally {
            //Dando erro ou não, desconectamos.
            conexao.desconectar();
        }
    }
    //</editor-fold>

    //Método alterar com o parâmetro "valor" sendo double.
    //<editor-fold desc="Alterar 3">
    public int alterar(double valor,int id,String tabela){
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try{
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();

            //Criando a String update.
            String update = "UPDATE dia_funcionamento SET "+tabela+" = ? WHERE id = ?";

            //Preparando o update.
            PreparedStatement pstmt = conn.prepareStatement(update);

            //Definindo os parâmetros com base nos objetos recebidos.
            pstmt.setDouble(1,valor);
            pstmt.setInt(2,id);

            //Retorno: se for 1 deu tudo certo,
            //se for 0 não executou, e se for -1, erro na conexão com o banco.
            if(pstmt.executeUpdate() > 0){
                return 1;
            }else {
                return 0;
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return -1;
        }finally {
            //Dando erro ou não, desconectamos.
            conexao.desconectar();
        }
    }
    //</editor-fold>

    //Método alterar com o parâmetro sendo todas as colunas (uma variável da classe "diaFuncionamento" e o id).
    //<editor-fold desc="Alterar 4">
    public int alterar(DiaFuncionamento diaFuncionamento, int id){
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try{
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();

            //Criando a String update.
            String update = "UPDATE dia_funcionamento SET id_museu = ?, hr_inicio = ?, hr_termino = ?, pr_dia_funcionamento = ?, dia_semana = ? WHERE id = ?";

            //Preparando o update.
            PreparedStatement pstmt = conn.prepareStatement(update);

            //Definindo os parâmetros com base nos objetos recebidos.
            pstmt.setInt(1,diaFuncionamento.getIdMuseu());
            pstmt.setString(2,diaFuncionamento.getHrInicio());
            pstmt.setString(3, diaFuncionamento.getHrTermino());
            pstmt.setDouble(4,diaFuncionamento.getPrDiaFuncionamento());
            pstmt.setString(5,diaFuncionamento.getDiaSemana());
            pstmt.setInt(6,id);

            //Retorno: se for 1 deu tudo certo,
            //se for 0 não executou, e se for -1, erro na conexão com o banco.
            if(pstmt.executeUpdate() > 0){
                return 1;
            }else {
                return 0;
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return -1;
        }finally {
            //Dando erro ou não, desconectamos.
            conexao.desconectar();
        }
    }
    //</editor-fold>

    //Método remover.
    //<editor-fold desc="Método remover">
    public int remover(int id){
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try{
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();

            //Preparando o delete.
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM dia_funcionamento WHERE id = ?");

            //Definindo o parâmetro com base no objeto recebido.
            pstmt.setInt(1,id);

            //Retorno: se for 1 deu tudo certo,
            //se for 0 não executou, e se for -1, erro na conexão com o banco.
            if(pstmt.executeUpdate() > 0){
                return 1;
            }else {
                return 0;
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return -1;
        }finally {
            //Dando erro ou não, desconectamos.
            conexao.desconectar();
        }
    }
    //</editor-fold>

    //Método buscar por id.
    //<editor-fold desc="Buscar por id">
    public ResultSet buscarPorId(int id){
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try{
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();

            //Preparando o select.
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM dia_funcionamento WHERE id = ?");

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
    //</editor-fold>

    //Método buscar por id do museu.
    //<editor-fold desc="Buscar por id do museu">
    public ResultSet buscarPorIdMuseu(int id){
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try{
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();

            //Preparando o select.
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM dia_funcionamento WHERE id_museu = ?");

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
    //</editor-fold>

    //Método buscar pelo id museu e pelo dia da semana.
    //<editor-fold desc="Buscar pelo id museu + dia da semana">
    public ResultSet buscarPorIdMuseuDiaSemana(int id, String dia){
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try{
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();

            //Preparando o select.
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM dia_funcionamento WHERE id_museu = ? and dia_semana = ?") ;

            //Definindo os parâmetros com base nos objetos recebidos.
            pstmt.setInt(1,id);
            pstmt.setString(2,dia);

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