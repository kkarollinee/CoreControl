package com.example.kkaro.corecontrol;

/**
 * Created by kkaro on 01/12/2016.
 */

//Classe pronta, não há mais nada a se alterar.

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//cria banco de dados com duas tabelas: Personagem e Item

public class CriaBanco extends SQLiteOpenHelper {
     static final String NOME_BANCO = "banco.db";
     static final String TABELA1 = "personagem";
     static final String ID = "_id";
     static final String CHARACTERNAME = "charactername";
     static final String CLASSE = "classe";
     static final String SPEC = "spec";

     static final String TABELA2 = "item";
     static final String CHARACTER = "character";
     static final String ITEMNAME = "itemname";
     static final String BOSSNAME = "bossname";
     static final String SLOT = "slot";

     static final int VERSAO = 1;

    public CriaBanco(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ TABELA1 +" ("
                + ID + " integer primary key autoincrement,"
                + CHARACTERNAME + " text,"
                + CLASSE + " text,"
                + SPEC + " text"
                +")";

        String sql2 = "CREATE TABLE "+ TABELA2 +" ("
                + ID + " integer primary key autoincrement,"
                + CHARACTER + " text,"
                + ITEMNAME + " text,"
                + BOSSNAME + " text,"
                + SLOT + " text"
                +")";

        db.execSQL(sql);
        db.execSQL(sql2);

    }

    @Override
    //Havendo uma atualização no app, mudar esta função para altertable
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABELA1 + TABELA2);
        onCreate(db);
    }
}