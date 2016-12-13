package com.example.kkaro.corecontrol;

/**
 * Created by kkaro on 10/12/2016.
 */

//Construtor do CharacterAdapter

public class Character {

    private String itemname, bossname;



    public Character(String itemname, String bossname) {
        this.itemname = itemname;
        this.bossname = bossname;


    }


    public String getitemname() {return itemname;}

    public void setitemname(String itemname) {
        this.itemname = itemname;
    }

    public String getbossname() {return bossname; }

    public void setbossname(String bossname) {
        this.bossname = bossname;
    }


}