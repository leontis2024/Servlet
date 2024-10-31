package org.example.leontisservlet.dao;

import org.example.leontisservlet.model.Museu;

import java.sql.*;

public class MuseuDAO {
    public int inserir(Museu museu){
        Conexao conexao = new Conexao();
        try{
//          Criando a String do insert
            String colunas = "(nm_museu,rua,estado,cidade,cep,dt_inauguracao,cnpj,id_museu_adm";
            String parametros = "(?,?,?,?,?,?,?,?";
            if(!museu.getDescMuseu().isEmpty()){
                colunas+=",desc_museu";
                parametros+=",'"+museu.getDescMuseu()+"'";
            }
            if(!museu.getNrTelMuseu().isEmpty()){
                colunas+=",ponto_referencia";
                parametros+=",'"+museu.getPontoReferencia()+"'";
            }
            if(!museu.getNrTelMuseu().isEmpty()){
                colunas+=",nr_tel_museu";
                parametros+=",'"+museu.getNrTelMuseu()+"'";
            }
            if(!museu.getUrlImagem().isEmpty()){
                colunas+=",url_imagem";
                parametros+=",'"+museu.getUrlImagem()+"'";
            }
            colunas +=")";
            parametros+=")";
//            Fazendo a conexao
            Connection conn = conexao.conectar();
            String insert = "INSERT INTO museu "+colunas+" VALUES "+parametros;
            PreparedStatement pstmt = conn.prepareStatement(insert);

            pstmt.setString(1, museu.getNmMuseu());
            pstmt.setString(2, museu.getRua());
            pstmt.setString(3, museu.getEstado());
            pstmt.setString(4, museu.getCidade());
            pstmt.setString(5, museu.getCep());
            pstmt.setDate(6, museu.getDtInauguracao());
            pstmt.setString(7, museu.getCnpj());
            pstmt.setInt(8, museu.getId_adm_museu());


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

    public int alterar(String valor,int id,String coluna){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String update = "UPDATE museu SET "+coluna+" = ? WHERE id = ?";
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
    public int alterar(int valor,int id,String coluna){
        Conexao conexao = new Conexao();

        try{
            Connection conn = conexao.conectar();
            String update = "UPDATE museu SET "+coluna+" = ? WHERE id = ?";
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
    public int alterar(Date valor,int id,String coluna){
        Conexao conexao = new Conexao();

        try{
            Connection conn = conexao.conectar();
            String update = "UPDATE museu SET "+coluna+" = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setDate(1,valor);
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

    //Metodo apenas para url da imagem, pois ela só é editada caso o usuario insira uma nova no cadastro, caso contrario, mantemos a mesma
    public int alterarUrlImagem(String url_imagem, int id){
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.conectar();
            String update = "UPDATE museu SET url_imagem = ? WHERE id = ?";
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
    public int alterar(Museu museu, int id){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String update = "UPDATE museu SET id_museu_adm = ?, cnpj = ?, cep = ?, nm_museu = ?, desc_museu = ?, dt_inauguracao = ?, rua = ?, estado = ?, cidade = ?, ponto_referencia = ?, nr_tel_museu = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setInt(1,museu.getId_adm_museu());
            pstmt.setString(2,museu.getCnpj());
            pstmt.setString(3,museu.getCep());
            pstmt.setString(4, museu.getNmMuseu());
            pstmt.setString(5, museu.getDescMuseu());
            pstmt.setDate(6, museu.getDtInauguracao());
            pstmt.setString(7, museu.getRua());
            pstmt.setString(8, museu.getEstado());
            pstmt.setString(9, museu.getCidade());
            pstmt.setString(10, museu.getPontoReferencia());
            pstmt.setString(11, museu.getNrTelMuseu());
            pstmt.setInt(12, id);
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
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM museu WHERE id = ?");
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
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM museu WHERE id = ?");
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
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM museu WHERE nm_museu = ?");
            pstmt.setString(1,nome);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }
    public ResultSet buscarPorIdMuseuAdm(int id){
        Conexao conexao = new Conexao();

        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM museu WHERE id_museu_adm = ?");
            pstmt.setInt(1,id);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }

}