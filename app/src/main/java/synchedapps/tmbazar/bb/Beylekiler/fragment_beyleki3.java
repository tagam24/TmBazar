package synchedapps.tmbazar.bb.Beylekiler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import synchedapps.tmbazar.R;
import synchedapps.tmbazar.dil;

public class fragment_beyleki3 extends Fragment {

    View view;
    dil di=new dil();
    static TextView c1;
    static TextView c2;
    static TextView c3;
    static TextView c4;
    static TextView c5;
    static TextView c6;
    public static String s1="",s2="",s3="",s4="",s5="",s6="";
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    LinearLayout l1,l2,l3,l4,l5,l6;
    public fragment_beyleki3() {
    }
    public  static  Handler set=new Handler();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment3_beylekiler, container, false);

        di.set_text();
        TextView textView_1 = (TextView) view.findViewById(R.id.asgabat_beylekiler_saylanan);
        textView_1.setText(dil.tm_small_asgabat + "(");
        TextView textView_2 = (TextView) view.findViewById(R.id.ahal_beylekiler_saylanan);
        textView_2.setText(dil.tm_small_ahal + "(");
        TextView textView_3 = (TextView) view.findViewById(R.id.mary_beylekiler_saylanan);
        textView_3.setText(dil.tm_small_mary + "(");
        TextView textView_4 = (TextView) view.findViewById(R.id.lebap_beylekiler_saylanan);
        textView_4.setText(dil.tm_small_lebap + "(");
        TextView textView_5 = (TextView) view.findViewById(R.id.dasoguz_beylekiler_saylanan);
        textView_5.setText(dil.tm_small_dasoguz + "(");
        TextView textView_6 = (TextView) view.findViewById(R.id.balkan_beylekiler_saylanan);
        textView_6.setText(dil.tm_small_balkan + "(");

        l1=(LinearLayout)view.findViewById(R.id.beylekiler_ashgabat);
        l2=(LinearLayout)view.findViewById(R.id.beylekiler_ahal);
        l3=(LinearLayout)view.findViewById(R.id.beylekiler_balkan);
        l4=(LinearLayout)view.findViewById(R.id.beylekiler_lebap);
        l5=(LinearLayout)view.findViewById(R.id.beylekiler_mary);
        l6=(LinearLayout)view.findViewById(R.id.beylekiler_dashoguz);
        c1 = (TextView) view.findViewById(R.id.text_count_ashgabat_yeri_fragment3_beylekiler);
        c2 = (TextView) view.findViewById(R.id.text_count_dasoguz_yeri_fragment3_beylekiler);
        c4 = (TextView) view.findViewById(R.id.text_count_lebap_yeri_fragment3_beylekiler);
        c5 = (TextView) view.findViewById(R.id.text_count_mary_yeri_fragment3_beylekiler);
        c6 = (TextView) view.findViewById(R.id.text_count_ahal_yeri_fragment3_beylekiler);
        c3 = (TextView) view.findViewById(R.id.text_count_balkan_yeri_fragment3_beylekiler);
        set = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    c1.setText(s1);
                    c2.setText(s6);
                    c3.setText(s3);
                    c4.setText(s4);
                    c5.setText(s5);
                    c6.setText(s2);

                }
            }
        };
        set.sendEmptyMessage(1);
        Onclicker();
        return view;
    }
    void Onclicker() {
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_beyleki1.empty();fragment_beyleki1.limit=30;
                fragment_beyleki1.category = "Aşgabat";
             //   fragment_beyleki1.refr();
                fragment_beyleki1.ret.setVisibility(View.VISIBLE);
                beylekiler_tab.tab = beylekiler_tab.tabLayout.getTabAt(0);
                beylekiler_tab.tab.select();
            }
        });
       l6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_beyleki1.empty();fragment_beyleki1.limit=30;
                fragment_beyleki1.category = "Daşoguz";
              //  fragment_beyleki1.refr();
                fragment_beyleki1.ret.setVisibility(View.VISIBLE);
                beylekiler_tab.tab = beylekiler_tab.tabLayout.getTabAt(0);
                beylekiler_tab.tab.select();
            }
        });
       l5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {fragment_beyleki1.limit=30;
                fragment_beyleki1.empty();
                fragment_beyleki1.category = "Mary";
              //  fragment_beyleki1.refr();
                fragment_beyleki1.ret.setVisibility(View.VISIBLE);
                beylekiler_tab.tab = beylekiler_tab.tabLayout.getTabAt(0);
                beylekiler_tab.tab.select();
            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {fragment_beyleki1.limit=30;
                fragment_beyleki1.empty();
                fragment_beyleki1.category = "Lebap";
               // fragment_beyleki1.refr();
                fragment_beyleki1.ret.setVisibility(View.VISIBLE);
                beylekiler_tab.tab = beylekiler_tab.tabLayout.getTabAt(0);
                beylekiler_tab.tab.select();
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_beyleki1.empty();fragment_beyleki1.limit=30;
                fragment_beyleki1.category = "Balkan";
              //  fragment_beyleki1.refr();
                fragment_beyleki1.ret.setVisibility(View.VISIBLE);
                beylekiler_tab.tab = beylekiler_tab.tabLayout.getTabAt(0);
                beylekiler_tab.tab.select();
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {fragment_beyleki1.limit=30;
                fragment_beyleki1.empty();
                fragment_beyleki1.category = "Ahal";
                //fragment_beyleki1.refr();
                fragment_beyleki1.ret.setVisibility(View.VISIBLE);
                beylekiler_tab.tab = beylekiler_tab.tabLayout.getTabAt(0);
                beylekiler_tab.tab.select();
            }
        });

    }

}
