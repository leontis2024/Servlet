package org.example.leontisservlet.dao;

import org.example.leontisservlet.model.Guia;

import java.sql.*;
public class GuiaDAO {
    public int inserir(Guia guia){
        Conexao conexao = new Conexao();
        try {
            String tabelas = "(id_museu, titulo_guia";
            String parametros = "(?,?";
            if (!guia.getDescGuia().isEmpty()){
                tabelas+=",desc_guia";
                parametros+=",'"+guia.getDescGuia()+"'";
            }
            if (!guia.getUrlImagem().isEmpty()){
                tabelas+=",url_imagem";
                parametros+=",'"+guia.getUrlImagem()+"'";
            }
            tabelas+=")";
            parametros+=")";
            Connection conn = conexao.conectar();
            String insert = "INSERT INTO guia "+tabelas+" VALUES "+parametros;
            PreparedStatement pstmt = conn.prepareStatement(insert);
            pstmt.setInt(1,guia.getIdMuseu());
            pstmt.setString(2,guia.getTituloGuia());

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

    public int alterar(String valor,int id, String tabela){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String update = "UPDATE guia SET "+tabela+" = ? WHERE id = ?";
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
    public int alterarUrlImagem(String url_imagem, int id){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String update = "UPDATE guia SET url_imagem = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setString(1,url_imagem);
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
    public int alterar(Guia guia, int id){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String update = "UPDATE guia SET id_museu = ?, titulo_guia = ?, desc_guia = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setInt(1,guia.getIdMuseu());
            pstmt.setString(2,guia.getTituloGuia());
            pstmt.setString(3, guia.getDescGuia());
            pstmt.setInt(4,id);
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
        try {
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM guia WHERE id = ?");
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
        try {
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM guia WHERE id = ?");
            pstmt.setInt(1,id);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }
    public ResultSet buscarPorIdMuseu(int id_museu){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM guia WHERE id_museu = ?");
            pstmt.setInt(1,id_museu);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }
    public ResultSet buscarPorTituloMuseu(int id_museu, String titulo){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM guia WHERE id_museu = ? AND titulo_guia = ?");
            pstmt.setInt(1,id_museu);
            pstmt.setString(2,titulo);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }
}
