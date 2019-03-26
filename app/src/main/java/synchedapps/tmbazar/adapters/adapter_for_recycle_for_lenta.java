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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.Models.model_lentas;
import synchedapps.tmbazar.Network.network;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.bb.Beylekiler.fragment_beyleki1;
import synchedapps.tmbazar.bb.lenta_show_details;
import synchedapps.tmbazar.bb.small_lenta_show_details;
import synchedapps.tmbazar.fragment.fragment_lenta1;
import synchedapps.tmbazar.fragment.fragment_lenta2;
import synchedapps.tmbazar.fragment.fragment_lenta3;

import java.util.ArrayList;
import java.util.List;


public class adapter_for_recycle_for_lenta extends RecyclerView.Adapter<adapter_for_recycle_for_lenta.Reklama_viewholder> {
    List<model_lentas> data;
    Context cntx;
    String table;
    static int s=0;
    public  static  boolean b=false;
    Db db;
    public class Reklama_viewholder extends RecyclerView.ViewHolder {
        TextView title,content,date,like,dislike,watch;
        ImageView imageView,love;

        public Reklama_viewholder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.image_for_lenta_card);
            title = (TextView) itemView.findViewById(R.id.title_for_lenta_card);
            content = (TextView) itemView.findViewById(R.id.content_for_lenta_card);
            date = (TextView) itemView.findViewById(R.id.date_for_lenta_card);
            like = (TextView) itemView.findViewById(R.id.like_for_lenta_card);
            dislike = (TextView) itemView.findViewById(R.id.dislike_for_lenta_card);
            watch = (TextView) itemView.findViewById(R.id.watch_for_lent_card);
            love=(ImageView)itemView.findViewById(R.id.love_card_lenta);

        }
    }

    public adapter_for_recycle_for_lenta(ArrayList<model_lentas> data, Context ctx,String table) {
        this.data = data;
        this.cntx = ctx;
        this.table=table;
        db=new Db(ctx);
    }

    @Override
    public Reklama_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_for_lenta, parent, false);
        final Reklama_viewholder view = new Reklama_viewholder(v);
        return view;

    }

    @Override
    public void onBindViewHolder(Reklama_viewholder holder, final int position) {
       try {
           if (table.equals("futbol")){
               if (position == data.size() - 1 && db.get_cursor_count("futbol") > fragment_lenta1.limit) {
                   fragment_lenta1.limit += 30;
                   fragment_lenta1.get_local();
               }else if (position == data.size() - 1 && db.get_cursor_count("futbol") <= fragment_lenta1.limit ) {
                   fragment_lenta1.refr();
                   //  fragment_beyleki1.loading = ProgressDialog.show(cntx, "Yüklenyär...", "Garaşyň...");
               }

           } else
           if (table.equals("tasinlikler")){
               if (position == data.size() - 1 && db.get_cursor_count("tasinlikler") > fragment_lenta2.limit) {
                   fragment_lenta2.limit += 30;
                   fragment_lenta2.get_local();
               }else if (position == data.size() - 1 && db.get_cursor_count("tasinlikler") <= fragment_lenta2.limit ) {
                   fragment_lenta2.refr();
                   //  fragment_beyleki1.loading = ProgressDialog.show(cntx, "Yüklenyär...", "Garaşyň...");
               }}
           else
           if (table.equals("gymmatly")){
               if (position == data.size() - 1 && db.get_cursor_count("gymmatly") > fragment_lenta3.limit) {
                   fragment_lenta3.limit += 30;
                   fragment_lenta3.get_local();
               }
               else if (position == data.size() - 1 && db.get_cursor_count("gymmatly") <= fragment_lenta3.limit ) {
                   fragment_lenta3.refr();
                   Log.d("asfg","giryarmi");
                   //  fragment_beyleki1.loading = ProgressDialog.show(cntx, "Yüklenyär...", "Garaşyň...");
               }}
           holder.love.setImageResource(R.drawable.love_blue);

           if(db.isIn("liked_gyzykly",data.get(position).getId())) holder.love.setImageResource(R.drawable.love_pink);
           holder.content.setText(data.get(position).getContent());
           holder.title.setText(data.get(position).getTitle());
           holder.date.setText(data.get(position).getDate());
           holder.like.setText(data.get(position).getLike());
           holder.dislike.setText(data.get(position).getDislike());
           holder.watch.setText(data.get(position).getWatch());
           Log.d("images",data.get(position).getImage());
           Glide.with(cntx)
                   .load("http://"+ network.address+"/Reklama/adds/images/"+data.get(position).getImage()).asBitmap().centerCrop() .diskCacheStrategy(DiskCacheStrategy.NONE)
                   .fitCenter()
                   .into(holder.imageView);
         //  holder.imageView.setImageBitmap(data.get(position).getImgbitmap());
           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                       intent = new Intent(cntx, lenta_show_details.class);
                   } else intent = new Intent(cntx, small_lenta_show_details.class);
                   intent.putExtra("id", data.get(position).getId());
                   intent.putExtra("pid", data.get(position).getPid());
                   intent.putExtra("owntable", data.get(position).getOwntable());
                   intent.putExtra("Image",data.get(position).getImage());
                   intent.putExtra("table", data.get(position).getTable());
                   Log.d("positon", "" + position);
                   intent.putExtra("position", "" + position);
                   cntx.startActivity(intent);
               }
           });
       }catch (IndexOutOfBoundsException ss){}
    }

    @Override
    public int getItemCount() {
     return data.size();
    }
    public void set_data(   List<model_lentas> da){
        this.data=da;
        notifyDataSetChanged();
    }
}
