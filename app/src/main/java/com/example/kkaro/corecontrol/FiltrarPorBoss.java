package com.example.kkaro.corecontrol;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by kkaro on 10/12/2016.
 */

public class FiltrarPorBoss extends Activity {
    private ListView listView;
    private ArrayList<Boss> itens;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //carrega o layout onde contem o ListView.
        setContentView(R.layout.activity_search);

        //Criamos nossa lista que preenchera o ListView
        itens = new ArrayList<Boss>();

        //Iniciando a conexão com o o banco.
        ControllerBanco db = new ControllerBanco(getBaseContext());

        // Bundle que contém infornmação do spiner pesquisar por char
        Bundle bundle = getIntent().getExtras();
        String boss = (String) bundle.getSerializable("bossSelecionado");

        final Cursor cursor = db.filtraBoss(boss);
        cursor.moveToFirst();

        // Criando a lista de itens
        while (!cursor.isAfterLast()) {
            itens.add(new Boss(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.CHARACTER)),
                    cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ITEMNAME))));
            cursor.moveToNext();
        }
        //Pega a referencia do ListView
        listView = (ListView) findViewById(R.id.list);

        //Cria o adapter
        listView.setAdapter(new BossAdapter(this, itens));


    }
}