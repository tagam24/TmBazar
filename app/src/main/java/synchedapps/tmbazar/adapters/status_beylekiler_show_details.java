package synchedapps.tmbazar.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.Models.ImagesClicker;
import synchedapps.tmbazar.Network.my_add_updater;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.ViewPagers.image_swipe_adapter_vip_show;
import synchedapps.tmbazar.bb.Beylekiler.Bildiris_gos_Navigation.fragment_bildiris_gosmak2;
import synchedapps.tmbazar.bb.Beylekiler.Bildiris_gos_Navigation.fragment_bildiris_gosmak3;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.bb.reklama_photo_show;
import synchedapps.tmbazar.bb.vip_show_details;
import synchedapps.tmbazar.dil;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Collections;

public class status_beylekiler_show_details extends AppCompatActivity {
    private static ViewPager mPager;
    Menu menu;
    Button satyldy, delete;
    dil di=new dil();
    ImageView status;
    private static int currentPage = 0;
    private static final Integer[] XMEN = {R.drawable.footbal2, R.drawable.beylekiler2, R.drawable.home2, R.drawable.cafe, R.drawable.car1};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    Db db;
    String id, category, name, status1,price, vip, number, watched, watch, description, location, date, image, table;
    TextView sms, categoryt, locationt, descriptiont, pricet, watchedt, datet,
            timet, numbert, kuzowt, probegt, yeart, colort, kreditt, obment, matort, karopkat, mainTitle, mainPrice, mainDate, mainLocation;
    public static ArrayList<ImagesClicker> images = new ArrayList<>();
    Handler create;
    TextView textView_1;
    ArrayList<ImagesClicker> banners=new ArrayList<>();
    ImageView img;
    ViewFlipper v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_beylekiler_show_details);
        di.set_text();
        vip_show_details.flazhok=1;
        satyldy = (Button) findViewById(R.id.satyldy_button_status_show_details_beylekiler);
        delete = (Button) findViewById(R.id.ayyr_button_status_show_details_beylekiler);
        satyldy.setText(dil.tm_satyldy);
        delete.setText(dil.tm_ayyr);
        textView_1=(TextView)findViewById(R.id.status_text_beylekiler_show_details);
        textView_1.setText(dil.status_yagdayy_garasylyar);
        TextView textView_2=(TextView)findViewById(R.id.status_information_name_in_beylekiler_show_details);
        textView_2.setText(dil.tm_ginisleyib_maglumat);
        TextView textView_3=(TextView)findViewById(R.id.status_yeri_show_detail_beylekiler);
        textView_3.setText(dil.tm_yerlesyan_yeri);
        TextView textView_4=(TextView)findViewById(R.id.status_bahasy_show_detail_beylekiler);
        textView_4.setText(dil.tm_car_bahasy);
        TextView textView_5=(TextView)findViewById(R.id.status_nomeri_show_detail_beylekiler);
        textView_5.setText(dil.tm_nomeri);
v=(ViewFlipper)findViewById(R.id.status_status_fliper_for_beylekiler_show_details);

        //  toolbar = (Toolbar) findViewById(R.id.toolbar_realtor_show_details);
        mainTitle = (TextView) findViewById(R.id.status_maintitle_beylekiler_show_details);
        mainPrice = (TextView) findViewById(R.id.status_mainprice_far_beylekiler_show_details);
        mainDate = (TextView) findViewById(R.id.status_maindata_for_beylekiler_show_details);
        status = (ImageView) findViewById(R.id.status_vip_satyldy_gyssagly_icon_beylekiler_shiow_details);
        pricet = (TextView) findViewById(R.id.status_text_bahasy_show_detail_beylekiler);
        locationt = (TextView) findViewById(R.id.status_text_model_show_detail_beylekiler);
        descriptiont = (TextView) findViewById(R.id.status_gosmaca_txt_beylekiler_show_details);
        //   watchedt=(TextView) findViewById(R.id.mainwatch_for_realtor_show_details);
        numbert = (TextView) findViewById(R.id.status_text_nomeri_show_detail_beylekiler);
        mainLocation = (TextView) findViewById(R.id.status_mainplace_for_beylekiler_show_details);
        status.setVisibility(View.INVISIBLE);
         sms=(TextView)findViewById(R.id.status_gosmac);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.status_toolbar_beylekiler_show_details);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new Db(MainActivity.ctx);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.layout_viewpager_status_beylekiler_show_details);
        Init();
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), reklama_photo_show.class);
                intent.putExtra("table", "myAddsB");
                Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });

        TextView textView_title = (TextView) findViewById(R.id.status_maintitle_beylekiler_show_details);
        String title = textView_title.getText().toString();
        getSupportActionBar().setTitle(title);
        init();
        satyldy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                my_add_updater updater = new my_add_updater();
                if (MainActivity.conn) {
                    Log.d("giryarmi", "da");
                    Log.d(table, id);
                    status.setVisibility(View.VISIBLE);
                    db.sale_myadd(table, id);
                    status.setImageResource(R.drawable.icon_satyldy);
                    if (table.contains("myAddsrealtor")) {
                        updater.updater_add("home", "", id);
                        fragment_bildiris_gosmak2.get_local();
                        Log.d("giryarmi", "da1");
                    }
                    if (table.equals("myAddsbeylekiler")) {
                        updater.updater_add("beylekiler", "", id);
                        fragment_bildiris_gosmak3.get_local();
                    }
                    Toast.makeText(getBaseContext(), "Kabul edildi", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                my_add_updater updater = new my_add_updater();
                if (MainActivity.conn) {
                    if (table.equals("myAddsrealtor")) updater.updater_add("home", id, "");
                    if (table.equals("myAddsbeylekiler")) updater.updater_add("beylekiler", id, "");
                    db.delete_myAdd(table, id);
                    if (table.equals("myAddsrealtor")) fragment_bildiris_gosmak2.get_local();
                    if (table.equals("myAddsbeylekiler")) fragment_bildiris_gosmak3.get_local();
                    Toast.makeText(getBaseContext(), "reklama ayyryldy", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

    private void init() {
        for (int i = 0; i < XMEN.length; i++)
            XMENArray.add(XMEN[i]);
        create = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                if (msg.what == 1) {

                    mPager = (ViewPager) findViewById(R.id.status_viewpager_for_beylerkiler_show_details);
                    mPager.setAdapter(new image_swipe_adapter_vip_show(images, status_beylekiler_show_details.this, "0"));
                    CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.status_indicator_beylekiler_show_details);
                    indicator.setViewPager(mPager);
                }
            }
        };
        get_local();
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
   finish();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        return true;
    }

    public void get_local() {
        Log.d("getlOcal", " ");
        Thread get_local = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(table, id);
                images = db.get_adds_detail_image(table, id);

                create.sendEmptyMessage(1);
                Thread.currentThread().interrupt();
            }
        });
        get_local.start();

    }

    public void Init() {
        Intent intent = getIntent();
        status1=intent.getStringExtra("status");
        image = intent.getStringExtra("image");
        table = intent.getStringExtra("table");
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
        Log.d("id", id);
        if(status1.equals("1"))  textView_1.setText(dil.status_yagdayy_goyuldy); else if(status1.equals("0")) textView_1.setText(dil.status_yagdayy_garasylyar);
        else textView_1.setText(dil.status_yagdayy_kabul_edilmedi);
        Log.d("table", table);
        mainTitle.setText( name);
        mainPrice.setText(price);
        mainDate.setText(date);
        mainLocation.setText(location);
        descriptiont.setText(description);
        if(!location.equals(""))locationt.setText(location); else locationt.setText("-");
        pricet.setText(price);
        numbert.setText(number);
        String lk="    Bildirlişdäki telefon belgini tassyklamak üçin 20 minudyň dowamynda "+number+" belgiden +99365166566 belgä boş sms ugradyň.\n\n" +
                "Для подтверждения объявления отправьте пустое смс с номера "+number+" на номер +99365166566 в течени 20 минут "+"\n\n"+
                "VIP ýa-da GYSSAGLY Statuslary almak üçin Habarlaşmak bölüminde adminstrator bilen habarlaşyň.\n Чтобы получить статус VIP или СРОЧНО " +
                "свяжитесь с админстратором в разделе Администратор ";
        sms.setText(lk);
        Log.d("vip", vip);
        if (vip.equals("4")) {
            status.setImageResource(R.drawable.icon_satyldy);
            status.setVisibility(View.VISIBLE);
        }
//        watchedt.setText(watched);
    }
    String t;

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
        super.onBackPressed();
        vip_show_details.flazhok=0;
        finish();
    }
}
