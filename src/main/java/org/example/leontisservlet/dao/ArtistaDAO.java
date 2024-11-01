package org.example.leontisservlet.dao;


import org.example.leontisservlet.model.Artista;

import java.sql.*;
public class ArtistaDAO {

    //<editor-fold desc="Método inserir">
    public int inserir(Artista artista){
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try{
            //Armazenando as colunas obrigatórias na variável "colunas".
            String colunas = "(nm_artista, dt_nasc_artista, local_nasc";

            //Armazenando a quantidade de colunas obrigatórias, com "?" para chamar no insert futuramente.
            String parametros = "(?,?,?";

            //Verificando as colunas que são opcionais, se não forem nulas, vai adicionar o nome da sua coluna na variável "colunas", e
            //uma vírgula e o seu valor na variável "parametros".
            if(!artista.getDescArtista().isEmpty()){
                colunas+=",desc_artista";
                parametros+=",'"+artista.getDescArtista()+"'";
            }
            if(artista.getDtFalecimento() != null){
                colunas+=",dt_falecimento";
                parametros+=",'"+artista.getDtFalecimento()+"'";
            }
            if(!artista.getLocalMorte().isEmpty()){
                colunas+=",local_morte";
                parametros+=",'"+artista.getLocalMorte()+"'";
            }

            //Armazenando um ")" para fechar as colunas e os parâmetros.
            colunas+=")";
            parametros+=")";

            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();

            //Criando a String de insert.
            String insert = "INSERT INTO artista "+colunas+" VALUES "+parametros;

            //Preparando o insert.
            PreparedStatement pstmt = conn.prepareStatement(insert);

            //Definindo os parâmetros com base nos objetos recebidos, apenas os obrigatórios,
            //pois os opcionais foram definidos na validação.
            pstmt.setString(1,artista.getNmArtista());
            pstmt.setDate(2,artista.getDtNascArtista());
            pstmt.setString(3,artista.getLocalNasc());

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
            //Dando erro ou não, desconectamos.
            conexao.desconectar();
        }
    }
    //</editor-fold>

    //<editor-fold desc="Métodos alterar">
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
    //</editor-fold>

    //<editor-fold desc="Método remover">
    public int remover(int id){
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try{
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();

            //Preparando o delete.
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM artista WHERE id = ?");

            //Definindo o parâmetro com base no objeto recebido.
            pstmt.setInt(1,id);

            //Retorno: se for 1 deu tudo certo,
            //se for 0 não executou, e se for -1, erro na conexão com o banco.
            if(pstmt.executeUpdate() > 0){
                return 1;
            }else {
                return 0;
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return -1;
        }finally {
            //Dando erro ou não, desconectamos.
            conexao.desconectar();
        }
    }
    //</editor-fold>

    //<editor-fold desc="Métodos buscar">
    public ResultSet buscarPorId(int id){
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try{
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();

            //Preparando o select.
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM artista WHERE id = ?");

            //Definindo o parâmetro com base no objeto recebido.
            pstmt.setInt(1,id);

            //Retornando os dados da consulta SQL.
            return pstmt.executeQuery();

            //Caso der algum erro no banco de dados, vai retornar nulo.
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            //Dando erro ou não, desconectamos.
            conexao.desconectar();
        }
    }

    public ResultSet buscarPorNome(String nome){
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try{
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();

            //Preparando o select.
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM artista WHERE nm_artista = ?");

            //Definindo o parâmetro com base no objeto recebido.
            pstmt.setString(1,nome);

            //Retornando os dados da consulta SQL.
            return pstmt.executeQuery();

            //Caso der algum erro no banco de dados, vai retornar nulo.
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            //Dando erro ou não, desconectamos.
            conexao.desconectar();
        }
    }

    public ResultSet buscarTodos(){
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try{
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();

            //Preparando o select.
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM artista ORDER BY nm_artista DESC");

            //Retornando os dados da consulta SQL.
            return pstmt.executeQuery();

            //Caso der algum erro no banco de dados, vai retornar nulo.
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            //Dando erro ou não, desconectamos.
            conexao.desconectar();
        }
    }
    //</editor-fold>

}




