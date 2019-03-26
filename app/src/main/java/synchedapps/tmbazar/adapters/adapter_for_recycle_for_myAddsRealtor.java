package synchedapps.tmbazar.adapters;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import synchedapps.tmbazar.Models.ModelRealtor;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.bb.ShowDetailsadds;
import synchedapps.tmbazar.bb.small_car_show_details;

import java.util.ArrayList;

import static synchedapps.tmbazar.R.id;
import static synchedapps.tmbazar.R.layout;


public class adapter_for_recycle_for_myAddsRealtor extends RecyclerView.Adapter<adapter_for_recycle_for_myAddsRealtor.Reklama_viewholder> {
 ArrayList<ModelRealtor> data;
    Context cntx;

    public class Reklama_viewholder extends RecyclerView.ViewHolder {
        TextView title,place,date,price,time,description,add_status;
        ImageView imageView,kredit,obmen,status;
        LinearLayout l;LinearLayout.LayoutParams ls;ViewGroup.LayoutParams ls1;

        public Reklama_viewholder(View itemView) {

            super(itemView);
            add_status=(TextView)itemView.findViewById(R.id.myStatus);
          //  l=(LinearLayout)itemView.findViewById(id.linearCar);
   //       ls=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
     //     ls1=l.getLayoutParams();
            imageView=(ImageView)itemView.findViewById( id.item_image);
            title = (TextView) itemView.findViewById(id.item_name);
            place = (TextView) itemView.findViewById(id.item_location);
            date = (TextView) itemView.findViewById(id.item_date);
            price=(TextView)itemView.findViewById(id.item_price);

        }
    }

    public adapter_for_recycle_for_myAddsRealtor(ArrayList<ModelRealtor> items, Context ctx) {
        this.data = items;
        this.cntx = ctx;
    }

    @Override
    public Reklama_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_my_adds, parent, false);
        final Reklama_viewholder view = new Reklama_viewholder(v);
        return view;

    }

    @Override
    public void onBindViewHolder(Reklama_viewholder holder, final int position) {
         holder.l.setLayoutParams(holder.ls1);
        Log.d("statusadp",""+data.get(position).getStatus().length());
       if(Integer.parseInt(data.get(position).getStatus())==1)      holder.add_status.setText("Goyuldy");else  holder.add_status.setText("Garaşylyar");
        holder.place.setText(data.get(position).getLocation());
        holder.price.setText((data.get(position).getPrice()));
        holder.title.setText(data.get(position).getCategory()+" "+data.get(position).getName());
        holder.date.setText(data.get(position).getDate());
       // if(data.get(position).getCredit().equals("1"))holder.kredit.setImageResource(drawable.tick); else holder.kredit.setImageResource(drawable.cross);
      //  if(data.get(position).getObmen().equals("1"))holder.obmen.setImageResource(drawable.tick); else holder.obmen.setImageResource(drawable.cross);
        //holder.time.setText(data.get(position).getTime());
        holder.imageView.setImageBitmap(data.get(position).getImg());
      //  holder.status.setVisibility(View.INVISIBLE);
       //    if(data.get(position).getVip().equals("1") ){ holder.status.setImageResource(drawable.vip); holder.status.setVisibility(View.VISIBLE);}
        // / holder.status.setImageResource(data.get(position).getIdimg());
       //    if(data.get(position).getVip().equals("2"))  { holder.status.setImageResource(drawable.gyssagly); holder.status.setVisibility(View.VISIBLE);   }
    ///    if(data.get(position).getVip().equals("3"))holder.l.setLayoutParams(holder.ls);
    //    if (data.get(position).getVip().equals("4")){ holder.status.setImageResource(drawable.icon_satyldy); holder.status.setVisibility(View.VISIBLE);}
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    intent = new Intent(cntx, ShowDetailsadds.class);} else intent=new Intent(cntx,small_car_show_details.class);
                    intent.putExtra("id",data.get(position).getId());
                    intent.putExtra("category",data.get(position).getCategory());
                    intent.putExtra("price",data.get(position).getPrice());
                    intent.putExtra("location",data.get(position).getLocation());
                    intent.putExtra("description",data.get(position).getDescription());
                    intent.putExtra("number",data.get(position).getNumber());
                    intent.putExtra("time",data.get(position).getTime());
                    intent.putExtra("date",data.get(position).getDate());
                    intent.putExtra("watch",data.get(position).getWatch());
                    intent.putExtra("watched",data.get(position).getWatched());
                    intent.putExtra("credit",data.get(position).getCredit());
                    intent.putExtra("obmen",data.get(position).getObmen());
                    intent.putExtra("vip",data.get(position).getVip());
                    intent.putExtra("image",data.get(position).getImg());
                    cntx.startActivity(intent);
            }

        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
