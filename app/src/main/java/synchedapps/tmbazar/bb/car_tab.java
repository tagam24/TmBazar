package synchedapps.tmbazar.bb;

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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import synchedapps.tmbazar.Network.vipstatusgetter;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.ViewPagers.ViewPagerAdapter_for_car;
import synchedapps.tmbazar.bb.Beylekiler.Bildiris_gos_Navigation.bildiris_gos_navidation;
import synchedapps.tmbazar.bb.Beylekiler.beylekiler_tab;
import synchedapps.tmbazar.bb.Realtor.realtor_tab;
import synchedapps.tmbazar.bb.halanlarym_reklama.halanlarym_tab;
import synchedapps.tmbazar.chat.Mainchat;
import synchedapps.tmbazar.dil;
import synchedapps.tmbazar.fragment.fragment_car1;
import synchedapps.tmbazar.fragment.fragment_car2;
import synchedapps.tmbazar.instant_notifcars;
import synchedapps.tmbazar.online_market.cities;
import synchedapps.tmbazar.online_market.post_tab.post_tabmarket;

public class car_tab extends AppCompatActivity {
    public static TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    public static int tabs_number = 0;
    public static TabLayout.Tab tab;
    MenuItem item;
    dil d = new dil();
    MenuItem i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11;
    vipstatusgetter getter = new vipstatusgetter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_draw2);
        dil di = new dil();
        di.set_text();
        toolbar = (Toolbar) findViewById(R.id.toolbar_car_sahypa);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(dil.tm_grid_awtoulaglar);
        item = (MenuItem) findViewById(R.id.add_white);

        drawerLayout = (DrawerLayout) findViewById(R.id.nav_draw_for_car_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_car);
        Log.d("nav", "" + navigationView.getId());
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(toogle);
        toogle.syncState();
    getter.vipGetStatus("cars");

        Menu menu = navigationView.getMenu();
        i1 = (MenuItem) menu.findItem(R.id.nav3_menu_1);
        i1.setTitle(dil.tm_nav_Esasy_sahypa);
        i2 = (MenuItem) menu.findItem(R.id.nav3_menu_2);
        i2.setTitle(dil.tm_nav_avtoulaglar);
        i3 = (MenuItem) menu.findItem(R.id.nav3_menu_3);
        i3.setTitle(dil.tm_nav_realtor);
        i4 = (MenuItem) menu.findItem(R.id.nav3_menu_4);
        i4.setTitle(dil.tm_nav_beylekiler);
        i5 = (MenuItem) menu.findItem(R.id.nav3_menu_5);
        i5.setTitle(dil.tm_nav_futbal);
        i6 = (MenuItem) menu.findItem(R.id.nav3_menu_6);
        i6.setTitle(dil.tm_nav_tasinlekler);
        i7 = (MenuItem) menu.findItem(R.id.nav3_menu_7);
        i7.setTitle(dil.tm_nav_gyzyldan_gymmatly);
        i8 = (MenuItem) menu.findItem(R.id.nav3_menu_8);
        i8.setTitle(dil.tm_navbildiri_gos);
        i9 = (MenuItem) menu.findItem(R.id.nav3_menu_9);
        i9.setTitle(dil.tm_nav_dili);
        i10 = (MenuItem) menu.findItem(R.id.nav3_menu_10);
        i10.setTitle(dil.tm_nav_duzgunnama);
        i11 = (MenuItem) menu.findItem(R.id.nav3_menu_11);
        i11.setTitle(dil.tm_nav_habarlas);

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        tabLayout = (TabLayout) findViewById(R.id.tablayout_car_sahypa);
        viewPager = (ViewPager) findViewById(R.id.ViewPager_car_sahypa);
        ViewPagerAdapter_for_car adapter = new ViewPagerAdapter_for_car(getSupportFragmentManager());

        adapter.AddFragment(new fragment_car1(), dil.tm_tablayout_autolugalar_recycle);
        adapter.AddFragment(new fragment_car2(), dil.tm_tablayout_autaulaglar_saylanan_recycle);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tab = tabLayout.getTabAt(tabs_number);
        tab.getPosition();
        tab.select();
        instant_notifcars i = new instant_notifcars();
        i.notif("cars");
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case (R.id.nav3_menu_1):
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                    case (R.id.nav3_menu_2):
                        Intent intent5 = new Intent(getApplicationContext(), car_tab.class);
                        startActivity(intent5);
                        break;
                    case (R.id.nav3_menu_3):
                        Intent intent9 = new Intent(getApplicationContext(), realtor_tab.class);
                        startActivity(intent9);
                        break;
                    case (R.id.nav3_menu_4):
                        Intent intent10 = new Intent(getApplicationContext(), beylekiler_tab.class);
                        startActivity(intent10);
                        break;
                    case (R.id.nav3_menu_5):
                        Intent intent2 = new Intent(getApplicationContext(), lenta_tab.class);
                        MainActivity.tabs_number_lenta=0;
                        startActivity(intent2);
                        break;
                    case (R.id.nav3_menu_6):
                        Intent intent3 = new Intent(getApplicationContext(), lenta_tab.class);
                        MainActivity.tabs_number_lenta=1;
                        startActivity(intent3);
                        break;
                    case (R.id.nav3_menu_7):
                        Intent intent4 = new Intent(getApplicationContext(), post_tabmarket.class);
                        startActivity(intent4);
                        break;
                    case (R.id.nav3_menu_8):
                        Intent intent8 = new Intent(getApplicationContext(), bildiris_gos_navidation.class);
                        startActivity(intent8);
                        break;
                    case (R.id.nav3_menu_9):
                        Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_SHORT).show();
                        d.dil(car_tab.this);
                        break;
                    case (R.id.nav3_menu_11):
                        Intent intent_chat = new Intent(getApplicationContext(), Mainchat.class);
                        startActivity(intent_chat);
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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.toString().equals("add")) {
            Intent intent = new Intent(getApplicationContext(), add_adds_cars.class);
            startActivity(intent);
        }
        if (item.toString().equals("LIKE")) {
            Intent intent = new Intent(getApplicationContext(), halanlarym_tab.class);
            intent.putExtra("tab", "0");
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        fragment_car1.recyclerView.stopScroll();
        fragment_car1.limit = 30;
        fragment_car1.data.clear();
        fragment_car1.empty_cars();
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            MainActivity.tabs_number_main = 1;
            startActivity(intent);

        }
    }

}


