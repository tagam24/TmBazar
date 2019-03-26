package synchedapps.tmbazar.online_market.pre_market;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import synchedapps.tmbazar.R;
import synchedapps.tmbazar.online_market.Constants;

import synchedapps.tmbazar.online_market.adapters.adapter_viewPagr;


public class markets extends AppCompatActivity {
   static ViewFlipper viewFlipper;
    TabLayout tab;
    TabLayout.Tab tab1;
    ViewPager viewPager;
    ArrayList<ImageView> imageViews;
    ImageView forward_button,back_button;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markets);
        tab=(TabLayout)findViewById(R.id.tab_market);
        viewPager=(ViewPager)findViewById(R.id.viewpager_market);
      toolbar=(Toolbar)findViewById(R.id.toolbar_markets);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Constants.hb);
        Constants.bazarlars.clear();;
        adapter_viewPagr adapter=new adapter_viewPagr(getSupportFragmentManager());
        adapter.AddFragment(new fragment_markets(),Constants.hb);
        adapter.AddFragment(new categories(),"Kategoriya");
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
        tab1 = tab.getTabAt(0);
        tab1.select();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Constants.market_category="";
        Constants.bazar="";
        Constants.iter_id="";
        Constants.iter=true;
        Constants.barazid.clear();
        Constants.market_category="";
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == android.R.id.home) {
            Constants.iter=true;
            Constants.iter_id="";
            Constants.market_category="";
            Constants.bazar="";
            Constants.bazarlars.clear();
            Constants.barazid.clear();
            Constants.market_category="";
          finish();
        }
        return true;
    }

}
