package synchedapps.tmbazar.Network;

import android.util.Log;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.bb.MainActivity;
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
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by User on 24.06.2018.
 */

public class Liked_getter {
    Db db;
    Thread getter;
    String table;
    String max;
    String min;
    String u;
    String post_data;
    public void getter(final String table,final String id){
        db=new Db(MainActivity.ctx);

        this.table=table;

       getter=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    max=db.get_max(table);
                    min=db.get_min(table);
                    if(table.equals("vip") || table.equals("home")|| table.equals("beylekiler")|| table.equals("cars")){
                        u="http://"+network.address+"/Reklama/vip/get_watched.php";
                        try {
                            post_data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8")+"&"+
                                    URLEncoder.encode("table", "UTF-8") + "=" + URLEncoder.encode(table, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    else  {
                        max=db.get_max(table);
                        min=db.get_min(table);
                        u="http://"+network.address+"/Reklama/lenta/get_watched.php";
                        try {
                            post_data = URLEncoder.encode("max", "UTF-8") + "=" + URLEncoder.encode(max, "UTF-8")+"&"+
                                    URLEncoder.encode("table", "UTF-8") + "=" + URLEncoder.encode(table, "UTF-8")+"&"+
                                    URLEncoder.encode("min", "UTF-8") + "=" + URLEncoder.encode(min, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                    }
                    URL url=new URL(u);
                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setRequestMethod("POST");
                    conn.connect();
                    OutputStream outputStream = conn.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
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
                    Log.d("get_likeda",sb.toString());
                    br.close();
                    in.close();
                    conn.disconnect();
                    GetJson(sb.toString());
                    Thread.currentThread().interrupt();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        getter.start();


    }
    public void GetJson(final String s){
        Log.d("GetLiked","  "+s);
        Thread json=new Thread(new Runnable() {
            @Override
            public void run() {
                try{//bashda data basadan hemme informasiyasyny alyas
                    JSONObject jsonObject=new JSONObject(s);
                    JSONArray jsonArray=jsonObject.getJSONArray("result");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data=jsonArray.getJSONObject(i);
                       if(table.equals("vip") || table.equals("home")|| table.equals("beylekiler")|| table.equals("cars"))  db.update_liked(table,data.optString("id"),data.optString("watched"));
                        else  db.update_liked(table,data.optString("id"),data.optString("liked"),data.optString("disliked"),data.optString("watched"));
                    }
                    if(table.equals("futbol")) fragment_lenta1.get_local();
                    if(table.equals("tasinlikler")) fragment_lenta2.get_local();
                    if(table.equals("gymmatly")) fragment_lenta3.get_local();
                    Thread.currentThread().interrupt();
                } catch (JSONException e) {e.printStackTrace();}
            }
        });
        json.start();
    }
}
