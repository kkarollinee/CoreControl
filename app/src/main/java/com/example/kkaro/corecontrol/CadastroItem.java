package com.example.kkaro.corecontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kkaro on 01/12/2016.
 */

public class CadastroItem extends AppCompatActivity {

    private EditText nomeitem;
    private Spinner spnpersonagem, boss, slot;
    private Button Cancelar, Cadastrar;

    private List<String> nomes = new ArrayList<String>();

    private Map<String,String> dados = new HashMap<String, String>();

    private ControllerBanco crud;

    //Recolhe os valores digitados nos campos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_item);


        nomeitem = (EditText) findViewById(R.id.nomeitem);

        //Pega a pesquisa do ControllerBanco que retorna todos os personagens cadastrados para inflar o spinner
        crud = new ControllerBanco(getBaseContext());
        nomes= crud.getPersonagens();

        //infla o spinner com os dados do banco
        spnpersonagem = (Spinner) findViewById(R.id.personagemName);
        //Cria um ArrayAdapter usando um padrão de layout da classe R do android, passando o ArrayList nomes
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nomes);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnpersonagem.setAdapter(spinnerArrayAdapter);

        //Spiners simples
        boss = (Spinner) findViewById(R.id.boss);
        ArrayAdapter listaBoss = ArrayAdapter.createFromResource(this, R.array.boss, android.R.layout.simple_spinner_item);
        boss.setAdapter(listaBoss);

        slot = (Spinner) findViewById(R.id.slot);
        ArrayAdapter listaSlot = ArrayAdapter.createFromResource(this, R.array.slot, android.R.layout.simple_spinner_item);
        slot.setAdapter(listaSlot);



        //Pega os valores das variáveis, converte para string e salva no banco
        Cadastrar = (Button) findViewById(R.id.buttonCadastrar);
        Cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControllerBanco crud = new ControllerBanco(getBaseContext());

                dados.put("character", spnpersonagem.getSelectedItem().toString());
                dados.put("itemname", nomeitem.getText().toString());
                dados.put("bossname", boss.getSelectedItem().toString());
                dados.put("slot", slot.getSelectedItem().toString());

                String resultado = crud.insertItem(dados);
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                //chama um search do banco para verificar os dados que foram inseridos
                Intent intent = new Intent(getBaseContext(), PesquisarItem.class);
                startActivity(intent);
            }
        });

        Cancelar= (Button)findViewById(R.id.buttonCancelar);

        Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroItem.this,Principal.class);
                startActivity(intent);
            }
        });




    }
}