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
import synchedapps.tmbazar.Models.model_lenta;
import synchedapps.tmbazar.Models.model_lentas;
import synchedapps.tmbazar.Network.Get_bannner;
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
import java.util.List;

public class fragment_lenta1 extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
  static   Get_data_lenta g = new Get_data_lenta();
    View view;
    static SwipeRefreshLayout swipe;
    public  static  RecyclerView recyclerView;
    public static boolean bool = true;
    LinearLayoutManager linearLayoutManager;
    List<model_lenta> list;
    static public ArrayList<model_lentas> data = new ArrayList<>();
    static String Tabname = "futbol";
    static Db db;
    static Handler create;
    int creation = 0;
    Liked_getter liked_getter = new Liked_getter();
    public static ArrayList<MOdel_lenta_banner> banners = new ArrayList<>();
    ImageView img;
    public static int limit = 30;
    public static Handler trans;
    Get_bannner gb;

    public fragment_lenta1() {
    }

    adapter_for_recycle_for_lenta a;
    AVLoadingIndicatorView avi;
    boolean check;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_lenta1, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycl_for_lenta1);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipel1);
        swipe.setOnRefreshListener(this);
        Log.d(Tabname, "creation " + creation);
        db = new Db(MainActivity.ctx);
        if (banners != null) Log.d("bannersizeMain", "" + banners.size());
    //    avi = (AVLoadingIndicatorView) view.findViewById(R.id.avi_lenta1);
        gb = new Get_bannner();
        a = new adapter_for_recycle_for_lenta(data, getActivity(), Tabname);
        recyclerView.setAdapter(a);
        adapter_for_recycle_for_lenta.b=true;
       //get_banner();
      // avi.show();

        create = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {

                    Log.d(Tabname, "geldi");
                    Log.d(Tabname, "datasize" + data.size());
                  //  recyclerView.removeAllViews();
                    swipe.setRefreshing(false);
                    //if(data.size()>30)recyclerView.scrollToPosition(data.size()-30);
                    a.set_data(data);
                }
            }
        };
    ///    v1 = (ViewFlipper) view.findViewById(R.id.fliper_for_lenta1);
      //  if(v1.getChildCount()>0)avi.hide();
       // Log.d("l", "" + v1.getChildCount());
        trans = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                  //  v1.removeAllViews();
                   // avi.hide();
                    check = true;
                  //get_banner();
                    try {
                        fragment_lenta2.trans.sendEmptyMessage(1);
                        fragment_lenta3.trans.sendEmptyMessage(1);
                    } catch (NullPointerException ee) {
                    }
                }
            }
        };

        onRefresh();
        Log.d(Tabname, "gildi");
        Log.d("data.size", "" + data.size());
        if (data.size() == 0) {
            Log.d("icinde", "   ");
            get_local();
        }
        if (MainActivity.conn == true && creation == 0) {
            swipe.setRefreshing(true);
            liked_getter.getter(Tabname, "");
            //gb.get_banner();
           // onRefresh();
          //  g.get_data("http://192.168.137.1/Reklama/lenta/get_lenta.php", Tabname,db.get_max(Tabname));
         //   g.get_data("http://u21940xgr.ha002.t.justns.ru/Reklama/lenta/get_lenta.php", Tabname,""+ 1000000);
            Log.d("gitdi","zapros");
            creation = 1;
        }


        return view;
    }

    static Thread get_sqlite;

    public static void get_local() {
        Log.d("getLocal", "  ");

        get_sqlite = new Thread(new Runnable() {
            @Override
            public void run() {
             try{ int k = data.size();
                data = db.get_gyzykly(Tabname, limit);
                if (k == data.size()) MainActivity.flazhok = false;
                else MainActivity.flazhok = true;
                Log.d("datasize", "" + data.size());
                try {
                  /*  for (int i = 0; i < data.size(); i++) {
                        Log.d("getlOCal imgfootbal", data.get(i).getImage());
                        try {
                            byte[] img = Base64.decode(data.get(i).getImage(), Base64.DEFAULT);
                            Bitmap image = BitmapFactory.decodeByteArray(img, 0, img.length);
                            data.get(i).setImgbitmap(image);
                        } catch (OutOfMemoryError s) {
                        }
                    }*/
                    create.sendEmptyMessage(1);
                } catch (IndexOutOfBoundsException ss) {
                } catch (NullPointerException ss) {
                }} catch (NullPointerException ss){}
//                swipe.setRefreshing(false);
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
                        g.get_data("http://"+ network.address+"/Reklama/lenta/get_Lenta1.php", Tabname, max);
                    else swipe.setRefreshing(false);
                }  else g.get_data("http://"+ network.address+"/Reklama/lenta/get_lenta.php", Tabname, max);
                    swipe.setRefreshing(false); // if(MainActivity.conn)g.get_data("http://192.168.137.1/reklama3/lenta/get_lenta.php",Tabname,max);else swipe.setRefreshing(false);
    }

    int k;
    Thread get_bann;

    public void get_banner() {
        final Handler x = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                 //   v1.removeAllViews();
                    Log.d("bannersize", " bb" + banners.size());
                    Collections.shuffle(banners);
//                    avi.hide();
                    fragment_lenta2.trans.sendEmptyMessage(1);
                    fragment_lenta3.trans.sendEmptyMessage(1);
                    for (int i = 0; i < banners.size(); i++) {
                        img = new ImageView(getActivity());
                        img.setTag(banners.get(i).getId());
                        img.setClickable(true);
                        try {
                            if (banners.size() >= i && banners.size() != 0);
                          img.setImageBitmap(banners.get(i).getImage1());
                        } catch (IndexOutOfBoundsException s) {
                        }
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

                                intent.putExtra("table", "bannerlenta");
                                startActivity(intent);
                                //Log.d("idclick",""+view.getTag());*/
                            }
                        });
                     //   v1.addView(img);

                    }

                }
            }
        };
        get_bann = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("banner s db", "girdi");
                banners = new ArrayList<>();
            banners = db.getBannerLenta();
                x.sendEmptyMessage(1);
                Thread.currentThread().interrupt();

            }
        });
        if (banners.size() == 0) get_bann.start();
        else if (check) {
            check = false;
            get_bann.start();
        } else x.sendEmptyMessage(1);

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
    void startAnim() {
        avi.show();
        // or avi.smoothToShow();
    }

    void stopAnim() {
        avi.hide();
        // or avi.smoothToHide();
    }

}
