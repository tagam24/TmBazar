package synchedapps.tmbazar.bb;

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

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.Models.model_lentas;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.adapters.adapter_for_recycle_for_lenta;
import synchedapps.tmbazar.dil;
import synchedapps.tmbazar.fragment.fragment_lenta1;

import java.util.ArrayList;

public class like_lenta_activity extends AppCompatActivity {
    View view;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    static ArrayList<model_lentas> data;
    Toolbar toolbar;
    static String Tabname = "liked_gyzykly";
    static Db db;
    dil di = new dil();
    static Handler create;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_lenta_activity);
        di.set_text();
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
        get_local();
        create = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    adapter_for_recycle_for_lenta a = new adapter_for_recycle_for_lenta(data, ctx, "liked_gyzykly");
                    recyclerView.setAdapter(a);
                }
            }
        };


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == android.R.id.home) {
            lenta_tab.lenta_path = false;
            Intent intent = new Intent(getApplicationContext(), lenta_tab.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        lenta_tab.lenta_path = false;
        fragment_lenta1.get_local();
        Intent intent = new Intent(getApplicationContext(), lenta_tab.class);
        startActivity(intent);
    }

    static Thread get_sqlite;

    public static void get_local() {
        Log.d("getLocal", "  ");
        get_sqlite = new Thread(new Runnable() {
            @Override
            public void run() {
                data = db.get_Likedgyzykly();
                Log.d("datasize", "" + data.size());

                create.sendEmptyMessage(1);
                Thread.currentThread().interrupt();
            }
        });
        get_sqlite.start();
    }

}
