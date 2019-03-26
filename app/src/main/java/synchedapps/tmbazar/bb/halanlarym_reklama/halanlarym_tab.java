package synchedapps.tmbazar.bb.halanlarym_reklama;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import synchedapps.tmbazar.R;
import synchedapps.tmbazar.ViewPagers.ViewPagerAdapter_for_car;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.dil;

public class halanlarym_tab extends AppCompatActivity {
    Toolbar toolbar;
    dil di = new dil();
    TabLayout tabLayout;
    ViewPager viewPager;
    TabLayout.Tab tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halanlarym_tab);
        di.set_text();
        toolbar = (Toolbar) findViewById(R.id.toolbar_halanlarym_sahypa);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(dil.tm_halanlarym.toUpperCase());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.tablayout_halanlarym_sahypa);
        viewPager = (ViewPager) findViewById(R.id.ViewPager_halanlarym_sahypa);
        ViewPagerAdapter_for_car adapter = new ViewPagerAdapter_for_car(getSupportFragmentManager());
        Intent i = getIntent();
        adapter.AddFragment(new fragment_halanlarym1(), dil.tm_grid_awtoulaglar.toUpperCase());
        adapter.AddFragment(new fragment_halanlarym2(), dil.tm_grid_realtor.toUpperCase());
        adapter.AddFragment(new fragment_halanlarym3(), dil.tm_grid_beylekiler.toUpperCase());
        String num = i.getStringExtra("tab");
        if (num == null) num = "0";
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        try {
            tab = tabLayout.getTabAt(Integer.parseInt(num));
            tab.getPosition();
            tab.select();
        } catch (NullPointerException s) {
        }
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


    @Override
    public void onBackPressed() {
        fragment_halanlarym1.limit=30;
        fragment_halanlarym2.limit=30;
        fragment_halanlarym3.limit=30;
     finish();
        super.onBackPressed();
    }
}
