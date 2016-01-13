package com.example.gsontest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Harsh on 13-01-2016.
 */
public class CustomAdapter extends BaseAdapter{

    private final Context context;
    private final List<newResopnse.ActorsEntity> moviesEntities;
    private LayoutInflater inflater ;

    public CustomAdapter(Context context , List<newResopnse.ActorsEntity> moviesEntities) {
        this.context = context;
        this.moviesEntities = moviesEntities;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return moviesEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return moviesEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowview ;
        rowview = convertView;
        ViewHolder holder;
        if (rowview == null) {
            rowview = inflater.inflate(R.layout.list_row,parent,false);
            holder = new ViewHolder();
            holder.image = (ImageView) rowview.findViewById(R.id.thumbnail);
            holder.title = (TextView) rowview.findViewById(R.id.mtitle);
            holder.rating = (TextView) rowview.findViewById(R.id.rating);

            rowview.setTag(holder);
        } else {

            holder = (ViewHolder) rowview.getTag();
        }

        newResopnse.ActorsEntity item = (newResopnse.ActorsEntity) getItem(position);
        String myimgurl = item.getImage();

        holder.title.setText(item.getName());
        holder.rating.setText(item.getDescription());
        Picasso.with(context).load(myimgurl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.image);

        return rowview;
    }

    public class ViewHolder{
        TextView title , rating ;
        ImageView image ;

    }
}
