package com.example.moshafapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class SuratAdapter extends BaseAdapter {
    Context context;
    List<BaseSurah> listSurah;

    private View vi;
    private ViewHolder viewHolder;
    private LayoutInflater layoutInflater = null;

    public SuratAdapter(Context context, List<BaseSurah> listSurah) {
        this.context = context;
        this.listSurah = listSurah;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listSurah.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        vi = view;
        //Populate the Listview
        final int pos = position;
        BaseSurah baseSurah = listSurah.get(pos);
        if(vi == null) {
            vi = layoutInflater.inflate(R.layout.list_item_surat, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) vi.findViewById(R.id.nama_surat);
            vi.setTag(viewHolder);
        }else
            viewHolder = (ViewHolder) view.getTag();

        viewHolder.name.setText(baseSurah.getNama());

        return vi;
    }

    public class ViewHolder{
        TextView name;
    }
}
