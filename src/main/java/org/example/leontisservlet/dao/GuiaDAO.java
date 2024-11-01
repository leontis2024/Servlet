package org.example.leontisservlet.dao;

import org.example.leontisservlet.model.Guia;

import java.sql.*;
public class GuiaDAO {
    //<editor-fold desc="Método inserir">
    public int inserir(Guia guia) {
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try {
            //Armazenando as colunas obrigatórias na variável "colunas".
            String colunas = "(id_museu, titulo_guia";

            //Armazenando a quantidade de colunas obrigatórias, com "?" para chamar no insert futuramente.
            String parametros = "(?,?";

            //Verificando as colunas que são opcionais, se não forem nulas, vai adicionar o nome da sua coluna na variável "colunas", e
            //uma vírgula e o seu valor na variável "parametros".
            if (!guia.getDescGuia().isEmpty()) {
                colunas += ",desc_guia";
                parametros += ",'" + guia.getDescGuia() + "'";
            }

            //Armazenando um ")" para fechar as colunas e os parâmetros.
            colunas += ")";
            parametros += ")";

            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();

            String insert = "INSERT INTO guia " + colunas + " VALUES " + parametros;
            PreparedStatement pstmt = conn.prepareStatement(insert);
            pstmt.setInt(1, guia.getIdMuseu());
            pstmt.setString(2, guia.getTituloGuia());

            if (pstmt.executeUpdate() > 0) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return -1;
        } finally {
            //Dando erro ou não, desconectamos.
            conexao.desconectar();
        }
    }
    //</editor-fold>

    //<editor-fold desc="Métodos alterar">
    public int alterar(Guia guia, int id) {
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try {
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();
            String update = "UPDATE guia SET id_museu = ?, titulo_guia = ?, desc_guia = ?, url_imagem = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setInt(1, guia.getIdMuseu());
            pstmt.setString(2, guia.getTituloGuia());
            pstmt.setString(3, guia.getDescGuia());
            pstmt.setString(4, guia.getUrlImagem());
            pstmt.setInt(5, id);
            if (pstmt.executeUpdate() > 0) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return -1;
        } finally {
            //Dando erro ou não, desconectamos.
            conexao.desconectar();
        }
    }

    public int alterarUrlImagem(String url_imagem, int id) {
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.conectar();
            String update = "UPDATE guia SET url_imagem = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setString(1, url_imagem);
            pstmt.setInt(2, id);
            if (pstmt.executeUpdate() > 0) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return -1;
        } finally {
            conexao.desconectar();
        }
    }
    //</editor-fold>

    //<editor-fold desc="Método remover">
    public int remover(int id) {
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try {
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM guia WHERE id = ?");
            pstmt.setInt(1,id);
            if (pstmt.executeUpdate() > 0) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return -1;
        } finally {
            //Dando erro ou não, desconectamos.
            conexao.desconectar();
        }
    }
    //</editor-fold>

    //<editor-fold desc="Métodos buscar">
    //Método buscar por id.
    public ResultSet buscarPorId(int id) {
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try {
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM guia WHERE id = ?");
            pstmt.setInt(1, id);
            return pstmt.executeQuery();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        } finally {
            //Dando erro ou não, desconectamos.
            conexao.desconectar();
        }
    }

    //Método buscar pelo titulo do museu
    public ResultSet buscarPorTituloMuseu(int id_museu, String titulo) {
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM guia WHERE id_museu = ? AND titulo_guia = ?");
            pstmt.setInt(1, id_museu);
            pstmt.setString(2, titulo);
            return pstmt.executeQuery();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        } finally {
            conexao.desconectar();
        }
    }

    //Método buscar pelo id do museu.
    public ResultSet buscarPorIdMuseu(int id_museu) {
        //Instanciando o objeto "conexao".
        Conexao conexao = new Conexao();
        try {
            //Abrindo uma conexão com o banco de dados.
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM guia WHERE id_museu = ?");
            pstmt.setInt(1, id_museu);
            return pstmt.executeQuery();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        } finally {
            //Dando erro ou não, desconectamos.
            conexao.desconectar();
        }
    }
    //</editor-fold>
}