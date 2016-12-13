package com.example.kkaro.corecontrol;

/**
 * Created by kkaro on 10/12/2016.
 */

public class Boss {

    private String itemname, character;



    public Boss(String itemname, String character) {
        this.itemname = itemname;
        this.character = character;


    }


    public String getitemname() {return itemname;}

    public void setitemname(String itemname) {
        this.itemname = itemname;
    }

    public String getcharacter() {return character; }

    public void setcharacter(String character) {
        this.character = character;
    }


}