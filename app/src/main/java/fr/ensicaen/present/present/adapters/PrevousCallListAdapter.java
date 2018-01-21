package fr.ensicaen.present.present.adapters;
/* 
 *
 * ENSICAEN
 * 6 Boulevard Marechal Juin
 * F-14050 Caen Cedex
 *
 * This file is owned by The Présent ! Team and created by
 * Quentin DEBROISE, Julian EASTERLY, Jeanne LEYMARIE, Pierre NICOL, and Coline SMAGGHE.
 * No portion of this document may be reproduced, copied
 * or revised without written permission of the authors.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.models.PreviousCallModel;

/*
 * @author The Présent ! Team <easterly@ecole.ensicaen.fr>
 * @version 0.0.1 - 21/01/18
 * @TODO refactor with other adapter ?
 */
public class PrevousCallListAdapter extends BaseAdapter{
    private final int _layout;
    private ArrayList<PreviousCallModel> _previousCalls;
    private LayoutInflater layoutInflater;

    public PrevousCallListAdapter(Context aContext, ArrayList<PreviousCallModel> previousCalls, int layout) {
        _previousCalls = previousCalls;
        layoutInflater = LayoutInflater.from(aContext);
        _layout = layout;
    }

    @Override
    public int getCount() {
        return _previousCalls.size();
    }

    @Override
    public Object getItem(int position) {
        return _previousCalls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(_layout, null);
            holder = new ViewHolder();
            holder._callEventName = convertView.findViewById(R.id.prev_call_event);
            holder._callEventDate = convertView.findViewById(R.id.prev_call_date);
            holder._callEventCode = convertView.findViewById(R.id.prev_call_code);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder._callEventName.setText(_previousCalls.get(position).getEvent());
        holder._callEventDate.setText(_previousCalls.get(position).getDate());
        holder._callEventCode.setText(_previousCalls.get(position).getCode());
        return convertView;
    }

    static class ViewHolder {
        TextView _callEventName;
        TextView _callEventDate;
        TextView _callEventCode;
    }
}
