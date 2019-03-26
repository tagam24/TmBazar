package synchedapps.tmbazar.fragment;

import android.app.ProgressDialog;
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
import android.widget.TextView;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.Models.model_cars;
import synchedapps.tmbazar.Network.Get_data_cars;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.adapters.adapter_for_recycle_for_car;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.bb.filter_car.filterActivity;
import synchedapps.tmbazar.dil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class fragment_car1 extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    static Get_data_cars get = new Get_data_cars();
    public static boolean bool = true;
    View view;
    static Db db = new Db(MainActivity.ctx);
    static String Tabname = "cars";
   public static RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    public static ImageView ret;
    static Handler create = new Handler();
    static SwipeRefreshLayout swipe;
    public static ArrayList<model_cars> data = new ArrayList<>();
    public static ArrayList<model_cars> temp = new ArrayList<>();
    public static TextView searchbox;
    ImageView search;
    public static int limit = 0;
    static int k = 0;
  static int s = 0;
    static adapter_for_recycle_for_car a;
    public static String downup="",table = "cars", category = "", model = "", year1 = "", year2 = "", price1 = "", price2 = "", credit = "", obmen = "", satyldy = "0", location = "", vip = "", gyssagly = "", color = "";
    public  static ProgressDialog loading;
    public fragment_car1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_car1, container, false);
        dil di = new dil();
        db = new Db(MainActivity.ctx);
        Log.d("classname",""+getClass());
        di.set_text();
        ret = (ImageView) view.findViewById(R.id.return_All);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycl_for_car);
        Log.d("Limit ", "" + limit);
        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipecar);
        swipe.setOnRefreshListener(this);
        search = (ImageView) view.findViewById(R.id.search_car);
        searchbox = (TextView) view.findViewById(R.id.searchCar);
        searchbox.setText(dil.tm_gozleg);
        searchbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), filterActivity.class);
                startActivity(i);
            }
        });
        int k;
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        //get.get_Data(table,category,model,year1,year2,price1,price2,credit,obmen,satyldy);

        for (int i = 0; i < filterActivity.data.size(); i++) {
            Log.d("dataFilter", filterActivity.data.get(i).getCategory() + " " + filterActivity.data.get(i).getModel());
        }
        Log.d("filtrden ",credit+obmen+ " cr");
        a = new adapter_for_recycle_for_car(data, getActivity(), "cars");
        recyclerView.setAdapter(a);
        swipe.setRefreshing(false);
        create = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    Log.d("create", "handler");

                    //  if(data.size()>0)if(!Get_data_adds.get_data.isAlive())swipe.setRefreshing(false);
                 //   recyclerView.getRecycledViewPool().clear();
               //     recyclerView.removeAllViews();
//if(loading!=null)                    if(loading.isShowing())loading.dismiss();
                 Log.d("limit",""+limit);
               //     Log.d("")
                 //   if(limit<data.size())if(data.size()>30)recyclerView.scrollToPosition(limit-30);
                   // if(limit>data.size())recyclerView.scrollToPosition(data.size()-30);
                  //if(adapter_for_recycle_for_car.b==false)recyclerView.scrollToPosition(data.size());
                   try{ a.setData(data);}catch (IndexOutOfBoundsException dd){}

                    swipe.setRefreshing(false);
                    //a = new adapter_for_recycle_for_car(data, getActivity());
                    // recyclerView.setAdapter(a);
                }

            }
        };

        Date date = new Date();
        SimpleDateFormat ff = new SimpleDateFormat("mm:MM");
       // db.vipCheker("cars", "12:3");

        Log.d("gelyanler", year1 + " " + year2 + " " + price1 + " " + price2 + " " + color + " " + location + " ");
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), filterActivity.class);
                startActivity(i);
            }
        });

        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                empty_cars();
                get_local();
                ret.setVisibility(View.INVISIBLE);
            }
        });

         get_local();
            swipe.setRefreshing(true);

try{            if (MainActivity.conn == true) get.get_Data(table, category, model, year1, year2, price1, price2, credit, obmen, location, color,vip);}catch (NullPointerException ss){}
            //else get_local();


       // db.add_deleter("cars");
        return view;
    }


    @Override
    public void onRefresh() {
       limit=30;
        db.deleteTable("cars");
        refr();
     //   limit = limit + 30;
      //  Log.d("limit", "" + limit);
     //   MainActivity.flazhok = true;
      //  Log.d("onRefrCars", " " + MainActivity.conn);
     //   if (MainActivity.conn)
      //      get.get_Data(table, category, model, year1, year2, price1, price2, credit, obmen, location, color);
    //    else swipe.setRefreshing(false);
        swipe.setRefreshing(false);

    }

    public static void refr() {
        //get_local();
        Log.d("refr",""+limit);
          if(!Get_data_cars.get_data.isAlive()&& !Get_data_cars.json.isAlive()) {
              if (vip.equals("") && gyssagly.equals("")) {
                  swipe.setRefreshing(true);
                  get.get_Data(table, category, model, year1, year2, price1, price2, credit, obmen, location, color, vip);
              }// else get_local();


          }


    }

    static Thread get_sqlite;

    public static void get_local() {
        Log.d("getlocs",""+limit);

        get_sqlite = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("limit", "" + limit);
                Log.d("flazhok getlocal", "" + MainActivity.flazhok);
                int k = data.size();
                Log.d("k=?", "" + k);
                if(vip.equals("4"))satyldy="1";
try{                data = db.get_adds(table, category, model, year1, year2, price1, price2, credit, obmen, location, vip, gyssagly, satyldy, color, limit,downup);}catch (NullPointerException ss){}
                if (k == data.size()) MainActivity.flazhok = false;
                else MainActivity.flazhok = true;
                Log.d("datasize", "" + data.size());
                create.sendEmptyMessage(1);
                Thread.currentThread().interrupt();
            }
        });
        //if (get_sqlite.isAlive()) get_sqlite.interrupt();
        get_sqlite.start();
    }

    public static void empty_cars() {
        adapter_for_recycle_for_car.b=true;
        searchbox.setText(dil.tm_gozleg);
        fragment_car1.category = "";
        fragment_car1.model = "";
        fragment_car1.location = "";
        fragment_car1.year1 = "";
        fragment_car1.year2 = "";
        fragment_car1.price2 = "";
        fragment_car1.price1 = "";
        fragment_car1.obmen = "";
        fragment_car1.credit = "";
        fragment_car1.satyldy = "";
        fragment_car1.vip = "";
        fragment_car1.gyssagly = "";
        color = "";

    }

    public static void print_filtr() {
        for (int i = 0; i < filterActivity.data.size(); i++) {
            Log.d("filtr", filterActivity.data.get(i).getModel());
        }
    }
}
