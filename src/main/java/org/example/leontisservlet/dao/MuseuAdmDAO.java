package org.example.leontisservlet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MuseuAdmDAO {

    //<editor-fold desc="Método alterar">
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



