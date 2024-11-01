package org.example.leontisservlet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MuseuAdmDAO {
    //<editor-fold desc="Método inserir">
    public int inserir(String email_adm, String senha_adm){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String insert = "INSERT INTO museu_adm (email_adm,senha_adm) VALUES (?,?)";
            PreparedStatement pstmt = conn.prepareStatement(insert);
            pstmt.setString(1,email_adm);
            pstmt.setString(2,senha_adm);
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
    //</editor-fold>

    //<editor-fold desc="Método alterar">
    public int alterar(String valor,int id,String tabela){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String update = "UPDATE museu_adm SET "+tabela+" = ? WHERE id = ?";
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
    public int alterarSenha(String senha,int id){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String update = "UPDATE museu_adm SET senha_adm = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setString(1,senha);
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
    //</editor-fold>

    //<editor-fold desc="Método remover">
    public int remover(int id){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM museu_adm WHERE id = ?");
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
    //</editor-fold>

    //<editor-fold desc="Método buscar">
    //Método buscar por id.
    public ResultSet buscarPorId(int id){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM museu_adm WHERE id = ?");
            pstmt.setInt(1,id);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }

    //Método buscar pelo email.
    public ResultSet buscarPorEmail(String email){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM museu_adm WHERE email_adm = ?");
            pstmt.setString(1,email);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }
    //</editor-fold>

}



