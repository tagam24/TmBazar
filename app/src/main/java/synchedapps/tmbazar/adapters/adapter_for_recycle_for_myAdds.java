package synchedapps.tmbazar.adapters;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import synchedapps.tmbazar.Models.model_cars;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.bb.status_car_show_details;
import synchedapps.tmbazar.dil;

import java.util.ArrayList;

import static synchedapps.tmbazar.R.id;


public class adapter_for_recycle_for_myAdds extends RecyclerView.Adapter<adapter_for_recycle_for_myAdds.Reklama_viewholder> {
 ArrayList<model_cars> data;
    Context cntx;
    ArrayList<String > idlar=new ArrayList<>();

    public class Reklama_viewholder extends RecyclerView.ViewHolder {
        TextView title,place,date,price,time,description,add_status;
        ImageView imageView,kredit,obmen;
        LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4;
        LinearLayout l;LinearLayout.LayoutParams ls;ViewGroup.LayoutParams ls1;

        public Reklama_viewholder(View itemView) {

            super(itemView);
            add_status=(TextView)itemView.findViewById(id.myStatus);
          // l=(LinearLayout)itemView.findViewById(id.linearCar);
       //   ls=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
//          ls1=l.getLayoutParams();
           imageView=(ImageView)itemView.findViewById( id.item_image);
          kredit=(ImageView)itemView.findViewById(id.image_obmen_kredit1);
           obmen=(ImageView)itemView.findViewById(id.image_obmen_kredit2);
            title = (TextView) itemView.findViewById(id.item_name);
          //  place = (TextView) itemView.findViewById(id.item_location);
         // date = (TextView) itemView.findViewById(id.item_date);
            price=(TextView)itemView.findViewById(id.item_price);
          //  time=(TextView)itemView.findViewById(id.item_time);
           // description=(TextView)itemView.findViewById(id.item_description);
           // status=(ImageView)itemView.findViewById(id.status);
        }
    }

    public adapter_for_recycle_for_myAdds(ArrayList<model_cars> items, Context ctx) {
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
      //  holder.l.setLayoutParams(holder.ls1);
      //  Log.d("statusadp",""+data.get(position).getStatus().length());
    //   if(Integer.parseInt(data.get(position).getStatus())==1)      holder.add_status.setText("Goyuldy");else  holder.add_status.setText("Garashylyar");
       // holder.place.setText(data.get(position).getLocation());

    holder.price.setText((data.get(position).getPrice()));
    holder.title.setText(data.get(position).getCategory() + " " + data.get(position).getModel() + "," + data.get(position).getYear());
  //  holder.date.setText(data.get(position).getDate());
    if (data.get(position).getCredit().equals("1")) holder.kredit.setImageResource(R.drawable.tick);
    else holder.kredit.setImageResource(R.drawable.cross);
    if (data.get(position).getObmen().equals("1")) holder.obmen.setImageResource(R.drawable.tick);
    else holder.obmen.setImageResource(R.drawable.cross);
    //holder.time.setText(data.get(position).getTime());
    holder.imageView.setImageBitmap(data.get(position).getImage());
        if (data.get(position).getStatus().equals("0")) {
            holder.add_status.setText(dil.tm_garasylyar);
            holder.add_status.setTextColor(Color.BLACK);
        } else if (data.get(position).getStatus().equals("-1")) {
            holder.add_status.setText(dil.tm_kabul_edilmedi);
            holder.add_status.setTextColor(Color.RED);
        } else if (data.get(position).getStatus().equals("1")) {
            holder.add_status.setText((dil.tm_goyuldy));
            holder.add_status.setTextColor(Color.BLACK);}
    // holder.status.setVisibility(View.INVISIBLE);
    //   if(data.get(position).getVip().equals("1") ){ holder.status.setImageResource(drawable.vip); holder.status.setVisibility(View.VISIBLE);}
    // / holder.status.setImageResource(data.get(position).getIdimg());
    //  if(data.get(position).getVip().equals("2"))  { holder.status.setImageResource(drawable.gyssagly); holder.status.setVisibility(View.VISIBLE);   }
    //if(data.get(position).getVip().equals("3"))holder.l.setLayoutParams(holder.ls);
    //if(data.get(position).getSatyldy().equals("1")) holder.status.setImageResource(drawable.icon_satyldy);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent=new Intent(cntx,status_car_show_details.class);
                try{
                    intent.putExtra("id",data.get(position).getId());
                    intent.putExtra("category",data.get(position).getCategory());
                    intent.putExtra("model",data.get(position).getModel());
                    intent.putExtra("kuzow",data.get(position).getKuzow());
                    intent.putExtra("probeg",data.get(position).getProbeg());
                    intent.putExtra("year",data.get(position).getYear());
                    intent.putExtra("color",data.get(position).getColor());
                    intent.putExtra("mator",data.get(position).getMator());
                    intent.putExtra("karopka",data.get(position).getKaropka());
                    intent.putExtra("price",data.get(position).getPrice());
                    intent.putExtra("location",data.get(position).getLocation());
                    intent.putExtra("description",data.get(position).getDescription());
                    intent.putExtra("number",data.get(position).getNumber());
                    intent.putExtra("time",data.get(position).getTime());
                    intent.putExtra("date",data.get(position).getDate());
                    intent.putExtra("watch",data.get(position).getWatch());
                    intent.putExtra("watched",data.get(position).getWathced());
                    intent.putExtra("credit",data.get(position).getCredit());
                    intent.putExtra("obmen",data.get(position).getObmen());
                    intent.putExtra("vip",data.get(position).getVip());
                    intent.putExtra("satyldy",data.get(position).getSatyldy());
                    intent.putExtra("status",data.get(position).getStatus());
                    intent.putExtra("image",data.get(position).getImg());} catch (NullPointerException ss){}
//                    if(data.get(position).getWatch().equals("0")) fragment_bildiris_gosmak1.data.get(position).setWathced(" "+(Integer.parseInt(data.get(position).getWathced())+1));
                //    fragment_bildiris_gosmak1.data.get(position).setWatch("1");
                    cntx.startActivity(intent);
            }

        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
