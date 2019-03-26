package synchedapps.tmbazar.bb.Realtor;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.Models.MOdel_lenta_banner;
import synchedapps.tmbazar.Models.ModelRealtor;
import synchedapps.tmbazar.Models.model_cars;
import synchedapps.tmbazar.Network.Get_data_realtor;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.bb.filter_car.filterActivity;
import synchedapps.tmbazar.bb.vip_show_details;
import synchedapps.tmbazar.dil;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

public class fragment1_realtor extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
public  static  ArrayList<Bitmap > b=new ArrayList<>();
    View view;
    static ViewFlipper v1;
    static Db db = new Db(MainActivity.ctx);
    static String Tabname = "Realtor";
    public static  RecyclerView recyclerView;
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
    dil di = new dil();
    static Get_data_realtor g = new Get_data_realtor();
    public static boolean flazhok = true;
    Context ctx;
    static int k = 0;
 int s = 0;
    public static int ban=0;
    Thread typer;
    TextView ot;
    public static ArrayList<MOdel_lenta_banner> banners = new ArrayList<>();
    ImageView img,ser;
    ImageView back_button, forward_button;
   public static String location="",name="",category="",price1="",price2="";
    public fragment1_realtor() {
    }
    AVLoadingIndicatorView avi;
    LinearLayout s1;
    public static  EditText editText;
    public  static ProgressDialog loading;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1_realtor, container, false);
        EditText editText_gozleg = (EditText) view.findViewById(R.id.edittext_realtor_Gozleg);
        LinearLayout linearLayout_gozleg=(LinearLayout)view.findViewById(R.id.searchButton_for_realtor) ;
        di.set_text();
        editText_gozleg.setHint(dil.tm_gozleg);
        ctx = getActivity();

        ser=(ImageView)view.findViewById(R.id.search_realto);
        s1=(LinearLayout)view.findViewById(R.id.searchButton_for_realtor);
        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),filterrealtor.class);
                startActivity(i);
            }
        });
        ser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),filterrealtor.class);
                startActivity(i);
            }
        });
        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe_realtor);
        swipe.setOnRefreshListener(this);
        // s=(SearchView) view.findViewById(R.id.search_realter);
        k = 1;
        Log.d("s", "" + realtor_tab.s);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycl_for_realtor);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
       editText = (EditText) view.findViewById(R.id.edittext_realtor_Gozleg);
        ret = (ImageView) view.findViewById(R.id.delete_realtor_rec);
        v1 = (ViewFlipper) view.findViewById(R.id.fliper_for_realtor);
        back_button = (ImageView) view.findViewById(R.id.back_button_realtor);
        forward_button = (ImageView) view.findViewById(R.id.forward_button_realtor);

        avi=(AVLoadingIndicatorView)view.findViewById(R.id.avi_realtor);


//            s.setIconified(false);
        //  s.setQueryHint("Gozleg");
        linearLayout_gozleg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), filterrealtor.class);
                startActivity(intent);
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = v1.getDisplayedChild();

                if (index > 0) {
                    v1.setDisplayedChild(index - 1);
                }
            }
        });
        forward_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = v1.getDisplayedChild();
                if (index < 2) {
                    v1.setDisplayedChild(index + 1);
                }
            }
        });

        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                empty();
                get_local();
                ret.setVisibility(View.INVISIBLE);
                editText.setCursorVisible(false);
                editText.setText("");

            }
        });
        if(!location.equals("")|| !category.equals("")){ ret.setVisibility(View.VISIBLE); }

        a = new adapter_for_recycle_for_realtor(data, getActivity(), "home");
        recyclerView.setAdapter(a);
        for (int i = 0; i < filterActivity.data.size(); i++) {
            Log.d("dataFilter", filterActivity.data.get(i).getCategory() + " " + filterActivity.data.get(i).getModel());
        }


        final Date date = new Date();
        SimpleDateFormat ff = new SimpleDateFormat("mm:MM");



        create = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    if(ban==0){get_banner();ban=1;}
                    Log.d("create", "handler");
                    swipe.setRefreshing(false);
                    if(loading!=null)                    if(loading.isShowing())loading.dismiss();

                    try{ a.setData(data);}catch (IndexOutOfBoundsException dd){}

                }

            }
        };

            s = 1;
            swipe.setRefreshing(true);
            g.get_Data("home", category, name,location);

        return view;
    }


    @Override
    public void onRefresh() {
      //  limit = limit + 30;
       ban=0;
        db.deleteTable("home");
        refr();
      //  Log.d("limit", "" + limit);
      //  Log.d("onRefrCars", " " + MainActivity.conn);
    ///    if (MainActivity.conn) {
        //    g.get_Data("home", category, name,location);
      //  } else get_local();
        swipe.setRefreshing(false);


    }

    public static void refr() {
     //   get_local();
        swipe.setRefreshing(true);
           g.get_Data("home", category, name,location);


    }

    static Thread get_sqlite;

    public static void get_local() {
        Log.d("getLocal", "  ");


        Log.d("limit", "" + limit);
        Log.d("CATS",category+name+location+price2+price1+limit);
        int k = data.size();
        data = db.get_dataRealtor(category, name,location,price1,price2, limit);

        if (data.size() == k) MainActivity.flazhok = false;
        else MainActivity.flazhok = true;
        Log.d("datasizeRealtor", "" + data.size());

        create.sendEmptyMessage(1);

    }

    public static void empty() {
        adapter_for_recycle_for_realtor.b=true;
        category = "";
        name = "";
        location="";
        price1="";
        price2="";
    }

    Thread get_bann;

    public void get_banner() {
        //db.add_deleter("home");
        final Handler x = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    v1.removeAllViews();
                    avi.hide();
                    Log.d("bannersize", " bb" + banners.size());
                    Collections.shuffle(banners);
                    for (int i = 0; i < banners.size(); i++) {
                        img = new ImageView(MainActivity.ctx);
                        img.setTag(banners.get(i).getId());
                        img.setClickable(true);
try {
   img.setImageBitmap(banners.get(i).getImage1());


}catch (IndexOutOfBoundsException ss){}
                        img.setScaleType(ImageView.ScaleType.FIT_XY);
                        k = i;

                        Log.d("knace", "" + banners.get(k).getId() + " " + banners.get(k).getNumber());
                        img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.d("knace", "" + k);
                                Intent intent = new Intent(getActivity(), vip_show_details.class);
                                intent.putExtra("id", "" + view.getTag());
                                intent.putExtra("category", banners.get(k).getTitle());
                                intent.putExtra("number", banners.get(k).getNumber());
                                intent.putExtra("description", banners.get(k).getDescription());
                                intent.putExtra("index", "" + k);
                                Log.d("index of", "" + banners.indexOf(1));

                                intent.putExtra("table", "home");
                                startActivity(intent);
                                //Log.d("idclick",""+view.getTag());*/
                            }
                        });
                        v1.addView(img);

                    }

                }
            }
        };
        get_bann = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("banner s db", "girdi");
                banners = new ArrayList<>();
                banners = db.getbannerRealtor();
                Collections.shuffle(banners,new Random());
                Log.d("bannersize",""+banners.size());
                x.sendEmptyMessage(1);
                Thread.currentThread().interrupt();

            }
        });
        get_bann.start();


    }

    void startAnim(){
        avi.show();
        // or avi.smoothToShow();
    }

    void stopAnim(){
        avi.hide();
        // or avi.smoothToHide();
    }

}
