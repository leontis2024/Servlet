package org.example.leontisservlet.dao;

import org.example.leontisservlet.model.Genero;

import java.sql.*;

public class GeneroDAO {
    public int inserir(Genero genero){
        Conexao conexao = new Conexao();
        try{
//          Criando a String do insert
            String tabelas = "(nm_genero";
            String parametros = "(?";
            if(genero.getIntro().isEmpty()){
                tabelas+=",intro";
                parametros+=",'"+genero.getIntro()+"'";
            }
            if(genero.getUrlImagem().isEmpty()){
                tabelas+=",desc_genero";
                parametros+=",'"+genero.getUrlImagem()+"'";
            }
            if(genero.getUrlImagem().isEmpty()){
                tabelas+=",url_imagem";
                parametros+=",'"+genero.getUrlImagem()+"'";
            }
            tabelas +=")";
            parametros+=")";
//            Fazendo a conexao
            Connection conn = conexao.conectar();
            String insert = "INSERT INTO genero "+tabelas+" VALUES "+parametros;
            PreparedStatement pstmt = conn.prepareStatement(insert);
            pstmt.setString(1,genero.getNmGenero());
            if(pstmt.executeUpdate() > 0){
                return 1;
            }else {
                return 0;
            }


        }catch (SQLException sqle){
            sqle.printStackTrace();
            return -1;
        }finally {
            conexao.desconectar();
        }
    }

    public int alterar(String valor,int id,String tabela){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String update = "UPDATE genero SET "+tabela+" = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setString(1,valor);
            pstmt.setInt(2,id);
            if(pstmt.executeUpdate() > 0){
                return 1;
            }else {
                return 0;
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return -1;
        }finally {
            conexao.desconectar();
        }
    }
    public int alterar(int valor,int id,String tabela){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String update = "UPDATE genero SET "+tabela+" = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setInt(1,valor);
            pstmt.setInt(2,id);
            if(pstmt.executeUpdate() > 0){
                return 1;
            }else {
                return 0;
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return -1;
        }finally {
            conexao.desconectar();
        }
    }

    public int remover(int id){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM genero WHERE id = ?");
            pstmt.setInt(1,id);
            if(pstmt.executeUpdate() > 0){
                return 1;
            }else {
                return 0;
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return -1;
        }finally {
            conexao.desconectar();
        }
    }

    public ResultSet buscarPorId(int id){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM genero WHERE id = ?");
            pstmt.setInt(1,id);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }
    public ResultSet buscarPorNome(String nome){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM genero WHERE nm_genero = ?");
            pstmt.setString(1,nome);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }
    public ResultSet buscarTodos(){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM genero");
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }
}
