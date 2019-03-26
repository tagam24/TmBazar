package synchedapps.tmbazar.Network;

import android.util.Log;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.bb.lenta_tab;

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

/**
 * Created by User on 17.07.2018.
 */

public class Get_bannner {
    Db db;
    String maxiD="";
    Thread bann;
    boolean b;
    public void get_banner(){
      db=new Db(MainActivity.ctx);
        b=true;
        bann=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                   while(b)   {
                       String k=maxiD;
                    maxiD=db.get_maxBanner();
                       if(!k.equals(maxiD)){
                Log.d("gidyarID"," max"+maxiD);
                    //  URL url=new URL("http://ynamly.biz/Reklama3/adds/get_image.php");
                    URL url=new URL("http://"+network.address+"/Reklama/lenta/get_banner.php");
                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setRequestProperty("Cookie", "__test=cef9bc49784623b7fc983b799bfb4b89; Friday, January 1, 2038 at 2:55:55 AM; path=/");
                    conn.setRequestMethod("POST");
                    conn.connect();
                    OutputStream outputStream = conn.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("maxID", "UTF-8") + "=" + URLEncoder.encode(maxiD, "UTF-8");
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
                    Log.d("get_DatadetailsBAnner",""+sb.toString());
                    br.close();
                    in.close();
                    conn.disconnect();
                       if(sb.toString().equals("")) b=false;
                    if (!sb.toString().equals(""))GetJsonBanner(sb.toString());}}//idlar gelyar tazelemeli}
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
        bann.start();}
    public void GetJsonBanner(final  String s){
        Log.d("GetJSon","  ");
        Thread json=new Thread(new Runnable() {
            @Override
            public void run() {
                try{//bashda data basadan hemme informasiyasyny alyas
                    JSONObject jsonObject=new JSONObject(s);
                    JSONArray jsonArray=jsonObject.getJSONArray("result");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data=jsonArray.getJSONObject(i);
                        Log.d("endDateBAnner",data.optString("endDate"));
                      db.insert_bannerLenta(data.optString("id"),data.optString("name"),
                              data.optString("number"),data.optString("description"),data.optString("image"),data.optString("image1"),data.optString("image2"),data.optString("image3"),data.optString("endDate"));
                    }
                    lenta_tab.trans.sendEmptyMessage(1);
               //     fragment_lenta2.trans.sendEmptyMessage(1);
                 //   fragment_lenta3.trans.sendEmptyMessage(1);
                    Thread.currentThread().interrupt();
                } catch (JSONException e) {
                    e.printStackTrace();
                }}
        });
        json.start();
    }

}
