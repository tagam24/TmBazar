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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.Models.model_cars;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.bb.ShowDetailsadds;
import synchedapps.tmbazar.bb.halanlarym_reklama.fragment_halanlarym1;
import synchedapps.tmbazar.bb.small_car_show_details;
import synchedapps.tmbazar.bb.vip_show_details;
import synchedapps.tmbazar.fragment.fragment_car1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static synchedapps.tmbazar.R.array;
import static synchedapps.tmbazar.R.drawable;
import static synchedapps.tmbazar.R.id;
import static synchedapps.tmbazar.R.layout;


public class adapter_for_recycle_for_status_car extends RecyclerView.Adapter<adapter_for_recycle_for_status_car.Reklama_viewholder> {
 static  ArrayList<model_cars> data;
    Context cntx;
    static  int  s=0;
    ArrayList<String > idlar=new ArrayList<>();
   String table;
    Db db=new Db(MainActivity.ctx);
    public class Reklama_viewholder extends RecyclerView.ViewHolder {
        TextView title,place,date,price,time,description;
        ImageView imageView,kredit,obmen,status;
        LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4;
        LinearLayout l;LinearLayout.LayoutParams ls;
        RelativeLayout l1; RelativeLayout.LayoutParams ls3;
        ViewGroup.LayoutParams ls1,ls2;

        public Reklama_viewholder(View itemView) {

            super(itemView);
            l=(LinearLayout)itemView.findViewById(id.linearCar);
           l1=(RelativeLayout)itemView.findViewById(id.layout_car_card);
          ls=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,150);
          ls1=l.getLayoutParams();
             ls2=l1.getLayoutParams();
             ls3=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,150);
             imageView=(ImageView)itemView.findViewById( id.item_image);
             kredit=(ImageView)itemView.findViewById(id.image_kredit1);
             obmen=(ImageView)itemView.findViewById(id.image_obmen);
             title = (TextView) itemView.findViewById(id.item_name);
             place = (TextView) itemView.findViewById(id.item_location);
             date = (TextView) itemView.findViewById(id.item_date);
             price=(TextView)itemView.findViewById(id.item_price);
            //  time=(TextView)itemView.findViewById(id.item_time);
           // description=(TextView)itemView.findViewById(id.item_description);
            status=(ImageView)itemView.findViewById(id.status);
        }
    }

    public adapter_for_recycle_for_status_car(ArrayList<model_cars> items, Context ctx, String table) {
        this.data = items;
        this.cntx = ctx;
        this.table=table;
    }

    @Override
    public Reklama_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_for_car, parent, false);
        final Reklama_viewholder view = new Reklama_viewholder(v);
        return view;

    }

    @Override
    public void onBindViewHolder(Reklama_viewholder holder, final int position) {

Log.d("datasize",""+data.size()+"  position "+position+ " flazhok "+MainActivity.flazhok);
      if(table.equals("cars"))if(position==data.size()-1 && s==0 && MainActivity.flazhok==false)
      {MainActivity.flazhok=true;if(position==data.size()-1 && MainActivity.flazhok==true){s=1; fragment_car1.limit=fragment_car1.limit+30;fragment_car1.get_local(); Log.d("girdi","adapterdan");}}
        else if(position==data.size()-1 && MainActivity.flazhok==true){s=1; fragment_car1.limit=fragment_car1.limit+30;fragment_car1.get_local(); Log.d("girdi","adapterdan");}
        if(table.equals("likedAdds"))if(position==data.size()-1 && s==0 && MainActivity.flazhok==false) MainActivity.flazhok=true; else
            if(position==data.size()-1 && MainActivity.flazhok==true){
            fragment_halanlarym1.limit=fragment_halanlarym1.limit+30;fragment_halanlarym1.get_local(); Log.d("girdi","adapterdan");}
         holder.l.setLayoutParams(holder.ls1);
        holder.l1.setLayoutParams(holder.ls2);
Date d=new Date();
        SimpleDateFormat ff=new SimpleDateFormat("dd.MM.yyyy");
        String date=ff.format(d);
        List<String> list=new ArrayList<>();
        if(!data.get(position).getLocation().equals("")){if(db.get_dil().equals("tm")){list.addAll(Arrays.asList(cntx.getResources().getStringArray(array.yeri_car)));
            holder.place.setText(list.get( Integer.parseInt(data.get(position).getLocation())));
        }
        else {list.addAll(Arrays.asList(cntx.getResources().getStringArray(array.yeri_car_ru)));
            holder.place.setText(list.get( Integer.parseInt(data.get(position).getLocation())));

        }} else  holder.place.setText("");



        holder.price.setText((data.get(position).getPrice()));
        holder.title.setText(data.get(position).getCategory()+" "+data.get(position).getModel()+","+data.get(position).getYear());
        if(data.get(position).getDate().equals(date)) {
            Date t=new Date();
            ff=new SimpleDateFormat("hh:mm");
            String ti=ff.format(t);

         if(db.get_dil().equals("tm"))  {


             if(data.get(position).getTime().equals(ti))holder.date.setText("Şu wagt"); else holder.date.setText("Şu gün"); } else
         {  if(data.get(position).getTime().equals(ti))holder.date.setText("Сейчас"); else holder.date.setText("Сегодня");}
            } else holder.date.setText(data.get(position).getDate());
        if(data.get(position).getCredit().equals("1"))holder.kredit.setImageResource(drawable.tick); else holder.kredit.setImageResource(drawable.cross);
        if(data.get(position).getObmen().equals("1"))holder.obmen.setImageResource(drawable.tick); else holder.obmen.setImageResource(drawable.cross);
        //holder.time.setText(data.get(position).getTime());
        if(!data.get(position).getImg().equals("")) Glide.with(cntx)
                .load("http://u21940xgr.ha002.t.justns.ru/Reklama/adds/images/"+data.get(position).getImg()).asBitmap().centerCrop()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView); else  holder.imageView.setImageResource(drawable.no_img_2);
        holder.status.setVisibility(View.INVISIBLE);
       // if(data.get(position).getVip().equals("1") ){ holder.status.setImageResource(R.drawable.vip); holder.status.setVisibility(View.VISIBLE);} //vip
       // if(data.get(position).getVip().equals("2"))  { holder.status.setImageResource(drawable.gyssagly); holder.status.setVisibility(View.VISIBLE);}//gyssagly
        if(data.get(position).getVip().equals("3")){ holder.l.setLayoutParams(holder.ls);holder.l1.setLayoutParams(holder.ls3);} //banner
        if(data.get(position).getVip().equals("4")){holder.status.setImageResource(drawable.icon_satyldy); holder.status.setVisibility(View.VISIBLE);} //satyldy
        if (data.get(position).getVip().equals("1")) {
            holder.l1.setBackgroundResource(drawable.gradient_vip);
            holder.status.setImageResource(drawable.vip_icon);
            holder.status.setVisibility(View.VISIBLE);
        } //vip
        else   if (data.get(position).getVip().equals("2")) {
            holder.l1.setBackgroundResource(R.drawable.gradient_gys);
            holder.status.setImageResource(drawable.gyssagly_icon);
            holder.status.setVisibility(View.VISIBLE);
        }//gyssagly
        else   if (data.get(position).getVip().equals("3")) {
            holder.l.setLayoutParams(holder.ls);
            holder.l1.setLayoutParams(holder.ls3);
        } //banner
        else   if (data.get(position).getVip().equals("4")) {
            holder.status.setImageResource(R.drawable.satyldy_icon);
            holder.status.setVisibility(View.VISIBLE);
        } //satyldy

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
               // Build.VERSION.SDK_INT
                try{            if( Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
                if(data.get(position).getVip().equals("3")) intent=new Intent(cntx,vip_show_details.class); else
                    intent = new Intent(cntx, ShowDetailsadds.class);} else if(data.get(position).getVip().equals("3")) intent=new Intent(cntx,vip_show_details.class); else intent=new Intent(cntx,small_car_show_details.class);
                    intent.putExtra("id",data.get(position).getId());
                    intent.putExtra("loved",data.get(position).getLoved());
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
                    intent.putExtra("satyldy",data.get(position).getVip());
                    intent.putExtra("image",data.get(position).getImg());
                    intent.putExtra("table",table);
if(!table.equals("likedAdds"))  {if(data.get(position).getWatch().equals("0")) fragment_car1.data.get(position).setWathced(" "+(Integer.parseInt(data.get(position).getWathced())+1));
                    fragment_car1.data.get(position).setWatch("1");}
                    cntx.startActivity(intent);}catch (IndexOutOfBoundsException ss){}
            }

        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
     public  void setData(ArrayList<model_cars> dat){
        this.data=dat;
         Log.d("Halanlarymdan",""+dat.size());
         notifyDataSetChanged();


     }
}
