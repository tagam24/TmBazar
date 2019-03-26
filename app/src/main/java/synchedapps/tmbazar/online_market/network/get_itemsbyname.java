package synchedapps.tmbazar.online_market.network;


import android.util.Log;

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

import synchedapps.tmbazar.online_market.Constants;
import synchedapps.tmbazar.online_market.items.items_activity;
import synchedapps.tmbazar.online_market.models.model_items;


/**
 * Created by User on 27.06.2018.
 */

public class get_itemsbyname {
   public  static String k;
    public  static int con=0;
    static String l;
    public static   Thread get_data=new Thread();
    public static void  get_Data(){

        get_data=new Thread(new Runnable() {
            @Override
            public void run() {
                try{

                    URL url=new URL("http://"+ Api.url+"get_itemsbyname.php");
                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setRequestMethod("POST");
                    conn.connect();
                    OutputStream outputStream = conn.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data =URLEncoder.encode("shop", "UTF-8") + "=" + URLEncoder.encode(Constants.shop_id, "UTF-8")+
                            "&"+URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(Constants.item_name, "UTF-8")+"&"+
                            URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(Constants.iter_id, "UTF-8");
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
Log.d("name",sb.toString());
                    br.close();
                    in.close();
                    conn.disconnect();
                    if (!sb.toString().equals(""))GetJson(sb.toString());//idlar gelyar tazelemeli

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
       if(con==0) get_data.start();
    }

    public static void GetJson(final String s){
        Thread json=new Thread(new Runnable() {
            @Override
            public void run() {
                try{//bashda data basadan hemme informasiyasyny alyas
                    JSONObject jsonObject=new JSONObject(s);
                    JSONArray jsonArray=jsonObject.getJSONArray("result");
              model_items m;
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data=jsonArray.getJSONObject(i);
                        m=new model_items(data.optString("id"),data.optString("name"),data.optString("image"),data.optString("image1"),
                                data.optString("image2"),data.optString("image3"),data.optString("content"),data.optString("skidka"),data.optString("price"),data.optString("number"));
                        if(!Constants.itemid.contains(data.optString("id"))){ Constants.items.add(m); Constants.itemid.add(data.optString("id"));}
                    }
                    items_activity.s1.sendEmptyMessage(1);
                    Log.d("conn",""+con);
                    Thread.currentThread().interrupt();
                } catch (JSONException e) {
                    e.printStackTrace();
                }}
        });
        json.start();
    }

}
