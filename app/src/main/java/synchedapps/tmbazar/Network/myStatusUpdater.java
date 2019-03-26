package synchedapps.tmbazar.Network;

import android.util.Log;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.bb.Beylekiler.Bildiris_gos_Navigation.fragment_bildiris_gosmak1;
import synchedapps.tmbazar.bb.Beylekiler.Bildiris_gos_Navigation.fragment_bildiris_gosmak2;
import synchedapps.tmbazar.bb.Beylekiler.Bildiris_gos_Navigation.fragment_bildiris_gosmak3;
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

/**
 * Created by User on 12.07.2018.
 */

public class myStatusUpdater {
    Thread updateStatus;
    Db db=new Db(MainActivity.ctx);
    public void myStatus(final String table,final String myaddtable,final String id){
        Log.d("idGitmeli",id);
    updateStatus=new Thread(new Runnable() {
        @Override
        public void run() {

        try{
            //  URL url=new URL("http://ynamly.biz/Reklama3/adds/get_image.php");
            URL url=new URL("http://"+network.address+"/Reklama/adds/myStatusCheker.php");
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Cookie", "__test=cef9bc49784623b7fc983b799bfb4b89; Friday, January 1, 2038 at 2:55:55 AM; path=/");
            conn.setRequestMethod("POST");
            conn.connect();
            OutputStream outputStream = conn.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("table", "UTF-8") + "=" + URLEncoder.encode(table, "UTF-8")+"&"+URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
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
            Log.d("ID",sb.toString());
            br.close();
            in.close();
            conn.disconnect();
            if (!sb.toString().equals("")){ GetJsonStatuses(myaddtable,sb.toString()); Log.d("dbUpdateStatus","gitdi");} //idlar gelyar tazelemeli

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
      updateStatus.start();

    }
    public void GetJsonStatuses(final String table,final String s){
        Log.d("GetJSon","  ");
        Thread json=new Thread(new Runnable() {
            @Override
            public void run() {
                try{//bashda data basadan hemme informasiyasyny alyas
                    JSONObject jsonObject=new JSONObject(s);
                    JSONArray jsonArray=jsonObject.getJSONArray("result");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data=jsonArray.getJSONObject(i);
                        db.update_status(table,data.optString("status"),data.optString("id"));
                    }
                    if(table.equals("myadds")) fragment_bildiris_gosmak1.get_local();
                    if(table.equals("myAddsrealtor")) fragment_bildiris_gosmak2.get_local();
                    if (table.equals("myAddsbeylekiler")){
                        fragment_bildiris_gosmak3.get_local();}
                    Thread.currentThread().interrupt();
                } catch (JSONException e) {
                    e.printStackTrace();
                }}
        });
        json.start();
    }
}
