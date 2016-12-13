package com.example.kkaro.corecontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kkaro on 01/12/2016.
 */


public class CadastroPersonagem extends AppCompatActivity {

    private EditText nomechar;
    private Spinner classe, spec;
    private Button Cancelar, Cadastrar;

    private Map<String,String> dados = new HashMap<String, String>();

    //Recolhe os valores digitados nos campos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_char);


        nomechar = (EditText) findViewById(R.id.nomechar);

        classe = (Spinner) findViewById(R.id.classe);
        ArrayAdapter listaClasses = ArrayAdapter.createFromResource(this, R.array.roleclass, android.R.layout.simple_spinner_item);
        classe.setAdapter(listaClasses);

        spec = (Spinner) findViewById(R.id.spec);
        ArrayAdapter listaSpec = ArrayAdapter.createFromResource(this, R.array.role, android.R.layout.simple_spinner_item);
        spec.setAdapter(listaSpec);



        //Pega os valores das variáveis, converte para string e salva no banco
        Cadastrar = (Button) findViewById(R.id.buttonCadastrar);
        Cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControllerBanco crud = new ControllerBanco(getBaseContext());

                dados.put("charactername", nomechar.getText().toString());
                dados.put("classe", classe.getSelectedItem().toString());
                dados.put("spec", spec.getSelectedItem().toString());

                String resultado = crud.insertPersonagem(dados);
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                //chama um search do banco para verificar os dados que foram inseridos
                Intent intent = new Intent(getBaseContext(), PesquisarChar.class);
                startActivity(intent);
            }
        });

        Cancelar= (Button)findViewById(R.id.buttonCancelar);

        Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroPersonagem.this,Principal.class);
                startActivity(intent);
            }
        });




    }
}