package synchedapps.tmbazar;

import android.util.Log;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.Network.network;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.bb.Realtor.fragment2_realtor;
import synchedapps.tmbazar.bb.Realtor.fragment3_realtor;

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

public class instant_notif {
    Db db=new Db(MainActivity.ctx);
    HashMap<String,String> tables=null;

public  void notif(String table ){

 // if(table.equals("home"))  tables=db.get_newNothome();
    final  String json=" ";
        Log.d("idlar1 ",json);
    Thread get_notify;
    get_notify=new Thread(new Runnable() {
        @Override
        public void run() {
            try{

                //     URL url=new URL("http://ynamly.biz/reklama3/adds/get_image.php");
                URL url=new URL("http://"+ network.address+"/Reklama/adds/notif/home.php");
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
                Log.d("get_DataAddsnotify1",sb.toString());
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
                    Log.d("namegelyar",s);
                    JSONArray jsonArray=jsonObject.getJSONArray("result");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data=jsonArray.getJSONObject(i);
                        fragment2_realtor.s1=data.optString("A");
                        fragment2_realtor.s2=data.optString("B");
                        fragment2_realtor.s3=data.optString("D");
                        fragment2_realtor.s4=data.optString("E");
                        fragment2_realtor.s5=data.optString("F");
                        fragment2_realtor.s6=data.optString("G");
                        fragment2_realtor.s7=data.optString("H");

                        fragment3_realtor.s1=data.optString("A1");
                        fragment3_realtor.s2=data.optString("B1");
                        fragment3_realtor.s3=data.optString("C1");
                        fragment3_realtor.s4=data.optString("D1");
                        fragment3_realtor.s5=data.optString("E1");
                        fragment3_realtor.s6=data.optString("F1");
                                                fragment2_realtor.set.sendEmptyMessage(1);
                      fragment3_realtor.set.sendEmptyMessage(1);


                    }

                    Thread.currentThread().interrupt();
                } catch (JSONException e) {
                    e.printStackTrace();
                }}
        });
        json.start();
    }
}
