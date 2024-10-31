package org.example.leontisservlet.model;

import java.sql.Date;

public class Museu {
    private int id;
    private String nmMuseu;
    private String descMuseu;
    private String rua;
    private String estado;
    private String cidade;
    private String pontoReferencia;
    private String cep;
    private Date dtInauguracao;
    private String nrTelMuseu;
    private String urlImagem;
    private String cnpj;
    private int id_adm_museu;

    public Museu(int id, String nmMuseu, String descMuseu, String rua, String estado, String cidade, String pontoReferencia, String cep, Date dtInauguracao, String nrTelMuseu, String urlImagem, String cnpj, int id_adm_museu){
        this.id = id;
        this.nmMuseu = nmMuseu;
        this.descMuseu = descMuseu;
        this.rua = rua;
        this.estado = estado;
        this.cidade = cidade;
        this.pontoReferencia = pontoReferencia;
        this.cep = cep;
        this.dtInauguracao = dtInauguracao;
        this.nrTelMuseu = nrTelMuseu;
        this.urlImagem = urlImagem;
        this.cnpj = cnpj;
        this.id_adm_museu = id_adm_museu;
    }

    public int getId() {
        return id;
    }

    public String getNmMuseu() {
        return nmMuseu;
    }

    public String getDescMuseu() {
        return descMuseu;
    }

    public String getRua() {
        return rua;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public String getCep() {
        return cep;
    }

    public Date getDtInauguracao() {
        return dtInauguracao;
    }

    public String getNrTelMuseu() {
        return nrTelMuseu;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public String getCnpj() {
        return cnpj;
    }

    public int getId_adm_museu() {
        return id_adm_museu;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNrTelMuseu(String nrTelMuseu) {
        this.nrTelMuseu = nrTelMuseu;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    @Override
    public String toString() {
        return "id: " + this.id +
                "\n nm_museu: " + this.nmMuseu +
                "\n desc_museu: " + this.descMuseu +
                "\n estado: " + this.estado +
                "";
    }
}
