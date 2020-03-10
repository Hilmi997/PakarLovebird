package com.example.hilmi.sistempakar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hilmi.sistempakar.R;
import com.example.hilmi.sistempakar.models.AkunListModels;
import com.example.hilmi.sistempakar.models.LogKonsultasiModels;

import java.util.ArrayList;

/**
 * Created by User on 12/01/2020.
 */

public class LogListKonsultasiAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<LogKonsultasiModels> recordList;

    public LogListKonsultasiAdapter(Context context, int layout, ArrayList<LogKonsultasiModels> recordList) {
        this.context = context;
        this.layout = layout;
        this.recordList = recordList;
    }

    @Override
    public int getCount() {
        return recordList.size();
    }

    @Override
    public Object getItem(int i) {
        return recordList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        //        ImageView imageView;
        TextView ID,resultPenyakit, resultGejala, resultSolusi;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        LogListKonsultasiAdapter.ViewHolder holder = new LogListKonsultasiAdapter.ViewHolder();

        if (row==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.ID = (TextView) row.findViewById(R.id.tv_custom_ID_res_log);
            holder.resultPenyakit = (TextView) row.findViewById(R.id.result_log_penyakit);
            holder.resultGejala = (TextView) row.findViewById(R.id.result_log_gejala);
            holder.resultSolusi = (TextView) row.findViewById(R.id.result_log_solusi);

            row.setTag(holder);
        }
        else {
            holder = (LogListKonsultasiAdapter.ViewHolder)row.getTag();
        }

        LogKonsultasiModels model = recordList.get(i);

        holder.ID.setText(model.getResultID());
        holder.resultPenyakit.setText(model.getResultPenyakit());
        holder.resultGejala.setText(model.getResultGejala());
        holder.resultSolusi.setText(model.getResultSolusi());



        return row;
    }
}