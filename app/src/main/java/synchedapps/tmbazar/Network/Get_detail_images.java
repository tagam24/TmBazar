package synchedapps.tmbazar.Network;

import android.os.Build;
import android.util.Log;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.bb.Realtor.realtor_show_details;
import synchedapps.tmbazar.bb.Realtor.small_realtor_show_details;
import synchedapps.tmbazar.bb.ShowDetailsadds;
import synchedapps.tmbazar.bb.small_car_show_details;
import synchedapps.tmbazar.bb.vip_show_details;

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
 * Created by User on 04.07.2018.
 */

public class Get_detail_images {
Db db=new Db(MainActivity.ctx);
    String vip="";
public void get_detail(final String table, final String id,String vip ) {
    Thread get_Image;
    this.vip=vip;
 Log.d("nameGidyardetail",table+" "+id+" "+vip);
        get_Image=new Thread(new Runnable() {
            @Override
            public void run() {
                try{

                    //  URL url=new URL("http://ynamly.biz/Reklama3/adds/get_image.php");
                    URL url=new URL("http://"+network.address+"/Reklama/adds/get_detail_images.php");
                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setRequestProperty("Cookie", "__test=cef9bc49784623b7fc983b799bfb4b89; Friday, January 1, 2038 at 2:55:55 AM; path=/");
                    conn.setRequestMethod("POST");
                    conn.connect();
                    OutputStream outputStream = conn.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("table", "UTF-8") + "=" + URLEncoder.encode(table, "UTF-8")+"&"+URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
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
                    Log.d("get_Datadetailsimage",sb.toString());
                    br.close();
                    in.close();
                    conn.disconnect();
                    if (!sb.toString().equals(""))GetJsonImage(table,id,sb.toString());//idlar gelyar tazelemeli
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
        get_Image.start();

}
    public void GetJsonImage(final String table,final String id,final String s){
        Log.d("GetJSon","  ");
        Thread json=new Thread(new Runnable() {
            @Override
            public void run() {
                try{//bashda data basadan hemme informa
                    // siyasyny alyas
                    JSONObject jsonObject=new JSONObject(s);
                    JSONArray jsonArray=jsonObject.getJSONArray("result");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data=jsonArray.getJSONObject(i);
                        Log.d("image3",data.optString("image3"));
                        db.update_adds_detail_image(table,id,data.optString("image1"),data.optString("image2"),data.optString("image3"));
                    }
                    if(table.equals("cars")&& !vip.equals("1")){
                        //Build.VERSION.SDK_INT
                        if(Build.VERSION.SDK_INT>=21)ShowDetailsadds.trans.sendEmptyMessage(1);
                    else small_car_show_details.trans.sendEmptyMessage(1);}
                    try{                  if((table.equals("home") || table.equals("beylekiler"))&& !vip.equals("1"))
                         if(Build.VERSION.SDK_INT>=21){if(realtor_show_details.trans!=null)
                           realtor_show_details.trans.sendEmptyMessage(1); } else{
                             small_realtor_show_details.trans.sendEmptyMessage(1);Log.d("small","gitdi");}
                     Log.d("vipgidyami",vip);
                      if(vip.equals("1")) vip_show_details.trans.sendEmptyMessage(1);}catch (ExceptionInInitializerError ss){}catch (NoClassDefFoundError ss){}
                    Thread.currentThread().interrupt();
                } catch (JSONException e) {
                    e.printStackTrace();
                }}
        });
        json.start();
    }

}
