package synchedapps.tmbazar.bb.Beylekiler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import synchedapps.tmbazar.R;
import synchedapps.tmbazar.dil;

public class fragment_beyleki2 extends Fragment {

    View view;
    dil di = new dil();
    public static String s1 = "", s2 = "", s3 = "", s4 = "", s5 = "", s6 = "", s7 = "", s8 = "", s9 = "", s10 = "", s11 = "", s12 = "";
    TextView c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12;
    LinearLayout l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12;

    public fragment_beyleki2() {
    }

    public static Handler set = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2_beylekiler, container, false);
        di.set_text();

        TextView textView_1 = (TextView) view.findViewById(R.id.title_hyzmatlar_beylekiler_fragment2);
        textView_1.setText(dil.tm_hyzmatlar.toUpperCase());
        TextView textView_2 = (TextView) view.findViewById(R.id.title_oytehnikasy_beylekiler_fragment2);
        textView_2.setText(dil.tm_oy_tehnikasy.toUpperCase());
        TextView textView_3 = (TextView) view.findViewById(R.id.title_telefonlar_beylekiler_fragment2);
        textView_3.setText(dil.tm_telefonlar.toUpperCase());
        TextView textView_4 = (TextView) view.findViewById(R.id.title_kampyuterlar_beylekiler_fragment2);
        textView_4.setText(dil.tm_kampyuterlar.toUpperCase());
        TextView textView_5 = (TextView) view.findViewById(R.id.title_is_we_isgar_fragment2);
        textView_5.setText(dil.tm_is_we_isgar.toUpperCase());
        TextView textView_6 = (TextView) view.findViewById(R.id.title_oygoslary_beylekiler_fragment2);
        textView_6.setText(dil.tm_oy_goslary.toUpperCase());
        TextView textView_7 = (TextView) view.findViewById(R.id.title_eginesikler_beylekiler_fragment2);
        textView_7.setText(dil.tm_egin_esikler.toUpperCase());
        TextView textView_8 = (TextView) view.findViewById(R.id.title_cagalar_beylekiler_fragment2);
        textView_8.setText(dil.tm_cagalar_ucin.toUpperCase());
        TextView textView_9 = (TextView) view.findViewById(R.id.title_awtosaylar_beylekiler_fragment2);
        textView_9.setText(dil.tm_awtosaylar.toUpperCase());
        TextView textView_10 = (TextView) view.findViewById(R.id.title_sport_we_dync_alys_beylekiler_fragment2);
        textView_10.setText(dil.tm_sport_we_dunc.toUpperCase());
        TextView textView_11 = (TextView) view.findViewById(R.id.title_haryt_gozleyan_beylekiler_fragment2);
        textView_11.setText(dil.tm_haryt_gozleyan);
        TextView textView_12 = (TextView) view.findViewById(R.id.title_yitirilen_tapylan_beylekiler_fragment2);
        textView_12.setText(dil.tm_yitirilen_tapylan);


        l1 = (LinearLayout) view.findViewById(R.id.layout_hyzmatlR_beylekiler_fragment2);
        l2 = (LinearLayout) view.findViewById(R.id.layout_oytehnikasy_beylekiler_fragment2);
        l3 = (LinearLayout) view.findViewById(R.id.layout_telefonlar_beylekiler_fragment2);
        l4 = (LinearLayout) view.findViewById(R.id.layout_kampyuterlar_beylekiler_fragment2);
        l5 = (LinearLayout) view.findViewById(R.id.layout_is_we_isgar_beylekiler_fragment2);
        l6 = (LinearLayout) view.findViewById(R.id.layout_oygoslary_beylekiler_fragment2);
        l7 = (LinearLayout) view.findViewById(R.id.layout_eginesikler_beylekiler_fragment2);
        l8 = (LinearLayout) view.findViewById(R.id.layout_cagalar_beylekiler_fragment2);
        l9 = (LinearLayout) view.findViewById(R.id.layout_awtosaylar_beylekiler_fragment2);
        l10 = (LinearLayout) view.findViewById(R.id.layout_sport_we_dync_alysh_beylekiler_fragment2);
        l11 = (LinearLayout) view.findViewById(R.id.layout_haryt_gozleyan_beylekiler_fragment2);
        l12 = (LinearLayout) view.findViewById(R.id.layout_yitirilen_tapylan_beylekiler_fragment2);

        c1 = (TextView) view.findViewById(R.id.count_hyzmatlR_beylekiler_fragment2);
        c2 = (TextView) view.findViewById(R.id.count_oytehnikasy_beylekiler_fragment2);
        c3 = (TextView) view.findViewById(R.id.count_telefonlar_beylekiler_fragment2);
        c4 = (TextView) view.findViewById(R.id.count_kompyuterlar_beylekiler_fragment2);
        c5 = (TextView) view.findViewById(R.id.count_is_we_isgar_beylekiler_fragment2);
        c6 = (TextView) view.findViewById(R.id.count_oygoslary_fragment2);
        c7 = (TextView) view.findViewById(R.id.count_eginesikler_beylekiler_fragment2);
        c8 = (TextView) view.findViewById(R.id.count_cagalar_beylekiler_fragment2);
        c9 = (TextView) view.findViewById(R.id.count_awtosaylar_beylekiler_fragment2);
        c10 = (TextView) view.findViewById(R.id.count_sport_we_dync_alys_beylekiler_fragment2);
        c11 = (TextView) view.findViewById(R.id.count_haryt_gozleyan_beylekiler_fragment2);
        c12 = (TextView) view.findViewById(R.id.count_yitirilen_tapylan_beylekiler_fragment2);
        set = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    Log.d("notif", "geldi");
                    c1.setText(s1);
                    c2.setText(s2);
                    c3.setText(s3);
                    c4.setText(s4);
                    c5.setText(s5);
                    c6.setText(s6);
                    c7.setText(s7);
                    c8.setText(s8);
                    c9.setText(s9);
                    c10.setText(s10);
                    c11.setText(s11);
                    c12.setText(s12);

                }
            }
        };
        set.sendEmptyMessage(1);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_beyleki1.limit=30;
                fragment_beyleki1.category = "Hyzmatlar";
                fragment_beyleki1.refr();
                fragment_beyleki1.ret.setVisibility(View.VISIBLE);
                beylekiler_tab.tab = beylekiler_tab.tabLayout.getTabAt(0);
                beylekiler_tab.tab.select();
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {fragment_beyleki1.limit=30;
                fragment_beyleki1.category = "Öý Tehnikasy";
                fragment_beyleki1.refr();
                fragment_beyleki1.ret.setVisibility(View.VISIBLE);
                beylekiler_tab.tab = beylekiler_tab.tabLayout.getTabAt(0);
                beylekiler_tab.tab.select();
            }
        });

        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {fragment_beyleki1.limit=30;
                fragment_beyleki1.category = "Telefonlar";
                fragment_beyleki1.refr();
                fragment_beyleki1.ret.setVisibility(View.VISIBLE);
                beylekiler_tab.tab = beylekiler_tab.tabLayout.getTabAt(0);
                beylekiler_tab.tab.select();
            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {fragment_beyleki1.limit=30;
                fragment_beyleki1.category = "Kompýuter we Enjamlary";
                fragment_beyleki1.refr();
                fragment_beyleki1.ret.setVisibility(View.VISIBLE);
                beylekiler_tab.tab = beylekiler_tab.tabLayout.getTabAt(0);
                beylekiler_tab.tab.select();
            }
        });
        l5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {fragment_beyleki1.limit=30;
                fragment_beyleki1.category = "Iş we Işgär";
                fragment_beyleki1.refr();
                fragment_beyleki1.ret.setVisibility(View.VISIBLE);
                beylekiler_tab.tab = beylekiler_tab.tabLayout.getTabAt(0);
                beylekiler_tab.tab.select();
            }
        });
        l6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {fragment_beyleki1.limit=30;
                fragment_beyleki1.category = "Öý Goşlary";
                fragment_beyleki1.refr();
                fragment_beyleki1.ret.setVisibility(View.VISIBLE);
                beylekiler_tab.tab = beylekiler_tab.tabLayout.getTabAt(0);
                beylekiler_tab.tab.select();
            }
        });
        l7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {fragment_beyleki1.limit=30;
                fragment_beyleki1.category = "Egin-Eşikler we Aksesuarlar";
                fragment_beyleki1.refr();
                fragment_beyleki1.ret.setVisibility(View.VISIBLE);
                beylekiler_tab.tab = beylekiler_tab.tabLayout.getTabAt(0);
                beylekiler_tab.tab.select();
            }
        });
        l8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {fragment_beyleki1.limit=30;
                fragment_beyleki1.category = "Çagalar Üçin Ähli Zatlar";
                fragment_beyleki1.refr();
                fragment_beyleki1.ret.setVisibility(View.VISIBLE);
                beylekiler_tab.tab = beylekiler_tab.tabLayout.getTabAt(0);
                beylekiler_tab.tab.select();
            }
        });
        l9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {fragment_beyleki1.limit=30;
                fragment_beyleki1.category = "Awtoşaylar";
                fragment_beyleki1.refr();
                fragment_beyleki1.ret.setVisibility(View.VISIBLE);
                beylekiler_tab.tab = beylekiler_tab.tabLayout.getTabAt(0);
                beylekiler_tab.tab.select();
            }
        });
        l10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {fragment_beyleki1.limit=30;
                fragment_beyleki1.category = "Sport we Dynç Alyş";
                fragment_beyleki1.refr();
                fragment_beyleki1.ret.setVisibility(View.VISIBLE);
                beylekiler_tab.tab = beylekiler_tab.tabLayout.getTabAt(0);
                beylekiler_tab.tab.select();
            }
        });
        l11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {fragment_beyleki1.limit=30;
                fragment_beyleki1.category = "Haryt Gözleýän-Maňa Gerek";
                fragment_beyleki1.refr();
                fragment_beyleki1.ret.setVisibility(View.VISIBLE);
                beylekiler_tab.tab = beylekiler_tab.tabLayout.getTabAt(0);
                beylekiler_tab.tab.select();
            }
        });
        l12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {fragment_beyleki1.limit=30;
                fragment_beyleki1.category = "Ýitirilen we Tapylan";
                fragment_beyleki1.refr();
                fragment_beyleki1.ret.setVisibility(View.VISIBLE);
                beylekiler_tab.tab = beylekiler_tab.tabLayout.getTabAt(0);
                beylekiler_tab.tab.select();
            }
        });
        return view;
    }


}
