package com.example.kkaro.corecontrol;

/**
 * Created by kkaro on 09/12/2016.
 */

public class Item{

        private String nome, personagem, boss, slot;


        public Item() {
        }

        public Item(String nome, String personagem, String boss, String slot) {
            this.nome = nome;
            this.personagem = personagem;
            this.boss = boss;
            this.slot = slot;

        }


        public String getNome() {return nome;}

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getPersonagem() {return personagem; }

        public void setPersonagem(String personagem) {
        this.personagem = personagem;
    }

        public String getBoss() {
            return boss;
        }

        public void setBoss(String boss) {
            this.boss = boss;
        }

        public String getSlot() {return slot; }

        public void setSlot(String slot) {
            this.slot = slot;
        }
}