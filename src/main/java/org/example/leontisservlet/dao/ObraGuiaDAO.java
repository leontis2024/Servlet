package org.example.leontisservlet.dao;

import org.example.leontisservlet.model.ObraGuia;

import java.sql.*;

public class ObraGuiaDAO {
    //<editor-fold desc="Método inserir">
    public int inserir(ObraGuia obraGuia){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String insert = "INSERT INTO obra_guia (nr_ordem, id_guia, id_obra) VALUES (?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(insert);
            pstmt.setInt(1,obraGuia.getNrOrdem());
            pstmt.setInt(2,obraGuia.getIdGuia());
            pstmt.setInt(3,obraGuia.getIdObra());
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
    public int alterar(ObraGuia obraGuia,int id, int nr_ordem){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String update = "UPDATE obra_guia SET nr_ordem = ?, id_guia = ?, id_obra = ?, desc_localizacao = ? WHERE id = ? AND nr_ordem = ?";
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setInt(1, obraGuia.getNrOrdem());
            pstmt.setInt(2, obraGuia.getIdGuia());
            pstmt.setInt(3, obraGuia.getIdObra());
            pstmt.setString(4, obraGuia.getDesc_localizacao());
            pstmt.setInt(5,id);
            pstmt.setInt(6,nr_ordem);
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
    public int remover(int id, int nrOrdem){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM obra_guia WHERE id = ? AND nr_ordem = ?");
            pstmt.setInt(1,id);
            pstmt.setInt(2,nrOrdem);
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
    public int removerTodosGuia(int idGuia){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM obra_guia WHERE id_guia = ?");
            pstmt.setInt(1,idGuia);
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

    //<editor-fold desc="Métodos buscar">
    //Método de buscar pelo id do guia.
    public ResultSet buscarPorIdGuia(int idGuia){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM obra_guia WHERE id_guia = ? ORDER BY nr_ordem ASC");
            pstmt.setInt(1,idGuia);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }
    //Método de buscar pelo id da obra
    public ResultSet buscarPorIdObra(int idObra){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM obra_guia WHERE id_obra = ? ORDER BY nr_ordem ASC");
            pstmt.setInt(1,idObra);
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
