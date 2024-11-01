package org.example.leontisservlet.model;

public class MuseuAdm {
//   ATRIBUTOS DA CLASSE
    private int    id;
    private String emailAdm;
    private String senhaAdm;

//    MÉTODO CONSTRUTOR
    public MuseuAdm(int id, String emailAdm, String senhaAdm){
        this.id       = id;
        this.emailAdm = emailAdm;
        this.senhaAdm = senhaAdm;
    }

//    MÉTODOS GETTERS E SETTERS
    public int getId() {
        return this.id;
    }
    public String getEmailAdm() {
        return this.emailAdm;
    }
    public String getSenhaAdm() {
        return this.senhaAdm;
    }


//    MÉTODO TOSTRING
    public String toString(){
        return "Id: "+this.id+"\n" +
                "Email:"+this.emailAdm+"\n" +
                "Senha:"+this.senhaAdm;
    }
}
