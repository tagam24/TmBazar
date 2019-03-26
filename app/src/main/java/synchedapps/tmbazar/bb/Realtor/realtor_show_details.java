package synchedapps.tmbazar.bb.Realtor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.Models.ImagesClicker;
import synchedapps.tmbazar.Network.Get_detail_images;
import synchedapps.tmbazar.Network.Liked_getter;
import synchedapps.tmbazar.Network.liked_sender;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.bb.Beylekiler.fragment_beyleki1;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.bb.halanlarym_reklama.fragment_halanlarym2;
import synchedapps.tmbazar.bb.halanlarym_reklama.fragment_halanlarym3;
import synchedapps.tmbazar.bb.vip_show_details;
import synchedapps.tmbazar.dil;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Collections;

public class realtor_show_details extends AppCompatActivity {
    private static ViewPager mPager;
    Menu menu;
    MenuItem item;
    FloatingActionButton call;
    Db db;
    dil di = new dil();
    ImageView status;
    String t;
    Toolbar toolbar;
    public static ArrayList<ImagesClicker> images = new ArrayList<>();
    private static int currentPage = 0;
    String id, category, name, price, vip, number, watched, watch, description, location, date, image="",loved;
    String table;
    Intent intent;
    Handler create;
    String image1="", image2="", image3="";
    public static Handler trans = new Handler();
    ViewFlipper v;
    ImageView img;
    String tabl;
    TextView ot,ot1;
    ArrayList<ImagesClicker> banners;
    Liked_getter lg = new Liked_getter();
    Get_detail_images g = new Get_detail_images();
    private static final Integer[] XMEN = {R.drawable.footbal2, R.drawable.beylekiler2, R.drawable.home2, R.drawable.cafe, R.drawable.realtor};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    TextView categoryt, locationt, descriptiont, pricet, watchedt, datet,
            timet, numbert, kuzowt, probegt, yeart, colort, kreditt, obment, matort, karopkat, mainTitle, mainPrice, mainDate, mainLocation;
 FloatingActionButton floatingActionButton;
    RelativeLayout r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtor_show_details);
        di.set_text();
          r=(RelativeLayout)findViewById(R.id.relative_layout_vip_back_realtor_details);
        TextView textView_1 = (TextView) findViewById(R.id.information_name_in_realtor_show_details);
        textView_1.setText(dil.tm_ginisleyib_maglumat);
        TextView textView_2 = (TextView) findViewById(R.id.yeri_show_detail_realtor);
        textView_2.setText(dil.tm_yerlesyan_yeri);
        TextView textView_3 = (TextView) findViewById(R.id.bahasy_show_detail_realtor);
        textView_3.setText(dil.tm_car_bahasy);
        TextView textView_4 = (TextView) findViewById(R.id.nomeri_show_detail_realtor);
        textView_4.setText(dil.tm_nomeri);
        init();
        ot=(TextView)findViewById(R.id.text_otagy_show_detail_realtor);
        ot1=(TextView)findViewById(R.id.otag_show_detail_realtor);

        db = new Db(MainActivity.ctx);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_realtor_show_details);
        setSupportActionBar(toolbar);
        intent = getIntent();
        images = new ArrayList<>();
        floatingActionButton = (FloatingActionButton) findViewById(R.id.flbut_realtor_show_details);
        GUI();
        Init();
        if(table.equals("beylekiler")){ot1.setVisibility(View.INVISIBLE); ot.setVisibility(View.INVISIBLE);}

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:" + number));
                startActivity(call);
            }
        });

        create = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    Log.d("create", " ");
                    mPager = (ViewPager) findViewById(R.id.viewpager_for_realtor_show_details);
                    mPager.setAdapter(new viewpager_adapter_for_realtor_show_details(realtor_show_details.this, images,table));
                    CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator_realtor_show_details);
                    indicator.setViewPager(mPager);
                }
            }

        };

        create.sendEmptyMessage(1);
        Log.d("trans ", "acyldy");
        trans = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    Log.d("trans", " ");
                    get_local();
                }
            }
        };
        if (table.equals("likedhome")) trans.sendEmptyMessage(1);


        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_realtor_show_details);
        collapsingToolbarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView textView_title = (TextView) findViewById(R.id.maintitle_realtor_show_details);
        final String title = category;
        getSupportActionBar().setTitle(title);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar_realtor_show_details);

        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager_for_realtor_show_details);




        floatingActionButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                /*try {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int k = 100;
                    images.get(0).getImg1().compress(Bitmap.CompressFormat.JPEG, k, baos);
                    byte[] imageBytes = baos.toByteArray();
                    image1 = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                    baos.reset();
                    images.get(1).getImg1().compress(Bitmap.CompressFormat.JPEG, k, baos);
                    imageBytes = baos.toByteArray();
                    image2 = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                    baos.reset();
                    images.get(2).getImg1().compress(Bitmap.CompressFormat.JPEG, k, baos);
                    imageBytes = baos.toByteArray();
                    image3 = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                } catch (NullPointerException s) {
                } catch (IndexOutOfBoundsException s) {
                }*/

                if(loved.equals("0")){
                    loved="1";
                    image1="";image2="";
                   if(images.size()>0) if(images.get(0).getImgG()!=null) image1=images.get(0).getImgG();
                    if(images.size()>1)  if(images.get(1).getImgG()!=null) image2=images.get(1).getImgG();
                    if(images.size()>2) if(images.get(2).getImgG()!=null) image3=images.get(2).getImgG();
           try         {               if(!db.isIn("liked"+table,id)) db.insert_likedaddRealtor("liked"+table, id, category, name, price, description, location, number,image, image1, image2,image3, date,watched,vip);}catch (IndexOutOfBoundsException ss){}
                db.insert_loved(table,id);
                    item.setIcon(R.drawable.gyzyl_like);
                    floatingActionButton.setImageResource(R.drawable.gyzyl_like);
                }else{
                    loved="0";
                    item.setIcon(R.drawable.like);
                    Log.d("Tabr",table);
                    if(tabl.equals("likedhome")){
                        Log.d("Realtor giryar","da");
                        db.delete_like(tabl,id);
                        db.delete_loved("home", id);
                         fragment_halanlarym2.get_local();
                        finish();} else
                    if(tabl.equals("likedbeylekiler")){
                        db.delete_loved("home", id);
                        db.delete_like(tabl,id);
                        fragment_halanlarym3.get_local();
                        finish();}else {
                        db.delete_like("liked" + table, id);
                        db.delete_loved(table, id);
                        floatingActionButton.setImageResource(R.drawable.like);
                    }

                }
            }
        });
        Log.d("name", id + name);
        Log.d("image", " " + image);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                    collapsingToolbarLayout.setTitle("");
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    collapsingToolbarLayout.setTitle(title);
                    showOption(R.id.realtor_show_details_hidden_like);
                } else if (isShow) {
                    isShow = false;
                    collapsingToolbarLayout.setTitle("");
                    hideOption(R.id.realtor_show_details_hidden_like);
                }
            }
        });
        get_banner();
        watchedt.setVisibility(View.INVISIBLE);
        Log.d("dbgeldi", "geldi");
        try {
            Log.d("shuTay",table+"  "+id+" "+db.get_adds_detail_image(table, id).size());
           if (db.get_adds_detail_image(table, id).size() < 1) g.get_detail(table, id, "0");
          else get_local();
        } catch (NullPointerException e) {
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == android.R.id.home) {
         finish();
        }
        if (res_id == R.id.realtor_show_details_hidden_like) {
         /*   try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int k = 100;
                images.get(0).getImg1().compress(Bitmap.CompressFormat.JPEG, k, baos);
                byte[] imageBytes = baos.toByteArray();
                image1 = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                baos.reset();
                images.get(1).getImg1().compress(Bitmap.CompressFormat.JPEG, k, baos);
                imageBytes = baos.toByteArray();
                image2 = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                images.get(2).getImg1().compress(Bitmap.CompressFormat.JPEG, k, baos);
                imageBytes = baos.toByteArray();
                image3 = Base64.encodeToString(imageBytes, Base64.DEFAULT);

            } catch (NullPointerException s) {
            } catch (IndexOutOfBoundsException s) {
            }*/

            if(loved.equals("0")){
                loved="1";
                image1="";image2="";
                if(images.size()>0) if(images.get(0).getImgG()!=null) image1=images.get(0).getImgG();
                if(images.size()>1)  if(images.get(1).getImgG()!=null) image2=images.get(1).getImgG();
                if(images.size()>2) if(images.get(2).getImgG()!=null) image3=images.get(2).getImgG();
                try         {    if(!db.isIn("liked"+table,id)) db.insert_likedaddRealtor("liked"+table, id, category, name, price, description, location, number,image, image1, image2,image3, date,watched,vip);}catch (IndexOutOfBoundsException ss){}
                db.insert_loved(table,id);
                item.setIcon(R.drawable.gyzyl_like);
                floatingActionButton.setImageResource(R.drawable.gyzyl_like);
                Toast.makeText(getBaseContext(), dil.reklama_halanlaryma_gouldy, Toast.LENGTH_SHORT).show();
            }else{   loved="0";
                item.setIcon(R.drawable.like);
                Log.d("Tabr",table);
                if(tabl.equals("likedhome")){
                    Log.d("Realtor giryar","da");
                    db.delete_like(tabl,id);
                    db.delete_loved("home", id);
                    fragment_halanlarym2.get_local();
                    finish();} else
                if(tabl.equals("likedbeylekiler")){
                    db.delete_loved("home", id);
                    db.delete_like(tabl,id);
                    fragment_halanlarym3.get_local();
                    finish();}else {
                    db.delete_like("liked" + table, id);
                    db.delete_loved(table, id);
                    floatingActionButton.setImageResource(R.drawable.like);}

        }}
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.realtor_show_detail_menu, menu);
        this.menu = menu;
        item=menu.findItem(R.id.realtor_show_details_hidden_like);
        if(loved.equals("1"))item.setIcon(R.drawable.gyzyl_like);

        hideOption(R.id.realtor_show_details_hidden_like);
        return true;
    }

    private void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }

    public void GUI() {
        v = (ViewFlipper) findViewById(R.id.small_fliper_for_realtor_show_details);
        call = (FloatingActionButton) findViewById(R.id.flbut2_realtor_show_details);
        toolbar = (Toolbar) findViewById(R.id.toolbar_realtor_show_details);
        mainTitle = (TextView) findViewById(R.id.maintitle_realtor_show_details);
        mainPrice = (TextView) findViewById(R.id.mainprice_far_realtor_show_details);
        mainDate = (TextView) findViewById(R.id.maindata_for_realtor_show_details);
        status = (ImageView) findViewById(R.id.vip_satyldy_gyssagly_icon_realtor_shiow_details);
        pricet = (TextView) findViewById(R.id.text_bahasy_show_detail_realtor);
        locationt = (TextView) findViewById(R.id.text_yeri_show_detail_realtor);
        descriptiont = (TextView) findViewById(R.id.gosmaca_txt_realtor_show_details);
        watchedt = (TextView) findViewById(R.id.mainwatch_for_realtor_show_details);
        numbert = (TextView) findViewById(R.id.text_nomeri_show_detail_realtor);
        mainLocation = (TextView) findViewById(R.id.mainplace_for_realtor_show_details);
        // timet=(TextView)findViewById(R.id.time) ;
        //   obment=(TextView)findViewById(R.id.realtor_obmen);
        // kreditt=(TextView)findViewById(R.id.realtor_credit);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    public void Init() {
        image = intent.getStringExtra("image");
        loved=intent.getStringExtra("loved");
        table = intent.getStringExtra("table");
        tabl=table;
        Log.d("Tablename",table);
        id = intent.getStringExtra("id");
        category = intent.getStringExtra("category");
        name = intent.getStringExtra("name");
        price = intent.getStringExtra("price");
        vip = intent.getStringExtra("vip");
        number = intent.getStringExtra("number");
        watched = intent.getStringExtra("watched");
        watch = intent.getStringExtra("watch");
        description = intent.getStringExtra("description");
        location = intent.getStringExtra("location");
        date = intent.getStringExtra("date");
       locationt.setText(location);
        Log.d("LOVEDs",loved);
       try{if(!table.contains("liked")) if(db.isIn("liked"+table,id))loved="1";}catch (NullPointerException ss){}
        if(loved.equals("1"))  floatingActionButton.setImageResource(R.drawable.gyzyl_like);
       if(table.equals("home")||table.equals("likedhome")) mainTitle.setText( name+" komnat");else
           mainTitle.setText( name);
        mainPrice.setText(price);
        mainDate.setText(date);
        mainLocation.setText(location);
        descriptiont.setText(description);
        pricet.setText(price);
        numbert.setText(number);
        watchedt.setText(watched);

        lg.getter("home", id);
      //  if (table.equals("home")) t = "likedhome";
   //     else if (table.equals("beylekiler")) t = "likedbeylekiler";
    //    if (table.equals("likedhome")) table = "home";
      //  else if (table.equals("likedbeylekiler")) table = "beylekiler";
        if(table.equals("home")) ot.setText(name);
        Log.d("watch", "  " + watch);
        Log.d("watched ", "  " + watched);
        try {
            if (MainActivity.conn) {
                if (watch.equals("0")) {
                    liked_sender l = new liked_sender();
                    Log.d("wathced", "repeat");
                    l.send_liked(table, "watched", id);
                    int k = Integer.parseInt(watched) + 1;
                    db.lwatched(table, "watched", id, k);
                    watchedt.setText("" + k);
                }
            }
            status.setVisibility(View.INVISIBLE);
            if (vip.equals("1")) {
                r.setBackground(getDrawable(R.drawable.gradient_vip));
                status.setImageResource(R.drawable.vip_icon);
                status.setVisibility(View.VISIBLE);
            }
            if (vip.equals("2")) {
                r.setBackground(getDrawable(R.drawable.gradient_gys));
                status.setImageResource(R.drawable.gyssagly_icon);
                status.setVisibility(View.VISIBLE);
            }
            if (vip.equals("4")) {
                r.setBackground(getDrawable(R.drawable.gradient_satyldy));
                status.setImageResource(R.drawable.satyldy_icon);
                status.setVisibility(View.VISIBLE);
            }

        } catch (NullPointerException e) {
        }
    }

    private void init() {
        for (int i = 0; i < XMEN.length; i++)
            XMENArray.add(XMEN[i]);


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

    public void get_local() {
        Log.d("getlOcal", " ");
        Thread get_local = new Thread(new Runnable() {
            @Override
            public void run() {
                images = db.get_adds_detail_image(table, id);
                Log.d("images.size", "" + images.size());
                create.sendEmptyMessage(1);

                Thread.currentThread().interrupt();
            }
        });
        get_local.start();

    }
Handler x=new Handler(){};
    public void get_banner() {
          t=table;
        if (table.equals("likedbeylekiler")) t = "beylekiler";
        if (table.equals("likedhome")) t = "home";

        Thread g=new Thread(new Runnable() {
            @Override
            public void run() {


        banners = new ArrayList<>();
        banners = db.getBanner(t);
                Collections.shuffle(banners);
            x.sendEmptyMessage(1);}
        });
        g.start();
        x=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==1){
            Collections.shuffle(banners);
        for (int i = 0; i < banners.size(); i++) {
            img = new ImageView(getBaseContext());
            //img.setTag(data.get(i).getId());
            img.setClickable(true);
            try {
                if (banners.size() >= i && banners.size() != 0)
                    img.setImageBitmap(banners.get(i).getImg1());
            } catch (IndexOutOfBoundsException s) {
            }
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            final int k = Integer.parseInt(banners.get(i).getId());
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), vip_show_details.class);
                    intent.putExtra("id", "" + k);
                    intent.putExtra("table",table);
                    startActivity(intent);
                }
            });

            v.addView(img);}}
        }};

    }

    @Override
    public void onBackPressed() {
        images.clear();
        mPager.setAdapter(new viewpager_adapter_for_realtor_show_details(realtor_show_details.this, images,table));
   if(tabl.equals("home")) fragment1_realtor.get_local();
      if(tabl.equals("beylekiler")) fragment_beyleki1.get_local();
        finish();
        super.onBackPressed();
    }
}

