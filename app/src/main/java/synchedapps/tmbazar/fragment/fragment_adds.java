package synchedapps.tmbazar.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.bb.Beylekiler.beylekiler_tab;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.bb.Realtor.realtor_tab;
import synchedapps.tmbazar.bb.car_tab;
import synchedapps.tmbazar.bb.lenta_tab;
import synchedapps.tmbazar.dil;
import synchedapps.tmbazar.online_market.cities;
import synchedapps.tmbazar.online_market.post_tab.post_tabmarket;

public class fragment_adds extends Fragment {
    View view;
    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6;
    TextView A, B, C, D, E, F;
    Db db;
    public static Handler c;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_adds, container, false);
        dil di = new dil();
        di.set_text();
     /*   NewtonCradleLoading newtonCradleLoading; newtonCradleLoading = (NewtonCradleLoading)view.findViewById(R.id.newton_cradle_loading);
        newtonCradleLoading.start();
        newtonCradleLoading.setLoadingColor(R.color.colorPrimary);*/
        A = (TextView) view.findViewById(R.id.sum1_for_adds);
        B = (TextView) view.findViewById(R.id.sum2_for_adds);
        C = (TextView) view.findViewById(R.id.sum3_for_adds);
        D = (TextView) view.findViewById(R.id.sum4_for_adds);
        E = (TextView) view.findViewById(R.id.sum5_for_adds);
        F = (TextView) view.findViewById(R.id.sum6_for_adds);
        linearLayout1 = (LinearLayout) view.findViewById(R.id.layout_awtoulaglar);
        linearLayout2 = (LinearLayout) view.findViewById(R.id.layout_realtor);
        linearLayout3 = (LinearLayout) view.findViewById(R.id.layout_beylekiler);
        linearLayout4 = (LinearLayout) view.findViewById(R.id.layout_futbol);
        linearLayout5 = (LinearLayout) view.findViewById(R.id.layout_tasinlikler);
        linearLayout6 = (LinearLayout) view.findViewById(R.id.layout_gyzyldangymmatly);

        TextView textView1=(TextView)view.findViewById(R.id.name1_for_adds);
        textView1.setText(dil.tm_grid_awtoulaglar);
        TextView textView2=(TextView)view.findViewById(R.id.name2_for_adds);
        textView2.setText(dil.tm_grid_realtor);
        TextView textView3=(TextView)view.findViewById(R.id.name3_for_adds);
        textView3.setText(dil.tm_grid_beylekiler);
        TextView textView4=(TextView)view.findViewById(R.id.name4_for_adds);
        textView4.setText(dil.tm_grid_futbal);
        TextView textView5=(TextView)view.findViewById(R.id.name5_for_adds);
        textView5.setText(dil.tm_grid_tasinlikler);
        TextView textView6=(TextView)view.findViewById(R.id.name6_for_adds);
        textView6.setText(dil.tm_grid_gyzyldan_gymmatly);
        db = new Db(MainActivity.ctx);
        c = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    A.setText(db.get_notifies("cars"));
                    B.setText(db.get_notifies("home"));
                    C.setText(db.get_notifies("beylekiler"));
                    D.setText(db.get_notifies("futbol"));
                    E.setText(db.get_notifies("tasinlikler"));
                    F.setText("");
                }
            }
        };
        c.sendEmptyMessage(1);
        empty_cars();
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), car_tab.class);
                startActivity(intent);
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), realtor_tab.class);
                startActivity(intent);
            }
        });

        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), beylekiler_tab.class);
                startActivity(intent);
                MainActivity.tabs_number_lenta = 0;
            }
        });
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), lenta_tab.class);
                startActivity(intent);
                MainActivity.tabs_number_lenta = 0;
            }
        });

        linearLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), lenta_tab.class);
                startActivity(intent);
                MainActivity.tabs_number_lenta = 1;
            }
        });

        linearLayout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),post_tabmarket.class);
                startActivity(intent);

            }
        });
        return view;
    }

    void empty_cars() {
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
    }
}