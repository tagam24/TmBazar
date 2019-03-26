package synchedapps.tmbazar.bb.Beylekiler.Bildiris_gos_Navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import synchedapps.tmbazar.R;
import synchedapps.tmbazar.ViewPagers.ViewPagerAdapter_for_car;
import synchedapps.tmbazar.bb.Beylekiler.beylekiler_tab;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.bb.Realtor.realtor_tab;
import synchedapps.tmbazar.bb.car_tab;
import synchedapps.tmbazar.bb.lenta_tab;
import synchedapps.tmbazar.chat.Mainchat;
import synchedapps.tmbazar.dil;
import synchedapps.tmbazar.online_market.cities;
import synchedapps.tmbazar.online_market.post_tab.post_tabmarket;


public class bildiris_gos_navidation extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    public static Context context1;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    public static TabLayout.Tab tab;
    public static int tabs_number = 0;
    dil di = new dil();
    MenuItem i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_draw_bildiris_gos);
        di.set_text();
        context1=this;
        toolbar = (Toolbar) findViewById(R.id.toolbar_bildiris_gosmak_sahypa);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(dil.tm_bildirisler);
        Intent i = getIntent();
        if (i.getStringExtra("from") != null) {
            tabs_number = Integer.parseInt(i.getStringExtra("from"));
        }
        drawerLayout = (DrawerLayout) findViewById(R.id.nav_draw_for_bildiris_gos_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_bildiris_gos_view);
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(toogle);
        toogle.syncState();

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        tabLayout = (TabLayout) findViewById(R.id.tablayout_bildiris_gosmak_sahypa);
        viewPager = (ViewPager) findViewById(R.id.ViewPager_bildiris_gosmak_sahypa);
        ViewPagerAdapter_for_car adapter = new ViewPagerAdapter_for_car(getSupportFragmentManager());

        adapter.AddFragment(new fragment_bildiris_gosmak1(), dil.tm_grid_awtoulaglar.toUpperCase());
        adapter.AddFragment(new fragment_bildiris_gosmak2(), dil.tm_grid_realtor.toUpperCase());
        adapter.AddFragment(new fragment_bildiris_gosmak3(), dil.tm_grid_beylekiler.toUpperCase());

        Menu menu = navigationView.getMenu();
        i1 = (MenuItem) menu.findItem(R.id.nav_bildirislerim_menu_1);
        i1.setTitle(dil.tm_nav_Esasy_sahypa);
        i2 = (MenuItem) menu.findItem(R.id.nav_bildirislerim_menu_2);
        i2.setTitle(dil.tm_nav_avtoulaglar);
        i3 = (MenuItem) menu.findItem(R.id.nav_bildirislerim_menu_3);
        i3.setTitle(dil.tm_nav_realtor);
        i4 = (MenuItem) menu.findItem(R.id.nav_bildirislerim_menu_4);
        i4.setTitle(dil.tm_nav_beylekiler);
        i5 = (MenuItem) menu.findItem(R.id.nav_bildirislerim_menu_5);
        i5.setTitle(dil.tm_nav_futbal);
        i6 = (MenuItem) menu.findItem(R.id.nav_bildirislerim_menu_6);
        i6.setTitle(dil.tm_nav_tasinlekler);
        i7 = (MenuItem) menu.findItem(R.id.nav_bildirislerim_menu_7);
        i7.setTitle(dil.tm_nav_gyzyldan_gymmatly);
        i8 = (MenuItem) menu.findItem(R.id.nav_bildirislerim_menu_8);
        i8.setTitle(dil.tm_navbildiri_gos);
        i9 = (MenuItem) menu.findItem(R.id.nav_bildirislerim_menu_9);
        i9.setTitle(dil.tm_nav_dili);
        i10 = (MenuItem) menu.findItem(R.id.nav_bildirislerim_menu_10);
        i10.setTitle(dil.tm_nav_duzgunnama);
        i11 = (MenuItem) menu.findItem(R.id.nav_bildirislerim_menu_11);
        i11.setTitle(dil.tm_nav_habarlas);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tab = tabLayout.getTabAt(tabs_number);
        tab.select();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case (R.id.nav_bildirislerim_menu_1):
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                    case (R.id.nav_bildirislerim_menu_2):
                        Intent intent5 = new Intent(getApplicationContext(), car_tab.class);
                        startActivity(intent5);
                        break;
                    case (R.id.nav_bildirislerim_menu_3):
                        Intent intent2 = new Intent(getApplicationContext(), realtor_tab.class);
                        startActivity(intent2);
                        break;
                    case (R.id.nav_bildirislerim_menu_4):
                        Intent intent3 = new Intent(getApplicationContext(), beylekiler_tab.class);
                        startActivity(intent3);
                        break;
                    case (R.id.nav_bildirislerim_menu_5):
                        Intent intent4 = new Intent(getApplicationContext(), lenta_tab.class);
                        startActivity(intent4);
                        break;
                    case (R.id.nav_bildirislerim_menu_6):
                        Intent intent7 = new Intent(getApplicationContext(), lenta_tab.class);
                        startActivity(intent7);
                        break;
                    case (R.id.nav_bildirislerim_menu_8):
                        Intent intent6 = new Intent(getApplicationContext(), post_tabmarket.class);
                        startActivity(intent6);
                        break;
                    case (R.id.nav_bildirislerim_menu_9):
                        di.dil(bildiris_gos_navidation.context1);
                        break;
                    case (R.id.nav_bildirislerim_menu_11):
                        Intent intent8 = new Intent(getApplicationContext(), Mainchat.class);
                        startActivity(intent8);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
    finish();


        super.onBackPressed();
    }
}
