package org.example.leontisservlet.dao;

import org.example.leontisservlet.model.DiaFuncionamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DiaFuncionamentoDAO {
    public int inserir(DiaFuncionamento diaFuncionamento) {
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String insert = "INSERT INTO dia_funcionamento (hr_inicio, hr_termino, pr_dia_funcionamento, dia_semana, id_museu) VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(insert);
            pstmt.setString(1, diaFuncionamento.getHrInicio());
            pstmt.setString(2, diaFuncionamento.getHrTermino());
            pstmt.setDouble(3,diaFuncionamento.getPrDiaFuncionamento());
            pstmt.setString(4,diaFuncionamento.getDiaSemana());
            pstmt.setInt(5,diaFuncionamento.getIdMuseu());
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

    public int alterar(String valor,int id,String tabela){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String update = "UPDATE dia_funcionamento SET "+tabela+" = ? WHERE id = ?";
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
            String update = "UPDATE dia_funcionamento SET "+tabela+" = ? WHERE id = ?";
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
    public int alterar(double valor,int id,String tabela){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String update = "UPDATE dia_funcionamento SET "+tabela+" = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setDouble(1,valor);
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

    public int alterar(DiaFuncionamento diaFuncionamento, int id){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            String update = "UPDATE dia_funcionamento SET id_museu = ?, hr_inicio = ?, hr_termino = ?, pr_dia_funcionamento = ?, dia_semana = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setInt(1,diaFuncionamento.getIdMuseu());
            pstmt.setString(2,diaFuncionamento.getHrInicio());
            pstmt.setString(3, diaFuncionamento.getHrTermino());
            pstmt.setDouble(4,diaFuncionamento.getPrDiaFuncionamento());
            pstmt.setString(5,diaFuncionamento.getDiaSemana());
            pstmt.setInt(6,id);
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
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM dia_funcionamento WHERE id = ?");
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
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM dia_funcionamento WHERE id = ?");
            pstmt.setInt(1,id);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }
    public ResultSet buscarPorIdMuseu(int id){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM dia_funcionamento WHERE id_museu = ?");
            pstmt.setInt(1,id);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }
    public ResultSet buscarPorIdMuseuDiaSemana(int id, String dia){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM dia_funcionamento WHERE id_museu = ? and dia_semana = ?") ;
            pstmt.setInt(1,id);
            pstmt.setString(2,dia);
            return pstmt.executeQuery();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            conexao.desconectar();
        }
    }

}


