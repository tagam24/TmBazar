package synchedapps.tmbazar.online_market.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import synchedapps.tmbazar.R;
import synchedapps.tmbazar.online_market.Constants;

import synchedapps.tmbazar.online_market.models.model_premarkets;
import synchedapps.tmbazar.online_market.models.shops;
import synchedapps.tmbazar.online_market.post_markets;
import synchedapps.tmbazar.online_market.post_tab.post_tabmarket;
import synchedapps.tmbazar.online_market.pre_market.markets;


public class adapter_premarkets extends RecyclerView.Adapter<adapter_premarkets.Reklama_viewholder> {
    ArrayList<model_premarkets> list1;
    Context cntx;
    public class Reklama_viewholder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;

        public Reklama_viewholder(View itemView) {
            super(itemView);
          name=(TextView)itemView.findViewById(R.id.premarket_name);
            image=(ImageView)itemView.findViewById(R.id.image_premarket);
        }
    }

    public adapter_premarkets(Context ctx, ArrayList<model_premarkets> items) {
        this.list1 = items;
        this.cntx = ctx;
    }

    @Override
    public Reklama_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_premarkets, parent, false);
        final Reklama_viewholder view = new Reklama_viewholder(v);
        return view;

    }

    @Override
    public void onBindViewHolder(Reklama_viewholder holder, final int position) {
holder.name.setText(list1.get(position).getName());
        holder.image.setImageResource(list1.get(position).getImageResource());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list1.get(position).getName().equals("Bazarlar")|| list1.get(position).getName().equals("Söwda Merkezler")){
                    Constants.hb=list1.get(position).getName();
               Intent intent=new Intent(cntx,markets.class);
             cntx.startActivity(intent);}
                 else if( list1.get(position).getName().equals("Dükanlar")){
                    Constants.hb="Dükanlar";
                    Intent intent=new Intent(cntx,post_tabmarket.class);
                    cntx.startActivity(intent);
                } else {
                    Constants.hb=list1.get(position).getName();
                    Intent intent=new Intent(cntx,post_markets.class);
                    cntx.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }
    public void setData(ArrayList<model_premarkets> list1){
        this.list1=list1;
        notifyDataSetChanged();
    }

}
