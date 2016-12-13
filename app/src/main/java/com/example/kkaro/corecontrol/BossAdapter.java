package com.example.kkaro.corecontrol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kkaro on 10/12/2016.
 */

public class BossAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private ArrayList<Boss> itens;

    public BossAdapter(Context context, ArrayList<Boss> itens) {
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
    public Boss getItem(int position) {
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
        Boss item = itens.get(position);

        //infla o layout para podermos preencher os dados
        view = mInflater.inflate(R.layout.activity_search_line_porchefe, null);

        //Define o texto da textview
        ((TextView) view.findViewById(R.id.itemUm)).setText(item.getitemname());
        ((TextView) view.findViewById(R.id.itemDois)).setText(item.getcharacter());
        return view;
    }
}