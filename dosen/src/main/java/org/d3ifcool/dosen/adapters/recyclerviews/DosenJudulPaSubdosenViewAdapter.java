package org.d3ifcool.dosen.adapters.recyclerviews;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.activities.details.DosenJudulPaSubdosenDetailActivity;
import org.d3ifcool.service.models.Judul;

import java.util.ArrayList;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 26/01/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */
public class DosenJudulPaSubdosenViewAdapter extends RecyclerView.Adapter<DosenJudulPaSubdosenViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Judul> data;
    private int layoutType;

    public DosenJudulPaSubdosenViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<Judul> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void setLayoutType(int mLayoutType){
        this.layoutType = mLayoutType;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView judul, kategori;

        public ViewHolder(View itemView) {
            super(itemView);
            // -------------------------------------------------------------------------------------
            judul = itemView.findViewById(R.id.ctn_dsn_judul_dsn_textview_judul);
            kategori = itemView.findViewById(R.id.ctn_dsn_judul_dsn_textview_kategori);
            // -------------------------------------------------------------------------------------
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.judul.setText(data.get(position).getJudul());
        holder.kategori.setText(data.get(position).getKategori_nama());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentData = new Intent(context, DosenJudulPaSubdosenDetailActivity.class);
                Judul parcelinfo = data.get(position);
                intentData.putExtra(DosenJudulPaSubdosenDetailActivity.EXTRA_INFORMASI, parcelinfo);
                context.startActivity(intentData);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutType, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}