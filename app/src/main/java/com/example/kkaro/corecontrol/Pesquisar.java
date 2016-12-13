package com.example.kkaro.corecontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kkaro on 01/12/2016.
 */

public class Pesquisar extends AppCompatActivity {
    private Button searchChar,searchBoss;
    private Spinner spnpersonagem, boss;
    private ControllerBanco crud; //declara o banco que será usado para inflar os spinner
    private List<String> nomes = new ArrayList<String>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        searchBoss = (Button) findViewById(R.id.button_searchboss);
        searchChar = (Button) findViewById(R.id.button_searchchar);

        crud = new ControllerBanco(getBaseContext());
        nomes= crud.getPersonagens(); //retorna uma lista de todos os personagens cadastrados no banco
        //infla o spinner com os dados do banco
        spnpersonagem = (Spinner) findViewById(R.id.Searchchar);
        //Cria um ArrayAdapter usando um padrão de layout da classe R do android, passando o ArrayList nomes
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nomes);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnpersonagem.setAdapter(spinnerArrayAdapter);

        //Spiners simples
        boss = (Spinner) findViewById(R.id.SearchBoss);
        ArrayAdapter listaBoss = ArrayAdapter.createFromResource(this, R.array.boss, android.R.layout.simple_spinner_item);
        boss.setAdapter(listaBoss);



        //chama a classe responsável por filtrar por char
        searchChar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(Pesquisar.this, FiltrarPorChar.class);
                Bundle bundle = new Bundle();
                // bundle.putSerializable("bossSelecionado", boss.getSelectedItem().toString());
                bundle.putSerializable("personagemSelecionado", spnpersonagem.getSelectedItem().toString());
                it.putExtras(bundle);
                startActivity(it);

            }
        });

        //Chama a classe responsável por filtrar por boss

        searchBoss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(Pesquisar.this, FiltrarPorBoss.class);
                Bundle bundle = new Bundle();
                // bundle.putSerializable("bossSelecionado", boss.getSelectedItem().toString());
                bundle.putSerializable("bossSelecionado", boss.getSelectedItem().toString());
                it.putExtras(bundle);
                startActivity(it);

            }
        });





    }
}
