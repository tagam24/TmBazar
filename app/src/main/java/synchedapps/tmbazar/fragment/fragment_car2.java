package synchedapps.tmbazar.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import synchedapps.tmbazar.R;
import synchedapps.tmbazar.bb.Beylekiler.Bildiris_gos_Navigation.bildiris_gos_navidation;
import synchedapps.tmbazar.bb.car_tab;
import synchedapps.tmbazar.dil;
import synchedapps.tmbazar.instant_notifcars;

public class fragment_car2 extends Fragment {
    View view;
    LinearLayout toyota, lexus, bmw, mercedes, Hyundai, Nissan, Opel, LAda, Wolkswagen, infinity, Kia, Ford, Mitsubishi, Uaz, Gaz, Kamaz, Zil, Maz,
            Beylerkiler, Kredit, obmen, vip, gyssagly, satylan, MeninAwto, Ashgabat, Ahal, Balkan, Lebap, Mary, Dashoguz;
    public static String s1 = "", s2 = "", s3 = "", s4 = "", s5 = "", s6 = "";
    TextView c1, c2, c3, c4, c5, c6;

    public fragment_car2() {
    }

    instant_notifcars i = new instant_notifcars();
    public static Handler set;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment_car1.limit = 30;
        view = inflater.inflate(R.layout.fragment_car2, container, false);
        toyota = (LinearLayout) view.findViewById(R.id.marka_toyota);
        lexus = (LinearLayout) view.findViewById(R.id.marka_lexus);
        bmw = (LinearLayout) view.findViewById(R.id.marka_bmw);
        mercedes = (LinearLayout) view.findViewById(R.id.marka_mercedes);
        Hyundai = (LinearLayout) view.findViewById(R.id.marka_hyundai);
        Nissan = (LinearLayout) view.findViewById(R.id.marka_nissan);
        Opel = (LinearLayout) view.findViewById(R.id.marka_opel);
        LAda = (LinearLayout) view.findViewById(R.id.marka_lada);
        Wolkswagen = (LinearLayout) view.findViewById(R.id.marka_wolkswagen);
        infinity = (LinearLayout) view.findViewById(R.id.marka_infiiti);
        Kia = (LinearLayout) view.findViewById(R.id.marka_kia);
        Ford = (LinearLayout) view.findViewById(R.id.marka_ford);
        Mitsubishi = (LinearLayout) view.findViewById(R.id.marka_mitsubishi);
        Uaz = (LinearLayout) view.findViewById(R.id.marka_uaz);
        Gaz = (LinearLayout) view.findViewById(R.id.marka_gaz);
        Kamaz = (LinearLayout) view.findViewById(R.id.marka_kamaz);
        Zil = (LinearLayout) view.findViewById(R.id.marka_zil);
        Maz = (LinearLayout) view.findViewById(R.id.marka_maz);
        Beylerkiler = (LinearLayout) view.findViewById(R.id.marka_beýlekiler);
        Kredit = (LinearLayout) view.findViewById(R.id.saylanan_kredit);
        obmen = (LinearLayout) view.findViewById(R.id.saylanan_obmen);
        vip = (LinearLayout) view.findViewById(R.id.saylanan_vip);
        gyssagly = (LinearLayout) view.findViewById(R.id.saylanan_gyssagly);
     //   satylan = (LinearLayout) view.findViewById(R.id.saylanan_satylan);
        MeninAwto = (LinearLayout) view.findViewById(R.id.saylanan_meninawtulagym);
        Ashgabat = (LinearLayout) view.findViewById(R.id.place_asgabat);
        Ahal = (LinearLayout) view.findViewById(R.id.place_ahal);
        Dashoguz = (LinearLayout) view.findViewById(R.id.place_dasoguz);
        Balkan = (LinearLayout) view.findViewById(R.id.place_balkan);
        Mary = (LinearLayout) view.findViewById(R.id.place_mary);
        Lebap = (LinearLayout) view.findViewById(R.id.place_lebap);
        c1 = (TextView) view.findViewById(R.id.count_add_asgabat);
        c2 = (TextView) view.findViewById(R.id.count_add_ahal);
        c3 = (TextView) view.findViewById(R.id.count_add_balkan);
        c4 = (TextView) view.findViewById(R.id.count_add_lebap);
        c5 = (TextView) view.findViewById(R.id.count_add_mary);
        c6 = (TextView) view.findViewById(R.id.count_add_dasoguz);

        TextView textView_marka = (TextView) view.findViewById(R.id.car_saylanan_marka);
        textView_marka.setText(dil.tm_car_marka.toUpperCase());
        TextView textView_saylanan = (TextView) view.findViewById(R.id.car_saylanan_saylanan);
        textView_saylanan.setText(dil.tm_tablayout_autaulaglar_saylanan_recycle);
        TextView textView_yeri = (TextView) view.findViewById(R.id.car_saylanan_yeri);
        textView_yeri.setText(dil.tm_yerlesyan_yeri.toUpperCase());
        TextView textView_kredit = (TextView) view.findViewById(R.id.car_saylanan_kredit);
        textView_kredit.setText(dil.tm_kredit.toUpperCase());
        TextView textView_obmen = (TextView) view.findViewById(R.id.car_saylanan_obmen);
        textView_obmen.setText(dil.tm_obmen.toUpperCase());
        TextView textView_gyssagly = (TextView) view.findViewById(R.id.car_saylanan_gysagly);
        textView_gyssagly.setText(dil.tm_gyssagly_car);
      //TextView textView_satylan = (TextView) view.findViewById(R.id.car_saylanan_satylan);
     //   textView_satylan.setText(dil.tm_car_satylan);
        TextView textView_men_ulagym = (TextView) view.findViewById(R.id.car_menin_awtoulagym);
        textView_men_ulagym.setText(dil.tm_men_awtoulagym);
        TextView textView_asgabat = (TextView) view.findViewById(R.id.car_saylanan_asgabat);
        textView_asgabat.setText(dil.tm_asgabat);
        TextView textView_ahal = (TextView) view.findViewById(R.id.car_saylanan_ahal);
        textView_ahal.setText(dil.tm_ahal);
        TextView textView_balkan = (TextView) view.findViewById(R.id.car_saylanan_balkan);
        textView_balkan.setText(dil.tm_balkan);
        TextView textView_lebap = (TextView) view.findViewById(R.id.car_saylanan_lebap);
        textView_lebap.setText(dil.tm_lebap);
        TextView textView_mary = (TextView) view.findViewById(R.id.car_saylanan_mary);
        textView_mary.setText(dil.tm_mary);
        TextView textView_dasoguz = (TextView) view.findViewById(R.id.car_saylanan_dasoguz);
        textView_dasoguz.setText(dil.tm_dasoguz);

        i.notif("cars");
        set = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    c1.setText(s1);
                    c2.setText(s2);
                    c3.setText(s3);
                    c4.setText(s4);
                    c5.setText(s5);
                    c6.setText(s6);

                }
            }
        };
        set.sendEmptyMessage(1);
        toyota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_car1.limit=30;
                fragment_car1.empty_cars();
                fragment_car1.category = "Toyota";
                fragment_car1.refr();
                fragment_car1.searchbox.setText("Toyota");
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        lexus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  fragment_car1.limit=30;
                fragment_car1.empty_cars();
                fragment_car1.category = "Lexus";
                fragment_car1.refr();
                fragment_car1.searchbox.setText("Lexus");
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        bmw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  fragment_car1.limit=30;
                fragment_car1.limit=30;
                fragment_car1.empty_cars();
                fragment_car1.category = "BMW";
                fragment_car1.refr();
                fragment_car1.searchbox.setText("BMW");
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        mercedes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  fragment_car1.limit=30;
                fragment_car1.empty_cars();
                fragment_car1.category = "Mercedec-Benz";
                fragment_car1.refr();
                fragment_car1.searchbox.setText("Mercedec-Benz");
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        Hyundai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  fragment_car1.limit=30;
                fragment_car1.empty_cars();
                fragment_car1.category = "Hyundai";
                fragment_car1.refr();
                fragment_car1.searchbox.setText("Hyundai");
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        Nissan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  fragment_car1.limit=30;
                fragment_car1.empty_cars();
                fragment_car1.category = "Nissan";
                fragment_car1.refr();
                fragment_car1.searchbox.setText("Nissan");
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        Opel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  fragment_car1.limit=30;
                fragment_car1.empty_cars();
                fragment_car1.category = "Opel";
                fragment_car1.refr();
                fragment_car1.searchbox.setText("Lada");
                fragment_car1.searchbox.setText("Opel");
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        LAda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  fragment_car1.limit=30;
                fragment_car1.empty_cars();
                fragment_car1.category = "Lada";
                fragment_car1.refr();
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        Wolkswagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  fragment_car1.limit=30;
                fragment_car1.empty_cars();
                fragment_car1.category = "Wolkswagen";
                fragment_car1.searchbox.setText("Wolkswagen");
                fragment_car1.refr();
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        infinity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_car1.empty_cars();  fragment_car1.limit=30;
                fragment_car1.category = "Infiniti";
                fragment_car1.searchbox.setText("Infiniti");
                fragment_car1.refr();
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        Kia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_car1.empty_cars();
                fragment_car1.category = "Kia";
                fragment_car1.searchbox.setText("Kia");
                fragment_car1.refr();
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        Ford.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  fragment_car1.limit=30;
                fragment_car1.empty_cars();
                fragment_car1.category = "Ford";
                fragment_car1.searchbox.setText("Ford");
                fragment_car1.refr();
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        Mitsubishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  fragment_car1.limit=30;
                fragment_car1.empty_cars();
                fragment_car1.category = "Mitsubishi";
                fragment_car1.searchbox.setText("Mitsubishi");
                fragment_car1.refr();
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        Uaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  fragment_car1.limit=30;
                fragment_car1.empty_cars();
                fragment_car1.category = "UAZ";
                fragment_car1.searchbox.setText("UAZ");
                fragment_car1.refr();
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        Gaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  fragment_car1.limit=30;
                fragment_car1.empty_cars();
                fragment_car1.category = "Gaz";
                fragment_car1.searchbox.setText("Gaz");
                fragment_car1.refr();
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        Kamaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  fragment_car1.limit=30;
                fragment_car1.empty_cars();
                fragment_car1.category = "Kamaz";
                fragment_car1.searchbox.setText("Kamaz");
                fragment_car1.refr();
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        Zil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_car1.empty_cars();  fragment_car1.limit=30;
                fragment_car1.category = "Zil";
                fragment_car1.searchbox.setText("Zil");
                fragment_car1.refr();
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        Maz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  fragment_car1.limit=30;
                fragment_car1.empty_cars();
                fragment_car1.category = "Maz";
                fragment_car1.searchbox.setText("Maz");
                fragment_car1.refr();
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        Beylerkiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  fragment_car1.limit=30;
                fragment_car1.empty_cars();
                fragment_car1.category = "beylekiler";
                fragment_car1.searchbox.setText("Beýlekiler");
                fragment_car1.refr();
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        Kredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  fragment_car1.limit=30;
                fragment_car1.empty_cars();
                fragment_car1.credit = "1";
                fragment_car1.refr();
                fragment_car1.searchbox.setText("Kredit");
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        obmen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_car1.empty_cars();  fragment_car1.limit=30;
                fragment_car1.obmen = "1";
                fragment_car1.refr();
                fragment_car1.searchbox.setText("Obmen");
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        vip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_car1.empty_cars();  fragment_car1.limit=30;
                fragment_car1.vip = "1";
                fragment_car1.get_local();
                fragment_car1.searchbox.setText("VIP");
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();

            }
        });
        gyssagly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  fragment_car1.limit=30;
                fragment_car1.limit=30;
                fragment_car1.empty_cars();
                fragment_car1.gyssagly = "1";
                fragment_car1.get_local();
                fragment_car1.searchbox.setText("Gyssagly");
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
       /* satylan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_car1.empty_cars();
                fragment_car1.vip = "4";
                fragment_car1.refr();
                fragment_car1.searchbox.setText("Satylan");
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });*/
        MeninAwto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment_car1.ret.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getActivity(), bildiris_gos_navidation.class);
                startActivity(intent);

            }
        });
        Ashgabat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  fragment_car1.limit=30;
                fragment_car1.empty_cars();
                fragment_car1.location = "0";
                fragment_car1.refr();
                fragment_car1.searchbox.setText("Aşgabat");
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        Ahal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  fragment_car1.limit=30;
                fragment_car1.empty_cars();
                fragment_car1.location = "1";
                fragment_car1.refr();
                fragment_car1.searchbox.setText("Ahal");
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        Balkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  fragment_car1.limit=30;
                fragment_car1.empty_cars();
                fragment_car1.location = "3";
                fragment_car1.searchbox.setText("Balkan");
                fragment_car1.refr();
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        Lebap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  fragment_car1.limit=30;
                fragment_car1.empty_cars();
                fragment_car1.location = "4";
                fragment_car1.searchbox.setText("Lebap");
                fragment_car1.refr();
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        Mary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  fragment_car1.limit=30;
                fragment_car1.empty_cars();
                fragment_car1.location = "2";
                fragment_car1.searchbox.setText("Mary");
                fragment_car1.refr();
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        Dashoguz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_car1.empty_cars();  fragment_car1.limit=30;
                fragment_car1.location = "5";
                fragment_car1.searchbox.setText("Daşoguz");
                fragment_car1.refr();
                fragment_car1.ret.setVisibility(View.VISIBLE);
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
            }
        });
        return view;

    }
}
