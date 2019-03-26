package synchedapps.tmbazar;

import android.util.Log;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.Network.network;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.fragment.fragment_car2;
import com.google.gson.Gson;

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
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by User on 22.07.2018.
 */

public class instant_notifcars {
    Db db=new Db(MainActivity.ctx);
    HashMap<String,String> tables=null;

public  void notif(String table ){
   // if(table.equals("cars")) tables=db.get_newNotcars();
    final  String json=new Gson().toJson(tables);
        Log.d("idlarcars1 ",json);
    Thread get_notify;
    get_notify=new Thread(new Runnable() {
        @Override
        public void run() {
            try{


                URL url=new URL("http://"+ network.address+"/Reklama/adds/notif/CARSNOTIF.php");
              //  URL url=new URL("http://u21940xgr.ha002.t.justns.ru/Reklama/adds/notif/home.php");
                HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setRequestProperty("Cookie", "__test=cef9bc49784623b7fc983b799bfb4b89; Friday, January 1, 2038 at 2:55:55 AM; path=/");
                conn.setRequestMethod("POST");
                conn.connect();
                OutputStream outputStream = conn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("json", "UTF-8") + "=" + URLEncoder.encode(json, "UTF-8");
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
                Log.d("cars",sb.toString());
                br.close();
                in.close();
                conn.disconnect();
                if (!sb.toString().equals(""))GetJsonNotif(sb.toString());//idlar gelyar tazelemeli
                Thread.currentThread().interrupt();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }) ;
        get_notify.start();}
    public void GetJsonNotif(final String s){
        Log.d("GetJSon","  ");
        Thread json=new Thread(new Runnable() {
            @Override
            public void run() {
                try{//bashda data basadan hemme informasiyasyny alyas
                    JSONObject jsonObject=new JSONObject(s);
                    JSONArray jsonArray=jsonObject.getJSONArray("result");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data=jsonArray.getJSONObject(i);
                       fragment_car2.s1=data.optString("A");
                        fragment_car2.s2=data.optString("B");
                       fragment_car2.s3=data.optString("C");
                       fragment_car2.s4=data.optString("D");
                       fragment_car2.s5=data.optString("E");
                       fragment_car2.s6=data.optString("F");
try                    {   fragment_car2.set.sendEmptyMessage(1);}catch (NullPointerException ss){}
                    }

                    Thread.currentThread().interrupt();
                } catch (JSONException e) {
                    e.printStackTrace();
                }}
        });
        json.start();
    }
}
