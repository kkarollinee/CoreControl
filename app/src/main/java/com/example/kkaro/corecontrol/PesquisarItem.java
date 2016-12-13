package com.example.kkaro.corecontrol;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by kkaro on 09/12/2016.
 */

public class PesquisarItem extends Activity {
    private ListView listView;
    private ArrayList<Item> itens;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //carrega o layout onde contem o ListView.
        setContentView(R.layout.activity_search2);

        //Criamos nossa lista que preenchera o ListView
        itens = new ArrayList<Item>();

        //Iniciando a conexão com o o banco.
        ControllerBanco db = new ControllerBanco(getBaseContext());

        // Carregando os dados do banco (vem da classe ControllerBanco).
        final Cursor cursor = db.searchItem();
        cursor.moveToFirst();

        // Criando a lista de itens
        while (!cursor.isAfterLast()) {
            itens.add(new Item(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ITEMNAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.BOSSNAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.CHARACTER)),
                    cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.SLOT))));
            cursor.moveToNext();
        }

        //Pega a referencia do ListView
        listView = (ListView)findViewById(R.id.list);

        //Cria o adapter
        listView.setAdapter(new ItemAdapter(this, itens));

        // Adicionando o evento em cada linha do listView.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ID));
                Intent intent = new Intent(PesquisarItem.this, UpdateItem.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });
    }

}