package org.example.leontisservlet.dao;

import org.example.leontisservlet.model.ArtistaGenero;

import java.sql.*;
public class ArtistaGeneroDAO {
    public int inserir(ArtistaGenero artistaGenero){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String insert = "INSERT INTO artista_genero(id_artista,id_genero) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insert);
            pstmt.setInt(1,artistaGenero.getIdArtista());
            pstmt.setInt(2,artistaGenero.getIdGenero());
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
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM artista_genero WHERE id = ?");
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
    public void removerTodosArtista(int idArtista){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM artista_genero WHERE id_artista = ?");
            pstmt.setInt(1,idArtista);
            pstmt.execute();
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }finally {
            conexao.desconectar();
        }
    }

    public ResultSet buscarPorId(int id){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM artista_genero WHERE id = ?");
            pstmt.setInt(1,id);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }
    public ResultSet buscarPorIdArtista(int id_artista){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM artista_genero WHERE id_artista = ?");
            pstmt.setInt(1,id_artista);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }
    public ResultSet buscarPorIdGenero(int id_genero){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM artista_genero WHERE id_genero = ?");
            pstmt.setInt(1,id_genero);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }
}
