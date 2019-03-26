package synchedapps.tmbazar.bb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import synchedapps.tmbazar.Models.MOdel_lenta_banner;
import synchedapps.tmbazar.Network.Get_bannner;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.ViewPagers.ViewPagerAdapter_for_lenta;
import synchedapps.tmbazar.adapters.adapter_for_recycle_for_lenta;
import synchedapps.tmbazar.bb.Beylekiler.Bildiris_gos_Navigation.bildiris_gos_navidation;
import synchedapps.tmbazar.bb.Beylekiler.beylekiler_tab;
import synchedapps.tmbazar.bb.Realtor.adapter_for_recycle_for_realtor;
import synchedapps.tmbazar.bb.Realtor.realtor_tab;
import synchedapps.tmbazar.chat.Mainchat;
import synchedapps.tmbazar.dil;
import synchedapps.tmbazar.fragment.fragment_lenta1;
import synchedapps.tmbazar.fragment.fragment_lenta2;
import synchedapps.tmbazar.fragment.fragment_lenta3;
import synchedapps.tmbazar.online_market.cities;
import synchedapps.tmbazar.online_market.post_tab.post_tabmarket;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class lenta_tab extends MainActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    public  static Context context;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView img;
    boolean check;
    dil di = new dil();
    public static boolean lenta_path = false;
    MenuItem i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11;
    public static  Handler trans;
    Get_bannner gb=new Get_bannner();
    AVLoadingIndicatorView avi;
    ImageView back_button, forward_button;
    public static ArrayList<MOdel_lenta_banner> banners = new ArrayList<>();
static int k=0;
    ViewFlipper v1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_draw_for_lenta);
        context=this;
        di.set_text();
        toolbar = (Toolbar) findViewById(R.id.toolbar_lenta_sahypa);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(dil.tm_gyzykly_maglumatlar.toUpperCase());
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi_lenta1);
        Log.d("Lenta.tab", "  ");
        drawerLayout = (DrawerLayout) findViewById(R.id.nav_drawlayout_for_lenta);
        navigationView = (NavigationView) findViewById(R.id.nav_view_for_lenta);
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(toogle);
        toogle.syncState();
        back_button = (ImageView) findViewById(R.id.back_button_lenta1);
        forward_button = (ImageView)findViewById(R.id.forward_button_lenta1);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        tabLayout = (TabLayout) findViewById(R.id.tablayout_lenta_sahypa);
        viewPager = (ViewPager) findViewById(R.id.ViewPager_lenta_sahypa);
        ViewPagerAdapter_for_lenta adapter = new ViewPagerAdapter_for_lenta(getSupportFragmentManager());

        adapter.AddFragment(new fragment_lenta1(), dil.tm_grid_futbal.toUpperCase());
        adapter.AddFragment(new fragment_lenta2(), dil.tm_grid_tasinlikler.toUpperCase());
     //   adapter.AddFragment(new fragment_lenta3(), dil.tm_nav_gyzyldan_gymmatly.toUpperCase());

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        TabLayout.Tab tab = tabLayout.getTabAt(MainActivity.tabs_number_lenta);
        tab.select();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                MainActivity.tabs_number_lenta = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        v1 = (ViewFlipper) findViewById(R.id.fliper_for_lenta1);
        Menu menu = navigationView.getMenu();
        i1 = (MenuItem) menu.findItem(R.id.nav_lenta_menu_1);
        i1.setTitle(dil.tm_nav_Esasy_sahypa);
        i2 = (MenuItem) menu.findItem(R.id.nav_lenta_menu_2);
        i2.setTitle(dil.tm_nav_avtoulaglar);
        i3 = (MenuItem) menu.findItem(R.id.nav_lenta_menu_3);
        i3.setTitle(dil.tm_nav_realtor);
        i4 = (MenuItem) menu.findItem(R.id.nav_lenta_menu_4);
        i4.setTitle(dil.tm_nav_beylekiler);
        i5 = (MenuItem) menu.findItem(R.id.nav_lenta_menu_5);
        i5.setTitle(dil.tm_nav_futbal);
        i6 = (MenuItem) menu.findItem(R.id.nav_lenta_menu_6);
        i6.setTitle(dil.tm_nav_tasinlekler);
        i7 = (MenuItem) menu.findItem(R.id.nav_lenta_menu_7);
        i7.setTitle(dil.tm_nav_gyzyldan_gymmatly);
        i8 = (MenuItem) menu.findItem(R.id.nav_lenta_menu_8);
        i8.setTitle(dil.tm_navbildiri_gos);
        i9 = (MenuItem) menu.findItem(R.id.nav_lenta_menu_9);
        i9.setTitle(dil.tm_nav_dili);
        i10 = (MenuItem) menu.findItem(R.id.nav_lenta_menu_10);
        i10.setTitle(dil.tm_nav_duzgunnama);
        i11 = (MenuItem) menu.findItem(R.id.nav_lenta_menu_11);
        i11.setTitle(dil.tm_nav_habarlas);
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi_lenta1);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case (R.id.nav_lenta_menu_1):
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                    case (R.id.nav_lenta_menu_2):
                        Intent intent5 = new Intent(getApplicationContext(), car_tab.class);
                        startActivity(intent5);
                        break;
                    case (R.id.nav_lenta_menu_3):
                        Intent intent9 = new Intent(getApplicationContext(), realtor_tab.class);
                        startActivity(intent9);
                        break;
                    case (R.id.nav_lenta_menu_4):
                        Intent intent10 = new Intent(getApplicationContext(), beylekiler_tab.class);
                        startActivity(intent10);
                        break;
                    case (R.id.nav_lenta_menu_5):
                        Intent intent2 = new Intent(getApplicationContext(), lenta_tab.class);
                        MainActivity.tabs_number_lenta=0;
                        startActivity(intent2);
                        break;
                    case (R.id.nav_lenta_menu_6):
                        Intent intent3 = new Intent(getApplicationContext(), lenta_tab.class);
                        MainActivity.tabs_number_lenta=1;
                        startActivity(intent3);
                        break;
                    case (R.id.nav_lenta_menu_7):
                        Intent intent4 = new Intent(getApplicationContext(), post_tabmarket.class);
                        startActivity(intent4);
                        break;
                    case (R.id.nav_lenta_menu_8):
                        Intent intent6 = new Intent(getApplicationContext(), bildiris_gos_navidation.class);
                        startActivity(intent6);
                        break;
                    case (R.id.nav_lenta_menu_9):
                        di.dil(lenta_tab.context);
                        break;
                    case (R.id.nav_lenta_menu_11):
                        Intent intent8 = new Intent(getApplicationContext(), Mainchat.class);
                        startActivity(intent8);
                        break;
                }
                return false;
            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = v1.getDisplayedChild();

                if (index > 0) {
                    v1.setDisplayedChild(index - 1);
                }
            }
        });
        forward_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = v1.getDisplayedChild();
                if (index < 2) {
                    v1.setDisplayedChild(index + 1);
                }
            }
        });

        Menu menu1=navigationView.getMenu();
        MenuItem item=menu1.getItem(7);
        SpannableString spanString = new SpannableString(item.getTitle().toString());
        spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.red)), 0, spanString.length(), 0);
        item.setTitle(spanString);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case (R.id.lenta_toolbar_like):
                        lenta_path = true;
                        Intent intent4 = new Intent(getApplicationContext(), like_lenta_activity.class);
                        startActivity(intent4);

                }
                return false;
            }
        });
        trans = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    //     avi.hide();
                    check = true;
                    get_banner();
                    try {
                        fragment_lenta2.trans.sendEmptyMessage(1);
                        fragment_lenta3.trans.sendEmptyMessage(1);
                    } catch (NullPointerException ee) {
                    }
                }
            }
        };
        gb.get_banner();
        get_banner();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_for_toolbar_lenta, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        adapter_for_recycle_for_lenta.b=false;
       if(fragment_lenta1.recyclerView!=null) {fragment_lenta1.recyclerView.removeAllViews();
        fragment_lenta1.recyclerView.stopScroll();}
        if(fragment_lenta2.recyclerView!=null) {fragment_lenta2.recyclerView.removeAllViews();
            fragment_lenta2.recyclerView.stopScroll();}
        if(fragment_lenta3.recyclerView!=null) {fragment_lenta3.recyclerView.removeAllViews();
            fragment_lenta3.recyclerView.stopScroll();}
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            MainActivity.tabs_number_main = 1;
            startActivity(intent);

        }
    }

    Thread get_bann;

    public void get_banner() {
          final Handler x = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    v1.removeAllViews();
                    Log.d("bannersize", " bb" + banners.size());
                    Collections.shuffle(banners,new Random());
                      avi.hide();
                    for (int i = 0; i < banners.size(); i++) {
                        img = new ImageView(lenta_tab.this);
                        img.setTag(banners.get(i).getId());
                        img.setClickable(true);
                        try {
                            if (banners.size() >= i && banners.size() != 0);
                            img.setImageBitmap(banners.get(i).getImage1());
                        } catch (IndexOutOfBoundsException s) {
                        }
                        img.setScaleType(ImageView.ScaleType.FIT_XY);
                        k = i;
                        Log.d("knace", "" + banners.get(k).getId() + " " + banners.get(k).getNumber());
                        img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.d("knace", "" + k);
                                Intent intent = new Intent(lenta_tab.this, vip_show_details.class);
                                intent.putExtra("id", "" + view.getTag());
                                intent.putExtra("category", banners.get(k).getTitle());
                                intent.putExtra("number", banners.get(k).getNumber());
                                intent.putExtra("description", banners.get(k).getDescription());
                                intent.putExtra("index", "" + k);
                                Log.d("index of", "" + banners.indexOf(1));

                                intent.putExtra("table", "bannerlenta");
                                startActivity(intent);
                                //Log.d("idclick",""+view.getTag());*/
                            }
                        });
                        v1.addView(img);
                    }
                }
            }
        };
        get_bann = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("banner s db", "girdi");
                banners = new ArrayList<>();
                banners = db.getBannerLenta();
                x.sendEmptyMessage(1);
                Thread.currentThread().interrupt();

            }
        });
        if (banners.size() == 0) get_bann.start();
        else if (check) {
            check = false;
            get_bann.start();
        } else x.sendEmptyMessage(1);

    }
}
