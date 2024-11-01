package org.example.leontisservlet.dao;

import org.example.leontisservlet.model.Genero;

import java.sql.*;

public class GeneroDAO {
    //<editor-fold desc="Método inserir">
    public int inserir(Genero genero){
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try{
            //Armazenando as colunas obrigatórias na variável "colunas".
            String colunas = "(nm_genero";

            //Armazenando a quantidade de colunas obrigatórias, com "?" para chamar no insert futuramente.
            String parametros = "(?";

            //Verificando as colunas que são opcionais, se não forem nulas, vai adicionar o nome da sua coluna na variável "colunas", e
            //uma vírgula e o seu valor na variável "parametros".
            if(genero.getIntro().isEmpty()){
                colunas+=",intro";
                parametros+=",'"+genero.getIntro()+"'";
            }
            if(genero.getUrlImagem().isEmpty()){
                colunas+=",desc_genero";
                parametros+=",'"+genero.getUrlImagem()+"'";
            }

            //Armazenando um ")" para fechar as colunas e os parâmetros.
            colunas +=")";
            parametros+=")";

            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();

            //Criando a String de insert.
            String insert = "INSERT INTO genero "+colunas+" VALUES "+parametros;

            //Preparando o insert.
            PreparedStatement pstmt = conn.prepareStatement(insert);

            //Definindo o parâmetro com base no objetos recebido, apenas o obrigatório,
            //pois os opcionais foram definidos na validação.
            pstmt.setString(1,genero.getNmGenero());

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

    //Método alterar com o parâmetro "valor" sendo String.
    //<editor-fold desc="Alterar 1">
    public int alterar(String valor,int id,String tabela){
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try{
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();

            //Armazenando o update na variável "update".
            String update = "UPDATE genero SET "+tabela+" = ? WHERE id = ?";

            //Preparando o update com base na string de update.
            PreparedStatement pstmt = conn.prepareStatement(update);

            //Definindo os parâmetros com base nos objetos recebidos.
            pstmt.setString(1,valor);
            pstmt.setInt(2,id);

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

    //Método alterar com o parâmetro "valor" sendo int.
    //<editor-fold desc="Alterar 2">
    public int alterar(int valor,int id,String tabela){
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try{
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();

            //Armazenando o update na variável "update".
            String update = "UPDATE genero SET "+tabela+" = ? WHERE id = ?";

            //Preparando o update com base na string de update.
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setInt(1,valor);
            pstmt.setInt(2,id);

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

    //Método remover
    //<editor-fold desc="Método remover">
    public int remover(int id){
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try{
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();

            //Preparando o delete.
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM genero WHERE id = ?");

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

    //Método buscar por id.
    //<editor-fold desc="Buscar por id">
    public ResultSet buscarPorId(int id){
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try{
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();

            //Preparando o select.
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM genero WHERE id = ?");

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
    //</editor-fold>

    //Método buscar por nome.
    //<editor-fold desc="Buscar por nome">
    public ResultSet buscarPorNome(String nome){
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try{
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();

            //Preparando o select.
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM genero WHERE nm_genero = ?");

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
    //</editor-fold>

    //Método buscar - geral.
    //<editor-fold desc="Buscar - geral">
    public ResultSet buscarTodos(){
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try{
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();

            //Preparando o select.
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM genero");

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
