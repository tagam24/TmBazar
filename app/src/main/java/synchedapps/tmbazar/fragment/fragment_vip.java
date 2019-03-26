package synchedapps.tmbazar.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.Models.model_vip;
import synchedapps.tmbazar.Models.model_vip_check;
import synchedapps.tmbazar.Network.network;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.bb.vip_show_details;
import synchedapps.tmbazar.online_market.Constants;
import synchedapps.tmbazar.online_market.items.items_activity;
import synchedapps.tmbazar.online_market.post_tab.post_tabmarket;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class fragment_vip extends Fragment {
    View view;
    ViewFlipper v1,v2,v3,tv1,tv2,tv3;
    ArrayList<model_vip> data=new ArrayList<>();
    ArrayList<Bitmap> images=new ArrayList<>();
    Handler create;
    ImageView img;
    TextView label;
    String DATE="";
    boolean con= MainActivity.conn;
    public fragment_vip() {
    }
    AVLoadingIndicatorView avi1,avi2,avi3;
 Db db;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_vip, container, false);
        Log.d("Oncreate vip","  ");
        db=new Db(MainActivity.ctx);
        v1=(ViewFlipper)view.findViewById(R.id.fliper1_for_vip);
        v2=(ViewFlipper)view.findViewById(R.id.fliper2_for_vip);
        v3=(ViewFlipper)view.findViewById(R.id.fliper3_for_vip);
        tv1=(ViewFlipper)view.findViewById(R.id.textflip);
        tv2=(ViewFlipper)view.findViewById(R.id.textflip1);
        tv3=(ViewFlipper)view.findViewById(R.id.textflip3);
      final  ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
         avi1=(AVLoadingIndicatorView)view.findViewById(R.id.avi_vip1);
         avi2=(AVLoadingIndicatorView)view.findViewById(R.id.avi_vip2);
         avi3=(AVLoadingIndicatorView)view.findViewById(R.id.avi_vip3);
         ImageView r=new ImageView(MainActivity.ctx);
         tv1.addView(new TextView(MainActivity.ctx));
        tv2.addView(new TextView(MainActivity.ctx));
        tv3.addView(new TextView(MainActivity.ctx));
 create=new Handler(){
     @Override
     public void handleMessage(Message msg) {
         if(msg.what==1){
             Log.d("handler"," message 1");
             for(int i=0; i<data.size();i++){
                 img=new ImageView(MainActivity.ctx);
                 label=new TextView(MainActivity.ctx);
                 label.setText(data.get(i).getName());
                 //Log.d("setTagid",data.get(i).getId());
                 img.setTag(data.get(i).getId());
                 img.setClickable(true);
                 img.setScaleType(ImageView.ScaleType.FIT_XY);
                 final int k=i;
try{                 if(images.size()>=i&&images.size()!=0)img.setImageBitmap(images.get(i));} catch (IndexOutOfBoundsException s){}

               if(data.get(i).getShopname().equals("0"))  img.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {

                          Intent intent=new Intent(getActivity(), vip_show_details.class);
                         intent.putExtra("id",""+view.getTag());
                         startActivity(intent);
                         Log.d("idclick",""+view.getTag());
                     }
                 }); else{
                   img.setTag(i);
                   img.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {

                           Intent intent=new Intent(getActivity(), items_activity.class);
                           Constants.shop_id=data.get(Integer.parseInt(view.getTag().toString())).getShopID();
                           Constants.shop_name=data.get(Integer.parseInt(view.getTag().toString())).getShopname();
                           Constants.shopImage=images.get(Integer.parseInt(view.getTag().toString()));
                           startActivity(intent);
                           Log.d("idclick",""+view.getTag());
                       }
                   });}
                 switch (data.get(i).getLine()){
                     case "1":
                         avi1.hide();
                           v1.addView(img,lp);
                           v1.setTag(data.get(i).getId());
                       //  tv1.addView(label,lp);
                         break;
                     case "2":
                         avi2.hide();
                         v2.addView(img,lp);
                         v2.setTag(data.get(i).getId());
                       //  tv2.addView(label,lp);
                         break;
                     case "3":
                         avi3.hide();
                         v3.addView(img,lp);
                        // tv3.addView(label,lp);
                         break;}}}}
 };
        Date date=new Date();
        SimpleDateFormat ff=new SimpleDateFormat("hh");
        DATE=ff.format(date);

        Log.d("date",DATE);
        Log.d("get_date",db.get_date());
        get_local();
     // DATE="     ";
        Log.d("childcount",""+v1.getChildCount());
        Log.d("childcount",""+v2.getChildCount());
        Log.d("childcount",""+v3.getChildCount());
   if((!DATE.equals(db.get_date())&&con)){check_updates(); db.set_cheked(DATE);}


        return view;
    }

    Thread check;
    String upd;
    ArrayList<model_vip_check> d=new ArrayList<>();
    model_vip_check c=null;
    public void check_updates(){

      check=new Thread(new Runnable() {
          @Override
          public void run() {
              Log.d("Check updates"," " );

              try{

              URL url=new URL("http://"+ network.address+"/Reklama/vip/check_vip.php");
                  HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                  conn.setDoOutput(true);
                  conn.setDoInput(true);
          //   conn.setRequestProperty("Cookie", "__test=cef9bc49784623b7fc983b799bfb4b89;Friday, January 1, 2038 at 2:55:55 AM; path=/");
                  conn.setRequestMethod("POST");
                  conn.connect();
                  InputStream in = conn.getInputStream();
                  BufferedReader br = new BufferedReader(new InputStreamReader(in));
                  StringBuilder sb = new StringBuilder();
                  String row = "";
                  while ((row = br.readLine()) != null) {
                      sb.append(row + "\n");}
                  Log.d("chkupdJon",sb.toString());
                  br.close();
                  in.close();
                  conn.disconnect();
                  JSONObject jsonObject=new JSONObject(sb.toString());
                  JSONArray jsonArray=jsonObject.getJSONArray("result");

                  for(int i=0; i<jsonArray.length();i++){
                      JSONObject data=jsonArray.getJSONObject(i);
                      c=new model_vip_check();
                        c.setId(data.optString("id"));
                        c.setUpd(data.optString("upd"));
                       d.add(c);
                   }
                    upd=db.get_update(d);
                      if (!upd.toString().equals(""))get_vip(upd); //idlar gelyar tazelemeli
              } catch (MalformedURLException e) {
                  e.printStackTrace();
              } catch (IOException e) {
                  e.printStackTrace();
              } catch (JSONException e) {
                  e.printStackTrace();
              }
          }
      }
      );
         check.start();
 }
    Thread get;
    String send;
    public void get_vip(String upd){
      send=upd;
        Log.d("get_vip",send);
     get=new Thread(new Runnable() {
         @Override
         public void run() {
             try{
                 URL url=new URL("http://"+ network.address+"/Reklama/vip/get_vip1.php");
                 HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                 conn.setDoOutput(true);
                 conn.setDoInput(true);
              //   conn.setRequestProperty("Cookie", "__test=cef9bc49784623b7fc983b799bfb4b89; Friday, January 1, 2038 at 2:55:55 AM; path=/");
                 conn.setRequestMethod("POST");
                 conn.connect();
                 OutputStream outputStream = conn.getOutputStream();
                 BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                 String post_data = URLEncoder.encode("get", "UTF-8") + "=" + URLEncoder.encode(send, "UTF-8");
                 bufferedWriter.write(post_data);
                 bufferedWriter.flush();
                 bufferedWriter.close();
                 outputStream.close();
                 InputStream in = conn.getInputStream();
                 BufferedReader br = new BufferedReader(new InputStreamReader(in));
                 StringBuilder sb = new StringBuilder();
                 String row = "";
                 while ((row = br.readLine()) != null) {
                     sb.append(row + "\n");}
                 Log.d("get_Datavip",sb.toString());
                 br.close();
                 in.close();
                 conn.disconnect();

                 if (!sb.toString().equals(""))GetJson(sb.toString());//idlar gelyar tazelemeli
                 Thread.currentThread().interrupt();
             } catch (MalformedURLException e) {
                 e.printStackTrace();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
     });
 get.start();
 }
    public void GetJson(final String s){
        Log.d("GetJSon","  ");
        Thread json=new Thread(new Runnable() {
            @Override
            public void run() {
                try{//bashda data basadan hemme informasiyasyny alyas
                    JSONObject jsonObject=new JSONObject(s);
                    JSONArray jsonArray=jsonObject.getJSONArray("result");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data=jsonArray.getJSONObject(i);

                        db.insert_Vip(data.optString("id"),data.optString("name"),data.optString("description"),
                                data.optString("date"),data.optString("line"),data.optString("upd"),data.optInt("watched"),data.optString("number"),data.optString("time"),data.optString("link"));

                    }
                    jsonObject=new JSONObject(s);
                    jsonArray=jsonObject.getJSONArray("Image");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data=jsonArray.getJSONObject(i);

                        db.insert_Vip_image(data.optString("id"),data.optString("image"),data.optString("image1"),data.optString("image2"),
                                data.optString("name"),data.optString("line"),data.optString("shopId"),data.optString("shopName")
                        );
                           Log.d("vip",data.optString("image"));
                    }
//                     v1.removeAllViews();
               //     v2.removeAllViews();
               //     v3.removeAllViews();
                    get_local();
         //  Intent intent =new Intent(getContext(),MainActivity.class);
            //        startActivity(intent);
                    Thread.currentThread().interrupt();
                } catch (JSONException e) {e.printStackTrace();}
            }
        });
        json.start();
    }
    Thread get_sqlite;
    public void get_local(){
        Log.d("getLocal","  ");
        get_sqlite=new Thread(new Runnable() {
            @Override
            public void run() {
                images=new ArrayList<>();
              //  db.delete_elements();
                data=db.get_data_vip();

                Collections.shuffle(data,new Random());
                Log.d("datasize",""+data.size());
              try{  for(int i=0; i<data.size();i++){

                    byte[] img = Base64.decode(data.get(i).getImage(), Base64.DEFAULT);
                  Bitmap image = null;
                  try {
                      image = Glide.with(getActivity())
                              .load(img).asBitmap()
                              .diskCacheStrategy(DiskCacheStrategy.NONE)
                               .into(320,240)
                              .get();
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  } catch (ExecutionException e) {
                      e.printStackTrace();
                  }
                  images.add(image);
                }}catch (OutOfMemoryError ss){}
                 create.sendEmptyMessage(1);
                Thread.currentThread().interrupt();
            }
        });
        get_sqlite.start();
    }


}
