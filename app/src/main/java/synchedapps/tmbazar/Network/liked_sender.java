package synchedapps.tmbazar.Network;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by User on 24.06.2018.
 */

public class liked_sender {
    Thread send;
    public void send_liked(final String table,final String type,final String id){
Log.d("geldisendlike",table+type+id);
        send=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    URL url=new URL("http://"+network.address+"/Reklama/lenta/watched.php");
                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setRequestMethod("POST");
                    conn.connect();
                    OutputStream outputStream = conn.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8")+"&"+
                            URLEncoder.encode("table", "UTF-8") + "=" + URLEncoder.encode(table, "UTF-8")+"&"
                            +URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8");
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
                    Log.d("get_lentda",sb.toString());
                    br.close();
                    in.close();
                    conn.disconnect();
                    Thread.currentThread().interrupt();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        send.start();


    }
}
