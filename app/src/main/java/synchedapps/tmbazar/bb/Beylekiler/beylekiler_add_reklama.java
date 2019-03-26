package synchedapps.tmbazar.bb.Beylekiler;

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
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.bb.add_adds_cars;
import synchedapps.tmbazar.dil;
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

import in.myinnos.awesomeimagepicker.activities.AlbumSelectActivity;
import in.myinnos.awesomeimagepicker.helpers.ConstantsCustomGallery;
import in.myinnos.awesomeimagepicker.models.Image;

public class beylekiler_add_reklama extends AppCompatActivity {
    Toolbar toolbar;
    Db db;
    LinearLayout linearLayout_kategoriya;
    String category = "", name = "", price = "", location = "", description = "", image = "", image1 = "", image2 = "",
            image3 = "", number = "", id = "", credit = "0", obmen = "0", kod = "", location1 = "", category1 = "";
    EditText item_description, item_name, item_price, item_number, item_code;
    CheckBox item_credit, item_obmen;
    LinearLayout image_add;
    Button add_Add;
    public static String[] filepath = new String[5];
    public static Handler setImageView;
    ArrayList<Bitmap> img = new ArrayList<>();
    ImageView img1, img2, img3;
    ImageView del1, del2, del3;
    private ImageLoader imageLoader;
    List<String> list;
    List<String> list1;
    dil di = new dil();
    boolean b=false;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
 ArrayList<model_surat_gos> listView_surat;
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
    Handler  set_image;
 ProgressDialog loading;
    int i = 1;
    LinearLayout linearLayout_yeri;
    adapter_for_recycle_for_caard_surat_gos a;
    LinearLayout l3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beylekiler_add_reklama);
        db = new Db(MainActivity.ctx);
        di.set_text();
        linearLayout_kategoriya = (LinearLayout) findViewById(R.id.layout_beylekiler_add_reklama_kategoriya);
        toolbar = (Toolbar) findViewById(R.id.toolbar_beylekiler_add_reklama_sahypa);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(dil.tm_bildiris_gos);
        TextView textView_1 = (TextView) findViewById(R.id.text_kategoriya_title_add_reklama_beylekiler);
        textView_1.setText(dil.tm_kategoriya);
        TextView textView_2 = (TextView) findViewById(R.id.text_ady_add_reklama_beylekiler);
        textView_2.setText(dil.tm_ady);
        TextView textView_3 = (TextView) findViewById(R.id.text_bahasy_add_reklama_beylekiler);
        textView_3.setText(dil.tm_car_bahasy);
        TextView textView_4 = (TextView) findViewById(R.id.text_dusundirisi_add_reklama_beylekiler);
        textView_4.setText(dil.tm_dusundirisi);
        TextView textView_5 = (TextView) findViewById(R.id.text_yeri_title_add_reklama_beylekiler);
        textView_5.setText(dil.tm_yerlesyan_yeri);
        TextView textView_6 = (TextView) findViewById(R.id.surat_gos_add_reklama_beylekiler);
        textView_6.setText(dil.tm_surat_gos);
        TextView textView_7 = (TextView) findViewById(R.id.telefon_add_reklama_beylekiler);
        textView_7.setText(dil.tm_telefon_belgi);
        EditText editText_1=(EditText)findViewById(R.id.text_kategpriya_edit_add_reklama_beylekiler);
        editText_1.setHint(dil.tm_saylanylmadyk);
        EditText editText_2=(EditText)findViewById(R.id.edittext_ady_add_reklama_beylekiler);
        editText_2.setHint(dil.tm_ady);
        editText_2.setMaxLines(1);
        EditText editText_3=(EditText)findViewById(R.id.edittext_bahasy_add_reklama_beylekiler);
        editText_3.setHint(dil.tm_bahasy_manatda);
        editText_3.setMaxLines(1);
        EditText editText_4=(EditText)findViewById(R.id.edittext_dusundirisi_add_reklama_beylekiler);
        editText_4.setHint(dil.tm_ginisleyib_maglumat);
        EditText editText_5=(EditText)findViewById(R.id.text_yeri_edit_add_reklama_beylekiler);
        editText_5.setHint(dil.tm_saylanylmadyk);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_beylekiler_surat_gos);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        listView_surat=new  ArrayList<model_surat_gos>();

        a=new adapter_for_recycle_for_caard_surat_gos(listView_surat,this);

        recyclerView.setAdapter(a);
        set_image=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                a.setData(listView_surat);
            }
        };
        Gui();
        onclicks();
        handSetImageview();
        initImageLoader();

    }

    void onclicks() {
        linearLayout_kategoriya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(beylekiler_add_reklama.this);
                builder.setTitle("Kategoriýa");
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(beylekiler_add_reklama.this, android.R.layout.simple_dropdown_item_1line);
                list = new ArrayList<>();
                list.add("Hyzmatlar");
                list.add("Öý Tehnikasy");
                list.add("Telefonlar");
                list.add("Kompýuter we Enjamlary");
                list.add("Iş we Işgär");
                list.add("Öý Goşlary");
                list.add("Egin-Eşikler we Aksesuarlar");
                list.add("Çagalar Üçin Ähli Zatlar");
                list.add("Awtoşaylar");
                list.add("Sport we Dynç Alyş");
                list.add("Haryt Gözleýän-Maňa Gerek");
                list.add("Ýitirilen we Tapylan");
                if (db.get_dil().equals("tm")) arrayAdapter.addAll(list);
                list1 = new ArrayList<String>();
                list1.add("УСЛУГИ");
                list1.add("БЫТОВАЯ ТЕХНИКА");
                list1.add("МОБИЛЬНЫЕ ТЕЛЕФОНЫ И КОМПЛЕКТУЮЩИЕ");
                list1.add("КОМПЬЮТЕРЫ И КОМПЛЕКТУЮЩИЕ");
                list1.add("ИЩУ ИЛИ ПРЕДЛОГАЮ РАБОТУ");
                list1.add("ВЕЩИ ДЛЯ ДОМА");
                list1.add("ОДЕЖДЫ И АКСЕССУАРЫ");
                list1.add("МАЛЕНЬКИЕ ДЕТИ И ИХ РОДИТЕЛИ");
                list1.add("АВТОЗАПЧАСТИ");
                list1.add("СПОРТ И ОТДЫХ");
                list1.add("ИЩУ ИЛИ НУЖНО");
                list1.add("УТЕРЯНО И НАЙДЕНО");
                if (db.get_dil().equals("ru")) arrayAdapter.addAll(list1);
                builder.setNegativeButton("Yza", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText textView_kategoriya = (EditText) findViewById(R.id.text_kategpriya_edit_add_reklama_beylekiler);
                        textView_kategoriya.setText(arrayAdapter.getItem(which));
                        Log.d("aarrayadapter", "" + arrayAdapter.getItem(which) + arrayAdapter.getCount());
                        category = list.get(which);
                        category1 = list1.get(which);
                    }
                });
                builder.show();
            }
        });

        linearLayout_yeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(beylekiler_add_reklama.this);
                builder.setTitle(dil.tm_yerlesyan_yeri);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(beylekiler_add_reklama.this, android.R.layout.simple_dropdown_item_1line);
                list = new ArrayList<>();
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.yeri_car)));
                if (db.get_dil().equals("tm")) arrayAdapter.addAll(list);
                builder.setNegativeButton(dil.tm_yza, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                list1 = new ArrayList<String>();
                list1.addAll(Arrays.asList(getResources().getStringArray(R.array.yeri_car_ru)));
                if (db.get_dil().equals("ru")) arrayAdapter.addAll(list1);
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView textView_yeri = (TextView) findViewById(R.id.text_yeri_edit_add_reklama_beylekiler);
                        textView_yeri.setText(arrayAdapter.getItem(which));
                        location = list.get(which);
                        location1 = list1.get(which);
                    }
                });
                builder.show();
            }
        });
        item_description.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.edittext_dusundirisi_add_reklama_beylekiler) {
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

        image_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(beylekiler_add_reklama.this, AlbumSelectActivity.class);
                intent.putExtra(ConstantsCustomGallery.INTENT_EXTRA_LIMIT, 3); // set limit for image selection
                startActivityForResult(intent, ConstantsCustomGallery.REQUEST_CODE);
            }
        });
        add_Add.setText(dil.tm_sms_ugrat);
        add_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("i", "" + i);
                price=item_price.getText().toString();
                name=item_name.getText().toString();
                if(!category1.equals("")&& !location1.equals("")&&!name.equals("")&&!price.equals("")){
                    try {
                        number = item_number.getText().toString();
                    } catch (NumberFormatException io) {
                        io.printStackTrace();
                    }
                    if (number.length()==8) {
                        item_number.setEnabled(false);
                        verifier();
                    } else {
                        Toast.makeText(getApplication(), dil.tm_nomer_giriz, Toast.LENGTH_LONG).show();
                    }

                }else Toast.makeText(getApplicationContext(),dil.hemme_maglumaty_giriz,Toast.LENGTH_LONG).show();
            }
        });

    }

    void handSetImageview() {
        setImageView = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
//                    loading = ProgressDialog.show(beylekiler_add_reklama.this, "Yüklenyär...", "Garaşyň...");
                    //  int ksize = filepath.length;
                    //  Log.d("ksize", "" + ksize);
                    final Thread comp = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int t = 0;
                            for (model_surat_gos m:listView_surat) {
                                Bitmap bitmap = null;
                                try {
                                    bitmap = MediaStore.Images.Media.getBitmap(getBaseContext().getContentResolver(),m.getUri());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Log.d("getCountBitmap",""+bitmap.getByteCount());
                                img.add(bitmap);
                             //   if (t == 0) {
                                  //  compress(img, "main");
                                  //  t = 1;
                               // }
                            }
                            compress(img, "detail");
                            Thread.currentThread().interrupt();
                        }
                    });
                    comp.start();


                }}
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == android.R.id.home) {
           finish();

        }
        return true;
    }

    void Gui() {
        image_add = (LinearLayout) findViewById(R.id.beylekiler_image_add);
        item_name = (EditText) findViewById(R.id.edittext_ady_add_reklama_beylekiler);
        item_price = (EditText) findViewById(R.id.edittext_bahasy_add_reklama_beylekiler);
        item_number = (EditText) findViewById(R.id.edittext_nomeri_add_reklama_beylekiler);
        item_description = (EditText) findViewById(R.id.edittext_dusundirisi_add_reklama_beylekiler);
        linearLayout_yeri = (LinearLayout) findViewById(R.id.layout_beylekiler_add_reklama_yeri);
     //   del1 = (ImageView) findViewById(R.id.beylekiler_delete1_image_reklama_car);
        //del2 = (ImageView) findViewById(R.id.beylekiler_delete2_image_reklama_car);
       // del3 = (ImageView) findViewById(R.id.beylekiler_delete3_image_reklama_car);
       // img1 = (ImageView) findViewById(R.id.beylekiler_image1_for_car_reklama);
       // img2 = (ImageView) findViewById(R.id.beylekiler_image2_for_car_reklama);
       // img3 = (ImageView) findViewById(R.id.beylekiler_image3_for_car_reklama);
        add_Add = (Button) findViewById(R.id.beylekiler_add_add);
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
                    String date = day.format(d);
                    String time = ff.format(d);
                    String key = Integer.toHexString(Integer.parseInt(number) + Integer.parseInt(ss.format(d))) + ss.format(d);
                    Log.d("key", key);
                    description = item_description.getText().toString();
                    price = item_price.getText().toString();
                    name = item_name.getText().toString();
                    id = "";
                    SimpleDateFormat kk=new SimpleDateFormat("kk");
                    String verify_time=kk.format(d);
                    int l=(Integer.parseInt(verify_time)+2);
                    verify_time=""+l;
                    key = key.substring(0, 4);
                    HashMap<String, String> object = new HashMap<>();
                    object.put("key", key);
                    object.put("time", time);
                    object.put("date", date);
                    object.put("category", category);
                    object.put("name", name);
                    object.put("credit", credit);
                    object.put("price", price);
                    object.put("obmen", obmen);
                    object.put("category1", category1);
                    object.put("location1", location1);
                    object.put("description", description);
                    object.put("location", location);
                    object.put("image", image);
                    object.put("image1", image1);
                    object.put("image2", image2);
                    object.put("image3", image3);
                    object.put("number", "+993"+number);
                    object.put("table", "beylekiler");
                    object.put("verify_time",verify_time);
                    ff = new SimpleDateFormat("dd:hh:mm:ss");
                    key=ff.format(d);
                    db.insert_myaddBeylekiler(id, category, name, price, description, location, number, image, image1, image2, image3, key, credit, obmen, time, date,"0", category1, location1);
                    final String json = new Gson().toJson(object);
                    Log.d("infromation", json);
                    image=""; image1=""; image2=""; image3="";
                    //   URL url=new URL("http://ynamly.biz/reklama3/adds/insert_add.php");
                    URL url = new URL("http://"+ network.address+"/Reklama/adds/insert_add_realtor.php");
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

                    db.update_id("myAddsbeylekiler", key, id);
                    if (!id.equals("")) con.disconnect();

                    if (!id.equals("")) Thread.currentThread().interrupt();


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start(); //  loading.dismiss();
    }

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
                    if (t==0) {
                        bm = Scaler(imageBytes, 100, 100);
                        razmer = 50;
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
                    if (t== 1) image2 = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                    if (t == 2) image3 = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                    //  p++;
                }
                Register();
                Thread.currentThread().interrupt();


            }
        });
        if(compres.isAlive())compres.interrupt(); compres.start();
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



    protected void verifier() {
        setImageView.sendEmptyMessage(1);
        Toast.makeText(getBaseContext(), dil.tm_opublikowan, Toast.LENGTH_LONG).show();
        Intent i = new Intent(beylekiler_add_reklama.this, bildiris_gos_navidation.class);
        startActivity(i);
        finish();
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
            l3.setVisibility(View.VISIBLE);
            // Permission already granted. Enable the message button.

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
                    l3.setVisibility(View.VISIBLE);
                } else {
                    // Permission denied.
                    Log.d("permission", "failure");
                    Toast.makeText(beylekiler_add_reklama.this, "Failare",
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
        Log.d("geldiResult","geldi");
        if (requestCode == ConstantsCustomGallery.REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            //The array list has the image paths of thLe selected images
            ArrayList<Image> images = data.getParcelableArrayListExtra(ConstantsCustomGallery.INTENT_EXTRA_IMAGES);
            model_surat_gos m;
            for (int i = 0; i < images.size(); i++) {
                Uri uri = Uri.fromFile(new File(images.get(i).path));
                Log.d("uriset",uri.toString());
                m=new model_surat_gos();
                m.setUri(uri);
                //    m.setImageid2(R.drawable.delete);
                if(listView_surat.size()<3)listView_surat.add(m);
            }
            set_image.sendEmptyMessage(1);
            //setImageView.sendEmptyMessage(1);
            //setImageView.sendEmptyMessage(1);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("otp")) {
                final String message = intent.getStringExtra("message");
                final  String sender=intent.getStringExtra("Sender");
                Log.d("SenderGeldi",sender);
                Log.d("numbername","+993"+number);
                if(sender.equals("+993"+number)){b=true;verifier();}
            }
        }
    };

    private  boolean checkAndRequestPermissions() {
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
