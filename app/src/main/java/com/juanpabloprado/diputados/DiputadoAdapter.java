package com.juanpabloprado.diputados;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.juanpabloprado.diputados.model.Diputado;
import com.juanpabloprado.diputados.ui.MainActivity;
import com.juanpabloprado.diputados.utils.ParseConstants;
import com.parse.ParseObject;

import java.util.List;


/**
 * Created by Juan on 9/13/2015.
 */
public class DiputadoAdapter extends RecyclerView.Adapter<DiputadoAdapter.DiputadoHolder> {

    private LayoutInflater mInflater;
    private MainActivity mActivity;
    private List<Diputado> mDiputados;

    public DiputadoAdapter(MainActivity activity, List<Diputado> diputados) {
        mActivity = activity;
        mInflater = LayoutInflater.from(activity);
        mDiputados = diputados;
    }

    @Override
    public DiputadoHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new DiputadoHolder(mInflater.inflate(R.layout.layout_diputado, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(DiputadoHolder diputadoHolder, int i) {
        final ParseObject diputado = mDiputados.get(i);
        diputadoHolder.nameView.setText(diputado.getString(ParseConstants.KEY_NAME));
        diputadoHolder.entidadView.setText(diputado.getString(ParseConstants.KEY_ENTIDAD));
        String party = diputado.getString(ParseConstants.KEY_PARTY);
        diputadoHolder.partyView.setText(party);
        if(party.equals("PRI")) {
            diputadoHolder.partyImageView.setImageResource(R.drawable.pri01);
        }
        if(party.equals("PAN")) {
            diputadoHolder.partyImageView.setImageResource(R.drawable.pan);
        }
        if(party.equals("PRD")) {
            diputadoHolder.partyImageView.setImageResource(R.drawable.prd01);
        }
        if(party.equals("PVEM")) {
            diputadoHolder.partyImageView.setImageResource(R.drawable.vrd);
        }
        if(party.equals("MOVCI")) {
            diputadoHolder.partyImageView.setImageResource(R.drawable.movci);
        }
        if(party.equals("PT")) {
            diputadoHolder.partyImageView.setImageResource(R.drawable.pt);
        }
        if(party.equals("PANAL")) {
            diputadoHolder.partyImageView.setImageResource(R.drawable.ali);
        }
    }

    @Override
    public int getItemCount() {
        return mDiputados.size();
    }

    public class DiputadoHolder extends RecyclerView.ViewHolder {
        public TextView nameView;
        public TextView partyView;
        public TextView entidadView;
        public ImageView partyImageView;

        public DiputadoHolder(View itemView) {
            super(itemView);
            nameView = (TextView) itemView.findViewById(R.id.diputado_name);
            partyView = (TextView) itemView.findViewById(R.id.diputado_party);
            entidadView = (TextView) itemView.findViewById(R.id.diputado_entidad);
            partyImageView = (ImageView) itemView.findViewById(R.id.party_image);
        }
    }

    public void refill(List<Diputado> diputados) {
        mDiputados.clear();
        mDiputados.addAll(diputados);
        notifyDataSetChanged();
        mActivity.showList();
    }
}
