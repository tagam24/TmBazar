package synchedapps.tmbazar.bb.Realtor;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.dil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class filterrealtor extends AppCompatActivity {
    dil di = new dil();
    Db db = new Db(MainActivity.ctx);
    LinearLayout linearLayout_otag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filterrealtor);
        di.set_text();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_filter_for_realtor);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(dil.tm_filter_toolbar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        LinearLayout linearLayout_kateg = (LinearLayout) findViewById(R.id.layout_filter_kateg_realtor);
         linearLayout_otag = (LinearLayout) findViewById(R.id.layout_filter_otag_realtor);
        LinearLayout linearLayout_bahasy = (LinearLayout) findViewById(R.id.layout_filter_bahasy_realtor);
        LinearLayout linearLayout_yeri = (LinearLayout) findViewById(R.id.layout_filter_yeri_realtor);

        TextView textView_kateg = (TextView) findViewById(R.id.filter_kageg_title_realtor);
        textView_kateg.setText(dil.tm_kategoriya);
        TextView textView_otagy = (TextView) findViewById(R.id.filter_otag_title_realtor);
        textView_otagy.setText(dil.tm_otagyn_sany);
        TextView textView_bahasy = (TextView) findViewById(R.id.filter_bahasy_title_realtor);
        textView_bahasy.setText(dil.tm_car_bahasy);
        TextView textView_yeri = (TextView) findViewById(R.id.filter_yeri_title_realtor);
        textView_yeri.setText(dil.tm_yerlesyan_yeri);


        final EditText text_kateg = (EditText) findViewById(R.id.text_kateg_filter_for_realtor);
        text_kateg.setHint(dil.tm_saylanylmadyk);
        final EditText text_otagy = (EditText) findViewById(R.id.text_otag_filter_for_realtor);
        text_otagy.setHint(dil.tm_saylanylmadyk);
        final EditText text_bahasy = (EditText) findViewById(R.id.text_bahasy_filter_for_realtor);
        text_bahasy.setHint(dil.tm_saylanylmadyk);
        final EditText text_yeri = (EditText) findViewById(R.id.text_yeri_filter_for_realtor);
        text_yeri.setHint(dil.tm_saylanylmadyk);

        Button button = (Button) findViewById(R.id.button__realtor_for_filter_show_selected);
        button.setText(dil.tm_bildiris_gorkez);

        linearLayout_kateg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(filterrealtor.this);
                builder.setTitle(dil.tm_kategoriya);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(filterrealtor.this, android.R.layout.simple_dropdown_item_1line);
                List<String> list = new ArrayList<>();
                list.add("Satlyk Elitkalar");
                list.add("Arenda Elitkalar");
                list.add("Satlyk Jaýlar");
                list.add("Arenda Jaýlar");
                list.add("Satlyk we Arenda Ýerler");
                list.add("Satlyk Ofislar we Marketlar");
                list.add("Arenda Ofislar we Marketlar");
                if (db.get_dil().equals("tm")) arrayAdapter.addAll(list);
                List<String> list1 = new ArrayList<>();
                list1.add("ЭЛИТКА НА ПРОДАЖУ");
                list1.add("ЭЛИТКА НА АРЕНДУ");
                list1.add("КВАРТИРА НА ПРОДАЖУ");
                list1.add("КВАРТИРА НА АРЕНДУ");
                list1.add("ПЛОЩАДЬ НА АРЕНДУ И ПРОДАЖУ");
                list1.add("ОФИСЫ И МАРКЕТЫ НА ПРОДАЖУ");
                list1.add("ОФИСЫ И МАРКЕТЫ НА АРЕНДУ");
                if (db.get_dil().equals("ru")) arrayAdapter.addAll(list1);

                builder.setNegativeButton(dil.tm_yza, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        text_kateg.setText(arrayAdapter.getItem(which));
                        fragment1_realtor.category=arrayAdapter.getItem(which);
                        if (which > 3) {

                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout_otag.getLayoutParams();
                            layoutParams.topMargin = 0;
                            linearLayout_otag.setLayoutParams(layoutParams);linearLayout_otag.getLayoutParams().height = 0;
                            linearLayout_otag.requestLayout();

                        } else {
                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout_otag.getLayoutParams();
                            layoutParams.topMargin = 10;
                            linearLayout_otag.setLayoutParams(layoutParams);
                            linearLayout_otag.getLayoutParams().height = 130;
                            linearLayout_otag.requestLayout();

                        }

                    }
                });
                builder.show();
            }
        });
        linearLayout_yeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(filterrealtor.this);
                builder.setTitle(dil.tm_yerlesyan_yeri);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(filterrealtor.this, android.R.layout.simple_dropdown_item_1line);
                List<String> list = new ArrayList<>();
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.yeri_car)));
                if (db.get_dil().equals("tm")) arrayAdapter.addAll(list);
                List<String> list1 = new ArrayList<String>();
                list1.addAll(Arrays.asList(getResources().getStringArray(R.array.yeri_car_ru)));
                if (db.get_dil().equals("ru")) arrayAdapter.addAll(list1);
                builder.setNegativeButton(dil.tm_yza, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        text_yeri.setText(arrayAdapter.getItem(which));
                        fragment1_realtor.location=arrayAdapter.getItem(which);
                    }
                });
                builder.show();
            }
        });
        linearLayout_otag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(filterrealtor.this);
                builder.setTitle(dil.tm_kategoriya);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(filterrealtor.this, android.R.layout.simple_dropdown_item_1line);
                final List<String> list_1 = new ArrayList<>();
                list_1.add("1");
                list_1.add("2");
                list_1.add("3");
                list_1.add("4+");
                arrayAdapter.addAll(list_1);

                builder.setNegativeButton(dil.tm_yza, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        text_otagy.setText(list_1.get(which));
                        fragment1_realtor.name=list_1.get(which);
                    }
                });
                builder.show();
            }
        });
        linearLayout_bahasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog d = new Dialog(filterrealtor.this);
                d.requestWindowFeature(Window.FEATURE_NO_TITLE);
                d.setContentView(R.layout.dialog_bahasy_car);
                TextView textView = (TextView) d.findViewById(R.id.dialog_bahasy_title);
                textView.setText(dil.tm_car_bahasy);
                final EditText editText_basy = (EditText) d.findViewById(R.id.edittext_baskybaha_car_filter);
                final EditText editText_sony = (EditText) d.findViewById(R.id.edittext_sonkybaha_car_filter);

                editText_basy.setHint(dil.tm_in_arzan);
                editText_sony.setHint(dil.tm_in_gymmat);

             //   d.getWindow().setLayout(1000, 800);
                d.show();
                Button bt_sayla = (Button) d.findViewById(R.id.button_filter_bahasy_dialog_sayla);
                Button bt_yza = (Button) d.findViewById(R.id.button_filter_bahasy_dialog_yza);

                bt_sayla.setText(dil.tm_sayla);
                bt_yza.setText(dil.tm_yza);


                bt_sayla.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String s = editText_basy.getText() + "-" + editText_sony.getText();
                        fragment1_realtor. price1 = editText_basy.getText().toString();
                        fragment1_realtor.   price2 = editText_sony.getText().toString();
                        if (s.length() > 1) {
                            text_bahasy.setText(editText_basy.getText() + "-" + editText_sony.getText());
                        } else {
                            text_bahasy.setText(dil.tm_saylanylmadyk);
                        }
                        d.dismiss();
                    }
                });
                bt_yza.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        text_bahasy.setText(dil.tm_saylanylmadyk);
                        d.dismiss();
                    }
                });

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment1_realtor.editText.setText(fragment1_realtor.category+","+fragment1_realtor.location);
                 fragment1_realtor.ret.setVisibility(View.VISIBLE);
                 fragment1_realtor.refr();
                finish();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == android.R.id.home) {
           finish();

        }
        return true;
    }
}
