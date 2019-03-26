package synchedapps.tmbazar.Network;

import android.util.Log;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.bb.Beylekiler.fragment_beyleki1;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.bb.Realtor.adapter_for_recycle_for_realtor;
import synchedapps.tmbazar.bb.Realtor.fragment1_realtor;
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
 * Created by User on 13.07.2018.
 */

public class Get_data_realtor {

    public static   Thread get_data=new Thread();
    Db db=new Db(MainActivity.ctx);
    HashMap<String,String> object;
    URL url;
      int k=0;
    String s="";
    public  static Thread json=new Thread();

    public void  get_Data(final String table,final String category, final String name,final String location){
        final String tabl=table;

        object=db.get_absRealtor(table,category,name);
        object.put("table",table);
        object.put("category",category);
        object.put("name",name);
        object.put("location",location);
        // data.add(object);,
        Log.d("table",table);
        Log.d("name",name);
        Log.d("category",category);

        final  String json1=new Gson().toJson(object);
        Log.d("json",json1);
        Log.d("isAliveGet",""+get_data.isAlive()+" name"+name);
        get_data=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    // URL url=new URL("http://ynamly.biz/Reklama3/adds/get_data.php");
                    if(table.equals("beylekiler")) url=new URL("http://"+network.address+"/Reklama/adds/get_data_realtor.php");
                    else if(table.equals("home")) url=new URL("http://"+network.address+"/Reklama/adds/data_realtor.php");
                        HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setRequestProperty("Cookie", "__test=cef9bc49784623b7fc983b799bfb4b89; Friday, January 1, 2038 at 2:55:55 AM; path=/");
                    conn.setRequestMethod("POST");
                    conn.connect();
                    OutputStream outputStream = conn.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("json", "UTF-8") + "=" + URLEncoder.encode(json1, "UTF-8");
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
                    Log.d("get_DataAddsRealtor",sb.toString());
                //    if(sb.toString().equals(s))Thread.currentThread().interrupt();
                   if(sb.toString().length()<30)
                        adapter_for_recycle_for_realtor.b=false; else adapter_for_recycle_for_realtor.b=true;
                    br.close();
                    in.close();
                    s=sb.toString();
                    conn.disconnect();
                    if (!sb.toString().equals(""))GetJson(tabl,sb.toString());//idlar gelyar tazelemeli
                    //get_image(table,json1);

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
get_data.start();}
    public void GetJson(final String table,final String s){
        Log.d("GetJSon","  ");
        json=new Thread(new Runnable() {
            @Override
            public void run() {
                try{//bashda data basadan hemme informasiyasyny alyas
                    JSONObject jsonObject=new JSONObject(s);
                    JSONArray jsonArray=jsonObject.getJSONArray("result");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data=jsonArray.getJSONObject(i);
                     Log.d("location1",data.optString("location1"));
                        if(Integer.parseInt(data.optString("id"))>k)k=Integer.parseInt(data.optString("id"));
                    if(table.equals("home"))  {
                        Log.d("price123",data.optString("price"));
                          if(!db.isIn("home",data.optString("id")))
                        db.insert_realtor(data.optString("id"),data.optString("category"),data.optString("name"),data.optString("price")
                                ,data.optString("description"),data.optString("location"),data.optString("number")
                                ,data.optString("vip"),data.optString("image"),"",data.optString("watched"),"0","","",data.optString("time"),data.optString("date"),data.optString("category1"),
                            data.optString("location1"),data.optString("image1"),data.optString("image2"),data.optString("image3"));}
else    {   if(!db.isIn("beylekiler",data.optString("id")))db.insert_beylekiler("beylekiler",data.optString("id"),data.optString("category"),data.optString("name"),data.optString("price")
                            ,data.optString("description"),data.optString("location"),data.optString("number")
                            ,data.optString("vip"),data.optString("image"),"",data.optString("watched"),"0","","",data.optString("time"),data.optString("date"),data.optString("category1"),data.optString("location1"),data.optString("image1"),data.optString("image2"),data.optString("image3"));
                    }}

                    if(db.getMAxs(table)<k)db.insert_max(table,""+k);
                    if(table.equals("home")) {fragment1_realtor.get_local(); Log.d("getLocal,Realtor","gitdi");}
                    else fragment_beyleki1.get_local();
                   Thread.currentThread().interrupt();
                } catch (JSONException e) {
                    e.printStackTrace();
                }}
        });
        json.start();
    }

}