package org.example.leontisservlet.dao;

import org.example.leontisservlet.model.ArtistaGenero;

import java.sql.*;
public class ArtistaGeneroDAO {

    //<editor-fold desc="Método Inserir">
    public int inserir(ArtistaGenero artistaGenero){
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try{
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();

            //Criando a String de insert.
            String insert = "INSERT INTO artista_genero(id_artista,id_genero) VALUES (?, ?)";
            //Preparando o insert.
            PreparedStatement pstmt = conn.prepareStatement(insert);
            pstmt.setInt(1,artistaGenero.getIdArtista());
            pstmt.setInt(2,artistaGenero.getIdGenero());

            //Retorno: se for 1 deu tudo certo,
            //se for 0 não foi inserido, e se for -1, erro na conexão com o banco.
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

    //<editor-fold desc="Métodos Remover">
    //Método remover pelo id
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
    //Método para remover todos com base no artista
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
    //</editor-fold>

    //<editor-fold desc="Métodos Buscar">
    //Buscando por id
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
    //Buscando por IdArtista
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
    //</editor-fold>

}
