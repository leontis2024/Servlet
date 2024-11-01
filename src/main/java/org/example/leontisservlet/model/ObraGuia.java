package org.example.leontisservlet.model;

public class ObraGuia {
//   ATRIBUTOS DA CLASSE
    private int    nrOrdem;
    private int    idGuia;
    private int    idObra;
    private int    id;
    private String desc_localizacao;

//    MÉTODO CONSTRUTOR
    public ObraGuia(int nrOrdem, int idGuia, int idObra, int id, String desc_localizacao) {
        this.nrOrdem          = nrOrdem;
        this.idGuia           = idGuia;
        this.idObra           = idObra;
        this.id               = id;
        this.desc_localizacao = desc_localizacao;
    }

//    MÉTODOS GETTERS E SETTERS
    public int getNrOrdem() {
        return nrOrdem;
    }

    public int getIdGuia() {
        return idGuia;
    }

    public int getIdObra() {
        return idObra;
    }

    public int getId() {
        return id;
    }

    public String getDesc_localizacao() {
        return desc_localizacao;
    }

//    MÉTODO TOSTRING
    public String toString() {
        return "Número da ordem: " + this.nrOrdem + "\n" +
                "Id do guia: " + this.idGuia + "\n" +
                "Id da obra: " + this.idObra + "\n" +
                "Id do obra-guia: " + this.id + "\n" +
                "Descricao Localizacao: "+this.desc_localizacao+"\n";
    }
}
