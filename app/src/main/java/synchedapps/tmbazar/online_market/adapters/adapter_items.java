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
import synchedapps.tmbazar.online_market.items.show_details;
import synchedapps.tmbazar.online_market.models.model_items;
import synchedapps.tmbazar.online_market.models.shops;
import synchedapps.tmbazar.online_market.network.Api;
import synchedapps.tmbazar.online_market.network.get_bazarlar;
import synchedapps.tmbazar.online_market.network.get_items;


public class adapter_items extends RecyclerView.Adapter<adapter_items.Reklama_viewholder> {
    ArrayList<model_items> list1;
    Context cntx;



    public class Reklama_viewholder extends RecyclerView.ViewHolder {
        TextView name,skidka,content,price;
        ImageView image;

        public Reklama_viewholder(View itemView) {
            super(itemView);
            price=(TextView)itemView.findViewById(R.id.price_ti);
            name=(TextView)itemView.findViewById(R.id.itemname);
            skidka=(TextView)itemView.findViewById(R.id.skidka);
            content=(TextView) itemView.findViewById(R.id.item_content);
            image=(ImageView)itemView.findViewById(R.id.image_item);
            Typeface tp= Typeface.createFromAsset(cntx.getAssets(),"fonts/Panton-SemiBold.otf");
            Typeface tp1= Typeface.createFromAsset(cntx.getAssets(),"fonts/Panton.otf");
            Typeface tp2= Typeface.createFromAsset(cntx.getAssets(),"fonts/Panton-ExtraBold.otf");
            name.setTypeface(tp);
            content.setTypeface(tp1);
            price.setTypeface(tp2);
        }
    }

    public adapter_items(Context ctx, ArrayList<model_items> items) {
        this.list1 = items;
        this.cntx = ctx;
    }

    @Override
    public Reklama_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_items, parent, false);
        final Reklama_viewholder view = new Reklama_viewholder(v);
        return view;

    }

    @Override
    public void onBindViewHolder(Reklama_viewholder holder, final int position) {
         holder.name.setText(list1.get(position).getName().toUpperCase());
        if(position==list1.size()-1&& Constants.iter==true){ get_items.get_Data();
            Constants.iter_id=list1.get(list1.size()-1).getItemID();
            items_activity.s2.sendEmptyMessage(1);
            Log.d("geldi",""+position);

        }
        holder.price.setText(list1.get(position).getPrice());
        holder.content.setText(list1.get(position).getContent());
        holder.skidka.setText(list1.get(position).getSkidka());
        Log.d("url",Api.url+list1.get(position).getImage());
        Glide.with(cntx)
                .load("http://"+Api.url+"images/"+list1.get(position).getImage()).asBitmap().thumbnail(0.01f)
                .centerCrop()
                .into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(cntx,show_details.class);
                intent.putExtra("id",list1.get(position).getItemID());
                intent.putExtra("price",list1.get(position).getPrice());
                intent.putExtra("image1",list1.get(position).getImage1());
                intent.putExtra("name",list1.get(position).getName());
                intent.putExtra("image2",list1.get(position).getImage2());
                intent.putExtra("image3",list1.get(position).getImage3());
                intent.putExtra("content",list1.get(position).getContent());
                intent.putExtra("number",list1.get(position).getNumber());
             cntx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }
    public void setData(ArrayList<model_items> list1){
        this.list1=list1;
        notifyDataSetChanged();
    }

}
