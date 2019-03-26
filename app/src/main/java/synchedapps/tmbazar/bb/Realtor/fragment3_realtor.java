package synchedapps.tmbazar.bb.Realtor;

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


public class fragment3_realtor extends Fragment {

    View view;
    dil di = new dil();
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    LinearLayout ash, mar, das, leb, bal, ahal;
    public static Handler h = new Handler();
    static TextView c1;
    static TextView c2;
    static TextView c3;
    static TextView c4;
    static TextView c5;
    static TextView c6;
    public static String s1 = "", s2 = "", s3 = "", s4 = "", s5 = "", s6 = "";
    public static Handler set = new Handler();

    public fragment3_realtor() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment3_realtor, container, false);
        di.set_text();
        fragment1_realtor.ban=0;
        TextView textView_1 = (TextView) view.findViewById(R.id.text_realtor_ashgabat);
        textView_1.setText(dil.tm_small_asgabat + "(");
        TextView textView_2 = (TextView) view.findViewById(R.id.text_realtor_ahal);
        textView_2.setText(dil.tm_small_ahal + "(");
        TextView textView_3 = (TextView) view.findViewById(R.id.text_realtor_mary);
        textView_3.setText(dil.tm_small_mary + "(");
        TextView textView_4 = (TextView) view.findViewById(R.id.text_realtor_lebap);
        textView_4.setText(dil.tm_small_lebap + "(");
        TextView textView_5 = (TextView) view.findViewById(R.id.text_realtor_dashoguz);
        textView_5.setText(dil.tm_small_dasoguz + "(");
        TextView textView_6 = (TextView) view.findViewById(R.id.text_realtor_balkan);
        textView_6.setText(dil.tm_small_balkan + "(");

        ash = (LinearLayout) view.findViewById(R.id.realtor_ashgabat);
        c1 = (TextView) view.findViewById(R.id.text_count_ashgabat_yeri_fragment3_realtor);
        c2 = (TextView) view.findViewById(R.id.text_count_dasoguz_yeri_fragment3_realtor);
        c4 = (TextView) view.findViewById(R.id.text_count_lebap_yeri_fragment3_realtor);
        c5 = (TextView) view.findViewById(R.id.text_count_mary_yeri_fragment3_realtor);
        c6 = (TextView) view.findViewById(R.id.text_count_ahal_yeri_fragment3_realtor);
        c3 = (TextView) view.findViewById(R.id.text_count_balkan_yeri_fragment3_realtor);
        mar = (LinearLayout) view.findViewById(R.id.realtor_mary);
        das = (LinearLayout) view.findViewById(R.id.realtor_dashoguz);
        leb = (LinearLayout) view.findViewById(R.id.realtor_lebap);
        bal = (LinearLayout) view.findViewById(R.id.realtor_balkan);
        ahal = (LinearLayout) view.findViewById(R.id.realtor_ahal);
        Onclicker();

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
        return view;
    }

    void Onclicker() {
        ash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment1_realtor.recyclerView.smoothScrollToPosition(0);
                //fragment1_realtor.recyclerView.scrollToPosition();
                fragment1_realtor.empty();
                fragment1_realtor.limit=30;
                fragment1_realtor.location = "Aşgabat";
                fragment1_realtor.ret.setVisibility(View.VISIBLE);
             //   fragment1_realtor.refr();
                realtor_tab.tab = realtor_tab.tabLayout.getTabAt(0);
                realtor_tab.tab.select();
            }
        });
        das.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment1_realtor.empty();fragment1_realtor.limit=30;
                fragment1_realtor.location = "Daşoguz";
                fragment1_realtor.ret.setVisibility(View.VISIBLE);
              //  fragment1_realtor.refr();
                realtor_tab.tab = realtor_tab.tabLayout.getTabAt(0);
                realtor_tab.tab.select();
            }
        });
        mar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment1_realtor.empty();fragment1_realtor.limit=30;
                fragment1_realtor.location = "Mary";
                fragment1_realtor.ret.setVisibility(View.VISIBLE);
             //   fragment1_realtor.refr();
                realtor_tab.tab = realtor_tab.tabLayout.getTabAt(0);
                realtor_tab.tab.select();
            }
        });
        leb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment1_realtor.empty();fragment1_realtor.limit=30;
                fragment1_realtor.location = "Lebap";
                fragment1_realtor.ret.setVisibility(View.VISIBLE);
              //  fragment1_realtor.refr();
                realtor_tab.tab = realtor_tab.tabLayout.getTabAt(0);
                realtor_tab.tab.select();
            }
        });
        bal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment1_realtor.empty();fragment1_realtor.limit=30;
                fragment1_realtor.location = "Balkan";
                fragment1_realtor.ret.setVisibility(View.VISIBLE);
              //  fragment1_realtor.refr();
                realtor_tab.tab = realtor_tab.tabLayout.getTabAt(0);
                realtor_tab.tab.select();
            }
        });
        ahal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment1_realtor.empty();fragment1_realtor.limit=30;
                fragment1_realtor.location = "Ahal";
               // fragment1_realtor.refr();
                fragment1_realtor.ret.setVisibility(View.VISIBLE);
                realtor_tab.tab = realtor_tab.tabLayout.getTabAt(0);
                realtor_tab.tab.select();
            }
        });

    }

}
