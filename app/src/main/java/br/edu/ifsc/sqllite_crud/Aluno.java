package br.edu.ifsc.sqllite_crud;
public  class Aluno {
    int codigo;
    String nome_UC;

    public Aluno(int codigo, String nome_UC, double nota) {
        this.codigo = codigo;
        this.nome_UC = nome_UC;
        this.nota = nota;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome_UC() {
        return nome_UC;
    }

    public void setNome_UC(String nome_UC) {
        this.nome_UC = nome_UC;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    double nota;
}
