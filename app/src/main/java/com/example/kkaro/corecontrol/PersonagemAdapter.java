package com.example.kkaro.corecontrol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kkaro on 08/12/2016.
 */

public class PersonagemAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private ArrayList<Personagem> itens;

    public PersonagemAdapter(Context context, ArrayList<Personagem> itens) {
        this.itens = itens;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * Retorna a quantidade de itens
     *
     * @return
     */
    public int getCount() {
        return itens.size();
    }

    /**
     * Retorna o item de acordo com a posicao dele na tela.
     *
     * @param position
     * @return
     */
    public Personagem getItem(int position) {
        return itens.get(position);
    }

    /**
     * Sem implementação
     *
     * @param position
     * @return
     */
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {

        //Pega o item de acordo com a posção.
        Personagem item = itens.get(position);

        //infla o layout para podermos preencher os dados
        view = mInflater.inflate(R.layout.activity_search_line, null);

        //Atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado ao item e defino as informações.
        ((TextView) view.findViewById(R.id.personagemName)).setText(item.getNome());
        ((TextView) view.findViewById(R.id.classe)).setText(item.getClasse());
        ((TextView) view.findViewById(R.id.spec)).setText(item.getSpec());
        ((ImageView) view.findViewById(R.id.photoclient)).setImageResource(item.getIconeRid());
        return view;
    }
}