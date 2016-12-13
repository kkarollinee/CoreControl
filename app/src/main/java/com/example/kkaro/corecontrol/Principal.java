package com.example.kkaro.corecontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//Classe Principal, contém os três botões que chama as repectivas funções do app

public class Principal extends AppCompatActivity {

    private Button cadastroPersonagem,cadastroItem,search, about;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        cadastroPersonagem = (Button) findViewById(R.id.buttonCadChar);
        cadastroItem = (Button) findViewById(R.id.buttonCadBis);
        search = (Button) findViewById(R.id.buttonSearch);
        about = (Button) findViewById(R.id.buttonabout);

        //Conecta o botão Cadastro Personagem a função da classe CadastroPersonagem
        cadastroPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this,CadastroPersonagem.class);
                startActivity(intent);
            }
        });

        //botão Cadastro de Item
        cadastroItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this,CadastroItem.class);
                startActivity(intent);
            }
        });

        //botão Pesquisar
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Pesquisar.class);
                startActivity(intent);
            }
        });

        //botão Sobre
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), About.class);
                startActivity(intent);
            }
        });


    }
}
