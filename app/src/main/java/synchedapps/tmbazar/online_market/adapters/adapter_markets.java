package synchedapps.tmbazar.online_market.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import synchedapps.tmbazar.R;
import synchedapps.tmbazar.online_market.Constants;

import synchedapps.tmbazar.online_market.models.bazarlar;
import synchedapps.tmbazar.online_market.models.shops;
import synchedapps.tmbazar.online_market.network.Api;
import synchedapps.tmbazar.online_market.network.get_bazarlar;
import synchedapps.tmbazar.online_market.post_tab.post_tabmarket;


public class adapter_markets extends RecyclerView.Adapter<adapter_markets.Reklama_viewholder> {
    ArrayList<bazarlar> list1;
    Context cntx;
    public class Reklama_viewholder extends RecyclerView.ViewHolder {
        TextView name,number;
        ImageView image;

        public Reklama_viewholder(View itemView) {
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.market_name);
            image=(ImageView)itemView.findViewById(R.id.image_market);
        }
    }

    public adapter_markets(Context ctx, ArrayList<bazarlar> items) {
        this.list1 = items;
        this.cntx = ctx;
    }

    @Override
    public Reklama_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_markets, parent, false);
        final Reklama_viewholder view = new Reklama_viewholder(v);
        return view;

    }

    @Override
    public void onBindViewHolder(Reklama_viewholder holder, final int position) {

        if(position==list1.size()-1&& Constants.iter==true){ get_bazarlar.get_Data();
          Constants.iter_id=list1.get(list1.size()-1).getId();
            Log.d("geldi",""+position);

        }
        holder.name.setText(list1.get(position).getName());
        Glide.with(cntx)
                .load("http://"+Api.url+"images/"+list1.get(position).getUrlImage()).asBitmap().centerCrop()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.bazar=list1.get(position).getName();

               Intent intent=new Intent(cntx,post_tabmarket.class);
             cntx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }
    public void setData(ArrayList<bazarlar> list1){
        this.list1=list1;
        notifyDataSetChanged();
    }

}
