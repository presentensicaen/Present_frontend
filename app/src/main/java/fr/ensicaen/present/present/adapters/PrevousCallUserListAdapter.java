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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.models.PreviousCallModel;
import fr.ensicaen.present.present.models.PreviousCallUserModel;
import fr.ensicaen.present.present.utils.imagetools.RoundImage;

/*
 * @author The Présent ! Team <easterly@ecole.ensicaen.fr>
 * @version 0.0.1 - 21/01/18
 */
public class PrevousCallUserListAdapter extends BaseAdapter{
    private final int _layout;
    private ArrayList<PreviousCallUserModel> _users;
    private LayoutInflater layoutInflater;
    private Context _context;

    public PrevousCallUserListAdapter(Context aContext, ArrayList<PreviousCallUserModel> users, int layout ) {
        _users = users;
        _context = aContext;
        layoutInflater = LayoutInflater.from(aContext);
        _layout = layout;
    }

    @Override
    public int getCount() {
        return _users.size();
    }

    @Override
    public Object getItem(int position) {
        return _users.get(position);
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
            holder._userPhoto = convertView.findViewById(R.id.user_image);
            holder._userName = convertView.findViewById(R.id.user_name);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Drawable roundedImage = _users.get(position).getPhoto();
        if(roundedImage == null){
            Bitmap bm = BitmapFactory.decodeResource(
                    _context.getResources(),
                    R.drawable.blank_profile
            );
            roundedImage = new RoundImage(bm);
        }


        //holder._userPhoto.set(_users.get(position).getPhotoUrl());
        holder._userPhoto.setImageDrawable(roundedImage);
        holder._userName.setText(_users.get(position).getName());

        return convertView;
    }

    static class ViewHolder {
        ImageView _userPhoto;
        TextView _userName;
    }
}
