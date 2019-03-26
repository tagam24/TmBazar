package synchedapps.tmbazar.online_market.items;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.Models.model_lentas;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.adapters.adapter_for_recycle_for_lenta;
import synchedapps.tmbazar.bb.lenta_tab;
import synchedapps.tmbazar.dil;
import synchedapps.tmbazar.fragment.fragment_lenta1;
import synchedapps.tmbazar.online_market.adapters.adapter_items;
import synchedapps.tmbazar.online_market.models.model_items;

public class like_online_activity extends AppCompatActivity {
    View view;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
     ArrayList<model_items> data;
    Toolbar toolbar;
    static Db db;
    dil di = new dil();
    static Handler create;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_lenta_activity);
        di.set_text();
        getApplicationContext();
        toolbar = (Toolbar) findViewById(R.id.lenta_toolbar_like);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(dil.tm_halanlarym);
        db = new Db(this);
        ctx = this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        data = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.reycycler_for_like_lenta);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter_items ad= new adapter_items(ctx,data);
                    recyclerView.setAdapter(ad);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == android.R.id.home) {
            Intent intent = new Intent(this, items_activity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, items_activity.class);
        startActivity(intent);
        finish();
    }



}
