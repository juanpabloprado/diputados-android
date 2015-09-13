package com.juanpabloprado.diputados;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    private List<ParseObject> mDiputados;

    public DiputadoAdapter(MainActivity activity, List<ParseObject> diputados) {
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
        diputadoHolder.partyView.setText(diputado.getString(ParseConstants.KEY_PARTY));
    }

    @Override
    public int getItemCount() {
        return mDiputados.size();
    }

    public class DiputadoHolder extends RecyclerView.ViewHolder {
        public TextView nameView;
        public TextView partyView;

        public DiputadoHolder(View itemView) {
            super(itemView);
            nameView = (TextView) itemView.findViewById(R.id.diputado_name);
            partyView = (TextView) itemView.findViewById(R.id.diputado_party);
        }
    }

    public void refill(List<ParseObject> diputados) {
        mDiputados.clear();
        mDiputados.addAll(diputados);
        notifyDataSetChanged();
        mActivity.showList();
    }
}
