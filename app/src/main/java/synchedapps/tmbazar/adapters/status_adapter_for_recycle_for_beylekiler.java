package synchedapps.tmbazar.adapters;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import synchedapps.tmbazar.Models.ModelRealtor;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.dil;

import java.util.ArrayList;


public class status_adapter_for_recycle_for_beylekiler extends RecyclerView.Adapter<status_adapter_for_recycle_for_beylekiler.Reklama_viewholder> {
    ArrayList<ModelRealtor> list1;
    Context cntx;
    String table;

    public class Reklama_viewholder extends RecyclerView.ViewHolder {
        TextView title, place, date, price,status;
        ImageView imageView;
        RelativeLayout relativeLayout;

        public Reklama_viewholder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_for_beylekiler_rec);
            title = (TextView) itemView.findViewById(R.id.title_for_beylekiler_rec);
            place = (TextView) itemView.findViewById(R.id.place_for_beylekiler_rec);
            date = (TextView) itemView.findViewById(R.id.date_for_beylekiler_rec);
            price = (TextView) itemView.findViewById(R.id.price_for_beylekiler_rec);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.layout_beylekiler_card);
            status=(TextView)itemView.findViewById(R.id.status_text_for_beylekiler);
        }
    }

    public status_adapter_for_recycle_for_beylekiler(ArrayList<ModelRealtor> items, Context ctx,String table) {
        this.list1 = items;
        this.cntx = ctx;
        this.table=table;
    }

    @Override
    public Reklama_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_for_status_beylekiler_recycle, parent, false);
        final Reklama_viewholder view = new Reklama_viewholder(v);
        return view;

    }

    @Override
    public void onBindViewHolder(Reklama_viewholder holder, final int position) {

        holder.place.setText(list1.get(position).getLocation());
        holder.price.setText((list1.get(position).getPrice()));
        holder.title.setText(list1.get(position).getName());
        holder.date.setText(list1.get(position).getDate());
        holder.imageView.setImageBitmap(list1.get(position).getImg());
        if (list1.get(position).getStatus().equals("0")) {
            holder.status.setText(dil.tm_garasylyar);
            holder.status.setTextColor(Color.BLACK);
        } else if (list1.get(position).getStatus().equals("-1")) {
            holder.status.setText(dil.tm_kabul_edilmedi);
            holder.status.setTextColor(Color.RED);
        } else if (list1.get(position).getStatus().equals("1")) {
            holder.status.setText((dil.tm_goyuldy));
            holder.status.setTextColor(Color.BLACK);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(cntx, status_beylekiler_show_details.class);
                    Log.d("adapterda"," "+list1.get(position).getId());
                    intent.putExtra("id",list1.get(position).getId());
                    intent.putExtra("category",list1.get(position).getCategory());
                    intent.putExtra("name",list1.get(position).getName());
                    intent.putExtra("price",list1.get(position).getPrice());
                    intent.putExtra("vip",list1.get(position).getVip());
                    intent.putExtra("number",list1.get(position).getNumber());
                    intent.putExtra("watched",list1.get(position).getWatched());
                    intent.putExtra("watch",list1.get(position).getWatch());
                    intent.putExtra("description",list1.get(position).getDescription());
                    intent.putExtra("location",list1.get(position).getLocation());
                    intent.putExtra("date",list1.get(position).getDate());
                intent.putExtra("status",list1.get(position).getStatus());
                    intent.putExtra("table",table);
                    intent.putExtra("image",list1.get(position).getImage());
                    cntx.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }
}
