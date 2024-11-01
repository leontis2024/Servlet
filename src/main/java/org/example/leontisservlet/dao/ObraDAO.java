package org.example.leontisservlet.dao;

import org.example.leontisservlet.model.Obra;

import java.sql.*;

public class ObraDAO {

    //<editor-fold desc="Método inserir">
    public int inserir(Obra obra){
        Conexao conexao = new Conexao();
        try{
            String colunas = "(ano_inicio, ano_final, nm_obra, id_genero, id_artista, id_museu";
            String parametros = "(?,?,?,?,?,?";
            if(!obra.getDescObra().isEmpty()){
                colunas+=",desc_obra";
                parametros+= ",'"+obra.getDescObra()+"'";
            }
            colunas += ")";
            parametros+=")";
            Connection conn = conexao.conectar();
            String insert = "INSERT INTO obra "+colunas+" VALUES "+parametros;
            PreparedStatement pstmt = conn.prepareStatement(insert);
            pstmt.setInt(1,obra.getAnoInicio());
            pstmt.setInt(2,obra.getAnoFinal());
            pstmt.setString(3,obra.getNmObra());
            pstmt.setInt(4,obra.getIdGenero());
            pstmt.setInt(5,obra.getIdArtista());
            pstmt.setInt(6,obra.getIdMuseu());

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

    //<editor-fold desc="Métodos alterar">
    //Método que altera a url da imagem.
    public int alterarUrlImagem(String url_imagem, int id){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String update = "UPDATE obra SET url_imagem = ? WHERE id = ?";
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

    //Método alterar - geral.
    public int alterar(Obra obra, int id){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String update = "UPDATE obra SET id_genero = ?, id_artista = ?, id_museu = ?,ano_inicio = ?, ano_final = ?, desc_obra = ?, nm_obra = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setInt(1,obra.getIdGenero());
            pstmt.setInt(2,obra.getIdArtista());
            pstmt.setInt(3,obra.getIdMuseu());
            pstmt.setInt(4,obra.getAnoInicio());
            pstmt.setInt(5,obra.getAnoFinal());
            pstmt.setString(6,obra.getDescObra());
            pstmt.setString(7,obra.getNmObra());
            pstmt.setInt(8,id);
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
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM obra WHERE id = ?");
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

    //<editor-fold desc="Métodos buscar">
    //Método buscar por id.
    public ResultSet buscarPorId(int id){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM obra WHERE id = ?");
            pstmt.setInt(1,id);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }

    //Método buscar por nome.
    public ResultSet buscarPorNome(String nmObra){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM obra WHERE nm_obra = ?");
            pstmt.setString(1,nmObra);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }

    //Método buscar pelo id do gênero.
    public ResultSet buscarPorIdGenero(int idGenero){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM obra WHERE id_genero = ?");
            pstmt.setInt(1,idGenero);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }

    //Método buscar pelo artista.
    public ResultSet buscarPorArtista(int idArtista){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM obra WHERE id_artista = ?");
            pstmt.setInt(1,idArtista);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }

    //Método buscar pelo id do museu.
    public ResultSet buscarPorIdMuseu(int idMuseu){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM obra WHERE id_museu = ?");
            pstmt.setInt(1,idMuseu);
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
