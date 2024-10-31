package org.example.leontisservlet.model;

public class Guia {
//    ATRIBUTOS DA CLASSE GUIA
    private int id;
    private int idMuseu;
    private String tituloGuia;
    private String descGuia;
    private String urlImagem;
//    CONSTRUTOR
    public Guia (int id, int idMuseu, String tituloGuia, String descGuia, String urlImagem){
        this.id = id;
        this.idMuseu = idMuseu;
        this.tituloGuia = tituloGuia;
        this.descGuia = descGuia;
        this.urlImagem = urlImagem;
    }
//    MÉTODOS GET
    public int getId() {
        return this.id;
    }
    public int getIdMuseu() {
        return this.idMuseu;
    }
    public String getTituloGuia() {
        return this.tituloGuia;
    }
    public String getDescGuia() {
        return this.descGuia;
    }
    public String getUrlImagem() {
        return this.urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    //    MÉTODO TOSTRING
    public String toString(){
        return "Id: "+this.id+"\n" +
                "Id do museu: "+this.idMuseu+"\n" +
                "Título do guia: "+this.tituloGuia+"\n" +
                "Descrição: "+this.descGuia+"\n" +
                "URL da imagem: "+this.urlImagem;
    }
}
