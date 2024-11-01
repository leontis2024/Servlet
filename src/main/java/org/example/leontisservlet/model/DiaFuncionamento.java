package org.example.leontisservlet.model;

public class DiaFuncionamento {
//   ATRIBUTOS DA CLASSE
    private int id;
    private String hrInicio;
    private String hrTermino;
    private double prDiaFuncionamento;
    private String diaSemana;
    private int idMuseu;

//    MÉTODO CONSTRUTOR
    public DiaFuncionamento(int id, String hrInicio, String hrTermino, double prDiaFuncionamento, String diaSemana, int idMuseu) {
        this.id = id;
        this.hrInicio = hrInicio;
        this.hrTermino = hrTermino;
        this.prDiaFuncionamento = prDiaFuncionamento;
        this.diaSemana = diaSemana;
        this.idMuseu = idMuseu;
    }

//    MÉTODOS GETTERS E SETTERS
    public int getId() {
        return id;
    }

    public String getHrInicio() {
        return hrInicio;
    }

    public String getHrTermino() {
        return hrTermino;
    }

    public double getPrDiaFuncionamento() {
        return prDiaFuncionamento;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public int getIdMuseu() {
        return idMuseu;
    }

    public void setIdMuseu(int idMuseu) {
        this.idMuseu = idMuseu;
    }

//    MÉTODO TOSTRING
    @Override
    public String toString() {
        return
                "id: " + id +
                ", hrInicio: " + hrInicio +
                ", hrTermino: " + hrTermino +
                ", prDiaFuncionamento: " + prDiaFuncionamento +
                ", diaSemana: " + diaSemana +
                ", idMuseu: " + idMuseu ;

    }

}
