package synchedapps.tmbazar;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.IBinder;
import android.util.Log;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.Network.network;
import synchedapps.tmbazar.fragment.fragment_adds;
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
import java.util.List;

public class notificaton extends Service {
    Thread get;
    Db db;
    public notificaton() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
      get_not();
        db=new Db(this);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("stopped","stop");
        // get.interrupt();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public void get_not(){

    }
    String json="";
    public  void get_notify(){

    Thread get_notify;
        get_notify=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    try      { final HashMap<String,String> tables=db.get_maxTables();
                        json=new Gson().toJson(tables);
                        Log.d("idlar ",json);}catch (NullPointerException ss){}catch (SQLiteCantOpenDatabaseException ss){}

                    //     URL url=new URL("http://ynamly.biz/reklama3/adds/get_image.php");
                    URL url=new URL("http://"+ network.address+"/Reklama/adds/notif/notiftable.php");
                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setRequestProperty("Cookie", "__test=cef9bc49784623b7fc983b799bfb4b89; Friday, January 1, 2038 at 2:55:55 AM; path=/");
                    conn.setRequestMethod("POST");
                    conn.connect();
                    OutputStream outputStream = conn.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("json", "UTF-8") + "=" + URLEncoder.encode(json, "UTF-8");
                   Log.d("postdata",post_data);
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
                    Log.d("get_DataAddsnotify",sb.toString());
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
                }catch (OutOfMemoryError s){}
            }
        }) ;
        get_notify.start();
    }
 public static    int max=0;
    public void GetJsonNotif(final String s){
        Log.d("GetJSon","  ");
        Thread json=new Thread(new Runnable() {
            @Override
            public void run() {
                try{//bashda data basadan hemme informasiyasyny alyas
                    JSONObject jsonObject=new JSONObject(s);
                    JSONArray jsonArray=jsonObject.getJSONArray("result");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data=jsonArray.getJSONObject(i);
try {  max=data.optInt("A")+ data.optInt("B")+ data.optInt("C")+ data.optInt("D")+ data.optInt("E")+ data.optInt("F");
    db.insert_count_not(data.optString("A"), data.optString("B"), data.optString("C"), data.optString("D"), data.optString("E"), data.optString("F"));
}catch (SQLiteDatabaseLockedException ss){}
                    }
                 try{   fragment_adds.c.sendEmptyMessage(1);}catch (NullPointerException ss){}
                   // int badgeCount = 5;
                //    ShortcutBadger.with(getApplicationContext()).count(badgeCount); //for 1.1.3
                //    Notification.Builder n=new Notification.Builder(getApplicationContext()).setNumber(3);
//                    n.setBadgeIconType(R.drawable.add_button);
                    // MainActivity.not.sendEmptyMessage(1);
                  //  NotificationBadge badge=
                   Log.d("Max",""+max);
                    setBadge(getBaseContext(),max);
                    Thread.currentThread().interrupt();
                } catch (JSONException e) {
                    e.printStackTrace();
                }}
        });
        json.start();
    }

    public static void setBadge(Context context, int count) {


        String launcherClassName = getLauncherClassName(context);
        if (launcherClassName == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", count);
        intent.putExtra("badge_count_package_name", context.getPackageName());
        intent.putExtra("badge_count_class_name", launcherClassName);
        context.sendBroadcast(intent);
    }

    public static String getLauncherClassName(Context context) {

        PackageManager pm = context.getPackageManager();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

try{        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resolveInfos) {
            String pkgName = resolveInfo.activityInfo.applicationInfo.packageName;
            if (pkgName.equalsIgnoreCase(context.getPackageName())) {
                String className = resolveInfo.activityInfo.name;
                return className;
            }
        }} catch (RuntimeException sss){}
        return null;
    }


}
