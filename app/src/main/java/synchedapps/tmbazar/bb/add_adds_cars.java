package synchedapps.tmbazar.bb;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.Models.model_surat_gos;
import synchedapps.tmbazar.Network.network;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.adapters.adapter_for_recycle_for_caard_surat_gos;
import synchedapps.tmbazar.bb.Beylekiler.Bildiris_gos_Navigation.bildiris_gos_navidation;
import synchedapps.tmbazar.bb.Beylekiler.Bildiris_gos_Navigation.fragment_bildiris_gosmak1;
import synchedapps.tmbazar.dil;
import synchedapps.tmbazar.filter_model.add_car_model;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import in.myinnos.awesomeimagepicker.activities.AlbumSelectActivity;
import in.myinnos.awesomeimagepicker.helpers.ConstantsCustomGallery;
import in.myinnos.awesomeimagepicker.models.Image;

public class add_adds_cars extends AppCompatActivity {
    Db db = new Db(MainActivity.ctx);
    public static TextView textView_marka;
    public static TextView textView_model;
    TextView textView_kuzow;
    TextView textView_yyly;
    TextView textView_renki;
    TextView textView_mator;
    TextView textView_karopka;
    TextView textView_yeri;
    LinearLayout linearLayout_marka;
    LinearLayout linearLayout_model;
    LinearLayout linearLayout_yyly;
    LinearLayout linearLayout_kuzow;
    LinearLayout linearLayout_renki;
    LinearLayout linearLayout_mator;
    LinearLayout linearLayout_karopk;
    LinearLayout linearLayout_yeri;
    LinearLayout linearLayout_image;
    Toolbar toolbar;
    public static String[] filepath = new String[5];
    Bitmap bitmap, bitmap1, bitmap2;
    public static int index;
    int count = 0;
    public static int RESULT_LOAD_IMAGE = 1;
    static int counter_image = 0;
    EditText price, probeg, descriptioin, number, code;
    Button add_Add;
    int PICK_IMAGES = 1;
    ImageView img1, img2, img3;
    CheckBox credit, obmen1;
    ArrayList<Bitmap> img = new ArrayList<>();
    ImageView del1, del2, del3;
    ProgressDialog loading;
    boolean b = false;
    LinearLayout l2;
    ArrayList<model_surat_gos> listView_surat;
    public static String category = "0", model = "", kuzow = "0", probeg1 = "0", year = "0", color = "0", mator = "", karopka = "0", vip = "0", watched = "0", satyldy = "0",
            price1 = "0", location = "", kredit = "0", obmen = "0", description = "", image1 = "", image2 = "", image3 = "", image = "", number1 = "", kod = "", id = "";
    int i = 1;
    private ImageLoader imageLoader;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    private void initImageLoader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .bitmapConfig(Bitmap.Config.RGB_565).build();
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(
                this).defaultDisplayImageOptions(defaultOptions).memoryCache(
                new WeakMemoryCache());

        ImageLoaderConfiguration config = builder.build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
    }

    adapter_for_recycle_for_caard_surat_gos a;

    public static Handler setImageView, set_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_adds);
        dil di = new dil();
        di.set_text();
        toolbar = (Toolbar) findViewById(R.id.car_reklama_add_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(dil.tm_bildiris_gos);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        TextView textView_marka_title = (TextView) findViewById(R.id.car_add_reklama_marka_title);
        textView_marka_title.setText(dil.tm_car_marka);
        TextView textView_model_title = (TextView) findViewById(R.id.car_add_reklama_model_title);
        textView_model_title.setText(dil.tm_model);
        TextView textView_kuzow_title = (TextView) findViewById(R.id.car_add_reklama_kuzow_title);
        textView_kuzow_title.setText(dil.tm_kuzow);
        TextView textView_probeg_title = (TextView) findViewById(R.id.car_add_reklama_probeg_title);
        textView_probeg_title.setText(dil.tm_probeg);
        TextView textView_yyly_title = (TextView) findViewById(R.id.car_add_reklama_yyly_title);
        textView_yyly_title.setText(dil.tm_car_yyly);
        TextView textView_renki_title = (TextView) findViewById(R.id.car_add_reklama_renki_title);
        textView_renki_title.setText(dil.tm_car_renki);
        TextView textView_mator_title = (TextView) findViewById(R.id.car_add_reklama_mator_title);
        textView_mator_title.setText(dil.tm_mator);
        TextView textView_karopka_title = (TextView) findViewById(R.id.car_add_reklama_karopka_title);
        textView_karopka_title.setText(dil.tm_karopka);
        TextView textView_bahasy_title = (TextView) findViewById(R.id.car_add_reklama_bahasy_title);
        textView_bahasy_title.setText(dil.tm_car_bahasy);
        TextView textView_yeri_title = (TextView) findViewById(R.id.car_add_reklama_yeri_title);
        textView_yeri_title.setText(dil.tm_yerlesyan_yeri);
        TextView textView_gosmaca_title = (TextView) findViewById(R.id.car_add_reklama_gosmaca_title);
        textView_gosmaca_title.setText(dil.tm_gosmaca);
        TextView textView_surat_title = (TextView) findViewById(R.id.car_add_reklama_surat_gos_title);
        textView_surat_title.setText(dil.tm_surat_gos);
        TextView textView_telefon_title = (TextView) findViewById(R.id.car_add_reklama_telefon_title);
        textView_telefon_title.setText(dil.tm_telefon_belgi);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_car_surat_gos);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        listView_surat = new ArrayList<model_surat_gos>();
        a = new adapter_for_recycle_for_caard_surat_gos(listView_surat, this);
        descriptioin=(EditText)findViewById(R.id.description1);

        descriptioin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.description1) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_UP:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                }
                return false;
            }
        });



        recyclerView.setAdapter(a);
        Gui();
        initImageLoader();
        set_marka();
        set_model();
        set_kuzow();
        set_year();
        set_color();
        set_motor();
        set_karopka();
        set_location();
        set_onClick();
        handSetImageview();

        set_image = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                a.setData(listView_surat);
            }
        };
    }

    void handSetImageview() {
        setImageView = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                //    loading = ProgressDialog.show(add_adds_cars.this, "Yüklenyär...", "Garaşyň...");
                    final Thread comp = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int t = 0;
                            for (model_surat_gos m : listView_surat) {
                                Bitmap bitmap = null;
                                try {
                                    bitmap = MediaStore.Images.Media.getBitmap(getBaseContext().getContentResolver(), m.getUri());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Log.d("getCountBitmap", "" + bitmap.getByteCount());
                                img.add(bitmap);
                            }
                            compress(img, "detail");
                            Thread.currentThread().interrupt();
                        }
                    });
                    comp.start();


                }
            }
        };
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), car_tab.class);
        intent.putExtra("from", "0");
        startActivity(intent);
        finish();
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

    void Gui() {
        //  del1 = (ImageView) findViewById(R.id.delete1_image_reklama_car);
        //   del2 = (ImageView) findViewById(R.id.delete2_image_reklama_car);
        //  del3 = (ImageView) findViewById(R.id.delete3_image_reklama_car);
        //  img1 = (ImageView) findViewById(R.id.image1_for_car_reklama);
        //  img2 = (ImageView) findViewById(R.id.image2_for_car_reklama);
        //  img3 = (ImageView) findViewById(R.id.image3_for_car_reklama);
        add_Add = (Button) findViewById(R.id.add_add);
        probeg = (EditText) findViewById(R.id.car_add_reklama_probeg_content);
        price = (EditText) findViewById(R.id.car_add_reklama_bahasy_content);
        obmen1 = (CheckBox) findViewById(R.id.obmen);
        credit = (CheckBox) findViewById(R.id.credit);
        descriptioin = (EditText) findViewById(R.id.description1);
        number = (EditText) findViewById(R.id.number);
        textView_marka = (TextView) findViewById(R.id.car_add_reklama_marka_content);
        textView_model = (TextView) findViewById(R.id.car_add_reklama_model_content);
        textView_kuzow = (TextView) findViewById(R.id.car_add_reklam_kuzow_cont);
        textView_yyly = (TextView) findViewById(R.id.car_add_reklama_yyly_content);
        textView_renki = (TextView) findViewById(R.id.car_add_reklama_renki_content);
        textView_mator = (TextView) findViewById(R.id.car_add_reklama_mator_content);
        textView_karopka = (TextView) findViewById(R.id.car_add_reklama_karopka_content);
        textView_yeri = (TextView) findViewById(R.id.car_add_reklama_yeri_content);
        linearLayout_marka = (LinearLayout) findViewById(R.id.car_marka_layout);
        linearLayout_model = (LinearLayout) findViewById(R.id.layour_car_model);
        linearLayout_yyly = (LinearLayout) findViewById(R.id.layout_yyly_car);
        linearLayout_kuzow = (LinearLayout) findViewById(R.id.layout_for_kuzow_car);
        linearLayout_renki = (LinearLayout) findViewById(R.id.layout_renki_car);
        linearLayout_mator = (LinearLayout) findViewById(R.id.layout_matory_car);
        linearLayout_karopk = (LinearLayout) findViewById(R.id.layout_karopka_car);
        linearLayout_yeri = (LinearLayout) findViewById(R.id.layout_yeri_car);
        linearLayout_image = (LinearLayout) findViewById(R.id.image_add_for_car);

        textView_marka.setText(dil.tm_saylanylmadyk);
        textView_model.setText(dil.tm_saylanylmadyk);
        textView_kuzow.setText(dil.tm_saylanylmadyk);
        probeg.setHint(dil.tm_probegi_giriz);
        textView_yyly.setText(dil.tm_saylanylmadyk);
        textView_renki.setText(dil.tm_saylanylmadyk);
        textView_mator.setText(dil.tm_saylanylmadyk);
        textView_karopka.setText(dil.tm_saylanylmadyk);
        price.setHint(dil.tm_bahasy_manatda);
        textView_yeri.setText(dil.tm_saylanylmadyk);
        obmen1.setText(dil.tm_obmen_bolya);
        credit.setText(dil.tm_kredit_bolya);
        add_Add.setText(dil.tm_sms_ugrat);


    }

    public void send_sms(String number) {
        Date d = new Date();
        SimpleDateFormat ff = new SimpleDateFormat("ss");
        ff.format(d);
        Random rand = new Random();

        int n = rand.nextInt(9999) + 1;
        kod = Integer.toHexString(n);
        if (kod.length() > 4) kod = kod.substring(0, 5);
        Log.d("code", kod);
        if (checkAndRequestPermissions()) {
        }
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("+993" + number, null, kod, null, null);
        Toast.makeText(getApplicationContext(), dil.tm_nomeri_kod_ugradyldy,
                Toast.LENGTH_LONG).show();


    }

    protected void verifier() {
        setImageView.sendEmptyMessage(1);
        Toast.makeText(getBaseContext(), dil.tm_opublikowan, Toast.LENGTH_LONG).show();
        i = 3;
        finish();
    }


    public void Register() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Date d = new Date();
                    SimpleDateFormat ff = new SimpleDateFormat("hh:mm");
                    SimpleDateFormat day = new SimpleDateFormat("dd.MM.yyyy");
                    SimpleDateFormat ss = new SimpleDateFormat("ss");
                    SimpleDateFormat kk=new SimpleDateFormat("kk");
                    String verify_time=kk.format(d);
                    int l=(Integer.parseInt(verify_time)+2);
                    verify_time=""+l;
                    String date = day.format(d);
                    String time = ff.format(d);
                    String key = Integer.toHexString(Integer.parseInt(number1) + Integer.parseInt(ss.format(d))) + ss.format(d);
                    Log.d("key", key);
                    description = descriptioin.getText().toString();
                    price1 = price.getText().toString();
                    probeg1 = probeg.getText().toString();
                    id = "";
                    HashMap<String, String> object = new HashMap<>();
                    object.put("karopka", karopka);
                    object.put("motor", mator);
                    object.put("color", color);
                    object.put("key", key);
                    object.put("time", time);
                    object.put("date", date);
                    object.put("kuzow", kuzow);
                    object.put("table", "cars");
                    object.put("category", category);
                    object.put("model", model);
                    object.put("year", year);
                    object.put("probeg", probeg1);
                    object.put("price", price1);
                    object.put("credit", kredit);
                    object.put("obmen", obmen);
                    object.put("description", description);
                    object.put("location", location);
                    object.put("image", image);
                    object.put("image1", image1);
                    object.put("image2", image2);
                    object.put("image3", image3);
                    object.put("number", "+993" + number1);
                    object.put("verify_time",verify_time);
                    ff = new SimpleDateFormat("dd:hh:mm:ss");
                    key=ff.format(d);
                    db.insert_myadds("myadds", id, category, model, price1, description, location, vip, time, number1, watched, date, year,
                            kredit, obmen, satyldy, probeg1, kuzow, color, karopka, mator, image, image1, image2, image3, key);
                    final String json = new Gson().toJson(object);
                    Log.d("kreditob",kredit+obmen);
                    image="";image1="";image2=""; image3="";
                    category = ""; model = ""; kuzow = ""; probeg1 = ""; year = ""; color = ""; mator = ""; karopka = ""; vip = ""; watched = ""; satyldy = "";
                            price1 = ""; location = ""; kredit = "0"; obmen = "0"; description = ""; image1 = ""; image2 = ""; image3 = ""; image = ""; number1 = ""; kod = ""; id = "";

                    Log.d("infromation", json);
                    URL url = new URL("http://"+ network.address+"/Reklama/adds/insert_add.php");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setDoOutput(true);
                    con.setRequestMethod("POST");
                    OutputStream outputStream = con.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("json", "UTF-8") + "=" + URLEncoder.encode(json, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream in;
                    in = con.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String s = "";
                    while ((s = br.readLine()) != null) {
                        id = id + s;
                    }
                    Log.d("namegelya", id);
                    br.close();
                    in.close();
                    if (!id.equals("")) con.disconnect();

                    db.update_id("myadds", key, id);
                    fragment_bildiris_gosmak1.get_local();
                    if (!id.equals("")) {
                        Thread.currentThread().interrupt();
                        Intent i = new Intent(add_adds_cars.this, bildiris_gos_navidation.class);
                        startActivity(i);
                        finish();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    void set_marka() {
        linearLayout_marka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(add_adds_cars.this, add_car_model.class);
                i.putExtra("name", "marka");
                startActivity(i);

            }
        });


    }

    void set_model() {
        linearLayout_model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String test = textView_marka.getText().toString();
                if (test.equalsIgnoreCase(dil.tm_saylanylmadyk))
                    Toast.makeText(getApplicationContext(), dil.tm_marka_saylanylmady, Toast.LENGTH_SHORT).show();
                else {
                    Intent i = new Intent(add_adds_cars.this, add_car_model.class);
                    i.putExtra("name", "model");
                    startActivity(i);
                }


            }
        });


    }

    void set_kuzow() {
        linearLayout_kuzow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(add_adds_cars.this);
                builder.setTitle(dil.tm_kuzow);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(add_adds_cars.this, android.R.layout.simple_dropdown_item_1line);
                List<String> list = new ArrayList<>();
                if (db.get_dil().equals("tm"))
                    list.addAll(Arrays.asList(getResources().getStringArray(R.array.kyzow_turkmençe)));
                else list.addAll(Arrays.asList(getResources().getStringArray(R.array.kyzow_ru)));
                arrayAdapter.addAll(list);
                builder.setNegativeButton(dil.tm_yza, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView_kuzow.setText(arrayAdapter.getItem(which));
                        kuzow = String.valueOf(which);

                    }
                });
                builder.show();
            }
        });
    }

    void set_year() {

        linearLayout_yyly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(add_adds_cars.this);
                builder.setTitle(dil.tm_car_yyly);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(add_adds_cars.this, android.R.layout.simple_dropdown_item_1line);
                List<String> list = new ArrayList<>();
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.car_yyly)));
                arrayAdapter.addAll(list);
                builder.setNegativeButton(dil.tm_yza, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView_yyly.setText(arrayAdapter.getItem(which));
                        year = arrayAdapter.getItem(which);
                    }
                });
                builder.show();
            }
        });
    }

    void set_color() {

        linearLayout_renki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(add_adds_cars.this);
                builder.setTitle(dil.tm_car_renki);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(add_adds_cars.this, android.R.layout.simple_dropdown_item_1line);
                List<String> list = new ArrayList<>();
                if (db.get_dil().equals("tm"))
                    list.addAll(Arrays.asList(getResources().getStringArray(R.array.renki_car)));
                else
                    list.addAll(Arrays.asList(getResources().getStringArray(R.array.renki_car_ru)));
                arrayAdapter.addAll(list);
                builder.setNegativeButton(dil.tm_yza, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView_renki.setText(arrayAdapter.getItem(which));
                        // color = arrayAdapter.getItem(which);
                        color = "" + which;
                    }
                });
                builder.show();
            }
        });
    }

    void set_motor() {
        linearLayout_mator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(add_adds_cars.this);
                builder.setTitle(dil.tm_mator);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(add_adds_cars.this, android.R.layout.simple_dropdown_item_1line);
                List<String> list = new ArrayList<>();
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.mator_car)));

                arrayAdapter.addAll(list);
                builder.setNegativeButton(dil.tm_yza, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView_mator.setText(arrayAdapter.getItem(which));
                        mator = arrayAdapter.getItem(which);
                    }
                });
                builder.show();
            }
        });
    }

    void set_karopka() {

        linearLayout_karopk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(add_adds_cars.this);
                builder.setTitle(dil.tm_karopka);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(add_adds_cars.this, android.R.layout.simple_dropdown_item_1line);
                List<String> list = new ArrayList<>();
                if (db.get_dil().equals("tm"))
                    list.addAll(Arrays.asList(getResources().getStringArray(R.array.karopka_car)));
                else
                    list.addAll(Arrays.asList(getResources().getStringArray(R.array.karopka_car_ru)));
                arrayAdapter.addAll(list);
                builder.setNegativeButton(dil.tm_yza, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView_karopka.setText(arrayAdapter.getItem(which));
                        //karopka = arrayAdapter.getItem(which);
                        karopka = "" + which;
                    }
                });
                builder.show();
            }
        });

    }

    void set_location() {
        linearLayout_yeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(add_adds_cars.this);
                builder.setTitle(dil.tm_yerlesyan_yeri);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(add_adds_cars.this, android.R.layout.simple_dropdown_item_1line);
                List<String> list = new ArrayList<>();
                if (db.get_dil().equals("tm"))
                    list.addAll(Arrays.asList(getResources().getStringArray(R.array.yeri_car)));
                else list.addAll(Arrays.asList(getResources().getStringArray(R.array.yeri_car_ru)));
                arrayAdapter.addAll(list);
                builder.setNegativeButton(dil.tm_yza, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView_yeri.setText(arrayAdapter.getItem(which));
                        // location = arrayAdapter.getItem(which);
                        location = "" + which;
                    }
                });
                builder.show();
            }
        });


    }

    void set_onClick() {
        add_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                price1 = price.getText().toString();
                if (!category.equals("") && !price1.equals("") && !location.equals("")) {
                    number1 = number.getText().toString();

                    if  (number1.length() == 8) {
                        verifier();
                        // checkForSmsPermission();
                        //  add_Add.setText(dil.tm_tassykla);
                    } else {
                        Toast.makeText(getApplication(), dil.tm_nomer_giriz, Toast.LENGTH_LONG).show();
                    }
                } else {
                        Toast.makeText(add_adds_cars.this, dil.hemme_maglumaty_giriz, Toast.LENGTH_LONG).show();

                }
            }
        });

        credit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                kredit = "1";
            }
        });
        obmen1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                obmen = "1";
            }
        });
        linearLayout_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startImageSelection();
                Intent intent = new Intent(add_adds_cars.this, AlbumSelectActivity.class);
                intent.putExtra(ConstantsCustomGallery.INTENT_EXTRA_LIMIT, 3); // set limit for image selection
                startActivityForResult(intent, ConstantsCustomGallery.REQUEST_CODE);

            }
        });
        //image del

    }


    private void startImageSelection() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGES);


    }

    //taze
    Thread compres;

    public void compress(final ArrayList<Bitmap> bmp, final String type) {
        compres = new Thread(new Runnable() {
            @Override
            public void run() {
                int p = 0;
                Log.d("type", type + "  size:" + bmp.size());
                for (int t = 0; t < bmp.size(); t++) {
                    Log.d("haysy", type);
                    Bitmap bm = bmp.get(t);
                    int razmer = 0;
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int k = 100;
                    bm.compress(Bitmap.CompressFormat.JPEG, k, baos);
                    byte[] imageBytes = baos.toByteArray();
                    if (t == 0) {

                        bm = Scaler(imageBytes, 100, 100);
                        razmer = 30;
                        Log.d("girdi", "main");
                        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        while (baos.toByteArray().length / 1024 > razmer) {
                            k -= 10;
                            baos.reset();
                            bm.compress(Bitmap.CompressFormat.JPEG, k, baos);
                            Log.d("beforekb", "" + baos.toByteArray().length);
                        }
                        byte[] img = baos.toByteArray();
                        image = Base64.encodeToString(img, Base64.DEFAULT);

                    }
                    bm = bmp.get(t);
                    bm = Scaler(imageBytes, 320, 240);
                    razmer = 100;

                    baos.reset();
                    bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    while (baos.toByteArray().length / 1024 > razmer) {
                        k -= 10;
                        baos.reset();
                        bm.compress(Bitmap.CompressFormat.JPEG, k, baos);
                        Log.d("beforekb", "" + baos.toByteArray().length);
                    }

                    imageBytes = baos.toByteArray();
                    if (t == 0) image1 = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                    if (t == 1) image2 = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                    if (t == 2) image3 = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                    //  p++;
                }
                Register();
                Thread.currentThread().interrupt();


            }
        });
        if (compres.isAlive()) compres.interrupt();
        compres.start();
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {

                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static Bitmap Scaler(byte[] arr, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Log.d("array", " " + arr.length);
        BitmapFactory.decodeByteArray(arr, 0, arr.length, options);

        Log.d("size", "" + options.inScaled);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        Log.d("insample size", "" + options.inSampleSize);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(arr, 0, arr.length, options);


    }


    private void checkForSmsPermission() {
        Log.d("Checkforpere", "dsdsad");
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED) {
            Log.d("tag", "not granted");
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        } else {
            // Permission already granted. Enable the message button.
            l2.setVisibility(View.VISIBLE);
            send_sms(number1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (permissions[0].equalsIgnoreCase(Manifest.permission.SEND_SMS)
                        && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted.
                    l2.setVisibility(View.VISIBLE);
                    send_sms(number1);

                } else {
                    // Permission denied.
                    Log.d("permission", "failure");
                    Toast.makeText(add_adds_cars.this, "Failare",
                            Toast.LENGTH_SHORT).show();
                    // Disable the message button.
                    //   disableSmsButton();
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("geldiResult", "geldi");
        if (requestCode == ConstantsCustomGallery.REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            //The array list has the image paths of thLe selected images
            ArrayList<Image> images = data.getParcelableArrayListExtra(ConstantsCustomGallery.INTENT_EXTRA_IMAGES);
            model_surat_gos m;
            for (int i = 0; i < images.size(); i++) {
                Uri uri = Uri.fromFile(new File(images.get(i).path));
                Log.d("uriset", uri.toString());
                m = new model_surat_gos();
                m.setUri(uri);
                //    m.setImageid2(R.drawable.delete);
                if (listView_surat.size() < 3) listView_surat.add(m);
            }
            set_image.sendEmptyMessage(1);
            //setImageView.sendEmptyMessage(1);
            //setImageView.sendEmptyMessage(1);
        }
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("otp")) {
                final String message = intent.getStringExtra("message");
                final String sender = intent.getStringExtra("Sender");
                Log.d("SenderGeldi", sender);
                Log.d("numbername", "+993" + number1);
                if (sender.equals("+993" + number1)) {
                    b = true;
                    verifier();
                }
            }
        }
    };

    private boolean checkAndRequestPermissions() {
        int permissionSendMessage = ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);
        int receiveSMS = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        int readSMS = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (receiveSMS != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.RECEIVE_MMS);
        }
        if (readSMS != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_SMS);
        }
        if (permissionSendMessage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.SEND_SMS);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    1);
            return false;
        }
        return true;
    }

    @Override
    public void onResume() {
        LocalBroadcastManager.getInstance(this).
                registerReceiver(receiver, new IntentFilter("otp"));
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }
}
