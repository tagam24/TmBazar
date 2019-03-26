package synchedapps.tmbazar.bb.Realtor;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import synchedapps.tmbazar.R;
import synchedapps.tmbazar.dil;


public class fragment2_realtor extends Fragment {

    View view;
    dil di = new dil();
    LinearLayout Satlyk_elitka, Arenda_elitka, Satlyk_Arenda_we,
            Satlyk_ofislar, Arenda_Ofislar, Satlyk_jaylar, Arenda_jaylar;
    TextView c1, c2, c3, c4, c5, c6, c7;
    public static String s1 = "", s2 = "", s3 = "", s4 = "", s5 = "", s6 = "", s7 = "";
    public static Handler set = new Handler();
    public static Handler h = new Handler();
    ImageView i,i1,i2,i3,i4,i5,i6;
    public fragment2_realtor() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2_realtor, container, false);
        di.set_text();
fragment1_realtor.ban=0;
        TextView textView_1 = (TextView) view.findViewById(R.id.title_elitkalar_realtor_fragment2);
        textView_1.setText(dil.tm_satlyk_elitkalar);
        TextView textView_2 = (TextView) view.findViewById(R.id.title_arenda_elitkalar_fragment2);
        textView_2.setText(dil.tm_arenda_elitkalar);
        TextView textView_3 = (TextView) view.findViewById(R.id.title_satlyk_jaylar_fragment2);
        textView_3.setText(dil.tm_satlyk_jaylar);
        TextView textView_4 = (TextView) view.findViewById(R.id.title_arenda_jaylar_fragment2);
        textView_4.setText(dil.tm_arenda_jaylar);
        TextView textView_5 = (TextView) view.findViewById(R.id.title_mellek_yerler_fragment2);
        textView_5.setText(dil.tm_satlyk_we_arenda_yerler);
        TextView textView_6 = (TextView) view.findViewById(R.id.title_satlyk_ofislar_fragment2);
        textView_6.setText(dil.tm_satlyk_ofislar_we_marketlar);
        TextView textView_7 = (TextView) view.findViewById(R.id.title_arenda_ofislar_fragment2);
        textView_7.setText(dil.arenda_ofislar_we_marketlar);
i=(ImageView)view.findViewById(R.id.c1) ;

        i1=(ImageView)view.findViewById(R.id.c2) ;
        i2=(ImageView)view.findViewById(R.id.c3) ;
        i3=(ImageView)view.findViewById(R.id.c4) ;
        i4=(ImageView)view.findViewById(R.id.c5) ;
        i5=(ImageView)view.findViewById(R.id.c6) ;
        i6=(ImageView)view.findViewById(R.id.c7) ;
        i.setImageResource(R.drawable.arenda_elitka);
i1.setImageResource(R.drawable.satlyk_elitka);

       i2.setImageResource(R.drawable.satlyk_jay_2);
       i3.setImageResource(R.drawable.satlyk_jay);
       i4.setImageResource(R.drawable.melllek);
        i5.setImageResource(R.drawable.arenda_ofis);
       i6.setImageResource(R.drawable.office);
        Satlyk_elitka = (LinearLayout) view.findViewById(R.id.layout_satlyk_elitkalar_realtor_fragment2);
        c1 = (TextView) view.findViewById(R.id.count_satlyk_elitkalar_fragment2);
        Arenda_elitka = (LinearLayout) view.findViewById(R.id.layout_arenda_elitkalar_fragment2);
        c2 = (TextView) view.findViewById(R.id.count_arenda_elitkalar_fragment2);
        Satlyk_jaylar = (LinearLayout) view.findViewById(R.id.layout_satlyk_jaylar_fragment2);
        c3 = (TextView) view.findViewById(R.id.count_satlyk_jaylar_fragment2);
        Arenda_jaylar = (LinearLayout) view.findViewById(R.id.layout_arenda_jaylar_fragment2);
        c4 = (TextView) view.findViewById(R.id.count_arenda_jaylar_fragment2);
        Satlyk_Arenda_we = (LinearLayout) view.findViewById(R.id.layout_mellek_yerler_fragment2);
        c5 = (TextView) view.findViewById(R.id.count_mellek_yerler_fragment2);
        Satlyk_ofislar = (LinearLayout) view.findViewById(R.id.layout_satlyk_ofislar_fragment2);
        c6 = (TextView) view.findViewById(R.id.count_satlyk_ofislar_fragment2);
        Arenda_Ofislar = (LinearLayout) view.findViewById(R.id.layout_arenda_ofislar_fragment2);
        c7 = (TextView) view.findViewById(R.id.count_arenda_ofislar_fragment2);
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

                }
            }
        };
        set.sendEmptyMessage(1);
        Onclicker();


        return view;
    }


    void Onclicker() {
        Satlyk_elitka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment1_realtor.limit=30;
                fragment1_realtor.empty();
                fragment1_realtor.category = "Satlyk Elitkalar";
                fragment1_realtor.refr();
                fragment1_realtor.ret.setVisibility(View.VISIBLE);
                realtor_tab.tab = realtor_tab.tabLayout.getTabAt(0);
                realtor_tab.tab.select();
            }
        });
        Arenda_elitka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment1_realtor.limit=30;
                fragment1_realtor.empty();
                fragment1_realtor.category = "Arenda Elitkalar";
                fragment1_realtor.refr();
                fragment1_realtor.ret.setVisibility(View.VISIBLE);
                realtor_tab.tab = realtor_tab.tabLayout.getTabAt(0);
                realtor_tab.tab.select();
            }
        });
        Satlyk_jaylar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment1_realtor.empty();fragment1_realtor.limit=30;
                fragment1_realtor.category = "Satlyk Jaýlar";
                fragment1_realtor.ret.setVisibility(View.VISIBLE);
                fragment1_realtor.refr();
                realtor_tab.tab = realtor_tab.tabLayout.getTabAt(0);
                realtor_tab.tab.select();
            }
        });
        Arenda_jaylar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment1_realtor.empty();fragment1_realtor.limit=30;
                fragment1_realtor.category = "Arenda Jaýlar";
                fragment1_realtor.ret.setVisibility(View.VISIBLE);
                fragment1_realtor.refr();
                realtor_tab.tab = realtor_tab.tabLayout.getTabAt(0);
                realtor_tab.tab.select();
            }
        });
        Satlyk_Arenda_we.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment1_realtor.empty();fragment1_realtor.limit=30;
                fragment1_realtor.category = "Satlyk we Arenda Ýerler";
                fragment1_realtor.refr();
                fragment1_realtor.ret.setVisibility(View.VISIBLE);
                realtor_tab.tab = realtor_tab.tabLayout.getTabAt(0);
                realtor_tab.tab.select();
            }
        });
        Satlyk_ofislar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment1_realtor.empty();fragment1_realtor.limit=30;
                fragment1_realtor.category = "Satlyk Ofislar we Marketlar";
                fragment1_realtor.refr();
                fragment1_realtor.ret.setVisibility(View.VISIBLE);
                realtor_tab.tab = realtor_tab.tabLayout.getTabAt(0);
                realtor_tab.tab.select();
            }
        });
        Arenda_Ofislar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment1_realtor.empty();fragment1_realtor.limit=30;
                fragment1_realtor.category = "Arenda Ofislar we Marketlar";
                fragment1_realtor.refr();
                fragment1_realtor.ret.setVisibility(View.VISIBLE);
                realtor_tab.tab = realtor_tab.tabLayout.getTabAt(0);
                realtor_tab.tab.select();
            }
        });
    }
}
