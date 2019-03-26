package synchedapps.tmbazar.bb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
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
import synchedapps.tmbazar.bb.Realtor.viewpager_adapter_for_realtor_show_details;
import synchedapps.tmbazar.bb.halanlarym_reklama.fragment_halanlarym1;
import synchedapps.tmbazar.dil;
import synchedapps.tmbazar.fragment.fragment_car1;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShowDetailsadds extends AppCompatActivity {
    String id, category, price, year, time, model,
            kuzow, probeg, color, mator, karopka,
            location, kredit, obmen, description,
            number, Watched = "", watch = "", date, loved = "", vip, satyldy, image;

    ImageView status;
    Toolbar toolbar;
    Menu menu;
    MenuItem item;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView categoryt, locationt, descriptiont, pricet, watchedt, datet,
            timet, numbert, kuzowt, probegt, yeart, colort, kreditt, obment, matort, karopkat, mainTitle, mainPrice, mainDate, mainLocation;
    ViewPager viewPager;
    public static ArrayList<ImagesClicker> images = new ArrayList<>();
    Db db;
    dil di = new dil();
    Get_detail_images g = new Get_detail_images();
    viewpager_adapter_for_realtor_show_details imageSwipeAdapterVipShow;
    Liked_getter lg = new Liked_getter();
    Handler create;
    public static Handler trans;
    FloatingActionButton call;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ViewFlipper v;
    MenuItem menuItem;
    ImageView img;
    ArrayList<ImagesClicker> banners = new ArrayList<>();
    FloatingActionButton like;
    String table;
RelativeLayout r1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cars_detail);
        di.set_text();
        //   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);

        TextView textView_gosmaca = (TextView) findViewById(R.id.information_name_in_car_show_details);
        textView_gosmaca.setText(dil.tm_ginisleyib_maglumat);
        TextView textView_model = (TextView) findViewById(R.id.model_show_detail_car);
        textView_model.setText(dil.tm_model);
        TextView textView_yyly = (TextView) findViewById(R.id.Yyly_show_detail_car);
        textView_yyly.setText(dil.tm_car_yyly);
        TextView textView_probeg = (TextView) findViewById(R.id.Probeg_show_detail_car);
        textView_probeg.setText(dil.tm_probeg);
        TextView textView_renki = (TextView) findViewById(R.id.renki_show_detail_car);
        textView_renki.setText(dil.tm_car_renki);
        TextView textView_mator = (TextView) findViewById(R.id.motory_show_detail_car);
        textView_mator.setText(dil.tm_mator);
        TextView textView_kuzow = (TextView) findViewById(R.id.kuzow_show_detail_car);
        textView_kuzow.setText(dil.tm_kuzow);
        TextView textView_karobka = (TextView) findViewById(R.id.Karobka_show_detail_car);
        textView_karobka.setText(dil.tm_karopka);
        TextView textView_bahasy = (TextView) findViewById(R.id.bahasy_show_detail_car);
        textView_bahasy.setText(dil.tm_car_bahasy);
        TextView textView_nomer = (TextView) findViewById(R.id.nomeri_show_detail_car);
        textView_nomer.setText(dil.tm_nomeri);
        TextView textView_kredit = (TextView) findViewById(R.id.kredit_show_detail_car);
        textView_kredit.setText(dil.tm_kredit);
        TextView textView_obmen = (TextView) findViewById(R.id.obmen_show_detail_car);
        textView_obmen.setText(dil.tm_obmen);
        TextView textView_goyuldy = (TextView) findViewById(R.id.model_goyulanwagty_detail_car);
        textView_goyuldy.setText(dil.tm_goyuldy);
        TextView textView_yeri = (TextView) findViewById(R.id.yeri_show_detail_car);
        textView_yeri.setText(dil.tm_yerlesyan_yeri);
   r1=(RelativeLayout)findViewById(R.id.back_1);

        v = (ViewFlipper) findViewById(R.id.car_vip_flipper);
        db = new Db(this);
        create = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    Log.d("create", " ");
                    viewPager = (ViewPager) findViewById(R.id.car_viewPager);
                    imageSwipeAdapterVipShow=new viewpager_adapter_for_realtor_show_details( ShowDetailsadds.this,images, "cars");
                    viewPager.setAdapter(imageSwipeAdapterVipShow);
                    CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator_car_show_details);
                    indicator.setViewPager(viewPager);
                }
            }

        };
        create.sendEmptyMessage(1);

        like = (FloatingActionButton) findViewById(R.id.like_button_car);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        GUI();
        init();
        get_banner();
        if(vip.equals("1"))r1.setBackground(getDrawable(R.drawable.gradient_vip));
        if(vip.equals("2"))r1.setBackground(getDrawable(R.drawable.gradient_gys));
        if(vip.equals("4"))r1.setBackground(getDrawable(R.drawable.gradient_satyldy));
        trans = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    Log.d("trans", " ");
                    get_local();
                }
            }
        };


        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:" + number));
                startActivity(call);
            }
        });
        watchedt.setVisibility(View.INVISIBLE);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loved.equals("0")) {
                    loved = "1";
                    //  menu=toolbar.getMenu();
                    //  menuItem=menu.getItem(R.id.car_show_details_hidden_like);
                    //  menuItem.setIcon(R.drawable.gyzyl_like);
                    item.setIcon(R.drawable.gyzyl_like);
                    like.setImageResource(R.drawable.gyzyl_like);
                    db.insert_loved("cars", id);
                   if(!db.isIn("likedAdds",id)) db.insert_Likedadds("likeAdds", id, category, model, price, description, location, vip, time, number, Watched, date, year, kredit,
                            obmen, satyldy, probeg, kuzow, color, karopka, mator, image);
                    Toast.makeText(getBaseContext(), dil.reklama_halanlaryma_gouldy, Toast.LENGTH_SHORT).show();
                } else {
                    item.setIcon(R.drawable.like);
                    like.setImageResource(R.drawable.like);
                    loved = "0";
                    db.delete_loved("cars", id);
                    db.delete_like("likedAdds", id);
                    //   menu=toolbar.getMenu();
//                    menuItem=menu.getItem(R.id.car_show_details_hidden_like);
                    //     menuItem.setIcon(R.drawable.like);
                    Toast.makeText(getBaseContext(), dil.reklama_halanlarymdan_ayryldy, Toast.LENGTH_SHORT).show();
                    if (table.equals("likedAdds")) {
                        fragment_halanlarym1.get_local();
                        finish();
                    }
                }

            }
        });


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == android.R.id.home) {
            if(table.equals("cars"))fragment_car1.get_local();
            if(table.equals("likedAdds"))fragment_halanlarym1.get_local();
           finish();
        }
        if (res_id == R.id.car_show_details_hidden_like) {
            menuItem = item;
            if (loved.equals("0")) {
                loved = "1";
                db.insert_loved("cars", id);
                if(!db.isIn("likedAdds",id))   db.insert_Likedadds("likeAdds", id, category, model, price, description, location, vip, time, number, Watched, date, year, kredit,
                        obmen, satyldy, probeg, kuzow, color, karopka, mator, image);
                Toast.makeText(getBaseContext(), dil.reklama_halanlaryma_gouldy, Toast.LENGTH_SHORT).show();
                menuItem.setIcon(R.drawable.gyzyl_like);
                like.setImageResource(R.drawable.gyzyl_like);

            } else if (loved.equals("1")) {
                loved = "0";
                db.delete_loved("cars", id);
                db.delete_like("likedAdds", id);

                Toast.makeText(getBaseContext(), dil.reklama_halanlarymdan_ayryldy, Toast.LENGTH_SHORT).show();
                menuItem.setIcon(R.drawable.like);
                like.setImageResource(R.drawable.like);
                Log.d("table", table);
                if (table.equals("likedAdds")) {
                    fragment_halanlarym1.get_local();
                    finish();
                }
            }
        }

        return true;
    }

    public void GUI() {

        call = (FloatingActionButton) findViewById(R.id.car_call);
        toolbar = (Toolbar) findViewById(R.id.toolCars);
        mainTitle = (TextView) findViewById(R.id.maintitle_car_show_details);
        mainPrice = (TextView) findViewById(R.id.mainprice_far_car);
        mainDate = (TextView) findViewById(R.id.car_maind_date);
        status = (ImageView) findViewById(R.id.car_status);
        categoryt = (TextView) findViewById(R.id.car_category);
        kuzowt = (TextView) findViewById(R.id.car_kuzow);
        probegt = (TextView) findViewById(R.id.car_probeg);
        yeart = (TextView) findViewById(R.id.car_year);
        colort = (TextView) findViewById(R.id.car_color);
        matort = (TextView) findViewById(R.id.car_mator);
        karopkat = (TextView) findViewById(R.id.car_karopka);
        pricet = (TextView) findViewById(R.id.car_price);
        locationt = (TextView) findViewById(R.id.car_location);
        descriptiont = (TextView) findViewById(R.id.car_description);
        watchedt = (TextView) findViewById(R.id.watched);

        datet = (TextView) findViewById(R.id.car_date);
        numbert = (TextView) findViewById(R.id.car_nomer);
        mainLocation = (TextView) findViewById(R.id.car_main_location);
        // timet=(TextView)findViewById(R.id.time) ;
        obment = (TextView) findViewById(R.id.car_obmen);
        kreditt = (TextView) findViewById(R.id.car_credit);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar_car_show_details);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_car_show_details);
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
                    collapsingToolbarLayout.setTitle(category + " " + model + "," + year);
                    showOption(R.id.car_show_details_hidden_like);
                } else if (isShow) {
                    isShow = false;
                    collapsingToolbarLayout.setTitle("");
                    hideOption(R.id.car_show_details_hidden_like);
                }
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    public void init() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        loved = intent.getStringExtra("loved");
        if(db.isIn("likedAdds",id)) loved="1";
        if (loved.equals("1")) {
            like.setImageResource(R.drawable.gyzyl_like);
            // menu=toolbar.getMenu();
            //  menuItem=menu.getItem(R.id.car_show_details_hidden_like);
            // menuItem.setIcon(R.drawable.gyzyl_like);
        }

        image = intent.getStringExtra("image");
        category = intent.getStringExtra("category");
        model = intent.getStringExtra("model");
        kuzow = intent.getStringExtra("kuzow");
        probeg = intent.getStringExtra("probeg");
        year = intent.getStringExtra("year");
        color = intent.getStringExtra("color");
        mator = intent.getStringExtra("mator");
        table = intent.getStringExtra("table");
        karopka = intent.getStringExtra("karopka");
        price = intent.getStringExtra("price");
        location = intent.getStringExtra("location");
        description = intent.getStringExtra("description");
        number = intent.getStringExtra("number");
        time = intent.getStringExtra("time");
        date = intent.getStringExtra("date");
        watch = intent.getStringExtra("watch");//is wathced
        Watched = intent.getStringExtra("watched");
        kredit = intent.getStringExtra("credit");
        obmen = intent.getStringExtra("obmen");
        vip = intent.getStringExtra("vip");
        satyldy = intent.getStringExtra("satyldy");
        if (MainActivity.conn) lg.getter("cars", id);
        if(year.equals("0"))
        mainTitle.setText(category + " " + model);else mainTitle.setText(category + " " + model+","+year);
        mainPrice.setText(price);
        mainDate.setText(date);
        categoryt.setText(category);
        List list = new ArrayList();
        String s = "";
        if (!kuzow.equals("")) {
            if (db.get_dil().equals("tm")) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.kyzow_turkmençe)));
                s = list.get(Integer.parseInt(kuzow)).toString();
                kuzowt.setText(s);
            } else {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.kyzow_ru)));
                s = list.get(Integer.parseInt(kuzow)).toString();
                kuzowt.setText(s);
            }
        } else kuzowt.setText("-");
        list.clear();
        if (vip.equals("1")) status.setImageResource(R.drawable.vip_icon);
        if (vip.equals("2")) status.setImageResource(R.drawable.gyssagly_icon);
        if (vip.equals("4")) status.setImageResource(R.drawable.satyldy_icon);
        Log.d("color", " " + color);
        if (!color.equals("")) {
            if (db.get_dil().equals("tm")) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.renki_car)));
                s = list.get(Integer.parseInt(color)).toString();
                colort.setText(s);
            } else {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.renki_car_ru)));
                s = list.get(Integer.parseInt(color)).toString();
                colort.setText(s);
            }
        } else colort.setText("-");
        list.clear();
        matort.setText(mator);
        if (!karopka.equals("")) {
            if (db.get_dil().equals("tm")) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.karopka_car)));
                s = list.get(Integer.parseInt(karopka)).toString();
                karopkat.setText(s);
            } else {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.karopka_car_ru)));
                s = list.get(Integer.parseInt(karopka)).toString();
                karopkat.setText(s);
            }
        } else karopkat.setText("-");
        list.clear();
        pricet.setText(price);
        if (!location.equals("")) {
            if (db.get_dil().equals("tm")) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.yeri_car)));
                s = list.get(Integer.parseInt(location)).toString();
                locationt.setText(s);
                mainLocation.setText(s);
            } else {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.yeri_car_ru)));
                s = list.get(Integer.parseInt(location)).toString();
                locationt.setText(s);
                mainLocation.setText(s);
            }
        } else {
            locationt.setText("-");
            mainLocation.setText("-");
        }
        list.clear();

     if(!probeg.equals(""))   probegt.setText(probeg); else probegt.setText("-");
       if(!year.equals("0")) yeart.setText(year); else yeart.setText("-");
       if(!mator.equals("")) matort.setText(mator); else matort.setText("-");

        pricet.setText(price);
        descriptiont.setText(description);
        numbert.setText(number);
//      timet.setText(time);
        datet.setText(date);
        watchedt.setText(Watched);
        if (db.get_dil().equals("tm")) {
            if (obmen.equals("1")) obment.setText("Hawa");
            else obment.setText("Yok");
            if (kredit.equals("1")) kreditt.setText("Hawa");
            else kreditt.setText("Yok");
        } else {
            if (obmen.equals("1")) obment.setText("Да");
            else obment.setText("Нет");
            if (kredit.equals("1")) kreditt.setText("Да");
            else kreditt.setText("Нет");
        }

        if (MainActivity.conn) {
            if (watch.equals("0")) {
                liked_sender l = new liked_sender();
                Log.d("wathced", "repeat");
                l.send_liked("cars", "watched", id);

                if(Watched.contains(" "))Watched=Watched.substring(1,Watched.length());
                int k = Integer.parseInt(Watched) + 1;
                db.lwatched("cars", "watched", id, k);
                watchedt.setText("" + k);
            }
        }
        Log.d("size", " " + db.get_adds_detail_image("cars", id).size());
        if (db.get_adds_detail_image("cars", id).size() < 1) g.get_detail("cars", id, "0");
        else get_local();

    }
    Handler x=new Handler(){};
    public void get_banner() {
        Thread g=new Thread(new Runnable() {
            @Override
            public void run() {
                banners = new ArrayList<>();
                banners = db.getBanner("cars");
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


    public void get_local() {
        Log.d("getlOcal", " ");
        Thread get_local = new Thread(new Runnable() {
            @Override
            public void run() {
                images = db.get_adds_detail_image("cars", id);
                Log.d("imagesize", " " + images.size());
                create.sendEmptyMessage(1);
                Thread.currentThread().interrupt();
            }
        });
        get_local.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.car_show_detail_menu, menu);
        this.menu = menu;
        MenuItem item=menu.findItem(R.id.car_show_details_hidden_like);
        this.item=item;
        if(loved.equals("1"))item.setIcon(R.drawable.gyzyl_like);

        hideOption(R.id.car_show_details_hidden_like);
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

    @Override
    public void onBackPressed() {
        images.clear();
    //    imageSwipeAdapterVipShow=new viewpager_adapter_for_realtor_show_details( ShowDetailsadds.this,images, "cars");
        imageSwipeAdapterVipShow.notifyDataSetChanged();
       if(table.equals("cars"))fragment_car1.get_local();
        if(table.equals("likedAdds"))fragment_halanlarym1.get_local();
        finish();
        super.onBackPressed();
    }
}
