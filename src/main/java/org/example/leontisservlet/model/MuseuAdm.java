package org.example.leontisservlet.model;

public class MuseuAdm {
//    Atributos da classe
    private int    id;
    private String emailAdm;
    private String senhaAdm;
//    Método construtor
    public MuseuAdm(int id, String emailAdm, String senhaAdm){
        this.id       = id;
        this.emailAdm = emailAdm;
        this.senhaAdm = senhaAdm;
    }

//    Métodos getters

    public int getId() {
        return this.id;
    }
    public String getEmailAdm() {
        return this.emailAdm;
    }
    public String getSenhaAdm() {
        return this.senhaAdm;
    }


//    Método toString
    public String toString(){
        return "Id: "+this.id+"\n" +
                "Email:"+this.emailAdm+"\n" +
                "Senha:"+this.senhaAdm;
    }
}
