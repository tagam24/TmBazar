package synchedapps.tmbazar.Network;

import android.util.Log;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.adapters.adapter_for_recycle_for_car;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.fragment.fragment_car1;
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
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by User on 27.06.2018.
 */

public class Get_data_cars {
  public static   Thread get_data=new Thread();
   public static  Thread json=new Thread();
    static int k=0;
    Db db=new Db(MainActivity.ctx);
    HashMap<String,String> object;
  public void  get_Data(final String table,String category,String model,String year1,String year2,String price1,String price2,
                        String credit,String obmen,String location,String color,String vip){
      object=db.get_abs(table,category,model);
      object.put("table",table);
      object.put("category",category);
      object.put("model",model);
      object.put("year1",year1);
      object.put("year2",year2);
      object.put("price1",price1);
      object.put("price2",price2);
      object.put("credit",credit);
      object.put("obmen",obmen);
      object.put("location",location);
      object.put("color",color);
      //object.put("vip",vip);
     // data.add(object);
      Log.d("table",table);
    final  String json=new Gson().toJson(object);
      Log.d("json",json);
     get_data=new Thread(new Runnable() {
         @Override
         public void run() {
              try{
                // URL url=new URL("http://ynamly.biz/Reklama3/adds/get_data.php");
                URL url=new URL("http://"+network.address+"/Reklama/adds/get1.php");
                  HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                  conn.setDoOutput(true);
                  conn.setDoInput(true);
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
                  Log.d("get_DataAdds",sb.toString());
                  if(sb.toString().toString().length()<30) adapter_for_recycle_for_car.b=false; else  adapter_for_recycle_for_car.b=true;
                  br.close();
                  in.close();
                  conn.disconnect();
                  if (!sb.toString().equals(""))GetJson(table,sb.toString());//idlar gelyar tazelemeli
                  //get_image(table,json);
                  Thread.currentThread().interrupt();
} catch (UnsupportedEncodingException e) {
                  e.printStackTrace();
                  try{
                      // URL url=new URL("http://ynamly.biz/Reklama3/adds/get_data.php");
                      URL url=new URL("http://"+network.address+"/Reklama/adds/get_data.php");
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
                      Log.d("get_DataAdds",sb.toString());
                      if(sb.toString().toString().length()<30) adapter_for_recycle_for_car.b=false; else  adapter_for_recycle_for_car.b=true;
                      br.close();
                      in.close();
                      conn.disconnect();
                      if (!sb.toString().equals(""))GetJson(table,sb.toString());//idlar gelyar tazelemeli
                      //get_image(table,json);
                      Thread.currentThread().interrupt();
                  } catch (UnsupportedEncodingException k) {
                      e.printStackTrace();

                  } catch (ProtocolException k) {
                      e.printStackTrace();
                  } catch (MalformedURLException k) {
                      e.printStackTrace();
                  } catch (ConnectException s)
                  {

                  } catch (IOException k) {
                      e.printStackTrace();
                  }

              } catch (ProtocolException e) {
                  e.printStackTrace();
              } catch (MalformedURLException e) {
                  e.printStackTrace();
              } catch (ConnectException s)
              {
                  try{
                      // URL url=new URL("http://ynamly.biz/Reklama3/adds/get_data.php");
                      URL url=new URL("http://"+network.address+"/Reklama/adds/get_data.php");
                      HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                      conn.setDoOutput(true);
                      conn.setDoInput(true);
                      conn.setRequestProperty("Cookie", "__test=cef9bc49784623b7fc983b799bfb4b89; Friday, January 1, 2038 at 2:55:55 AM; path=/");
                      conn.setRequestMethod("POS" +
                              "T");
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
                      Log.d("get_DataAdds",sb.toString());
                      if(sb.toString().toString().length()<30) adapter_for_recycle_for_car.b=false; else  adapter_for_recycle_for_car.b=true;
                      br.close();
                      in.close();
                      conn.disconnect();
                      if (!sb.toString().equals(""))GetJson(table,sb.toString());//idlar gelyar tazelemeli
                      //get_image(table,json);
                      Thread.currentThread().interrupt();
                  } catch (UnsupportedEncodingException e) {
                      e.printStackTrace();

                  } catch (ProtocolException e) {
                      e.printStackTrace();
                  } catch (MalformedURLException l) {
                      l.printStackTrace();
                  } catch (ConnectException l)
                  {

                  } catch (IOException e) {
                      e.printStackTrace();
                  }

              } catch (IOException e) {
                  e.printStackTrace();
              }
         }
     }) ;
      Log.d("isAlivemy",""+get_data.isAlive());
       get_data.start();
  }
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
                            if(Integer.parseInt(data.optString("id"))>k)k=Integer.parseInt(data.optString("id"));
                        if(!db.isIn("cars",data.optString("id")))db.insert_adds(table,data.optString("id"),data.optString("category"),data.optString("name"),data.optString("price"),data.optString("description")
                                ,data.optString("location"),data.optString("vip"),data.optString("time"),data.optString("number"),data.optString("watched"),data.optString("date")
                                ,data.optString("year"),data.optString("credit"),data.optString("obmen"),data.optString("satyldy"),data.optString("probeg"),data.optString("kuzow"),
                                data.optString("color"),data.optString("karopka"),data.optString("motor"),data.optString("image"),data.optString("vipEndDate"),data.optString("image1"),data.optString("image2"),data.optString("image3"));

                }
                   if(db.getMAxs("cars")<k) db.insert_max("cars",""+k);
                        Log.d("name alanok",""+db.getMAxs("cars"))   ;

                 if(table.equals("cars")) fragment_car1.get_local();
                  //  if(table.equals("Realtor"))
                    Thread.currentThread().interrupt();
            } catch (JSONException e) {
                    e.printStackTrace();
                }}
            });
        json.start();
    }

}
