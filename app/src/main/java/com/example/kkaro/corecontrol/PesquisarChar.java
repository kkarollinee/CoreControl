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
 * Created by kkaro on 01/12/2016.
 */

// Deve conter uma pesquisa apenas dos dados inseriodos de um char para possibilitar edição posteriormente

public class PesquisarChar extends Activity{
    private ListView listView;
    private ArrayList<Personagem> itens;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //carrega o layout onde contem o ListView.
        setContentView(R.layout.activity_search);

        //Criamos nossa lista que preenchera o ListView
        itens = new ArrayList<Personagem>();

        //Iniciando a conexão com o o banco.
        ControllerBanco db = new ControllerBanco(getBaseContext());

        // Carregando os dados do banco (vem da classe ControllerBanco).
        final Cursor cursor = db.searchPersonagem();
        cursor.moveToFirst();



        // Criando a lista de clientes
        while (!cursor.isAfterLast()) {
            String mDrawableName = "wow_icon";
            int resID = getResources().getIdentifier(mDrawableName , "drawable", getPackageName());


            itens.add(new Personagem(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.CHARACTERNAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.CLASSE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.SPEC)), resID));
            cursor.moveToNext();
        }

        //Pega a referencia do ListView
        listView = (ListView)findViewById(R.id.list);

        //Cria o adapter
        listView.setAdapter(new PersonagemAdapter(this, itens));

        // Adicionando o evento em cada linha do listView.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ID));
                Intent intent = new Intent(PesquisarChar.this, UpdatePersonagem.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });
    }

}