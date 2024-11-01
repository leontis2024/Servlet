package org.example.leontisservlet.model;

public class Obra {
//  ATRIBUTOS DA CLASSE
    private int id; //n
    private int anoInicio; //n
    private int anoFinal; //n
    String descObra; //s
    private String nmObra; //n
    private int idGenero; //
    private int idArtista; //n
    private int idMuseu; //s
    private String urlImagem; //

//    MÉTODO CONSTRUTOR
    public Obra(int id, int anoInicio, int anoFinal, String descObra, String nmObra, int idGenero, int idArtista, int idMuseu, String urlImagem) {
        this.id = id;
        this.anoInicio = anoInicio;
        this.anoFinal = anoFinal;
        this.descObra = descObra;
        this.nmObra = nmObra;
        this.idGenero = idGenero;
        this.idArtista = idArtista;
        this.idMuseu = idMuseu;
        this.urlImagem = urlImagem;
    }

//    MÉTODOS GETTERS E SETTERS
    public int getId() {
        return this.id;
    }

    public int getAnoInicio() {
        return this.anoInicio;
    }

    public int getAnoFinal() {
        return this.anoFinal;
    }

    public String getDescObra() {
        return this.descObra;
    }

    public String getNmObra() {
        return this.nmObra;
    }

    public int getIdGenero() {
        return this.idGenero;
    }

    public int getIdArtista() {
        return this.idArtista;
    }

    public int getIdMuseu() {
        return this.idMuseu;
    }

    public String getUrlImagem() {
        return this.urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

//    MÉTODO TOSTRING
    public String toString() {
        return "Id da obra: " + this.id + "\n" +
                "Ano que a obra começou a ser produzida: " + this.anoInicio + "\n" +
                "Ano que a obra terminou de ser produzida: " + this.anoFinal + "\n" +
                "Descrição da obra: " + this.descObra + "\n" +
                "Nome da obra: " + this.nmObra + "\n" +
                "Id do gênero: " + this.idGenero + "\n" +
                "Id do artista: " + this.idArtista + "\n" +
                "Id do museu: " + this.idMuseu + "\n" +
                "Url da imagem: " + this.urlImagem + "\n";
    }
}
