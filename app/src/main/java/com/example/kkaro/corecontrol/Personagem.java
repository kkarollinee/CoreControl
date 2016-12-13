package com.example.kkaro.corecontrol;

/**
 * Created by kkaro on 08/12/2016.
 */

public class Personagem {


    private String nome, classe, spec;
    private int iconeRid;

    public Personagem() {
    }

    public Personagem(String nome, String classe, String spec, int iconeRid) {
        this.nome = nome;
        this.classe = classe;
        this.spec = spec;
        this.iconeRid = iconeRid;
    }

    public int getIconeRid() {
        return iconeRid;
    }

    public void setIconeRid(int iconeRid) {
        this.iconeRid = iconeRid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
}
