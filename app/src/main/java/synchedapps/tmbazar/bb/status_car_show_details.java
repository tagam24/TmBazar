package synchedapps.tmbazar.bb;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.Models.ImagesClicker;
import synchedapps.tmbazar.Network.Get_detail_images;
import synchedapps.tmbazar.Network.Liked_getter;
import synchedapps.tmbazar.Network.liked_sender;
import synchedapps.tmbazar.Network.my_add_updater;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.ViewPagers.image_swipe_adapter_vip_show;
import synchedapps.tmbazar.bb.Beylekiler.Bildiris_gos_Navigation.fragment_bildiris_gosmak1;
import synchedapps.tmbazar.dil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class status_car_show_details extends AppCompatActivity {
    String id, category, price, year, time, model,
            kuzow, probeg, color, mator, karopka,status1,
            location, kredit, obmen, description,
            number, Watched = "", watch = "", date, vip, satyldy, image;
    Button delete, satyld;
    ImageView status;
    Toolbar toolbar;
    dil di=new dil();
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView categoryt, locationt, descriptiont, pricet, watchedt, datet,
            timet,  sms,numbert, kuzowt, probegt, yeart, colort, kreditt, obment, matort, karopkat, mainTitle, mainPrice, mainDate, mainLocation;
    ViewPager viewPager;
    public static ArrayList<ImagesClicker> images = new ArrayList<>();
    Db db;
    Get_detail_images g = new Get_detail_images();
    image_swipe_adapter_vip_show imageSwipeAdapterVipShow;
    Liked_getter lg = new Liked_getter();
    Handler create;
    public static Handler trans;
    FloatingActionButton call;
    ViewFlipper v;
    ImageView img;
    ArrayList<ImagesClicker> banners = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_car_show_details);
        di.set_text();
        vip_show_details.flazhok=1;
        TextView textView_1=(TextView)findViewById(R.id.status_model_show_detail_car);
        textView_1.setText(dil.tm_model);
        TextView textView_2=(TextView)findViewById(R.id.status_Yyly_show_detail_car);
        textView_2.setText(dil.tm_car_yyly);
        TextView textView_3=(TextView)findViewById(R.id.status_Probeg_show_detail_car);
        textView_3.setText(dil.tm_probeg);
        TextView textView_4=(TextView)findViewById(R.id.status_renki_show_detail_car);
        textView_4.setText(dil.tm_car_renki);
        TextView textView_5=(TextView)findViewById(R.id.status_motory_show_detail_car);
        textView_5.setText(dil.tm_mator);
        TextView textView_6=(TextView)findViewById(R.id.status_kuzow_show_detail_car);
        textView_6.setText(dil.tm_kuzow);
        TextView textView_7=(TextView)findViewById(R.id.status_Karobka_show_detail_car);
        textView_7.setText(dil.tm_karopka);
        TextView textView_8=(TextView)findViewById(R.id.status_bahasy_show_detail_car);
        textView_8.setText(dil.tm_car_bahasy);
        TextView textView_9=(TextView)findViewById(R.id.status_nomeri_show_detail_car);
        textView_9.setText(dil.tm_nomeri);
        TextView textView_10=(TextView)findViewById(R.id.status_yeri_show_detail_car);
        textView_10.setText(dil.tm_yerlesyan_yeri);
        TextView textView_11=(TextView)findViewById(R.id.status_kredit_show_detail_car);
        textView_11.setText(dil.tm_kredit);
        TextView textView_12=(TextView)findViewById(R.id.status_obmen_show_detail_car);
        textView_12.setText(dil.tm_obmen);
        TextView textView_13=(TextView)findViewById(R.id.status_model_goyulanwagty_detail_car);
        textView_13.setText(dil.tm_goyuldy);
        TextView textView_14=(TextView)findViewById(R.id.status_information_name_in_car_show_details);
        textView_14.setText(dil.tm_ginisleyib_maglumat);
        TextView textView_15=(TextView)findViewById(R.id.status_text_car_show_details);
        textView_15.setText(dil.status_yagdayy_goyuldy);

        //   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
        v = (ViewFlipper) findViewById(R.id.status_status_fliper_for_car_show_details);
        db = new Db(this);
        create = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    Log.d("create", " ");
                    viewPager = (ViewPager) findViewById(R.id.status_viewpager_for_car_show_details);
                    imageSwipeAdapterVipShow = new image_swipe_adapter_vip_show(images, status_car_show_details.this, "myAdds");
                    viewPager.setAdapter(imageSwipeAdapterVipShow);
                }
            }

        };
        create.sendEmptyMessage(1);

        FloatingActionButton like = (FloatingActionButton) findViewById(R.id.like_button_car);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        GUI();
        init();
        if(status1.equals("1")) {
            textView_15.setText(dil.status_yagdayy_goyuldy);

        } else if(status1.equals("0"))   textView_15.setText(dil.status_yagdayy_garasylyar); else    textView_15.setText(dil.status_yagdayy_kabul_edilmedi);
        get_banner();
        satyld.setText(dil.tm_satyldy);
        satyld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                my_add_updater updater = new my_add_updater();
                if (MainActivity.conn) {
                    Log.d("giryarmi", "da");
                    status.setVisibility(View.VISIBLE);
                    db.sale_myadd("myadds", id);
                    updater.updater_add("cars", "", id);
                    fragment_bildiris_gosmak1.get_local();
                    status.setImageResource(R.drawable.icon_satyldy);
                    Toast.makeText(getBaseContext(), "Kabul edildi", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
        delete.setText(dil.tm_ayyr);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                my_add_updater updater = new my_add_updater();
                if (MainActivity.conn) {
                    updater.updater_add("cars", id, "");
                    db.delete_myAdd("myAdds", id);
                    fragment_bildiris_gosmak1.get_local();
                    Toast.makeText(getBaseContext(), "reklama ayyryldy", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == android.R.id.home) {

            Intent intent = new Intent(getApplicationContext(), car_tab.class);
            startActivity(intent);
        }

        return true;
    }

    public void GUI() {
        sms=(TextView)findViewById(R.id.status_gosmaca);
        satyld = (Button) findViewById(R.id.satyldy_button_status_show_details_car);
        delete = (Button) findViewById(R.id.ayyr_button_status_show_details_car);
        toolbar = (Toolbar) findViewById(R.id.status_toolbar_car_show_details);
        mainTitle = (TextView) findViewById(R.id.status_maintitle_car_show_details);
        mainPrice = (TextView) findViewById(R.id.status_mainprice_far_car_show_details);
        mainDate = (TextView) findViewById(R.id.status_maindata_for_car_show_details);
        status = (ImageView) findViewById(R.id.status_vip_satyldy_gyssagly_icon_car_shiow_details);
        categoryt = (TextView) findViewById(R.id.status_text_model_show_detail_car);
        kuzowt = (TextView) findViewById(R.id.status_text_kuzow_show_detail_car);
        probegt = (TextView) findViewById(R.id.status_text_Probeg_show_detail_car);
        yeart = (TextView) findViewById(R.id.status_text_Yyly_show_detail_car);
        colort = (TextView) findViewById(R.id.status_text_renki_show_detail_car);
        matort = (TextView) findViewById(R.id.status_text_motory_show_detail_car);
        karopkat = (TextView) findViewById(R.id.status_text_Karobka_show_detail_car);
        pricet = (TextView) findViewById(R.id.status_text_bahasy_show_detail_car);
        locationt = (TextView) findViewById(R.id.status_text_yeri_show_detail_car);
        descriptiont = (TextView) findViewById(R.id.status_gosmaca_txt_car_show_details);
        watchedt = (TextView) findViewById(R.id.status_mainwatch_for_car_show_details);
        datet = (TextView) findViewById(R.id.status_text_model_goyulanwagty_detail_car);
        numbert = (TextView) findViewById(R.id.status_text_nomeri_show_detail_car);
        mainLocation = (TextView) findViewById(R.id.status_mainplace_for_car_show_details);
        // timet=(TextView)findViewById(R.id.time) ;
        obment = (TextView) findViewById(R.id.status_text_obmen_show_detail_car);
        kreditt = (TextView) findViewById(R.id.status_text_kredit_show_detail_car);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    public void init() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        image = intent.getStringExtra("image");
        status1=intent.getStringExtra("status");
        category = intent.getStringExtra("category");
        model = intent.getStringExtra("model");
        kuzow = intent.getStringExtra("kuzow");
        probeg = intent.getStringExtra("probeg");
        year = intent.getStringExtra("year");
        color = intent.getStringExtra("color");
        mator = intent.getStringExtra("mator");
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
        getSupportActionBar().setTitle(category + " " + model);
        if(!year.equals("0"))
        mainTitle.setText(category + " " + model + "," + year);else  mainTitle.setText(category + " " + model);
        mainPrice.setText(price);
        categoryt.setText(category);
        List list = new ArrayList();
        String lk="    Bildirlişdäki telefon belgini tassyklamak üçin 20 minudyň dowamynda "+number+" belgiden +99365166566 belgä boş sms ugradyň.\n\n" +
                "Для подтверждения объявления отправьте пустое смс с номера "+number+" на номер +99365166566 в течени 20 минут "+"\n\n"+
                "VIP ýa-da GYSSAGLY Statuslary almak üçin Habarlaşmak bölüminde adminstrator bilen habarlaşyň.\n Чтобы получить статус VIP или СРОЧНО " +
                "свяжитесь с админстратором в разделе Администратор ";
        sms.setText(lk);
        String s = "";
        //if (vip.equals("1")) status.setImageResource(R.drawable.vip);
       // if (vip.equals("2")) status.setImageResource(R.drawable.gyssagly);
        if (satyldy.equals("4   ``" +
                "")) status.setImageResource(R.drawable.icon_satyldy);
        Log.d("satyldy",satyldy);
      if(!kuzow.equals(""))  {if (db.get_dil().equals("tm")) {
            list.addAll(Arrays.asList(getResources().getStringArray(R.array.kyzow_turkmençe)));
            s = list.get(Integer.parseInt(kuzow)).toString();
            kuzowt.setText(s);
        } else {
            list.addAll(Arrays.asList(getResources().getStringArray(R.array.kyzow_ru)));
            s = list.get(Integer.parseInt(kuzow)).toString();
            kuzowt.setText(s);
        }} else    kuzowt.setText("-");
        list.clear();
        if(!probeg.equals(""))probegt.setText(probeg); else probegt.setText("-");
        if(!year.equals("")) yeart.setText(year); else yeart.setText("-");
      if(!color.equals("")) { if (db.get_dil().equals("tm")) {
            list.addAll(Arrays.asList(getResources().getStringArray(R.array.renki_car)));
            s = list.get(Integer.parseInt(color)).toString();
            colort.setText(s);
        } else {
            list.addAll(Arrays.asList(getResources().getStringArray(R.array.renki_car_ru)));
            s = list.get(Integer.parseInt(color)).toString();
            colort.setText(s);
        }}else   colort.setText("-");
        list.clear();
        if(!mator.equals(""))matort.setText(mator);else matort.setText("-");
        if(!karopka.equals("")){if (db.get_dil().equals("tm")) {
            list.addAll(Arrays.asList(getResources().getStringArray(R.array.karopka_car)));
            s = list.get(Integer.parseInt(karopka)).toString();
            karopkat.setText(s);
        } else {
            list.addAll(Arrays.asList(getResources().getStringArray(R.array.karopka_car_ru)));
            s = list.get(Integer.parseInt(karopka)).toString();
            karopkat.setText(s);
        }} else      karopkat.setText("-");
        list.clear();
        pricet.setText(price);
       if(!location.equals("")) {
           if (db.get_dil().equals("tm")) {
               list.addAll(Arrays.asList(getResources().getStringArray(R.array.yeri_car)));
               s = list.get(Integer.parseInt(location)).toString();
               locationt.setText(s);
           } else {
               list.addAll(Arrays.asList(getResources().getStringArray(R.array.yeri_car_ru)));
               s = list.get(Integer.parseInt(location)).toString();
               locationt.setText(s);
           }
       } else        locationt.setText("-");
        list.clear();
        descriptiont.setText(description);
        numbert.setText(number);
//      timet.setText(time);
        datet.setText(date);
        watchedt.setText("++");
        try {
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
                    int k = Integer.parseInt(Watched) + 1;
                    db.lwatched("myadds", "watched", id, k);
                    watchedt.setText("" + k);
                }
            }
        } catch (NullPointerException ss) {
        }
        Log.d("size", " " + db.get_adds_detail_image("myadds", id).size());
        get_local();

    }

    Handler x=new Handler(){};
    public void get_banner() {
        Thread g=new Thread(new Runnable() {
            @Override
            public void run() {
                banners = new ArrayList<>();
                banners = db.getBanner("cars");
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
                                intent.putExtra("table","cars");
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
                images = db.get_adds_detail_image("myadds", id);

                create.sendEmptyMessage(1);
                Thread.currentThread().interrupt();
            }
        });
        get_local.start();

    }

    @Override
    public void onBackPressed() {
        imageSwipeAdapterVipShow = new image_swipe_adapter_vip_show(images, status_car_show_details.this, "myAdds");
     vip_show_details.flazhok=0;
        finish();
    }
}
