package synchedapps.tmbazar.bb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.Models.ImagesClicker;
import synchedapps.tmbazar.Models.MOdel_lenta_banner;
import synchedapps.tmbazar.Models.model_vip;
import synchedapps.tmbazar.Network.Get_detail_images;
import synchedapps.tmbazar.Network.Liked_getter;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.ViewPagers.image_swipe_adapter_vip_show;
import synchedapps.tmbazar.bb.Realtor.fragment1_realtor;
import synchedapps.tmbazar.dil;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

public class vip_show_details extends AppCompatActivity {
    private static ViewPager mPager;
    Menu menu;
    Liked_getter lg=new Liked_getter();
    Db db;
    Get_detail_images g=new Get_detail_images();
    String name;
    String numb;
    String desc;
    String index;
    Handler create;
  public static Handler trans;
    String id,table;
    public static int  flazhok=0;
 public static ArrayList<ImagesClicker> images=new ArrayList<>();
    private static int currentPage = 0;
    private static final Integer[] XMEN = {R.drawable.footbal2, R.drawable.beylekiler2, R.drawable.home2, R.drawable.cafe, R.drawable.car1};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    TextView main;
            TextView number;
    TextView des,link;
    image_swipe_adapter_vip_show i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_show_details);
        db=new Db(this);
        init();


        main=(TextView)findViewById(R.id.vip_maintitle_show_details);
        number=(TextView)findViewById(R.id.vip_show_details);
        des=(TextView)findViewById(R.id.small_gosmaca);
        Intent i=getIntent();
         id=i.getStringExtra("id");
         table=i.getStringExtra("table");
         dil d=new dil();
         d.set_text();
       try {
           if (!table.equals("")) {
               id=i.getStringExtra("id");
               if(!table.equals("bannerlenta")) {
                   model_vip m1 = db.get_detail_adds(table, id);
                   name = m1.getName();
                   numb = m1.getNumber();
                   desc = m1.getDescription();

               }
                if(table.equals("bannerlenta") || table.equals("home") ||table.equals("beylekiler")){
                    Log.d("bannerlentada","da");
                    Log.d("id",id);
                    Log.d("tablenam",table);
                    MOdel_lenta_banner m=null;
                    for(int k=0; k<lenta_tab.banners.size(); k++){
                     //   if(table.equals("home"))if(id.equals(fragment_beylekiler.banners.get(k).getId())) m=fragment1_realtor.banners.get(k);
try{          if(table.equals("home"))if(id.equals(fragment1_realtor.banners.get(k).getId())) m=fragment1_realtor.banners.get(k);
          if(table.equals("bannerlenta"))if(id.equals(lenta_tab.banners.get(k).getId())) m=lenta_tab.banners.get(k);}catch (IndexOutOfBoundsException ss){}}
                    ImagesClicker c=new ImagesClicker();
                    name=m.getTitle();
                    numb = m.getNumber();
                    desc = m.getDescription();
                    main.setText(name);
                    number.setText(numb);
                    des.setText(desc);
                  c.setImgG(m.getImg1());
                    if(!m.getImg1().equals(""))    images.add(c);
                    c=new ImagesClicker();
                 c.setImgG(m.getImg2());
                    if(!m.getImg2().equals(""))   images.add(c);
                    c=new ImagesClicker();
                  c.setImgG(m.getImg3());
                    if(!m.getImg3().equals(""))  images.add(c);
                    create.sendEmptyMessage(1);
                }
                else  get_local(table,id);
           }

       }catch (NullPointerException s){}
        trans=new Handler(){
            public void handleMessage(Message msg) {
                if(msg.what==1){
                    Log.d("trans"," ");
                    get_local(table,id);
                }}
        };
        if(MainActivity.conn) lg.getter("vip",id);






        final  model_vip m=db.get_detail_vip(id);
       if(table==null){
           flazhok=1;
       link=(TextView)findViewById(R.id.link);

           images=db.get_images_vip(id);
           create.sendEmptyMessage(1);
           main.setText(m.getName());
           number.setText(m.getNumber());
           des.setText(m.getDescription());
      link.setText(m.getLink());
           link.setMovementMethod(LinkMovementMethod.getInstance());
           Linkify.addLinks(link, Linkify.WEB_URLS);
               } else{
           link=(TextView)findViewById(R.id.link);
           main.setText(name);
           number.setText(numb);
           des.setText(desc);
           link.setText(m.getLink());
           link.setMovementMethod(LinkMovementMethod.getInstance());
           Linkify.addLinks(link, Linkify.WEB_URLS);
       }
       Button call=(Button) findViewById(R.id.vip_show_call);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.vip_toolbar_show_details);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textView_title = (TextView) findViewById(R.id.vip_maintitle_show_details);
        String title = textView_title.getText().toString();
        getSupportActionBar().setTitle(title);
      call.setText(dil.jan_etmek);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("giryar","giryar");
                Intent call=new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:"+number.getText().toString()));
                startActivity(call);
            }
        });

      if(table!=null&& !table.equals("bannerlenta"))  {if(db.get_adds_detail_image(table,id).size()<1) g.get_detail(table,id,"1");else get_local(table,id);}

    }

    private void init() {
        for (int i = 0; i < XMEN.length; i++)
            XMENArray.add(XMEN[i]);
        create=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==1){
        mPager = (ViewPager) findViewById(R.id.viewpager_vip_show_details);
              i=new image_swipe_adapter_vip_show( images,vip_show_details.this,"vip");
        mPager.setAdapter(i);
        CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator_vip_show_details);
        indicator.setViewPager(mPager);}}};

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == android.R.id.home) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        return true;
    }

    public   void get_local(final String table,final String id){
        Log.d("getlOcal"," ");
        Thread get_local=new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("table,id",table+" "+id);
                images=db.get_adds_detail_image(table,id);

                create.sendEmptyMessage(1);
                Thread.currentThread().interrupt();
            }
        });
        get_local.start();

    }

    @Override
    public void onBackPressed() {
        flazhok=0;
        images.clear();
        i.notifyDataSetChanged();
        finish();
        super.onBackPressed();
    }
}
