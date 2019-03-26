package synchedapps.tmbazar.online_market.adapters;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import synchedapps.tmbazar.R;
import synchedapps.tmbazar.online_market.Constants;
import synchedapps.tmbazar.online_market.items.items_activity;
import synchedapps.tmbazar.online_market.models.shops;
import synchedapps.tmbazar.online_market.network.Api;
import synchedapps.tmbazar.online_market.network.get_minishops;
import synchedapps.tmbazar.online_market.network.get_shops;


public class adapter_postmarkets extends RecyclerView.Adapter<adapter_postmarkets.Reklama_viewholder> {
    ArrayList<shops> list1;
    Context cntx;
    public class Reklama_viewholder extends RecyclerView.ViewHolder {
        TextView name,skidka,category;
        ImageView image;

        public Reklama_viewholder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.shopname);
            skidka=(TextView)itemView.findViewById(R.id.skidka);
            category=(TextView) itemView.findViewById(R.id.shop_category);
            image=(ImageView)itemView.findViewById(R.id.image_postmarket);
            Typeface tp= Typeface.createFromAsset(cntx.getAssets(),"fonts/Panton-ExtraBold.otf");
            Typeface tp1= Typeface.createFromAsset(cntx.getAssets(),"fonts/Panton.otf");
            name.setTypeface(tp);

            category.setTypeface(tp1);
        }
    }

    public adapter_postmarkets(Context ctx, ArrayList<shops> items) {
        this.list1 = items;
        this.cntx = ctx;
    }

    @Override
    public Reklama_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_postmarkets, parent, false);
        final Reklama_viewholder view = new Reklama_viewholder(v);
        return view;

    }

    @Override
    public void onBindViewHolder(Reklama_viewholder holder, final int position) {
        if(position==list1.size()-1&& Constants.iter==true){
           get_shops.get_Data();
            Constants.iter_id=list1.get(list1.size()-1).getId();
            Log.d("geldi",""+position);

        }
        holder.name.setText(list1.get(position).getName().toUpperCase());
        holder.category.setText(list1.get(position).getKategoriya());
        holder.skidka.setText(list1.get(position).getSkidka());
        Log.d("url",Api.url+list1.get(position).getImage());
        Glide.with(cntx)
                .load("http://"+Api.url+"images/"+list1.get(position).getImage()).asBitmap().thumbnail(0.01f)
                .into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.shop_id=list1.get(position).getId();
                Constants.shop_name=list1.get(position).getName();
               Intent intent=new Intent(cntx,items_activity.class);
                 intent.putExtra("image",list1.get(position).getImage());
             cntx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }
    public  void not(){
        list1.clear();
        notifyDataSetChanged();
    }
    public void setData(ArrayList<shops> list1){
        this.list1=list1;
        notifyDataSetChanged();
    }

}
