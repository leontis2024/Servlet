package org.example.leontisservlet.dao;

//importando a API JDBC - somente o necessário
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//A classe "conexao" foi criada para evitar ficar repetindo os mesmos métodos em todas as classes.
public class Conexao {
    private Connection conn;

    //Método conectar
    //<editor-fold desc="Conectar">
    public Connection conectar() {
        try {
            //Carregando o driver.
            Class.forName("org.postgresql.Driver");

            //Armazenando as informações sobre o banco.
            conn = DriverManager.getConnection(
                    System.getenv("DB_URL")
                    , System.getenv("DB_USER")
                    , System.getenv("DB_PASSWD")
            );
            //Retorna um objeto Connection ou null caso a conexão falhe.
            return conn;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            return null;
        }
    }
    //</editor-fold>

    //Método desconectar.
    //<editor-fold desc="Desconectar">
    public void desconectar() {
        try {
            //Verificando se a conexão conn existe e está aberta.
            if (conn != null && !conn.isClosed()) {
                //Fechando a conexão.
                conn.close();
            }
            //Tratando possíveis erros.
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    //</editor-fold>
}