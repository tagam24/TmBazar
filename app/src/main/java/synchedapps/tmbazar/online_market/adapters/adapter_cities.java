package synchedapps.tmbazar.online_market.adapters;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import synchedapps.tmbazar.R;
import synchedapps.tmbazar.online_market.Constants;

import synchedapps.tmbazar.online_market.post_tab.post_tabmarket;
import synchedapps.tmbazar.online_market.pre_markets;


public class adapter_cities extends RecyclerView.Adapter<adapter_cities.Reklama_viewholder> {
    List<String> list1;
    Context cntx;
    public class Reklama_viewholder extends RecyclerView.ViewHolder {
        TextView city;

        public Reklama_viewholder(View itemView) {
            super(itemView);
           city=(TextView)itemView.findViewById(R.id.city);
            Typeface tp= Typeface.createFromAsset(cntx.getAssets(),"fonts/Panton-ExtraBold.otf");
            city.setTypeface(tp);
        }
    }

    public adapter_cities(Context ctx, List<String> items) {
        this.list1 = items;
        this.cntx = ctx;
    }

    @Override
    public Reklama_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_cities, parent, false);
        final Reklama_viewholder view = new Reklama_viewholder(v);
        return view;

    }

    @Override
    public void onBindViewHolder(Reklama_viewholder holder, final int position) {
        holder.city.setText(list1.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Constants.Location=list1.get(position);
                if(Constants.Location.equals("Ähli"))Constants.Location="";
                post_tabmarket.s2.sendEmptyMessage(1);
           Intent intent=new Intent(cntx,post_tabmarket.class);
              cntx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

}
