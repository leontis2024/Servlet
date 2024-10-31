package org.example.leontisservlet.dao;


import org.example.leontisservlet.model.Artista;

import java.sql.*;
public class ArtistaDAO {

    public int inserir(Artista artista){
        Conexao conexao = new Conexao();
        try{
            String tabelas = "(nm_artista, dt_nasc_artista, local_nasc";
            String parametros = "(?,?,?";
            if(!artista.getDescArtista().isEmpty()){
                tabelas+=",desc_artista";
                parametros+=",'"+artista.getDescArtista()+"'";
            }
            if(artista.getDtFalecimento() != null){
                tabelas+=",dt_falecimento";
                parametros+=",'"+artista.getDtFalecimento()+"'";
            }
            if(!artista.getLocalMorte().isEmpty()){
                tabelas+=",local_morte";
                parametros+=",'"+artista.getLocalMorte()+"'";
            }
            if(!artista.getUrlImagem().isEmpty()){
                tabelas+=",url_imagem";
                parametros+=",'"+artista.getUrlImagem()+"'";

            }
            tabelas+=")";
            parametros+=")";
            Connection conn = conexao.conectar();
            String insert = "INSERT INTO artista "+tabelas+" VALUES "+parametros;
            PreparedStatement pstmt = conn.prepareStatement(insert);
            pstmt.setString(1,artista.getNmArtista());
            pstmt.setDate(2,artista.getDtNascArtista());
            pstmt.setString(3,artista.getLocalNasc());

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

    public int alterar(String valor,int id,String campo){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String update = "UPDATE artista SET "+campo+" = ? WHERE id = ?";
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
            String update = "UPDATE artista SET "+tabela+" = ? WHERE id = ?";
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

    public int alterarUrlImagem(String url_imagem, int id){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String update = "UPDATE artista SET url_imagem = ? WHERE id = ?";
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
    public int alterar(Artista artista, int id){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String update = "UPDATE artista SET nm_artista = ?, desc_artista = ?, dt_nasc_artista = ?, dt_falecimento = ?, local_nasc = ?, local_morte = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setString(1,artista.getNmArtista());
            pstmt.setString(2,artista.getDescArtista());
            pstmt.setDate(3,artista.getDtNascArtista());
            pstmt.setDate(4,artista.getDtFalecimento());
            pstmt.setString(5,artista.getLocalNasc());
            pstmt.setString(6,artista.getLocalMorte());
            pstmt.setInt(7,id);
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
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM artista WHERE id = ?");
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
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM artista WHERE id = ?");
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
            //ordenando pelo id decrescente para sempre retornar mais recente inserido primeiro em caso de nome repetido
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM artista WHERE nm_artista = ? ORDER BY id DESC");
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
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM artista ORDER BY nm_artista");
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }
}