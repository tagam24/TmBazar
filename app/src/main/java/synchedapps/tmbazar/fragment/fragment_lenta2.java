package synchedapps.tmbazar.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.Models.MOdel_lenta_banner;
import synchedapps.tmbazar.Models.model_lentas;
import synchedapps.tmbazar.Network.Get_data_lenta;
import synchedapps.tmbazar.Network.Liked_getter;
import synchedapps.tmbazar.Network.network;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.adapters.adapter_for_recycle_for_lenta;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.bb.vip_show_details;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Collections;

public class fragment_lenta2 extends Fragment implements  SwipeRefreshLayout.OnRefreshListener{

    View view;
   public static RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
  static SwipeRefreshLayout swipe;
    static Db db;
    public  static boolean bool=true;
   static public ArrayList<model_lentas> data=new ArrayList<>();
   static Handler create;
  static   String Tabname="tasinlikler";
    static Get_data_lenta g=new Get_data_lenta();
     int creation=0;
    public static   ArrayList<MOdel_lenta_banner> banners=new ArrayList<>();
    ImageView img;
    public static  int limit=30;
    public  static  Handler trans=new Handler();
    Liked_getter liked_getter=new Liked_getter();
    AVLoadingIndicatorView avi;
    public fragment_lenta2() {
    }
    adapter_for_recycle_for_lenta a;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_lenta2, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycl_for_lenta2);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        swipe=(SwipeRefreshLayout)view.findViewById(R.id.swipe2);
        swipe.setOnRefreshListener(this);
        adapter_for_recycle_for_lenta.b=true;
       Log.d("tasinlikler","girdi");
      //  avi = (AVLoadingIndicatorView) view.findViewById(R.id.avi_lenta2);
      //  liked_getter=new Liked_getter();

        db=new Db(MainActivity.ctx);
      //if(banners.size()==0) get_banner();
            Log.d(Tabname,"gildi");
             a = new adapter_for_recycle_for_lenta(data, getActivity(),Tabname);
            recyclerView.setAdapter(a);
         //   swipe.setRefreshing(true);
         create=new Handler() {
             @Override
             public void handleMessage(Message msg) {
                 if(msg.what==1){
                     Log.d(Tabname,"geldi");

                     swipe.setRefreshing(false);

                     a.set_data(data);}
             }


         };      onRefresh();
         trans=new Handler(){
             @Override
             public void handleMessage(Message msg) {
                 if(msg.what==1){
                     //get_banner();
                 }
             }
         };
            //data=new ArrayList<>();
        // swipe.setRefreshing(true);

         if(MainActivity.conn==true&& creation==0){  liked_getter.getter(Tabname,"");swipe.setRefreshing(true);
          //   onRefresh();
             creation=1;}



     //   if(data.size()==0)get_local();
      //  ImageView back_button=(ImageView)view.findViewById(R.id.back_button_lenta2);
      //  ImageView forward_button=(ImageView)view.findViewById(R.id.forward_button_lenta2);


        return view;
    }
    static Thread get_sqlite;
    public static void get_local(){
        Log.d("getLocal","  ");

        get_sqlite=new Thread(new Runnable() {
            @Override
            public void run() {
                data=db.get_gyzykly(Tabname,limit);
                Log.d("datasize",""+data.size());
           /*  try{   for(int i=0; i<data.size();i++){
                    Log.d("getlOCal img",data.get(i).getImage())try{      byte[] img = Base64.decode(data.get(i).getImage(), Base64.DEFAULT);
                   Bitmap image = BitmapFactory.decodeByteArray(img, 0, img.length);
                    data.get(i).setImgbitmap(image);}catch (OutOfMemoryError es){}} }catch (IndexOutOfBoundsException oo){}*/
               try{ create.sendEmptyMessage(1);}catch (NullPointerException ee){}
                Thread.currentThread().interrupt();
            }
        });
        get_sqlite.start();
    }



    @Override
    public void onRefresh() {
        swipe.setRefreshing(true);
        // limit+=30;
        adapter_for_recycle_for_lenta.b=true;
        //db.deleteTable(Tabname);
        String max = db.get_max1(Tabname);
        if(creation==0) {
            max = "1000000";
            if (MainActivity.conn)
                g.get_data("http://"+network.address+"/Reklama/lenta/get_Lenta1.php", Tabname, max);
            else swipe.setRefreshing(false);
        }  else g.get_data("http://"+ network.address+"/Reklama/lenta/get_lenta.php", Tabname, max);
        swipe.setRefreshing(false); // if(MainActivity.conn)g.get_data("http://192.168.137.1/reklama3/lenta/get_lenta.php",Tabname,max);else swipe.setRefreshing(false);
    }

    public static void refr() {
        adapter_for_recycle_for_lenta.b=true;
        Thread k=new Thread(new Runnable() {
            @Override
            public void run() {
                String max=db.get_max(Tabname);
                Log.d("max",max);
                g.get_data("http://"+ network.address+"/Reklama/lenta/get_Lenta1.php",Tabname,max);
            }
        });
        k.start();
        swipe.setRefreshing(false);
    }
    int k;
    Thread get_bann;
    public void  get_banner(){
      if(fragment_lenta1.banners!=null)  banners=fragment_lenta1.banners;
        final    Handler x=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==1){
                 //   avi.hide();
//                    Log.d("bannersize"," bb"+banners.size());
                    Collections.shuffle(banners);
                    for(int i=0; i<banners.size();i++){
                        img=new ImageView(getActivity());
                        img.setTag(banners.get(i).getId());
                        img.setClickable(true);
                     //   try{if(banners.size()>=i&&banners.size()!=0)img.setImageBitmap(banners.get(i).getImg());} catch (IndexOutOfBoundsException s){}
                        img.setScaleType(ImageView.ScaleType.FIT_XY);
                        k=i;
                        img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(getActivity(), vip_show_details.class);
                                intent.putExtra("id",""+view.getTag());
                                intent.putExtra("category",banners.get(k).getTitle());
                                intent.putExtra("number",banners.get(k).getNumber());
                                intent.putExtra("description",banners.get(k).getDescription());
                                intent.putExtra("index",""+k);
                                intent.putExtra("table","bannerlenta");
                                startActivity(intent);
                                //Log.d("idclick",""+view.getTag());*/
                            }
                        });

                    }
                    try{                   }catch (NullPointerException ss){}
                }
            }
        };x.sendEmptyMessage(1);
       /* get_bann=new Thread(new Runnable() {
            @Override
            public void run() {


                banners=new ArrayList<>();
                banners=db.getBannerLenta();
                x.sendEmptyMessage(1);
                Thread.currentThread().interrupt();

            }
        });
        get_bann.start();*/

    }

    }

