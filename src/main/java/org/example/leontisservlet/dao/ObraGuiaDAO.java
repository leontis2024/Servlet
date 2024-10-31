package org.example.leontisservlet.dao;

import org.example.leontisservlet.model.ObraGuia;

import java.sql.*;

public class ObraGuiaDAO {
    //MÉTODO INSERIR
    public int inserir(ObraGuia obraGuia){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String insert = "INSERT INTO obra_guia (nr_ordem, id_guia, id_obra, desc_localizacao) VALUES (?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(insert);
            pstmt.setInt(1,obraGuia.getNrOrdem());
            pstmt.setInt(2,obraGuia.getIdGuia());
            pstmt.setInt(3,obraGuia.getIdObra());
            pstmt.setString(4, obraGuia.getDesc_localizacao());
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

    //MÉTODO ALTERAR
    public int alterar(int valor,int id,String coluna){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String update = "UPDATE obra_guia SET "+coluna+" = ? WHERE id = ?";
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
    public int alterar(ObraGuia obraGuia,int id){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String update = "UPDATE obra_guia SET nr_ordem = ?, id_guia = ?, id_obra = ?, desc_localizacao = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setInt(1, obraGuia.getNrOrdem());
            pstmt.setInt(2, obraGuia.getIdGuia());
            pstmt.setInt(3, obraGuia.getIdObra());
            pstmt.setString(4, obraGuia.getDesc_localizacao());
            pstmt.setInt(5,id);
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
    public int alterar(ObraGuia obraGuia, int id_guia, int nr_ordem){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String update = "UPDATE obra_guia SET nr_ordem = ?, id_guia = ?, id_obra = ?, desc_localizacao = ? WHERE id_guia = ? AND nr_ordem = ?";
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setInt(1, obraGuia.getNrOrdem());
            pstmt.setInt(2, obraGuia.getIdGuia());
            pstmt.setInt(3, obraGuia.getIdObra());
            pstmt.setString(4, obraGuia.getDesc_localizacao());
            pstmt.setInt(5,id_guia);
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


    //MÉTODO REMOVER
    public int remover(int id){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM obra_guia WHERE id = ?");
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
    public int remover(int id_guia, int nr_ordem){
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM obra_guia WHERE id_guia = ? AND nr_ordem = ?");
            pstmt.setInt(1,id_guia);
            pstmt.setInt(2,nr_ordem);
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
    public void removerTodosGuia(int id_guia){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM obra_guia WHERE id_guia = ?");
            pstmt.setInt(1,id_guia);
            pstmt.execute();
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }finally {
            conexao.desconectar();
        }
    }

    //MÉTODO DE BUSCAR PELO ID PRINCIPAL
    public ResultSet buscarPorId(int id){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM obra_guia WHERE id = ?");
            pstmt.setInt(1,id);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }

    //MÉTODO DE BUSCAR PELO ID DO GUIA
    public ResultSet buscarPorIdGuia(int idGuia){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM obra_guia WHERE id_guia = ? ORDER BY nr_ordem");
            pstmt.setInt(1,idGuia);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }

    //MÉTODO DE BUSCAR PELO ID Da GUIA
    public ResultSet buscarPorIdObra(int idObra){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM obra_guia WHERE id_obra = ? ORDER BY nr_ordem");
            pstmt.setInt(1,idObra);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }


    //MÉTODO DE BUSCAR PELO ID GUIA + ID OBRA
    public ResultSet buscarPorObraGuia(int idGuia, int idObra){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM obra_guia WHERE id_guia = ? AND id_obra = ?");
            pstmt.setInt(1,idGuia);
            pstmt.setInt(2,idObra);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }

    //MÉTODO DE BUSCAR PELO ID GUIA + NUMERO DA ORDEM
    public ResultSet buscarPorGuiaOrdem(int idGuia, int nrOrdem){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM obra_guia WHERE id_guia = ? AND nr_obra = ?");
            pstmt.setInt(1,idGuia);
            pstmt.setInt(2,nrOrdem);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }

}
