package synchedapps.tmbazar.Network;

import android.util.Log;

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
 * Created by User on 06.07.2018.
 */

public class my_add_updater {
    Thread updater;
   public void  updater_add(final String table, final String deleteID,final String satyldy){
       updater=new Thread(new Runnable() {
           @Override
           public void run() {
               try{
                   Log.d(table,satyldy+","+deleteID);
                   //URL url=new URL("http://ynamly.biz/Reklama3/adds/get_data.php");
                   URL url=new URL("http://"+network.address+"/Reklama/adds/update_myAdd.php");
                   HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                   conn.setDoOutput(true);
                   conn.setDoInput(true);
                   conn.setRequestProperty("Cookie", "__test=cef9bc49784623b7fc983b799bfb4b89; Friday, January 1, 2038 at 2:55:55 AM; path=/");
                   conn.setRequestMethod("POST");
                   conn.connect();
                   OutputStream outputStream = conn.getOutputStream();
                   BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                   String post_data = URLEncoder.encode("delete", "UTF-8") + "=" + URLEncoder.encode(deleteID, "UTF-8")+"&"+
                           URLEncoder.encode("satyldy","UTF-8")+"="+URLEncoder.encode(satyldy,"UTF-8")+"&"+
                           URLEncoder.encode("table","UTF-8")+"="+URLEncoder.encode(table,"UTF-8");
                   Log.d("namegidyar",post_data);
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
                 Log.d("namegelya",sb.toString());
                   br.close();
                   in.close();
                   conn.disconnect();
                  // if (!sb.toString().equals(""))GetJson(table,sb.toString());//idlar gelyar tazelemeli
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
       updater.start();
           }

   }

