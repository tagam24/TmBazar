package synchedapps.tmbazar.online_market.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import synchedapps.tmbazar.R;
import synchedapps.tmbazar.online_market.Constants;

import synchedapps.tmbazar.online_market.models.model_premarkets;
import synchedapps.tmbazar.online_market.post_markets;
import synchedapps.tmbazar.online_market.post_tab.post_tabmarket;


public class adapter_categories extends RecyclerView.Adapter<adapter_categories.Reklama_viewholder> {
    ArrayList<model_premarkets> list1;
    Context cntx;
    public class Reklama_viewholder extends RecyclerView.ViewHolder {
        TextView name;

        public Reklama_viewholder(View itemView) {
            super(itemView);
          name=(TextView)itemView.findViewById(R.id.category_name);

        }
    }

    public adapter_categories(Context ctx, ArrayList<model_premarkets> items) {
        this.list1 = items;
        this.cntx = ctx;
    }

    @Override
    public Reklama_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_categories, parent, false);
        final Reklama_viewholder view = new Reklama_viewholder(v);
        return view;

    }

    @Override
    public void onBindViewHolder(Reklama_viewholder holder, final int position) {
holder.name.setText(list1.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post_tabmarket.s2.sendEmptyMessage(1);
                Constants.shopses.clear();
               Constants.market_category=list1.get(position).getName();
                Intent intent=new Intent(cntx,post_markets.class);
                cntx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

}
