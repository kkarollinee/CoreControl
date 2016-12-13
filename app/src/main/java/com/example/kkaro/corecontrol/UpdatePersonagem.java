package com.example.kkaro.corecontrol;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kkaro on 08/12/2016.
 */

public class UpdatePersonagem extends Activity {

    private EditText name;
    private Spinner classe, spec;
    private Button cancel, send, deletPersonagem;
    private Cursor cursor;
    private ControllerBanco crud;

    private String code;
    private Map<String,String> dadosPersonagem = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personagem_info);

        code = this.getIntent().getStringExtra("codigo");
        crud = new ControllerBanco(getBaseContext());

        //Salva o que estiver nos campos pára suas variaveis
        name = (EditText)findViewById(R.id.nomechar);

        classe = (Spinner) findViewById(R.id.classe);
        ArrayAdapter listaEstados = ArrayAdapter.createFromResource(this, R.array.role, android.R.layout.simple_spinner_item);
        classe.setAdapter(listaEstados);

        spec = (Spinner) findViewById(R.id.spec);
        ArrayAdapter listaCategoria = ArrayAdapter.createFromResource(this, R.array.roleclass, android.R.layout.simple_spinner_item);
        spec.setAdapter(listaCategoria);

        //Pesquisa o personagem
        cursor = crud.searchPersonagemById(Integer.parseInt(code));

        name.setText(cursor.getString(cursor.getColumnIndexOrThrow("charactername")));


        //Botão Cancelar
        cancel= (Button)findViewById(R.id.buttonCancelar);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdatePersonagem.this,Principal.class);
                startActivity(intent);
            }
        });



        //Botão Salvar
        send = (Button)findViewById(R.id.buttonSalvar);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dadosPersonagem.put("_id", code);
                dadosPersonagem.put("charactername", name.getText().toString());
                dadosPersonagem.put("classe", classe.getSelectedItem().toString());
                dadosPersonagem.put("spec", spec.getSelectedItem().toString());

                String resultado = crud.updatePersonagem(dadosPersonagem);
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UpdatePersonagem.this, PesquisarChar.class);
                startActivity(intent);
                finish();
            }
        });

        //Botão Excluir
        deletPersonagem = (Button)findViewById(R.id.buttonDelete);
        deletPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultado = crud.deleteClient(Integer.parseInt(code));
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UpdatePersonagem.this, PesquisarChar.class);
                startActivity(intent);
                finish();
            }
        });
    }
}