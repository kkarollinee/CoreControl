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
 * Created by kkaro on 09/12/2016.
 */

public class UpdateItem extends Activity {

    private EditText name;
    private Spinner boss, slot;
    private Button cancel, send, deletPersonagem;
    private Cursor cursor;
    private ControllerBanco crud;

    private String code;
    private Map<String,String> dadosItem = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_info);

        code = this.getIntent().getStringExtra("codigo");
        crud = new ControllerBanco(getBaseContext());

        //Salva o que estiver nos campos pára suas variaveis
        name = (EditText)findViewById(R.id.nomeitem);

        boss = (Spinner) findViewById(R.id.boss);
        ArrayAdapter listaEstados = ArrayAdapter.createFromResource(this, R.array.boss, android.R.layout.simple_spinner_item);
        boss.setAdapter(listaEstados);

        slot = (Spinner) findViewById(R.id.slot);
        ArrayAdapter listaCategoria = ArrayAdapter.createFromResource(this, R.array.slot, android.R.layout.simple_spinner_item);
        slot.setAdapter(listaCategoria);

        //Pesquisa o item
        cursor = crud.searchItemById(Integer.parseInt(code));

        name.setText(cursor.getString(cursor.getColumnIndexOrThrow("itemname")));


        //Botão Cancelar
        cancel= (Button)findViewById(R.id.buttonCancelar);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateItem.this,Principal.class);
                startActivity(intent);
            }
        });



        //Botão Salvar
        send = (Button)findViewById(R.id.buttonSalvar);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dadosItem.put("_id", code);
                dadosItem.put("itemname", name.getText().toString());
                dadosItem.put("boss", boss.getSelectedItem().toString());
                dadosItem.put("slot", slot.getSelectedItem().toString());

                String resultado = crud.updateItem(dadosItem);
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UpdateItem.this, PesquisarItem.class);
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
                Intent intent = new Intent(UpdateItem.this, PesquisarItem.class);
                startActivity(intent);
                finish();
            }
        });
    }
}