package org.example.leontisservlet.model;

public class ArtistaGenero {
//   ATRIBUTOS DA CLASSE
    private int id;
    private int idArtista;
    private int idGenero;
//    MÉTODO CONSTRUTOR
    public ArtistaGenero (int id, int idArtista,int idGenero){
        this.id = id;
        this.idArtista = idArtista;
        this.idGenero = idGenero;
    }
//    MÉTODOS GETTERS E SETTERS
    public int getId() {
        return this.id;
    }
    public int getIdArtista() {
        return this.idArtista;
    }
    public int getIdGenero() {
        return this.idGenero;
    }
//    MÉTODO TOSTRING
    public String toString(){
        return "Id: "+this.id+"\n" +
                "Id do artista: "+this.idArtista+"\n" +
                "Id do gênero: "+this.idGenero+"\n";
    }
}
