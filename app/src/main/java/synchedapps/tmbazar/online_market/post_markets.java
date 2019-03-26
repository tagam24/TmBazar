package synchedapps.tmbazar.online_market;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import synchedapps.tmbazar.online_market.adapters.adapter_postmarkets;
import synchedapps.tmbazar.online_market.models.model_premarkets;
import synchedapps.tmbazar.online_market.network.get_minishops;
import synchedapps.tmbazar.online_market.network.get_shops;
import synchedapps.tmbazar.online_market.network.get_shopsByname;
import synchedapps.tmbazar.online_market.post_tab.post_tabmarket;

public class post_markets extends AppCompatActivity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener,SwipeRefreshLayout.OnRefreshListener  {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    adapter_postmarkets ad;
    ArrayList<model_premarkets> list;
    SearchView searchView;
    MenuItem searchItem;
    Toolbar toolbar;
SwipeRefreshLayout s7;
    public  static  Handler s1=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_markets);
        recyclerView = (RecyclerView)findViewById(R.id.post_markets);
        toolbar=(Toolbar)findViewById(R.id.toolbar_post);
        setSupportActionBar(toolbar);
        Constants.iter_id="";
        Constants.iter=true;
        Constants.shopses.clear();
        s7=(SwipeRefreshLayout)findViewById(R.id.swipep1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(Constants.market_category.equals(""))getSupportActionBar().setTitle(Constants.hb); else
            getSupportActionBar().setTitle(Constants.market_category);
        linearLayoutManager = new LinearLayoutManager(this);
        ad=new adapter_postmarkets(this, Constants.shopses);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(ad);
        s7.setOnRefreshListener(this);
        s1=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==1){
                    ad.setData(Constants.shopses);
                s7.setRefreshing(false);}
            }
        };
        s7.setRefreshing(true);
  //if(Constants.hb.equals("Sowda Merkezler")|| Constants.hb.equals("Bazarlar")|| Constants.hb.equals("Dükanlar") )
      get_shops.get_Data();
  //else
     // get_minishops.get_Data();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Constants.iter=true;
        Constants.shopid.clear();
        Constants.iter_id="";
        Constants.market_category="";
        Constants.shopses.clear();
        Intent intent=new Intent(post_markets.this,post_tabmarket.class);
        startActivity(intent);
        finish();
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
        Constants.shopid.clear();
        Constants.shopses=new ArrayList<>();
      //  if(Constants.hb.equals("Sowda Merkezler")|| Constants.hb.equals("Bazarlar")|| Constants.hb.equals("Dükanlar"))
            get_shopsByname.get_Data();//else
         //   get_minishops.get_Data();

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
        Constants.shopid.clear();
        Constants.shopses=new ArrayList<>();
        Constants.shcname=s;
     //   if(Constants.hb.equals("Sowda Merkezler")|| Constants.hb.equals("Bazarlar")|| Constants.hb.equals("Dükanlar"))

            get_shopsByname.get_Data();//else
           // get_minishops.get_Data();

        return false;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == android.R.id.home) {
            Constants.shopid.clear();
            Constants.iter=true;
            Constants.iter_id="";
            Constants.market_category="";
            Intent intent=new Intent(post_markets.this,post_tabmarket.class);
            startActivity(intent);
            finish();

        }
        return true;
    }

    @Override
    public void onRefresh() {
        s7.setRefreshing(false);
    }

}
