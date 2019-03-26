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
import synchedapps.tmbazar.Models.ModelRealtor;
import synchedapps.tmbazar.Models.model_cars;
import synchedapps.tmbazar.Network.myStatusUpdater;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.adapters.status_adapter_for_recycle_for_beylekiler;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.bb.Realtor.realtor_add_reklama;

import java.util.ArrayList;


public class fragment_bildiris_gosmak2 extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    View view;
    ArrayList<model_cars> list;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    public static ArrayList<ModelRealtor> data;
    Toolbar toolbar;
    static Db db;
    public static Handler create;
    Context ctx;
    SwipeRefreshLayout swipe;
    public static String table="myadds";
    myStatusUpdater m=new myStatusUpdater();

    public fragment_bildiris_gosmak2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2_bildiris_gos, container, false);
        swipe=(SwipeRefreshLayout)view.findViewById(R.id.swipe_add_realtor);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle2_for_reklama_gos);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();

        FloatingActionButton floatingActionButton=(FloatingActionButton)view.findViewById(R.id.button2_reklama_gos_reklama_gos);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),realtor_add_reklama.class);
                startActivity(intent);
            }
        });
        db=new Db(MainActivity.ctx);
        data=new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle2_for_reklama_gos);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        swipe.setOnRefreshListener(this);
        create=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==1){
                    swipe.setRefreshing(false);
                    status_adapter_for_recycle_for_beylekiler a = new status_adapter_for_recycle_for_beylekiler(data,MainActivity.ctx,"myAddsrealtor");
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
try{                data=db.getMyaddsRealtor();
//                Log.d("datasize",""+data.size());
                for(int i=0; i<data.size();i++){
                    Log.d("status",data.get(i).getStatus());
                    byte[] img = Base64.decode(data.get(i).getImage(), Base64.DEFAULT);
                    Bitmap image = BitmapFactory.decodeByteArray(img, 0, img.length);
                    data.get(i).setImg(image);}
                create.sendEmptyMessage(1);} catch (NullPointerException ss){}
                Thread.currentThread().interrupt();
            }
        });
        get_sqlite.start();
    }

    @Override
    public void onRefresh() {
        if(MainActivity.conn){ m.myStatus("home","myAddsrealtor",db.getMyAddsId("myAddsrealtor"));   }
        else get_local();
    }
}
