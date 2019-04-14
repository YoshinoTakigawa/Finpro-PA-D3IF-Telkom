package org.d3ifcool.superuser.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.service.models.Judul;
import org.d3ifcool.service.models.KategoriJudul;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.activities.details.KoorJudulPaArsipDetailActivity;
import org.d3ifcool.superuser.activities.details.KoorJudulPaSubdosenDetailActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KoorJudulPaArsipViewAdapter  extends RecyclerView.Adapter<KoorJudulPaArsipViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Judul> judul;

    public KoorJudulPaArsipViewAdapter(Context context) {
        this.context = context;
    }

    public void additem(ArrayList<Judul> juduls){
        this.judul = juduls;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView judulpa, kategori;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            judulpa = itemView.findViewById(R.id.ctn_koor_judul_dsn_textview_judul);
            kategori = itemView.findViewById(R.id.ctn_koor_judul_dsn_textview_kategori);
        }
    }

    @NonNull
    @Override
    public KoorJudulPaArsipViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_item_koor_judul, parent, false);
        return new KoorJudulPaArsipViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KoorJudulPaArsipViewAdapter.ViewHolder holder, final int position) {
        holder.judulpa.setText(judul.get(position).getJudul());
        holder.kategori.setText(judul.get(position).getKategori_nama());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KoorJudulPaArsipDetailActivity.class);
                Judul parcelJudul = judul.get(position);
                intent.putExtra(KoorJudulPaArsipDetailActivity.EXTRA_JUDUL, parcelJudul);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return judul.size();
    }
}