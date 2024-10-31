package org.example.leontisservlet.service;

import org.example.leontisservlet.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class MetodosAuxiliares {

    //Museu
    public static Museu pegarMuseu(ResultSet rs){
        try {
            //Gerando e retornando um museu com base no result set enviado
            Museu museu = null;
            if (rs.next()){
                museu = new Museu(rs.getInt("id")
                        ,rs.getString("nm_museu")
                        ,rs.getString("desc_museu")
                        ,rs.getString("rua")
                        ,rs.getString("estado")
                        ,rs.getString("cidade")
                        ,rs.getString("ponto_referencia")
                        ,rs.getString("cep")
                        ,rs.getDate("dt_inauguracao")
                        ,rs.getString("nr_tel_museu")
                        ,rs.getString("url_imagem")
                        ,rs.getString("cnpj")
                        ,rs.getInt("id_museu_adm")
                );
            }

            return museu;
        }catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        }
    }
    //Dia funcionamento
    public static LinkedList<DiaFuncionamento> listarDiaFuncionamento(ResultSet rs){
        //Gerando e retornando uma lista de dias de funcionemento

        try{
            LinkedList<DiaFuncionamento> diasFuncionamento = new LinkedList<>();
            while (rs.next()) {
                diasFuncionamento.add(new DiaFuncionamento(rs.getInt("id")
                        , rs.getString("hr_inicio")
                        , rs.getString("hr_termino")
                        , rs.getDouble("pr_dia_funcionamento")
                        , rs.getString("dia_semana")
                        , rs.getInt("id_museu")));
            }
            return diasFuncionamento;
        }catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        }

    }
    public static DiaFuncionamento pegarDiaFuncionamento(ResultSet rs){
        //Gerando e retornando um dia de funcionamento com base no result set enviado
        try{
            DiaFuncionamento diaFuncionamento = null;
            if (rs.next()) {
                diaFuncionamento = new DiaFuncionamento(rs.getInt("id")
                        , rs.getString("hr_inicio")
                        , rs.getString("hr_termino")
                        , rs.getDouble("pr_dia_funcionamento")
                        , rs.getString("dia_semana")
                        , rs.getInt("id_museu"));
            }
            return diaFuncionamento;
        }catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        }
    }
    public static LinkedList<DiaFuncionamento> ordenarDiasFuncionamento(LinkedList<DiaFuncionamento> diasFuncionamento) {
        String[] dias = {"dom","seg","ter","qua","qui","sex","sab"};
        for (int i = 0; i < diasFuncionamento.size() -1; i++) {
            int diaI = 0;
            for (int k = 0; k < dias.length; k++) {
                if(diasFuncionamento.get(i).getDiaSemana().equals(dias[k])){
                    diaI = k;
                }
            }
            for (int j = i+1; j < diasFuncionamento.size(); j++) {
                int diaJ = 0;
                for (int k = 0; k < dias.length; k++) {
                    if(diasFuncionamento.get(j).getDiaSemana().equals(dias[k])){
                        diaJ = k;
                    }
                }
                if(diaI>diaJ){
                    DiaFuncionamento temp = diasFuncionamento.get(i);
                    diasFuncionamento.set(i, diasFuncionamento.get(j));
                    diasFuncionamento.set(j, temp);
                }
            }
        }
        return diasFuncionamento;
    }
    //Artista
    public static Artista pegarArtista(ResultSet rs){
        try{
            Artista artista = null;
            if(rs.next()) {
                artista = new Artista(rs.getInt("id"),
                        rs.getString("nm_artista"),
                        rs.getDate("dt_nasc_artista"),
                        rs.getDate("dt_falecimento"),
                        rs.getString("local_nasc"),
                        rs.getString("local_morte"),
                        rs.getString("desc_artista"),
                        rs.getString("url_imagem"));
            }
            return artista;
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }
    }
    public static LinkedList<Artista> listarArtistas(ResultSet rs){
        try {
            LinkedList<Artista> artistas= new LinkedList<Artista>();
            while (rs.next()){
                artistas.add(new Artista(rs.getInt("id"),
                        rs.getString("nm_artista"),
                        rs.getDate("dt_nasc_artista"),
                        rs.getDate("dt_falecimento"),
                        rs.getString("local_nasc"),
                        rs.getString("local_morte"),
                        rs.getString("desc_artista"),
                        rs.getString("url_imagem")));

            }
            return artistas;
        }catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        }
    }
    public static LinkedList<ArtistaGenero> listarArtistaGenero(ResultSet rs){
        LinkedList<ArtistaGenero> artistaGeneros = new LinkedList<>();
        try {
            while (rs.next()) {
                artistaGeneros.add(new ArtistaGenero(rs.getInt("id")
                        , rs.getInt("id_artista")
                        , rs.getInt("id_genero")
                ));
            }
            return artistaGeneros;
        }catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        }
    }
    //Guia
    public static Guia pegarGuia(ResultSet rs){
        try{
            Guia guia = null;
            if(rs.next()) {
                guia = new Guia(rs.getInt("id")
                        , rs.getInt("id_museu")
                        , rs.getString("titulo_guia")
                        , rs.getString("desc_guia")
                        , rs.getString("url_imagem"));
            }
            return guia;
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }
    }
    public static LinkedList<Guia> listarGuias(ResultSet rs){
        try{
            LinkedList<Guia> guias = new LinkedList<>();
            while (rs.next()){
                guias.add(new Guia(rs.getInt("id")
                        , rs.getInt("id_museu")
                        , rs.getString("titulo_guia")
                        , rs.getString("desc_guia")
                        , rs.getString("url_imagem")));
            }
            return guias;
        }catch (SQLException sqle){
            return null;
        }
    }
    public static LinkedList<ObraGuia> listarObraGuia(ResultSet rs){
        LinkedList<ObraGuia> obraGuias = new LinkedList<>();
        try {
            while (rs.next()) {
                obraGuias.add(new ObraGuia(
                        rs.getInt("nr_ordem")
                        , rs.getInt("id_guia")
                        , rs.getInt("id_obra")
                        , rs.getInt("id")
                        , rs.getString("desc_localizacao")
                ));
            }
            return obraGuias;
        }catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        }
    }
    //Obra
    public static Obra pegarObra(ResultSet rs){
        try{
            Obra obra = null;
            if(rs.next()){
                obra = new Obra(rs.getInt("id")
                        , rs.getInt("ano_inicio")
                        , rs.getInt("ano_final")
                        , rs.getString("desc_obra")
                        , rs.getString("nm_obra")
                        , rs.getInt("id_genero")
                        , rs.getInt("id_artista")
                        , rs.getInt("id_museu")
                        , rs.getString("url_imagem"));
            }
            return obra;
        }catch (SQLException sqle){
            return null;
        }

    }
    public static LinkedList<Obra> listarObras(ResultSet rs){
        try{
            LinkedList<Obra> obras = new LinkedList<Obra>();
            while(rs.next()){
                obras.add(new Obra(rs.getInt("id")
                        , rs.getInt("ano_inicio")
                        , rs.getInt("ano_final")
                        , rs.getString("desc_obra")
                        , rs.getString("nm_obra")
                        , rs.getInt("id_genero")
                        , rs.getInt("id_artista")
                        , rs.getInt("id_museu")
                        , rs.getString("url_imagem")));
            }
            return obras;
        }catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        }
    }
    //Genero
    public static Genero pegarGenero(ResultSet rs){
        try{
            Genero genero = null;
            if(rs.next()){
                genero = new Genero(rs.getInt("id")
                        , rs.getString("nm_genero")
                        , rs.getString("intro")
                        , rs.getString("desc_genero")
                        , rs.getString("url_imagem")
                );
            }
            return genero;
        }catch (SQLException sqle){
            return null;
        }

    }
    public static LinkedList<Genero> listarGeneros(ResultSet rs){
        try{
            LinkedList<Genero> generos = new LinkedList<>();
            while (rs.next()){
                generos.add(new Genero(rs.getInt("id")
                        , rs.getString("nm_genero")
                        , rs.getString("intro")
                        , rs.getString("desc_genero")
                        , rs.getString("url_imagem"))
                );
            }
            return generos;
        }catch (SQLException sqle){
            return null;
        }

    }
}
