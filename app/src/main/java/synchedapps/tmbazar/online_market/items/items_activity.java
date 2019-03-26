package synchedapps.tmbazar.online_market.items;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import synchedapps.tmbazar.R;
import synchedapps.tmbazar.online_market.Constants;

import synchedapps.tmbazar.online_market.adapters.adapter_items;
import synchedapps.tmbazar.online_market.models.model_items;
import synchedapps.tmbazar.online_market.network.Api;
import synchedapps.tmbazar.online_market.network.get_items;
import synchedapps.tmbazar.online_market.network.get_itemsbyname;

public class items_activity extends AppCompatActivity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener,SwipeRefreshLayout.OnRefreshListener{
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    adapter_items ad;
    ArrayList<model_items> list;
    public  static Handler s1,s2;
    SearchView searchView;
    MenuItem searchItem,liked;
    Toolbar toolbar;
    SwipeRefreshLayout s8;
    AppBarLayout appBarLayout;
    TextView t;
    private boolean appBarExpanded = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if( Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP)
            setContentView(R.layout.activity_items_activity);
            else {
            setContentView(R.layout.collapse);
            appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
            appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                boolean isShow = false;
                int scrollRange = -1;

                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if (scrollRange == -1) {
                        scrollRange = appBarLayout.getTotalScrollRange();
                    }
                    if (scrollRange + verticalOffset == 0) {
                        isShow = true;
                        getSupportActionBar().setTitle("");
                    } else if (isShow) {
                        isShow = false;
                    }
                }
            });
            ImageView i=(ImageView)findViewById(R.id.pager_vip_show);
            Intent i1=getIntent();
                 if(Constants.shopImage==null)
            Glide.with(this)
                    .load("http://"+ Api.url+"images/"+i1.getStringExtra("image")).asBitmap().thumbnail(0.01f)
                    .into(i); else i.setImageBitmap(Constants.shopImage);
            Typeface tp= Typeface.createFromAsset(getAssets(),"fonts/Panton-SemiBold.otf");
            t=(TextView)findViewById(R.id.shop_name);
            t.setTypeface(tp);
            t.setText(Constants.shop_name);
        }recyclerView = (RecyclerView)findViewById(R.id.items);
        toolbar=(Toolbar)findViewById(R.id.toolbar_items);
        setSupportActionBar(toolbar);
        Constants.iter_id="";
        Constants.iter=true;
        Constants.itemid.clear();
        Constants.items.clear();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        linearLayoutManager = new LinearLayoutManager(this);
        list=new ArrayList<>();
        s8=(SwipeRefreshLayout)findViewById(R.id.swipei1);
        ad=new adapter_items(this, Constants.items);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(ad);
        s8.setOnRefreshListener(this);
        s1=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==1){
                    ad.setData(Constants.items);
                    s8.setRefreshing(false);
                   }
            }
        };
        s2=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==1){
                    s8.setRefreshing(true);
                }
            }
        };
        s8.setRefreshing(true);
        get_items.get_Data();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchliked, menu);
        searchItem = menu.findItem(R.id.action_search1);
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
        Constants.iter=true;
        Constants.iter_id="";
        Constants.item_name="";
        Constants.items.clear();
        Constants.itemid.clear();
        get_itemsbyname.get_Data();

        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        Constants.iter=true;
        Constants.iter_id="";
        Constants.item_name=s;
        Constants.items.clear();
        Constants.itemid.clear();
        get_itemsbyname.get_Data();
        return false;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Constants.iter=true;
        Constants.iter_id="";
        Constants.items.clear();
        Constants.shopImage=null;
        s1.sendEmptyMessage(1);
        finish();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == android.R.id.home) {
            Constants.iter=true;
            Constants.iter_id="";
            Constants.items.clear();
            finish();

        }
        return true;
    }
    @Override
    public void onRefresh() {
        s8.setRefreshing(false);
    }
}
