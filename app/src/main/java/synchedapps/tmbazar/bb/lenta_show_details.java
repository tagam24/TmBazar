package synchedapps.tmbazar.bb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.Models.MOdel_lenta_banner;
import synchedapps.tmbazar.Models.model_lentas;
import synchedapps.tmbazar.Network.Get_data_lenta;
import synchedapps.tmbazar.Network.liked_sender;
import synchedapps.tmbazar.Network.network;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.bb.Realtor.photo_show;
import synchedapps.tmbazar.fragment.fragment_lenta1;
import synchedapps.tmbazar.fragment.fragment_lenta2;
import synchedapps.tmbazar.fragment.fragment_lenta3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class lenta_show_details extends AppCompatActivity {
    Menu menu;
    MenuItem item;
Db db=new Db(MainActivity.ctx);
    liked_sender l=new liked_sender();
    String table,loved;
    model_lentas m;
    Toolbar toolbar;
    FloatingActionButton floatingActionButton;
    TextView  content,maz,like,dislike,watch,date;
    ImageView main,like_image,dislike_image;
    String id,pid,owntable;
    int pos;
    AppBarLayout appBarLayout;
    public static   ArrayList<MOdel_lenta_banner> banners;
    ImageView img;
    ViewFlipper v;
    Context ctx;
    Get_data_lenta g;
    public  static  Handler upd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_lenta_show_details);
         toolbar = (Toolbar) findViewById(R.id.toolbar_vip_show);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
      ctx=this;
         appBarLayout = (AppBarLayout) findViewById(R.id.appbar_lenta_show);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.flbut_lenta_love_show);
        Gui();
        init();



        g=new Get_data_lenta();
        upd=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                m=db.get_detail(table,id);
                Glide.with(lenta_show_details.this)
                        .load("http://"+ network.address+"/Reklama/adds/images/"+m.getImage()).asBitmap().centerCrop()
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(main);
            }
        };
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(lenta_show_details.this,photo_show.class);
                i.putExtra("image",m.getImage());
                startActivity(i);
            }
        });
      //  if(!table.equals("liked_gyzykly")) g.get_image(table,id);


try{        if(MainActivity.conn){ if(m.getWatched().equals("0")){
            Log.d("mwatched","girdi");
            int count=Integer.parseInt(m.getWatch())+1;
            db.lwatched(table,"watched",id,count);
            l.send_liked(table,"watched",id);
            if(table.equals("futbol")) fragment_lenta1.data.get(pos).setWatch(""+count);
            if(table.equals("tasinlikler")) fragment_lenta2.data.get(pos).setWatch(""+count);
            if(table.equals("gymmatly")) fragment_lenta3.data.get(pos).setWatch(""+count);
            watch.setText(""+count);}
        }} catch (NullPointerException ss){}

          like_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m.getLiked().equals("0")){
                if(MainActivity.conn){   int k=Integer.parseInt(m.getLike())+1;
                db.lwatched(table,"liked",id,k);
                 l.send_liked(table,"liked",id);
                    m=db.get_detail(table,id);
                if(table.equals("futbol")) fragment_lenta1.data.get(pos).setLike(""+k);
                if(table.equals("tasinlikler")) fragment_lenta2.data.get(pos).setLike(""+k);
                if(table.equals("gymmatly")) fragment_lenta3.data.get(pos).setLike(""+k);
                like.setText(""+k);}}
            }
        });

        dislike_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m.getLiked().equals("0")){
                if(MainActivity.conn){ db.lwatched(table,"disliked",id,Integer.parseInt(m.getDislike())+1);
                l.send_liked(table,"disliked",id);
                int k=Integer.parseInt(m.getDislike())+1;
                    m=db.get_detail(table,id);
                if(table.equals("futbol")) fragment_lenta1.data.get(pos).setDislike(""+k);
                if(table.equals("tasinlikler")) fragment_lenta2.data.get(pos).setDislike(""+k);
                if(table.equals("gymmatly")) fragment_lenta3.data.get(pos).setDislike(""+k);
                dislike.setText(""+k);}}
            }
        });


         floatingActionButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if(loved.equals("0")){
                    db.insert_loved(table,id);
               floatingActionButton.setImageResource(R.drawable.love_pink);
                    Toast.makeText(getApplicationContext(), "Halanlryma gosyldy", Toast.LENGTH_LONG).show();
               loved="1";
                    item.setIcon(R.drawable.love_pink);
                    db.insert_Likegyzykly(id,m.getTitle(),m.getImage(),m.getContent(),m.getDate(),m.getLike(),m.getDislike(),m.getWatch(),table);
                    if(table.equals("futbol")) fragment_lenta1.data.get(pos).setLoved("1");
                    if(table.equals("tasinlikler")) fragment_lenta2.data.get(pos).setLoved("1");
                    if(table.equals("gymmatly")) fragment_lenta3.data.get(pos).setLoved("1");}
                else{
                    item.setIcon(R.drawable.love);
                    if(table.equals("liked_gyzykly")){
                        db.delete_like("liked_gyzykly",pid);
                        db.delete_loved(owntable,id);
                        like_lenta_activity.get_local();
                        finish();} else{
                    db.delete_liked("liked_gyzykly",id,table);
                    db.delete_loved(table,id);
                    loved="0";
                    Toast.makeText(getApplicationContext(), "Halanlrymdan ayryldy", Toast.LENGTH_LONG).show();
                    floatingActionButton.setImageResource(R.drawable.love);
                    if(table.equals("futbol")) fragment_lenta1.data.get(pos).setLoved("0");
                    if(table.equals("tasinlikler")) fragment_lenta2.data.get(pos).setLoved("0");
                    if(table.equals("gymmatly")) fragment_lenta3.data.get(pos).setLoved("0");}
                }
            }
        });


      appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;

                    showOption(R.id.lenta_toolbar__show__det_love);
                } else if (isShow) {
                    isShow = false;
                    hideOption(R.id.lenta_toolbar__show__det_love);
                }
            }
        });
        get_banner();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == android.R.id.home) {
                Intent intent = null;
                lenta_tab.lenta_path = false;
               if(table.equals("liked_gyzykly")) {intent = new Intent(getApplicationContext(), like_lenta_activity.class);}
                else{  intent = new Intent(getApplicationContext(), lenta_tab.class);}
                startActivity(intent);

        }
        if (res_id == R.id.lenta_toolbar__show__det_love) {
            if(loved.equals("0")){
                db.insert_loved(table,id);
                loved="1";
                floatingActionButton.setImageResource(R.drawable.love_pink);
                item.setIcon(R.drawable.love_pink);
                Toast.makeText(getApplicationContext(), "Halanlryma gosyldy", Toast.LENGTH_LONG).show();
                db.insert_Likegyzykly(id,m.getTitle(),m.getImage(),m.getContent(),m.getDate(),m.getLike(),m.getDislike(),m.getWatch(),table);
                if(table.equals("futbol")) fragment_lenta1.data.get(pos).setLoved("1");
                if(table.equals("tasinlikler")) fragment_lenta2.data.get(pos).setLoved("1");
                if(table.equals("gymmatly")) fragment_lenta3.data.get(pos).setLoved("1");}
            else{
                floatingActionButton.setImageResource(R.drawable.love);
                if(table.equals("liked_gyzykly")){   db.delete_like("liked_gyzykly",pid);
                    db.delete_loved(owntable,id);
                    like_lenta_activity.get_local();
                     loved="0";
                    finish();} else{
                    db.delete_liked("liked_gyzykly",id,table);
                    db.delete_loved(table,id);}
                Toast.makeText(getApplicationContext(), "Halanlrymdan ayryldy", Toast.LENGTH_LONG).show();
                item.setIcon(R.drawable.love);
                if(table.equals("futbol")) fragment_lenta1.data.get(pos).setLoved("0");
                if(table.equals("tasinlikler")) fragment_lenta2.data.get(pos).setLoved("0");
                if(table.equals("gymmatly")) fragment_lenta3.data.get(pos).setLoved("0");
        }}

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_for_toolbar_lenta_love, menu);
        MenuItem item=menu.findItem(R.id.lenta_toolbar__show__det_love);
        this.item=item;
        if(loved.equals("1"))item.setIcon(R.drawable.love_pink);
        hideOption(R.id.lenta_toolbar__show__det_love);
        return true;
    }

    private void hideOption(int id) {
        toolbar.setTitle("");
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id) {
        toolbar.setTitle(m.getTitle());
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }
    @Override
    public void onBackPressed() {
        if(table.equals("futbol")) fragment_lenta1.get_local();
        if(table.equals("tasinlikler")) fragment_lenta2.get_local();
        if(table.equals("gymmatly")) fragment_lenta3.get_local();
        finish();
        super.onBackPressed();
    }
    void Gui(){
        v=(ViewFlipper)findViewById(R.id.fliper_for_lenta1);
        date    =(TextView)findViewById(R.id.sene_lenta_show) ;
        content =(TextView)findViewById(R.id.content_lenta_show) ;
        maz     =(TextView)findViewById(R.id.mazmuny_lenta_show);
        like    = (TextView) findViewById(R.id.like_lenta_show);
        dislike = (TextView) findViewById(R.id.dislike_lenta_show);
        main    =(ImageView)findViewById(R.id.pager_vip_show);
        watch   =(TextView)findViewById(R.id.watch_lenta_show);
   like_image   = (ImageView) findViewById(R.id.image_like_lent_show);
 dislike_image  = (ImageView) findViewById(R.id.image_dislike_lent_show);
    }
    void  init(){
          Intent intent=getIntent();
          id = intent.getStringExtra("id");
Log.d("id",id);

          table=intent.getStringExtra("table");
        Log.d("tableNmae",table);
        pid=intent.getStringExtra("pid");
        owntable=intent.getStringExtra("owntable");
        if(!table.equals("liked_gyzykly")){m=db.get_detail(table,id);
            if(db.isIn("liked_gyzykly",id)) loved="1"; else loved="0";} else {m=db.get_detail(table,pid);
            loved="1";}
          if(loved.equals("1"))  floatingActionButton.setImageResource(R.drawable.love_pink);
          getSupportActionBar().setTitle(m.getTitle());
          pos=Integer.parseInt(intent.getStringExtra("position"));
          Log.d("m.getWached()"," "+m.getWatched());
          if(m.getWatched()!=null)  if (!m.getWatched().equals("0"))
          watch.setText(m.getWatch());
          content.setText(m.getTitle());
          main.setImageBitmap(m.getImgbitmap());
        Glide.with(this)
                .load("http://"+network.address+"/Reklama/adds/images/"+m.getImage()).asBitmap().centerCrop()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(main);
          maz.setText(m.getContent());
          like.setText(m.getLike());
          dislike.setText(m.getDislike());
          date.setText(m.getDate());


    }
    int k;
    public void  get_banner(){

        banners=new ArrayList<>();
        banners=lenta_tab.banners;
        Collections.shuffle(banners);
        Log.d("bannersize"," bb"+banners.size());
        Collections.shuffle(banners,new Random());
        for(int i=0; i<banners.size();i++){
            img=new ImageView(ctx);
            img.setTag(banners.get(i).getId());
            img.setClickable(true);
            try{if(banners.size()>=i&&banners.size()!=0)img.setImageBitmap(banners.get(i).getImage1());} catch (IndexOutOfBoundsException s){}
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            k=i;
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(ctx, vip_show_details.class);
                    intent.putExtra("id",""+view.getTag());
                    intent.putExtra("category",banners.get(k).getTitle());
                    intent.putExtra("number",banners.get(k).getNumber());
                    intent.putExtra("description",banners.get(k).getDescription());
                    intent.putExtra("index",""+k);
                    intent.putExtra("table","bannerlenta");
                    startActivity(intent);
                    //Log.d("idclick",""+view.getTag());*/
                }
            });

            v.addView(img);
        }}


}
