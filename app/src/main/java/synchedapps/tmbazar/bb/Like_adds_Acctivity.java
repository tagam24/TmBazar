package synchedapps.tmbazar.bb;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.Models.model_cars;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.adapters.adapter_for_recycle_for_car;

import java.util.ArrayList;

public class Like_adds_Acctivity extends AppCompatActivity {
    View view;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    static ArrayList<model_cars> data;
    Toolbar toolbar;
    static String Tabname="liked_adds";
    static Db db;
    static Handler create;
    Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_lenta_activity);
        toolbar = (Toolbar) findViewById(R.id.lenta_toolbar_like);
        setSupportActionBar(toolbar);
        MainActivity.name_toolbar = "HALANLARYM Reklamalar";
        getSupportActionBar().setTitle(MainActivity.name_toolbar);
        db=new Db(this);
        ctx=this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        data=new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.reycycler_for_like_lenta);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        get_local();
        create=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==1){
                    adapter_for_recycle_for_car a = new adapter_for_recycle_for_car (data,ctx,"");
                    recyclerView.setAdapter(a);}}
        };


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == android.R.id.home) {
            lenta_tab.lenta_path=false;
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        lenta_tab.lenta_path=false;
        Intent intent = new Intent(getApplicationContext(), car_tab.class);
        startActivity(intent);
    }
    static Thread get_sqlite;
    public static void get_local(){
        Log.d("getLocal","  ");
        get_sqlite=new Thread(new Runnable() {
            @Override
            public void run() {
             //data=db.get_like_adds();
                Log.d("datasize",""+data.size());
                for(int i=0; i<data.size();i++){
                    byte[] img = Base64.decode(data.get(i).getImg(), Base64.DEFAULT);
                    Bitmap image = BitmapFactory.decodeByteArray(img, 0, img.length);
                    data.get(i).setImage(image);}
                create.sendEmptyMessage(1);
                Thread.currentThread().interrupt();
            }
        });
        get_sqlite.start();
    }

}
