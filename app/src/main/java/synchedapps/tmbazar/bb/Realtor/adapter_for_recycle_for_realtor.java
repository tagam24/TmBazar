package synchedapps.tmbazar.bb.Realtor;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.Models.ModelRealtor;
import synchedapps.tmbazar.Network.network;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.bb.Beylekiler.fragment_beyleki1;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.bb.halanlarym_reklama.fragment_halanlarym2;
import synchedapps.tmbazar.bb.vip_show_details;


public class adapter_for_recycle_for_realtor extends RecyclerView.Adapter<adapter_for_recycle_for_realtor.Reklama_viewholder> {
    List<ModelRealtor> list1;
    Context cntx;
    String table;
    Db db;
    Drawable d1;
    String date1;
    public  static  boolean b=false;
    public class Reklama_viewholder extends RecyclerView.ViewHolder {
        TextView title, place, date, price, content;
        ImageView imageView, show_property;
        RelativeLayout relativeLayout;
        RelativeLayout l1; RelativeLayout.LayoutParams ls3;
        ViewGroup.LayoutParams ls1,ls2;

        LinearLayout l;LinearLayout.LayoutParams ls;
        public Reklama_viewholder(View itemView) {
            super(itemView);
            db=new Db(MainActivity.ctx);
            l=(LinearLayout)itemView.findViewById(R.id.banners);
            l1=(RelativeLayout)itemView.findViewById(R.id.layout_realtor_card);
            ls=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,100);
            ls1=l.getLayoutParams();
            d1=l1.getBackground();
            ls2=l1.getLayoutParams();
            ls3=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,100);
            imageView = (ImageView) itemView.findViewById(R.id.image_for_realtor_rec);
            content = (TextView) itemView.findViewById(R.id.content_for_realtor_rec);
            title = (TextView) itemView.findViewById(R.id.title_for_realtor_rec);
            place = (TextView) itemView.findViewById(R.id.place_for_realtor_rec);
            date = (TextView) itemView.findViewById(R.id.date_for_realtor_rec);
            price = (TextView) itemView.findViewById(R.id.price_for_realtor_rec);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.layout_realtor_card);
            show_property = (ImageView) itemView.findViewById(R.id.image_for_realtor_rec_property);
        }
    }

    public adapter_for_recycle_for_realtor(ArrayList<ModelRealtor> items, Context ctx,String table) {
        this.list1 = items;
        this.cntx = ctx;
        this.table=table;
        Date d = new Date();
        SimpleDateFormat ff = new SimpleDateFormat("dd.MM.yyyy");
         this.date1 = ff.format(d);
    }

    @Override
    public Reklama_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_for_realtor_recycle, parent, false);
        final Reklama_viewholder view = new Reklama_viewholder(v);
        return view;

    }

    @Override
    public void onBindViewHolder(Reklama_viewholder holder, final int position) {
        Log.d("table", table);
        Log.d("truemy", "" + MainActivity.flazhok);
        try {
            Log.d("bname",""+b);
            if (table.equals("home")) {
                if (position == list1.size() - 1 && db.get_cursor_count("home") > fragment1_realtor.limit) {
                    fragment1_realtor.limit = fragment1_realtor.limit + 30;
                    fragment1_realtor.get_local();
                } else if (position == list1.size() - 1 && db.get_cursor_count("home") <= fragment1_realtor.limit && b) {
                    fragment1_realtor.refr();
                    //fragment1_realtor.loading = ProgressDialog.show(cntx, "Yüklenyär...", "Garaşyň...");

            }
            //if(position==list1.size()-1)  fragment1_realtor.refr();
            }

        if (table.equals("beylekiler")) {
            if (position == list1.size() - 1 && db.get_cursor_count("beylekiler") > fragment_beyleki1.limit) {
                fragment_beyleki1.limit = fragment_beyleki1.limit + 30;
                fragment_beyleki1.get_local();
            } else if (position == list1.size() - 1 && db.get_cursor_count("beylekiler") <= fragment_beyleki1.limit && b) {
                fragment_beyleki1.refr();
              //  fragment_beyleki1.loading = ProgressDialog.show(cntx, "Yüklenyär...", "Garaşyň...");
            }
        }
        if (table.equals("likedhome")) {
            if (position == list1.size() - 1 && db.get_cursor_count("likedhome") > fragment_halanlarym2.limit) {
                fragment_halanlarym2.limit = fragment_halanlarym2.limit + 30;
                fragment_halanlarym2.get_local();
            }
        }
        //    if(table.equals("likedbeylekiler"))
        //     if(position==list1.size()-1&& MainActivity.flazhok) fragment_halanlarym3.limit=fragment_halanlarym3.limit+30;fragment_halanlarym3.get_local();
//Log.d("priceRealtor",list1.get(position).getPrice());
        holder.l.setLayoutParams(holder.ls1);
        holder.l1.setLayoutParams(holder.ls2);
        holder.show_property.setVisibility(View.INVISIBLE);
        holder.place.setText(list1.get(position).getLocation());
        holder.price.setText((list1.get(position).getPrice()));
        if (table.equals("home") || table.equals("likedhome"))
            holder.title.setText(list1.get(position).getCategory());
        else holder.title.setText(list1.get(position).getName());
            if (list1.get(position).getDate().equals(date1)) {
                holder.date.setText(list1.get(position).getWagt());
            }else holder.date.setText(list1.get(position).getDate());
        if(!list1.get(position).getImage().equals(""))holder.imageView.setImageBitmap(list1.get(position).getImg());
            else holder.imageView.setImageResource(R.drawable.no_img_2);
        holder.content.setText(list1.get(position).getDescription());
        Log.d("imagePos","kp"+list1.get(position).getImage());

            if (!list1.get(position).getImage().equals("")&&!list1.get(position).getVip().equals("3") )
                Glide.with(cntx)
                        .load("http://"+ network.address+"/Reklama/adds/images/" + list1.get(position).getImage()).asBitmap().centerCrop().override(130,100)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.imageView); else
            if (list1.get(position).getVip().equals("3") )
                Glide.with(cntx)
                        .load("http://"+ network.address+"/Reklama/adds/images/" + list1.get(position).getImage()).asBitmap()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.imageView); else holder.imageView.setImageResource(R.drawable.no_img_2);
        //holder.show_property.setVisibility(View.INVISIBLE);
        try {
            holder.l1.setBackground(d1);
            if (list1.get(position).getVip().equals("1")) {
                holder.l1.setBackgroundResource(R.drawable.gradient_vip);
                holder.show_property.setImageResource(R.drawable.vip_icon);
                holder.show_property.setVisibility(View.VISIBLE);
            } //vip
            if (list1.get(position).getVip().equals("2")) {
                holder.l1.setBackgroundResource(R.drawable.gradient_gys);
                holder.show_property.setImageResource(R.drawable.gyssagly_icon);
                holder.show_property.setVisibility(View.VISIBLE);
            }//gyssagly
            if (list1.get(position).getVip().equals("3")) {
                holder.l.setLayoutParams(holder.ls);
                holder.l1.setLayoutParams(holder.ls3);
            } //banner
            if (list1.get(position).getVip().equals("4")) {

                holder.show_property.setImageResource(R.drawable.satyldy_icon);
                holder.show_property.setVisibility(View.VISIBLE);
            } //satyldy


        } catch (NullPointerException s) {
            Log.d("try", " ");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
               try{ if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                   if (list1.get(position).getVip().equals("3"))
                        intent = new Intent(cntx, vip_show_details.class);
                    else {
                        intent = new Intent(cntx, realtor_show_details.class);
                    }
                } else {
                    intent = new Intent(cntx, small_realtor_show_details.class);
                    if (list1.get(position).getVip().equals("3"))
                        intent = new Intent(cntx, vip_show_details.class);
                }}catch ( IndexOutOfBoundsException s){}
                intent.putExtra("id", list1.get(position).getId());
                intent.putExtra("category", list1.get(position).getCategory());
                intent.putExtra("name", list1.get(position).getName());
                intent.putExtra("price", list1.get(position).getPrice());
                intent.putExtra("vip", list1.get(position).getVip());
                intent.putExtra("number", list1.get(position).getNumber());
                intent.putExtra("watched", list1.get(position).getWatched());
                intent.putExtra("watch", list1.get(position).getWatch());
                intent.putExtra("description", list1.get(position).getDescription());
                intent.putExtra("location", list1.get(position).getLocation());
                intent.putExtra("date", list1.get(position).getDate());
                intent.putExtra("table", table);
                intent.putExtra("loved", list1.get(position).getLoved());
                intent.putExtra("model", list1.get(position).getName());
                intent.putExtra("image", list1.get(position).getImage());

                cntx.startActivity(intent);

            }
        });
    }catch(IndexOutOfBoundsException ss){}
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }
    public  void setData(ArrayList<ModelRealtor> data){
       // if(table.equals("beylekiler")) fragment_beyleki1.recyclerView.stopScroll()
        list1=data;
        notifyDataSetChanged();

    }
}
