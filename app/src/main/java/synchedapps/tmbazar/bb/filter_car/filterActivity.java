package synchedapps.tmbazar.bb.filter_car;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.Models.model_cars;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.bb.car_tab;
import synchedapps.tmbazar.dil;
import synchedapps.tmbazar.fragment.fragment_car1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class filterActivity extends AppCompatActivity {

    public static List<String> list_yeri;
    public static List<String> list_renki;
    public static ArrayList<model_cars> data = new ArrayList<>();
    Button bt_sayla;
    Button bt_yza;
    Button filtr;
    Dialog d;
    EditText editText_basy;
    EditText editText_sony;
    CheckBox credit, obmen;
    ArrayList<String> cat;
    ArrayList<String> mod;
    public static TextView marka;
    private Db db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        db = new Db(MainActivity.ctx);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_filter_for_car);
        setSupportActionBar(toolbar);
        dil dd=new dil();
        dd.set_text();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(dil.tm_filter_toolbar);

        TextView textView_title=(TextView)findViewById(R.id.filter_marka_title);
        textView_title.setText(dil.tm_car_marka);
        TextView textView_yyly=(TextView)findViewById(R.id.filter_yyly_title);
        textView_yyly.setText(dil.tm_car_yyly);
        TextView textView_renki=(TextView)findViewById(R.id.filter_renki_title);
        textView_renki.setText(dil.tm_car_renki);
        TextView textView_bahasy=(TextView)findViewById(R.id.filter_bahasy_title);
        textView_bahasy.setText(dil.tm_car_bahasy);
        TextView textView_yeri=(TextView)findViewById(R.id.filter_yeri_title);
        textView_yeri.setText(dil.tm_yerlesyan_yeri);

        TextView textView_marka_content=(TextView)findViewById(R.id.text_marka_filter_for_car);
        textView_marka_content.setText(dil.tm_saylanylmadyk);
        TextView textView_yyly_content=(TextView)findViewById(R.id.text_yyly_filter_for_car);
        textView_yyly_content.setText(dil.tm_saylanylmadyk);
        TextView textView_renki_content=(TextView)findViewById(R.id.text_renki_filter_for_car);
        textView_renki_content.setText(dil.tm_saylanylmadyk);
        TextView textView_bahasy_content=(TextView)findViewById(R.id.text_bahasy_filter_for_car);
        textView_bahasy_content.setText(dil.tm_saylanylmadyk);
        TextView textView_yeri_content=(TextView)findViewById(R.id.text_yeri_filter_for_car);
        textView_yeri_content.setText(dil.tm_saylanylmadyk);
        TextView textView_kredit_content=(TextView)findViewById(R.id.checkbox_text_kredit_for_сar);
        textView_kredit_content.setText(dil.tm_kredit_beryanler);
        TextView textView_obmen_content=(TextView)findViewById(R.id.checkbox_text_obmen_for_сar);
        textView_obmen_content.setText(dil.tm_obmen_edyanler);

        marka = (TextView) findViewById(R.id.text_marka_filter_for_car);
        credit = (CheckBox) findViewById(R.id.checkbox_check_kredit_for_сar);
        obmen = (CheckBox) findViewById(R.id.checkbox_check_obmen_for_сar);

        filtr = (Button) findViewById(R.id.button_for_filter_show_selected);
        filtr.setText(dil.tm_bildiris_gorkez);
        LinearLayout linearLayout_marka = (LinearLayout) findViewById(R.id.layout_filter_marka);
        LinearLayout linearLayout_yyly = (LinearLayout) findViewById(R.id.layout_filter_yyly);
        LinearLayout linearLayout_yeri = (LinearLayout) findViewById(R.id.layout_filter_yeri);
        LinearLayout linearLayout_renki = (LinearLayout) findViewById(R.id.layout_filter_renki);
        LinearLayout linearLayout_bahasy = (LinearLayout) findViewById(R.id.layout_filter_bahasy);

        fragment_car1.empty_cars();
        cat = new ArrayList<>();
        mod = new ArrayList<>();
        data = new ArrayList<>();
        filtr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(credit.isChecked())fragment_car1.credit="1"; else fragment_car1.credit="";
                if(obmen.isChecked())fragment_car1.obmen="1"; else fragment_car1.obmen="";
                for (int i = 0; i < data.size(); i++) {
                    if (!cat.contains(data.get(i).getCategory())) {
                        cat.add(data.get(i).getCategory());
                        fragment_car1.category += data.get(i).getCategory() + ",";

                    }
                    if (!mod.contains(data.get(i).getModel())) {
                        mod.add(data.get(i).getModel());

                        fragment_car1.model += data.get(i).getModel() + ",";
                    }
                }
                if (fragment_car1.category.length() > 0) {
                    fragment_car1.category = fragment_car1.category.substring(0, fragment_car1.category.length() - 1);
                }
                if (fragment_car1.model.length() > 0)
                    fragment_car1.model = fragment_car1.model.substring(0, fragment_car1.model.length() - 1);
                fragment_car1.refr();
                fragment_car1.ret.setVisibility(View.VISIBLE);
                String y = "", p = "";
              /*  if (!fragment_car1.year1.equals("") || fragment_car1.year2.equals("") || fragment_car1.year1.equals("") || !fragment_car1.year2.equals("")) {
                    y = fragment_car1.year1 + "<" + fragment_car1.year2 + ",";
                }
                if (!fragment_car1.price1.equals("") || fragment_car1.price2.equals("") || fragment_car1.price1.equals("") || !fragment_car1.price2.equals("")) {
                    y = fragment_car1.price1 + "<" + fragment_car1.price2 + ",";
                }*/
                fragment_car1.searchbox.setText(fragment_car1.category /* fragment_car1.model + fragment_car1.color /*+
                        p + y +fragment_car1.location*/);
                fragment_car1.print_filtr();
                car_tab.tab = car_tab.tabLayout.getTabAt(0);
                car_tab.tab.select();
                Log.d("creditObmen",fragment_car1.credit+"  "+fragment_car1.obmen);
                finish();
            }
        });


        linearLayout_marka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_marka = new Intent(getApplicationContext(), axpandableListview_marka.class);
                startActivity(intent_marka);
            }
        });
        final String[] numbers_basy = new String[]{"Başy", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989",
                "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005"
                , "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018"};
        final String[] numbers_sony = new String[]{"Soňy", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989",
                "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005"
                , "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018"};
        final String[] numbers_basy_ru = new String[]{"Начало", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989",
                "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005"
                , "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018"};
        final String[] numbers_sony_ru = new String[]{"Конец", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989",
                "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003" ,"2004", "2005"
                , "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018"};
                linearLayout_yyly.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Dialog d = new Dialog(filterActivity.this);
                        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        d.setContentView(R.layout.dialog_yyly);

                        final NumberPicker np_sony = (NumberPicker) d.findViewById(R.id.numberpicker_yyly2);
                        TextView textView = (TextView) d.findViewById(R.id.filter_dialog_yyly_title);
                        textView.setText(dil.tm_car_yyly);
                        final NumberPicker np_basy = (NumberPicker) d.findViewById(R.id.numberpicker_yyly1);
                        if (db.get_dil().equals("ru")) {


                            np_basy.setDisplayedValues(numbers_basy_ru);
                            np_basy.setMinValue(0);
                            np_basy.setMaxValue(39);
                            np_basy.setWrapSelectorWheel(true);

                            np_sony.setDisplayedValues(numbers_sony_ru);
                            np_sony.setMinValue(0);
                            np_sony.setMaxValue(39);
                            np_sony.setWrapSelectorWheel(true);
                        } else {
                            np_basy.setDisplayedValues(numbers_basy);
                            np_basy.setMinValue(0);
                            np_basy.setMaxValue(39);
                            np_basy.setWrapSelectorWheel(true);

                            np_sony.setDisplayedValues(numbers_sony);
                            np_sony.setMinValue(0);
                            np_sony.setMaxValue(39);
                            np_sony.setWrapSelectorWheel(true);
                        }

                        //d.getWindow().setLayout(, 1200);

                        d.show();
                        Button bt_sayla = (Button) d.findViewById(R.id.button_filter_yyly_dialog_sayla);
                        Button bt_yza = (Button) d.findViewById(R.id.button_filter_yyly_dialog_yza);

                        bt_sayla.setText(dil.tm_sayla);
                        bt_yza.setText(dil.tm_yza);

                        bt_sayla.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                TextView textView_yyly = (TextView) findViewById(R.id.text_yyly_filter_for_car);
                                textView_yyly.setText(numbers_basy[np_basy.getValue()] + "-" + numbers_sony[np_sony.getValue()]);
                                if( numbers_basy[np_basy.getValue()].equals("Başy"))fragment_car1.year1 = ""+0; else fragment_car1.year1 = numbers_basy[np_basy.getValue()];
                               if(numbers_sony[np_sony.getValue()].equals("Soňy")) fragment_car1.year2 =""+100000;  else fragment_car1.year2 = numbers_sony[np_sony.getValue()];

                                d.dismiss();
                            }
                        });
                        bt_yza.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                TextView textView_yyly = (TextView) findViewById(R.id.text_yyly_filter_for_car);
                                textView_yyly.setText(dil.tm_saylanylmadyk);
                                d.dismiss();
                            }
                        });


                    }
                });
        linearLayout_bahasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d = new Dialog(filterActivity.this);
                d.requestWindowFeature(Window.FEATURE_NO_TITLE);
                d.setContentView(R.layout.dialog_bahasy_car);
                TextView textView = (TextView) d.findViewById(R.id.dialog_bahasy_title);
                textView.setText(dil.tm_car_bahasy);
                editText_basy = (EditText) d.findViewById(R.id.edittext_baskybaha_car_filter);
                editText_sony = (EditText) d.findViewById(R.id.edittext_sonkybaha_car_filter);

                editText_basy.setHint(dil.tm_in_arzan);
                editText_sony.setHint(dil.tm_in_gymmat);

//                d.getWindow().setLayout(1000, 800);
                d.show();
                Button bt_sayla = (Button) d.findViewById(R.id.button_filter_bahasy_dialog_sayla);
                Button bt_yza = (Button) d.findViewById(R.id.button_filter_bahasy_dialog_yza);

                bt_sayla.setText(dil.tm_sayla);
                bt_yza.setText(dil.tm_yza);


                bt_sayla.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView textView_yyly = (TextView) findViewById(R.id.text_bahasy_filter_for_car);
                        String s = editText_basy.getText() + "-" + editText_sony.getText();
                        fragment_car1.price1 = editText_basy.getText().toString();
                        fragment_car1.price2 = editText_sony.getText().toString();
                        if (s.length() > 1) {
                            textView_yyly.setText(editText_basy.getText() + "-" + editText_sony.getText());
                        } else {
                            textView_yyly.setText(dil.tm_saylanylmadyk);
                        }
                        d.dismiss();
                    }
                });
                bt_yza.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView textView_yyly = (TextView) findViewById(R.id.text_yyly_filter_for_car);
                        textView_yyly.setText(dil.tm_saylanylmadyk);
                        d.dismiss();
                    }
                });

            }
        });

        linearLayout_yeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog d = new Dialog(filterActivity.this);
                d.requestWindowFeature(Window.FEATURE_NO_TITLE);
                d.setContentView(R.layout.dialog_listview_with_checkbox);
                list_yeri = new ArrayList<String>();

                if (db.get_dil().equals("ru")) {
                    list_yeri = Arrays.asList(getResources().getStringArray(R.array.yeri_car_ru));
                } else {
                    list_yeri = Arrays.asList(getResources().getStringArray(R.array.yeri_car));
                }
                TextView title = (TextView) d.findViewById(R.id.title_for_listviewWithCheckbox_for_car_filter);
                title.setText(dil.tm_yerlesyan_yeri);
                final ListView listView = (ListView) d.findViewById(R.id.listviewWithCheck_for_car_filter);
                listView.setChoiceMode(listView.CHOICE_MODE_MULTIPLE);
                listView.setTextFilterEnabled(true);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(filterActivity.this, android.R.layout.simple_list_item_multiple_choice, list_yeri);
                listView.setAdapter(arrayAdapter);
                Button bt_sayla = (Button) d.findViewById(R.id.button_filter_dialog_sayla_listview);
                Button bt_yza = (Button) d.findViewById(R.id.button_filter_dialog_yza_listview);

                bt_sayla.setText(dil.tm_sayla);
                bt_yza.setText(dil.tm_yza);

                bt_sayla.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String items = "";
                        int len = listView.getCount();
                        SparseBooleanArray checked = listView.getCheckedItemPositions();
                        String yerler="";
                        for (int i = 0; i < len; i++)
                            if (checked.get(i)) {
                             //   String item = list_yeri.get(i);

                                yerler+=" " +list_yeri.get(i)+",";
                                String item = ""+i;
                                items = items + item + ",";
                                fragment_car1.location = items;
                                /* do whatever you want with the checked item */
                            }
                        if (items.length() > 0) {
                            String items2 = items.substring(0, items.length() - 1);
                            TextView textView_yyly = (TextView) findViewById(R.id.text_yeri_filter_for_car);
                            textView_yyly.setText(yerler);
                        } else {
                            TextView textView_yyly = (TextView) findViewById(R.id.text_yeri_filter_for_car);
                            textView_yyly.setText(dil.tm_saylanylmadyk);
                        }
                        d.dismiss();
                    }
                });

                bt_yza.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView textView_yyly = (TextView) findViewById(R.id.text_yeri_filter_for_car);
                        textView_yyly.setText(dil.tm_saylanylmadyk);
                        d.dismiss();
                    }
                });

                d.show();


            }


        });

        linearLayout_renki.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                final Dialog d = new Dialog(filterActivity.this);
                d.requestWindowFeature(Window.FEATURE_NO_TITLE);
                d.setContentView(R.layout.dialog_listview_with_checkbox);

                list_renki = new ArrayList<String>();
                if (db.get_dil().equals("ru")) {
                    list_renki = Arrays.asList(getResources().getStringArray(R.array.renki_car_ru));
                } else {
                    list_renki = Arrays.asList(getResources().getStringArray(R.array.renki_car));
                }

                TextView title = (TextView) d.findViewById(R.id.title_for_listviewWithCheckbox_for_car_filter);
                title.setText(dil.tm_car_renki);
                final ListView listView = (ListView) d.findViewById(R.id.listviewWithCheck_for_car_filter);
                listView.setChoiceMode(listView.CHOICE_MODE_MULTIPLE);
                listView.setTextFilterEnabled(true);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(filterActivity.this, android.R.layout.simple_list_item_multiple_choice, list_renki);
                listView.setAdapter(arrayAdapter);
                Button bt_sayla = (Button) d.findViewById(R.id.button_filter_dialog_sayla_listview);
                Button bt_yza = (Button) d.findViewById(R.id.button_filter_dialog_yza_listview);

                bt_sayla.setText(dil.tm_sayla);
                bt_yza.setText(dil.tm_yza);

                bt_sayla.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String items = "";
                        int len = listView.getCount();
                        SparseBooleanArray checked = listView.getCheckedItemPositions();
                        String colors="";
                        for (int i = 0; i < len; i++)
                            if (checked.get(i)) {
                                String item = ""+i;
                                colors+=list_renki.get(i)+",";
                                items = items + item + ",";
                                fragment_car1.color = items;
                                /* do whatever you want with the checked item */
                            }
                        if (items.length() > 0) {
                            String items2 = items.substring(0, items.length() - 1);
                            TextView textView_yyly = (TextView) findViewById(R.id.text_renki_filter_for_car);
                            textView_yyly.setText(colors);
                        } else {
                            TextView textView_yyly = (TextView) findViewById(R.id.text_renki_filter_for_car);
                            textView_yyly.setText(dil.tm_saylanylmadyk);
                        }
                        d.dismiss();
                    }
                });

                bt_yza.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView textView_yyly = (TextView) findViewById(R.id.text_renki_filter_for_car);
                        textView_yyly.setText(dil.tm_saylanylmadyk);
                        d.dismiss();
                    }
                });
                d.show();


            }
        });

    }

    public void Gui() {
        bt_sayla = (Button) d.findViewById(R.id.button_filter_dialog_sayla_listview);
        bt_yza = (Button) d.findViewById(R.id.button_filter_dialog_sayla_listview);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == android.R.id.home) {

            Intent intent = new Intent(getApplicationContext(), car_tab.class);
            startActivity(intent);

        }
        return true;
    }

}
