package synchedapps.tmbazar.online_market.post_tab;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;

import synchedapps.tmbazar.R;
import synchedapps.tmbazar.online_market.Constants;

import synchedapps.tmbazar.online_market.adapters.adapter_postmarkets;
import synchedapps.tmbazar.online_market.adapters.adapter_viewPagr;
import synchedapps.tmbazar.online_market.models.model_premarkets;
import synchedapps.tmbazar.online_market.network.get_shops;
import synchedapps.tmbazar.online_market.network.get_shopsByname;
import synchedapps.tmbazar.online_market.pre_market.*;

public class post_tabmarket extends AppCompatActivity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener,SwipeRefreshLayout.OnRefreshListener {
    TabLayout tab;
    TabLayout.Tab tab1;
    static ViewPager viewPager;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    adapter_postmarkets ad;
    ArrayList<model_premarkets> list;
    SearchView searchView;
    MenuItem searchItem;
    Toolbar toolbar;
    public  static Handler s2=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_tabmarket);
        tab=(TabLayout)findViewById(R.id.tab_postmarket);
        toolbar=(Toolbar)findViewById(R.id.toolbarpostmarket);
        Constants.shopses=new ArrayList<>();
        Constants.iter=true;
        Constants.iter_id="";
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Constants.bazar);
        viewPager=(ViewPager)findViewById(R.id.viewpager_postmarket);
        adapter_viewPagr adapter=new adapter_viewPagr(getSupportFragmentManager());
        adapter.AddFragment(new fragment_postmarkets(),"Dükanlar");
        adapter.AddFragment(new categories1(),"Kategoriya");
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
        tab1 = tab.getTabAt(0);
        tab1.select();
        s2=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                finish();
            }
        };
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Constants.market_category="";
        Constants.shop_id="";
        Constants.shopses.clear();
        Constants.market_category="";
        fragment_postmarkets.s1.sendEmptyMessage(1);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
        // searchView.setSearchableInfo
        //     (searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        searchView.requestFocus();
        return true;}

    @Override
    public boolean onClose() {
        Constants.iter_id="";
        Constants.iter=true;
        Constants.shopses.clear();
        Constants.shopid.clear();
        get_shops.get_Data();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        Constants.iter_id="";
        Constants.iter=true;
        Constants.shopses.clear();
        Constants.shcname=s;
        Constants.shopid.clear();
        get_shops.get_Data();
        return false;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == android.R.id.home) {
            Constants.iter_id="";
            Constants.iter=true;
            Constants.shopses.clear();
            Constants.shopid.clear();
            Constants.market_category="";
            finish();
        }
        return true;
    }

    @Override
    public void onRefresh() {

    }
}
