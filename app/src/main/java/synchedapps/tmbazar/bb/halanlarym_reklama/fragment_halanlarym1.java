package synchedapps.tmbazar.bb.halanlarym_reklama;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.Models.model_cars;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.adapters.adapter_for_recycle_for_status_car;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.dil;

import java.util.ArrayList;


public class fragment_halanlarym1 extends Fragment {
    public static int limit = 30;
    View view;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    static Db db;
    public static ArrayList<model_cars> data;
    static Handler create;
    adapter_for_recycle_for_status_car a;
    dil di = new dil();

    public fragment_halanlarym1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1_halanlarym, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_for_halanlarym_car);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        db = new Db(MainActivity.ctx);
        data = new ArrayList<>();
        a = new adapter_for_recycle_for_status_car(data, getActivity(), "likedAdds");
        recyclerView.setAdapter(a);

        create = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                 a.setData(data);
            }

        };
        get_local();
        return view;
    }

    public static void get_local() {
        Log.d("getLocal", "  ");
        Log.d("limit", "" + limit);
        int k = data.size();
        data = db.get_like_adds("cars", limit);
        if (data.size() == k) MainActivity.flazhok = false;
        else MainActivity.flazhok = true;
        Log.d("datasizeRealtor", "" + data.size());
        /*if (data.size() > 0) for (int i = 0; i < data.size(); i++) {
            byte[] img = Base64.decode(data.get(i).getImg(), Base64.DEFAULT);
            Bitmap image = BitmapFactory.decodeByteArray(img, 0, img.length);

            try {
                if (img.length > 0) data.get(i).setImage(image);
            } catch (IndexOutOfBoundsException s) {
                s.printStackTrace();
            }
        }*/
        create.sendEmptyMessage(1);


    }
}
