package org.example.leontisservlet.model;

import java.sql.Date;
public class Artista {
//   ATRIBUTOS DA CLASSE
    private  int id;
    private String nmArtista;
    private Date dtNascArtista;
    private Date dtFalecimento;
    private String localNasc;
    private String localMorte;
    private String descArtista;
    private String urlImagem;

//    MÉTODO CONSTRUTOR
    public Artista (int id, String nmArtista, Date dtNascArtista,Date dtFalecimento, String localNasc, String localMorte, String descArtista, String urlImagem){
        this.id = id;
        this.nmArtista = nmArtista;
        this.dtNascArtista = dtNascArtista;
        this.dtFalecimento = dtFalecimento;
        this.localNasc = localNasc;
        this.localMorte = localMorte;
        this.descArtista = descArtista;
        this.urlImagem = urlImagem;
    }

//    MÉTODOS GETTERS E SETTERS
    public int getId() {
        return this.id;
    }
    public String getNmArtista(){
        return this.nmArtista;
    }
    public Date getDtNascArtista() {
        return this.dtNascArtista;
    }
    public Date getDtFalecimento() {
        return this.dtFalecimento;
    }
    public String getLocalNasc() {
        return this.localNasc;
    }
    public String getLocalMorte(){
        return this.localMorte;
    }
    public String getDescArtista(){
        return this.descArtista;
    }
    public String getUrlImagem(){
        return this.urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

//    MÉTODO TOSTRING
    public String toString(){
        return "Id: "+this.id+"\n" +
                "Nome: "+this.nmArtista+"\n" +
                "Data de nascimento: "+this.dtNascArtista+"\n" +
                "Data de falecimento: "+this.dtFalecimento+"\n" +
                "Local de nascimento: "+this.localNasc+"\n"+
                "Local de falecimento: "+this.localMorte+"\n"+
                "Descrição do artista: "+this.descArtista+"\n"+
                "URL da imagem: "+this.urlImagem;
    }
}
