package com.example.sebastian.ngara;

/**
 * Created by SEBASTIAN on 4/10/2018.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class AdapterList extends RecyclerView.Adapter<AdapterList.HolderItem>{
    List<ModelList>mListItem;
    Context context;

    public AdapterList(List<ModelList> mListItem, Context context) {
        this.mListItem = mListItem;
        this.context = context;
    }

    @Override
    public HolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_rows,parent,false);
        HolderItem holder=new HolderItem(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(HolderItem holder, int position) {
        ModelList mlist=mListItem.get(position);
        holder.tv_title.setText(mlist.getTitle());
        holder.tv_katerangan.setText(mlist.getKaterangan());
        /*loading image*/
        Glide.with(context).load(mlist.getImg()).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.thubnail);

    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }

    public class HolderItem extends RecyclerView.ViewHolder{
        ImageView thubnail;
        TextView tv_title,tv_katerangan;
        public HolderItem(View v) {
            super(v);
            thubnail=(ImageView)v.findViewById(R.id.img_cover);
            tv_title=(TextView) v.findViewById(R.id.tv_title);
            tv_katerangan=(TextView) v.findViewById(R.id.tv_description);
        }
    }
}