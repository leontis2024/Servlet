package org.example.leontisservlet.model;

public class Genero {
//    ATRIBUTOS DA CLASSE GENERO
    private int id;
    private String nmGenero;
    private String intro;
    private String descGenero;
    private String urlImagem;
//    MÉTODO CONSTRUTOR
    public Genero(int id, String nmGenero, String intro, String descGenero, String urlImagem){
        this.id = id;
        this.nmGenero = nmGenero;
        this.intro = intro;
        this.descGenero = descGenero;
        this.urlImagem = urlImagem;
    }
//    MÉTODOS GET
    public int getId() {
        return this.id;
    }
    public String getNmGenero(){
        return this.nmGenero;
    }
    public String getIntro(){
        return this.intro;
    }
    public String getDescGenero(){
        return this.descGenero;
    }
    public String getUrlImagem(){
        return this.urlImagem;
    }
//    MÉTODO TOSTRING
public String toString(){
    return "Id: "+this.id+"\n" +
            "Nome do Gênero: "+this.nmGenero+"\n" +
            "Introdução: "+this.intro+"\n" +
            "Descrição: "+this.descGenero+"\n" +
            "URL da imagem: "+this.urlImagem;
}
}
