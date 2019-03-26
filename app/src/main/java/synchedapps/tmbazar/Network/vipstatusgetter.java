package synchedapps.tmbazar.Network;

import android.util.Log;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.bb.Beylekiler.Bildiris_gos_Navigation.fragment_bildiris_gosmak1;
import synchedapps.tmbazar.bb.MainActivity;

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
 * Created by User on 12.07.2018.
 */

public class vipstatusgetter {
    Thread vipget;
    String post_data="";

    public void vipGetStatus(final String table){
        Db db=new Db(MainActivity.ctx);
        HashMap<String ,String>  d=new HashMap<>();
try{                  d=db.get_abs(table,"","");} catch (RuntimeException s){}
        final String idlar=d.get("idlar");

//  Log.d("Idlargidyar",idlar);
       vipget=new Thread(new Runnable() {
           @Override
           public void run() {
               try{
                   //  URL url=new URL("http://ynamly.biz/reklama3/adds/get_image.php");
                   URL url=new URL("http://"+network.address+"/Reklama/adds/vipStatusCheker.php");
                   HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                   conn.setDoOutput(true);
                   conn.setDoInput(true);
                   conn.setRequestMethod("POST");
                   conn.connect();
                   OutputStream outputStream = conn.getOutputStream();
                   BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
try {
                post_data = URLEncoder.encode("table", "UTF-8") + "=" + URLEncoder.encode(table, "UTF-8") + "&" + URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(idlar, "UTF-8");
}catch (NullPointerException ss){}
                 //  Log.d("post",post_data);
                   bufferedWriter.write(post_data);
                   bufferedWriter.flush();
                   bufferedWriter.close();
                   outputStream.close();
                   InputStream in = conn.getInputStream();
                   BufferedReader br = new BufferedReader(new InputStreamReader(in));
                   StringBuilder sb = new StringBuilder();
                   String row = "";
                   while ((row = br.readLine()) != null) {
                       sb.append(row);}
                   Log.d("statuses",sb.toString());
                   br.close();
                   in.close();
                   conn.disconnect();
                if (!sb.toString().equals("")){ GetJsonVipStatus(table,sb.toString()); Log.d("getJSon","gitdi");} //idlar gelyar tazelemeli
                   fragment_bildiris_gosmak1.get_local();
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
       });
        vipget.start();

    }
    public void GetJsonVipStatus(final String table,final String s){
        Log.d("GetJSon","  ");
final         Db db=new Db(MainActivity.ctx);
        Thread json=new Thread(new Runnable() {
            @Override
            public void run() {
                try{//bashda data basadan hemme informasiyasyny alyas
                    JSONObject jsonObject=new JSONObject(s);
                    JSONArray jsonArray=jsonObject.getJSONArray("result");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data=jsonArray.getJSONObject(i);
                        db.insert_vipStatuses(table,data.optString("id"),data.optString("status"));
                    }
                    Thread.currentThread().interrupt();
                } catch (JSONException e) {
                    e.printStackTrace();
                }}
        });
        json.start();
    }
}
