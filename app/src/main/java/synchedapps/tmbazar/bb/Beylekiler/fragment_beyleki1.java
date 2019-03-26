package synchedapps.tmbazar.bb.Beylekiler;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.Models.ModelRealtor;
import synchedapps.tmbazar.Models.model_cars;
import synchedapps.tmbazar.Network.Get_data_realtor;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.bb.Realtor.adapter_for_recycle_for_realtor;
import synchedapps.tmbazar.dil;
import synchedapps.tmbazar.instant_notifbeylekiler;

import java.util.ArrayList;

public class fragment_beyleki1 extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    View view;
    static Db db = new Db(MainActivity.ctx);
    static String Tabname = "Realtor";
 public static    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    public static ImageView ret;
    static Handler create;
    static SwipeRefreshLayout swipe;
    public static ArrayList<ModelRealtor> data = new ArrayList<>();
    public static ArrayList<model_cars> temp = new ArrayList<>();
    public static TextView searchbox;
    ImageView search;
    public static int limit = 30;
    static adapter_for_recycle_for_realtor a;
 int s = 0;
    static Get_data_realtor g = new Get_data_realtor();
    public static String category = "", location = "", name = "";
    public static boolean flazhok = true;
    public static Handler h = new Handler();
    Context ctx;
    dil di = new dil();
 int k = 0;
    Thread typer;
    instant_notifbeylekiler not = new instant_notifbeylekiler();
        RelativeLayout r;
    RelativeLayout.LayoutParams ls3;
    ViewGroup.LayoutParams ls1;
    public fragment_beyleki1() {
    }
    public  static ProgressDialog loading;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1_realtor, container, false);
        di.set_text();
        ctx = getActivity();
        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe_realtor);
        swipe.setOnRefreshListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycl_for_realtor);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        ls3=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,0);
        recyclerView.setLayoutManager(linearLayoutManager);
        final EditText editText = (EditText) view.findViewById(R.id.edittext_realtor_Gozleg);
        ret = (ImageView) view.findViewById(R.id.delete_realtor_rec);
        r=(RelativeLayout)view.findViewById(R.id.Relative_realtor);

// Gets the layout params that will allow you to resize the layout
        ViewGroup.LayoutParams params = r.getLayoutParams();
        params.height = 0;

        not.notif("beylekiler");
        editText.setHint(dil.tm_gozleg);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                name = charSequence.toString();
                db.deleteTable("beylekiler");
                Log.d("name", charSequence.toString());
                if (MainActivity.conn) refr();
                 get_local();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ret.setVisibility(View.INVISIBLE);
                empty();
                get_local();
//
                editText.setCursorVisible(false);
                editText.setText("");

            }
        });
        Log.d("category",category);

        if(!category.equals("")){data=new ArrayList<>();  ret.setVisibility(View.VISIBLE);// onRefresh();
        }
        a = new adapter_for_recycle_for_realtor(data, getActivity(), "beylekiler");
        recyclerView.setAdapter(a);
        create = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    a.setData(data);
                    swipe.setRefreshing(false);
                }

            }
        };

            swipe.setRefreshing(true);
            g.get_Data("beylekiler", category, name,"");




        return view;
    }


    @Override
    public void onRefresh() {
   limit =30;
        db.deleteTable("beylekiler");
        refr();
        Log.d("onRefrCars", " " + MainActivity.conn);
        swipe.setRefreshing(false);
      /*  try {
            if (MainActivity.conn) {
                if (!name.equals("") && !name.contains("%")) name += "%";
                Log.d(category, name);
                g.get_Data("beylekiler", category, name,"");
            } else get_local();
        } catch (NullPointerException ss) {
        }*/

    }

    public static void refr() {
   //     get_local()

           Log.d("isAlive",Get_data_realtor.get_data.isAlive()+"  "+ Get_data_realtor.json.isAlive());
            if (!name.equals("") && !name.contains("%")) name += "%";
           if(!Get_data_realtor.get_data.isAlive()&&!Get_data_realtor.json.isAlive())
               g.get_Data("beylekiler", category, name,"");
        swipe.setRefreshing(true);
    //    db.add_deleter("beylekiler");
        //if(MainActivity.conn) get.get_Data(table,category,model,year1,year2,price1,price2,credit,og.get_Data("beylekiler", category, name,"");bmen,location,color); else swipe.setRefreshing(false);
    }



    public static void get_local() {
        Log.d("getLocal", "  ");
        Log.d("limit", "" + limit);
        int k = data.size();
        if (!name.equals("") && !name.contains("%")) name += "%";
        data = db.get_dataBeylekiler("beylekiler", category, name, limit);
        if (data.size() == k) MainActivity.flazhok = false;
        else MainActivity.flazhok = true;
        Log.d("datasizeRealtor", "" + data.size());
        create.sendEmptyMessage(1);

    }


    public static void empty() {
        category = "";
        name = "";
    }

}
