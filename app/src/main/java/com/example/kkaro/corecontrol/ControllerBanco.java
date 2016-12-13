package com.example.kkaro.corecontrol;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by kkaro on 01/12/2016.
 */

//contém as funções de inserção, delete  e alteração do banco

public class ControllerBanco {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public ControllerBanco(Context context){
        banco = new CriaBanco(context);
    }

     //Inserção de Personagem no banco
    public String insertPersonagem(Map<String,String> dados){

        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("charactername", dados.get("charactername"));
        valores.put("classe", dados.get("classe"));
        valores.put("spec", dados.get("spec"));


        resultado = db.insert("personagem", null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro!!";
        else
            return "Registro inserido com sucesso!!";
    }

    //Inserção de Item no banco
    public String insertItem(Map<String,String> dados){

        ContentValues valores;
        long resultado;


        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("itemname", dados.get("itemname"));
        valores.put("character", dados.get("character"));
        valores.put("bossname", dados.get("bossname"));
        valores.put("slot", dados.get("slot"));


        resultado = db.insert("item", null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro!!";
        else
            return "Registro inserido com sucesso!!";
    }
    //Pesquisa o personagem no banco
    public Cursor searchPersonagem(){
        Cursor cursor;
        String[] campos = {"_id", "charactername", "classe", "spec"};
        db = banco.getReadableDatabase();
        cursor = db.query("personagem", campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    //Pesquisa o Item no banco
    public Cursor searchItem(){
        Cursor cursor;
        String[] campos = {"_id", "itemname","character", "bossname", "slot"};
        db = banco.getReadableDatabase();
        cursor = db.query("item", campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    //Pesquisa personagem no banco pela id
    public Cursor searchPersonagemById(int id){
        Cursor cursor;
        String[] campos =  {"_id", "charactername", "classe", "spec"};
        String where = "_id" + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query("personagem", campos, where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    //Pesquisa item no banco pela id
    public Cursor searchItemById(int id){
        Cursor cursor;
        String[] campos =  {"_id", "itemname","character", "bossname", "slot"};
        String where = "_id" + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query("item", campos, where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public String updatePersonagem(Map<String,String> dados){ContentValues valores;

        long resultado;
        String where;
        db = banco.getWritableDatabase();
        where = "_id" + "=" + dados.get("_id");

        valores = new ContentValues();
        valores.put("charactername", dados.get("charactername"));
        valores.put("classe", dados.get("classe"));
        valores.put("spec", dados.get("spec"));

        resultado = db.update("personagem", valores, where, null);
        db.close();

        if (resultado ==-1)
            return "Erro ao alterar registro!!";
        else
            return "Registro alterado com sucesso!!";
    }

    public String updateItem(Map<String,String> dados){ContentValues valores;

        long resultado;
        String where;
        db = banco.getWritableDatabase();
        where = "_id" + "=" + dados.get("_id");

        valores = new ContentValues();
        valores.put("itemname", dados.get("itemname"));
        valores.put("bossname", dados.get("bossname"));
        valores.put("slot", dados.get("slot"));

        resultado = db.update("item", valores, where, null);
        db.close();

        if (resultado ==-1)
            return "Erro ao alterar registro!!";
        else
            return "Registro alterado com sucesso!!";
    }

    public String deleteClient(int id){

        long resultado;
        String where = "_id" + "=" + id;
        db = banco.getReadableDatabase();
        resultado = db.delete("personagem", where, null);
        db.close();

        if (resultado ==-1)
            return "Erro ao apagar registro!!";
        else
            return "Registro apagado com sucesso!!";
    }

    //deleta item
    public String deleteItem(int id){

        long resultado;
        String where = "_id" + "=" + id;
        db = banco.getReadableDatabase();
        resultado = db.delete("item", where, null);
        db.close();

        if (resultado ==-1)
            return "Erro ao apagar registro!!";
        else
            return "Registro apagado com sucesso!!";
    }

    public Cursor filtraChar (String personagem){

        Cursor cursor;
        String[] campos =  {"itemname", "bossname"};
        String where = "character" + "=" + "'"+ personagem + "'";
        db = banco.getReadableDatabase();
        cursor = db.query("item", campos, where, null, null, null, null, null); // tableName, tableColumns, whereClause, whereArgs, groupBy, having, orderBy

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;

    }
    public Cursor filtraBoss (String boss){

        Cursor cursor;
        String[] campos =  {"itemname", "character"};
        String where = "bossname" + "=" + "'"+ boss + "'";
        db = banco.getReadableDatabase();
        cursor = db.query("item", campos, where, null, null, null, null, null); // tableName, tableColumns, whereClause, whereArgs, groupBy, having, orderBy

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;

    }

    // Pesquisa que retorna um array contendo todos os personagens da tabela de personagem
    public List<String> getPersonagens() {
        List<String> names = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + CriaBanco.TABELA1;

        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            int indiceDaColuna = cursor.getColumnIndex("charactername");

            do {
                names.add(cursor.getString(indiceDaColuna));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return names;
    }




}
