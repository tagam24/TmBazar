package synchedapps.tmbazar.bb.Beylekiler.Bildiris_gos_Navigation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.Models.model_cars;
import synchedapps.tmbazar.Network.myStatusUpdater;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.adapters.adapter_for_recycle_for_myAdds;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.bb.add_adds_cars;

import java.util.ArrayList;

public class fragment_bildiris_gosmak1 extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    View view;
   ArrayList<model_cars> list;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    public static ArrayList<model_cars> data;
    Toolbar toolbar;
    static Db db;
    static Handler create;
    Context ctx;
    SwipeRefreshLayout swipe;
    public static String table="";
    myStatusUpdater m=new myStatusUpdater();
    public fragment_bildiris_gosmak1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1_bildiris_gos, container, false);
        swipe=(SwipeRefreshLayout)view.findViewById(R.id.swipe_add_car);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle1_for_reklama_gos_car);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();

        FloatingActionButton floatingActionButton=(FloatingActionButton)view.findViewById(R.id.button1_reklama_gos_reklama_gos);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),add_adds_cars.class);
                startActivity(intent);
            }
        });
        db=new Db(MainActivity.ctx);
        data=new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle1_for_reklama_gos_car);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        swipe.setOnRefreshListener(this);
        create=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==1){
                    swipe.setRefreshing(false);
                    adapter_for_recycle_for_myAdds a = new adapter_for_recycle_for_myAdds (data,MainActivity.ctx);
                    recyclerView.setAdapter(a);}}
        };
        onRefresh();
        get_local();
        return view;
    }
    static Thread get_sqlite;
    public static void get_local(){
        Log.d("getLocal","  ");
        get_sqlite=new Thread(new Runnable() {
            @Override
            public void run() {
try{                data=db.get_myAddscar();
             //   Log.d("datasize",""+data.size());
                for(int i=0; i<data.size();i++){
                    Log.d("status",data.get(i).getStatus());
                    byte[] img = Base64.decode(data.get(i).getImg(), Base64.DEFAULT);
                    Bitmap image = BitmapFactory.decodeByteArray(img, 0, img.length);
                    data.get(i).setImage(image);}
                create.sendEmptyMessage(1);}catch (NullPointerException s){}
                Thread.currentThread().interrupt();
            }
        });
        get_sqlite.start();
    }

    @Override
    public void onRefresh() {
        if(MainActivity.conn){ m.myStatus("cars","myadds",db.getMyAddsId("myadds"));   }
         else get_local();
    }

}
