package synchedapps.tmbazar.bb.Beylekiler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import synchedapps.tmbazar.Network.vipstatusgetter;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.ViewPagers.ViewPagerAdapter_for_car;
import synchedapps.tmbazar.bb.Beylekiler.Bildiris_gos_Navigation.bildiris_gos_navidation;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.bb.Realtor.adapter_for_recycle_for_realtor;
import synchedapps.tmbazar.bb.Realtor.realtor_tab;
import synchedapps.tmbazar.bb.car_tab;
import synchedapps.tmbazar.bb.halanlarym_reklama.halanlarym_tab;
import synchedapps.tmbazar.bb.lenta_tab;
import synchedapps.tmbazar.chat.Mainchat;
import synchedapps.tmbazar.dil;
import synchedapps.tmbazar.online_market.cities;
import synchedapps.tmbazar.online_market.post_tab.post_tabmarket;


public class beylekiler_tab extends AppCompatActivity {
    public static TabLayout tabLayout;
    ViewPager viewPager;
    dil di = new dil();
    Toolbar toolbar;
    public  static Context ctx;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    public static TabLayout.Tab tab;
    vipstatusgetter v = new vipstatusgetter();
    MenuItem i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_draw_beylekiler);
        di.set_text();
        ctx=this;
        v.vipGetStatus("beylekiler");
        toolbar = (Toolbar) findViewById(R.id.toolbar_beylekiler_sahypa);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(dil.tm_grid_beylekiler.toUpperCase());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        drawerLayout = (DrawerLayout) findViewById(R.id.nav_draw_for_beylekiler_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_beylekiler_view);
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(toogle);
        toogle.syncState();

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        tabLayout = (TabLayout) findViewById(R.id.tablayout_beylekiler_sahypa);
        viewPager = (ViewPager) findViewById(R.id.ViewPager_beylekiler_sahypa);
        ViewPagerAdapter_for_car adapter = new ViewPagerAdapter_for_car(getSupportFragmentManager());
        tabLayout.setSelected(true);


        adapter.AddFragment(new fragment_beyleki1(), dil.tm_tablayout_autolugalar_recycle.toUpperCase());
        adapter.AddFragment(new fragment_beyleki2(), dil.tm_tablayout_autaulaglar_saylanan_recycle.toUpperCase());
        adapter.AddFragment(new fragment_beyleki3(), dil.tm_yerlesyan_yeri.toUpperCase());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tab = tabLayout.getTabAt(0);
//        tab.getPosition();
        //   fragment_beyleki1.h.sendEmptyMessage(0);
        tab.select();
        Menu menu = navigationView.getMenu();
        i1 = (MenuItem) menu.findItem(R.id.nav_beylekiler_menu_1);
        i1.setTitle(dil.tm_nav_Esasy_sahypa);
        i2 = (MenuItem) menu.findItem(R.id.nav_beylekiler_menu_2);
        i2.setTitle(dil.tm_nav_avtoulaglar);
        i3 = (MenuItem) menu.findItem(R.id.nav_beylekiler_menu_3);
        i3.setTitle(dil.tm_nav_realtor);
        i4 = (MenuItem) menu.findItem(R.id.nav_beylekiler_menu_4);
        i4.setTitle(dil.tm_nav_beylekiler);
        i5 = (MenuItem) menu.findItem(R.id.nav_beylekiler_menu_5);
        i5.setTitle(dil.tm_nav_futbal);
        i6 = (MenuItem) menu.findItem(R.id.nav_beylekiler_menu_6);
        i6.setTitle(dil.tm_nav_tasinlekler);
        i7 = (MenuItem) menu.findItem(R.id.nav_beylekiler_menu_7);
        i7.setTitle(dil.tm_nav_gyzyldan_gymmatly);
        i8 = (MenuItem) menu.findItem(R.id.nav_beylekiler_menu_8);
        i8.setTitle(dil.tm_navbildiri_gos);
        i9 = (MenuItem) menu.findItem(R.id.nav_beylekiler_menu_9);
        i9.setTitle(dil.tm_nav_dili);
        i10 = (MenuItem) menu.findItem(R.id.nav_beylekiler_menu_10);
        i10.setTitle(dil.tm_nav_duzgunnama);
        i11 = (MenuItem) menu.findItem(R.id.nav_beylekiler_menu_11);
        i11.setTitle(dil.tm_nav_habarlas);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case (R.id.nav_beylekiler_menu_1):
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                    case (R.id.nav_beylekiler_menu_2):
                        Intent intent5 = new Intent(getApplicationContext(), car_tab.class);
                        startActivity(intent5);
                        break;
                    case (R.id.nav_beylekiler_menu_3):
                        Intent intent9 = new Intent(getApplicationContext(), realtor_tab.class);
                        startActivity(intent9);
                        break;
                    case (R.id.nav_beylekiler_menu_4):
                        Intent intent10 = new Intent(getApplicationContext(), beylekiler_tab.class);
                        startActivity(intent10);
                        break;
                    case (R.id.nav_beylekiler_menu_5):
                        Intent intent2 = new Intent(getApplicationContext(), lenta_tab.class);
                        MainActivity.tabs_number_lenta=0;
                        startActivity(intent2);
                        break;
                    case (R.id.nav_beylekiler_menu_6):
                        Intent intent3 = new Intent(getApplicationContext(), lenta_tab.class);
                        MainActivity.tabs_number_lenta=1;
                        startActivity(intent3);
                        break;
                    case (R.id.nav_beylekiler_menu_7):
                        Intent intent4 = new Intent(getApplicationContext(), post_tabmarket.class);
                        startActivity(intent4);
                        break;
                    case (R.id.nav_beylekiler_menu_8):
                        Intent intent6 = new Intent(getApplicationContext(), bildiris_gos_navidation.class);
                        startActivity(intent6);
                        break;
                    case (R.id.nav_beylekiler_menu_9):
                        di.dil(beylekiler_tab.ctx);
                        break;
                    case (R.id.nav_beylekiler_menu_11):
                        Intent intent8 = new Intent(getApplicationContext(), Mainchat.class);
                        startActivity(intent8);
                        break;
                }
                return false;
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
                    case (R.id.add_white_tab_beylekiler):
                        Intent intent4 = new Intent(getApplicationContext(), beylekiler_add_reklama.class);
                        startActivity(intent4);
                        break;
                    case (R.id.like_tab_beylekiler):
                        Intent intent5 = new Intent(getApplicationContext(), halanlarym_tab.class);
                        intent5.putExtra("tab", "2");
                        startActivity(intent5);
                        break;

                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab_beylekiler, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
    fragment_beyleki1.limit=30;
        adapter_for_recycle_for_realtor.b=false;
        fragment_beyleki1.empty();
        fragment_beyleki1.data.clear();
        fragment_beyleki1.recyclerView.stopScroll();
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            MainActivity.tabs_number_main = 1;
            startActivity(intent);

        }
        finish();
    }

}
