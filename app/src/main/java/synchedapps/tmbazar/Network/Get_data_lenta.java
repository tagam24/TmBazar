package synchedapps.tmbazar.Network;

import android.os.Build;
import android.util.Log;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.adapters.adapter_for_recycle_for_lenta;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.bb.Realtor.adapter_for_recycle_for_realtor;
import synchedapps.tmbazar.bb.lenta_show_details;
import synchedapps.tmbazar.bb.small_lenta_show_details;
import synchedapps.tmbazar.fragment.fragment_lenta1;
import synchedapps.tmbazar.fragment.fragment_lenta2;
import synchedapps.tmbazar.fragment.fragment_lenta3;

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

/**
 * Created by User on 23.06.2018.
 */

public class Get_data_lenta {
    String table="";
    Db db;

    String send="";
      int k=0;
    Thread json=new Thread();
    Thread get=new Thread();
    public  void get_data(final String Url,final String table,String upd){
        db=new Db(MainActivity.ctx);
        send=upd;
        Log.d("gitmane","geldi");
        this.table=table;
        Log.d("get_vip",send);
        Log.d("table",this.table);
        Log.d("max1",send);
        get=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Log.d("gitdi","ok");
                    URL url=new URL(Url);
                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setRequestMethod("POST");
                    conn.connect();

                    OutputStream outputStream = conn.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("get", "UTF-8") + "=" + URLEncoder.encode(send, "UTF-8")+"&"+
                            URLEncoder.encode("table", "UTF-8") + "=" + URLEncoder.encode(table, "UTF-8");
                    bufferedWriter.write(post_data);
                    Log.d("gityanData",post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream in = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    StringBuilder sb = new StringBuilder();
                    String row = "";

                    while ((row = br.readLine()) != null) {
                        Log.d("buff",row);
                        sb.append(row + "\n");}
                    Log.d("get_Datavip",sb.toString());
                    if(sb.toString().length()<30)
                        adapter_for_recycle_for_lenta.b=false; else adapter_for_recycle_for_lenta.b=true;
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
    if(!json.isAlive()&&!get.isAlive()) get.start();
    }

    Thread image;
    String Url="";
    public  void get_image(final String table,final String send){
        db=new Db(MainActivity.ctx);
    //Url="http://192.168.137.1/Reklama3/lenta/get_lenta_image.php";
 Url="http://"+network.address+"/Reklama/lenta/get_lenta_image.php";
        image=new Thread(new Runnable() {
            @Override
            public void run() {

                try{
                    URL url=new URL(Url);
                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setRequestMethod("POST");
                    conn.connect();
                    OutputStream outputStream = conn.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("get", "UTF-8") + "=" + URLEncoder.encode(send, "UTF-8")+"&"+
                            URLEncoder.encode("table", "UTF-8") + "=" + URLEncoder.encode(table, "UTF-8");
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
                    Log.d("get_image",sb.toString());
                    br.close();
                    in.close();
                    conn.disconnect();
                    if (!sb.toString().equals(""))GetJsonImage(table,sb.toString());//idlar gelyar tazelemeli
                    Thread.currentThread().interrupt();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        image.start();
    }

    public void GetJsonImage(final String table,final String s){
        Log.d("GetLentamage",table + " "+s);

        Thread json=new Thread(new Runnable() {
            @Override
            public void run() {
                try{//bashda data basadan hemme informasiyasyny alyas
                    JSONObject jsonObject=new JSONObject(s);
                    JSONArray jsonArray=jsonObject.getJSONArray("result");
                    Log.d("sizeoFJSon",""+jsonArray.length());
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data=jsonArray.getJSONObject(i);
                     db.update_gyzykly(table,data.optString("id"),data.optString("image"));
                    }
                    if( Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
                        lenta_show_details.upd.sendEmptyMessage(1);

                   } else{

                        small_lenta_show_details.upd.sendEmptyMessage(1);

                    }
                    Thread.currentThread().interrupt();
                } catch (JSONException e) {e.printStackTrace();}
            }
        });
        json.start();
    }

    public void GetJson(final String s){
        Log.d("GetJSon","  ");
       json=new Thread(new Runnable() {
            @Override
            public void run() {
                try{//bashda data basadan hemme informasiyasyny alyas
                    JSONObject jsonObject=new JSONObject(s);
                    JSONArray jsonArray=jsonObject.getJSONArray("result");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data=jsonArray.getJSONObject(i);
                        if(Integer.parseInt(data.optString("id"))>k)k=Integer.parseInt(data.optString("id"));
                  if(!db.isIn(table,data.optString("id")))
                        db.insert_gyzykly(table,data.optString("id"),data.optString("name"),data.optString("image"),
                              data.optString("content"),data.optString("date"),data.optInt("liked"),data.optInt("disliked"),data.optInt("watched"));


                     /*   if (table=="tasinlikler")db.insert_gyzykly(table,data.optString("id"),data.optString("name"),data.optString("image"),
                                data.optString("content"),data.optString("date"),data.optInt("liked"),data.optInt("disliked"),data.optInt("watched"));
                        if (table=="gymmatly")db.insert_gyzykly(table,data.optString("id"),data.optString("name"),data.optString("image"),
                                data.optString("content"),data.optString("date"),data.optInt("liked"),data.optInt("disliked"),data.optInt("watched"));*
                                */
                    }//get_image(table,send);
try {
    if(table=="futbol")fragment_lenta1.get_local();

                    if(table=="tasinlikler")fragment_lenta2.get_local();
                    if(table=="gymmatly")fragment_lenta3.get_local();
                    if(db.getMAxs(table)<k)db.insert_max(table,""+k);}catch (IllegalThreadStateException ss){}
                    //db.getMAxs(table);
                    Thread.currentThread().interrupt();
                } catch (JSONException e) {e.printStackTrace();}
            }
        });
        json.start();
    }

}
