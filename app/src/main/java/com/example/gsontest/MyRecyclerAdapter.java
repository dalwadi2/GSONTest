package com.example.gsontest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Harsh on 13-01-2016.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.CustomViewHolder> {

    private final Context context;
    private final List<newResopnse.ActorsEntity> moviesEntities;

    public MyRecyclerAdapter(Context context, List<newResopnse.ActorsEntity> moviesEntities) {
        this.context = context;
        this.moviesEntities = moviesEntities;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        newResopnse.ActorsEntity item = moviesEntities.get(position);
        String myimgurl = item.getImage();

        holder.textView.setText(item.getName());
        holder.textView2.setText(item.getDescription());
        Picasso.with(context).load(myimgurl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return (null != moviesEntities ? moviesEntities.size() : 0);    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView textView,textView2;

        public CustomViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
            this.textView = (TextView) view.findViewById(R.id.mtitle);
            this.textView2 = (TextView) view.findViewById(R.id.rating);
        }
    }
}
