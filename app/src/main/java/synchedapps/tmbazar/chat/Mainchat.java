package synchedapps.tmbazar.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.Network.network;
import synchedapps.tmbazar.R;
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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Mainchat extends AppCompatActivity implements  SwipeRefreshLayout.OnRefreshListener{
Db db;
    int limit=30;
    SwipeRefreshLayout swipe;
    ArrayList<ChatAppMsgDTO> msgDtoList;
    boolean b=true;
    ChatAppMsgAdapter chatAppMsgAdapter;
    Handler notifer;
    Thread getmsg;
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        return super.onKeyUp(keyCode, event);
    }
    int newMsgPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainchat);
         swipe=(SwipeRefreshLayout)findViewById(R.id.swipe_chat);
        final RecyclerView msgRecyclerView = (RecyclerView) findViewById(R.id.recycle_for_chat);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_chat_sahypa);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("HABARLAŞ");
      db=new Db(MainActivity.ctx);
        if (db.getmyID() == null || db.getmyID().equals(""))Register();
          swipe.setOnRefreshListener(this);
        final EditText msgInputText = (EditText) findViewById(R.id.chat_edit_text);
        ImageView image_send = (ImageView) findViewById(R.id.send_image_chat);
        // Set RecyclerView layout manager.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(linearLayoutManager);

        // Create the initial data list.
         msgDtoList = new ArrayList<>();
        ChatAppMsgDTO msgDto = new ChatAppMsgDTO();
   //     msgDtoList.add(msgDto);

        // Create the data adapter with above data list.
         chatAppMsgAdapter = new ChatAppMsgAdapter(msgDtoList);

        // Set data adapter to RecyclerView.
        msgRecyclerView.setAdapter(chatAppMsgAdapter);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
         notifer=new Handler(){
             @Override
             public void handleMessage(Message msg) {
                if(msg.what==1){
                    chatAppMsgAdapter.notifier(msgDtoList);


                  if(msgDtoList.size()>1)   newMsgPosition = msgDtoList.size() - 1;
                    // Notify recycler view insert one new data.
                    chatAppMsgAdapter.notifyItemInserted(newMsgPosition);

                    // Scroll RecyclerView to the last message.
                    msgRecyclerView.scrollToPosition(newMsgPosition);
                }
             }
         };
        get_chat();
        image_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msgContent = msgInputText.getText().toString();
                if (!TextUtils.isEmpty(msgContent)) {
                    send_message(msgContent);
                    SimpleDateFormat day=new SimpleDateFormat("hh:mm");
                    Date d=new Date();
                    String date=day.format(d);
                    db.insert_chat("0","0",msgContent,date);
                    // Add a new sent message to the list.
              //     get_chat();
                    ChatAppMsgDTO c=new ChatAppMsgDTO();
                    c.setTime(date);
                    c.setMsgContent(msgContent);
                    c.setMsgType("0");
                    msgDtoList.add(c);
                    notifer.sendEmptyMessage(1);
                    if(msgDtoList.size()>1)  newMsgPosition = msgDtoList.size() - 1;
                    // Notify recycler view insert one new data.
                    chatAppMsgAdapter.notifyItemInserted(newMsgPosition);

                    // Scroll RecyclerView to the last message.
                    msgRecyclerView.scrollToPosition(newMsgPosition);

                    // Empty the input edit text box.
                    msgInputText.setText("");
                }

            }
        });
       getmsg=new Thread(new Runnable() {
            @Override
            public void run() {
                while(b){
                    get_message();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
 if(!db.getmyID().equals(""))   getmsg.start();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == android.R.id.home) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

        return true;
    }
    public void get_chat(){
        Log.d("getCHat","dat");
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                msgDtoList=db.get_chat(limit);
                notifer.sendEmptyMessage(1);
                Thread.currentThread().interrupt();
            }
        });
        t.start();

        Log.d("isswip",""+swipe.isRefreshing());
        swipe.setRefreshing(false);


          Log.d("getChatSize",""+msgDtoList.size());
    }

    @Override
    public void onRefresh() {
        swipe.setRefreshing(true);
        limit+=30;
        get_chat();
    }
    public void send_message(String content){
     final String myid=db.getmyID();
     final String c=content;
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Date d=new Date();

                    SimpleDateFormat day=new SimpleDateFormat("hh:mm");
                    SimpleDateFormat ss=new SimpleDateFormat("ss");
                    String date=day.format(d);
                    //   URL url=new URL("http://ynamly.biz/reklama3/adds/insert_add.php");
                    URL url=new URL("http://"+ network.address+"/Reklama/chat/insert_message.php");
                    HttpURLConnection con=(HttpURLConnection)url.openConnection();
                    con.setDoOutput(true);
                    con.setRequestMethod("POST");
                    OutputStream outputStream = con.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("mine", "UTF-8") + "=" + URLEncoder.encode(myid, "UTF-8")+
                            "&"+URLEncoder.encode("reciever", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8")+
                            "&"+URLEncoder.encode("content", "UTF-8") + "=" + URLEncoder.encode(c, "UTF-8")+
                            "&"+URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream in;
                    in = con.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String s="";
                    String name="";
                    while((s=br.readLine())!=null){
                        name=name+s;}
                    Log.d("namegelya",name);
                    br.close();
                    in.close();
                    con.disconnect();
                    Thread.currentThread().interrupt();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();


    }
    public void get_message(){
        final String myid=db.getmyID();
        Log.d("myIDDD",myid);
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Date d=new Date();

                    SimpleDateFormat day=new SimpleDateFormat("mm:HH,dd.MM");
                    SimpleDateFormat ss=new SimpleDateFormat("ss");
                    String date=day.format(d);
                    String maxID=db.getMAxMessageID();
                    Log.d("nameID",maxID);
                    //   URL url=new URL("http://ynamly.biz/reklama3/adds/insert_add.php");
                    URL url=new URL("http://"+network.address+"/Reklama/chat/get_message.php");
                    HttpURLConnection con=(HttpURLConnection)url.openConnection();
                    con.setDoOutput(true);
                    con.setRequestMethod("POST");
                    OutputStream outputStream = con.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("mineID", "UTF-8") + "=" + URLEncoder.encode(myid, "UTF-8")+"&"+
                            URLEncoder.encode("maxID", "UTF-8") + "=" + URLEncoder.encode(maxID, "UTF-8") ;
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream in;
                    in = con.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    StringBuilder sb = new StringBuilder();
                    String row = "";
                    while ((row = br.readLine()) != null) {
                        sb.append(row + "\n");}
                    Log.d("gelyansms",sb.toString());
                    br.close();
                    in.close();
                    con.disconnect();
                    if(!sb.toString().equals(""))GetJSonMessage(sb.toString());
                    Thread.currentThread().interrupt();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

    }    ChatAppMsgDTO c;
    public void GetJSonMessage(final String s){
        Log.d("GetJSon","  ");

        Thread json=new Thread(new Runnable() {
            @Override
            public void run() {
                try{//bashda data basadan hemme informasiyasyny alyas
                    JSONObject jsonObject=new JSONObject(s);
                    JSONArray jsonArray=jsonObject.getJSONArray("result");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data=jsonArray.getJSONObject(i);
                      db.insert_chat(data.optString("id"),"1",data.optString("content"),data.optString("date"));
                        c=new ChatAppMsgDTO();
                        c.setTime(data.optString("date"));
                        c.setMsgContent(data.optString("content"));
                        c.setMsgType(data.optString("1"));
                       // msgDtoList.add(c);
                   get_chat();
                        //notid();
                        //chatAppMsgAdapter.notifier(msgDtoList);

                    }

                    Thread.currentThread().interrupt();
                } catch (JSONException e) {
                    e.printStackTrace();
                }}
        });
        json.start();
    }
public void notid(){
    chatAppMsgAdapter.notifier(msgDtoList);

}
    @Override
    public void onBackPressed() {
        b=false;
        getmsg.interrupt();
        finish();

    }
    public void Register() {
        Thread t = new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                try {
                    Date d = new Date();
                    SimpleDateFormat ff = new SimpleDateFormat("hh:mm");
                    SimpleDateFormat day = new SimpleDateFormat("dd.MM.yyyy");
                    SimpleDateFormat ss = new SimpleDateFormat("ss");
//                    String usernam = username.getText().toString();
                    String date = day.format(d);
                    String time = ff.format(d);
                  // TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
//                    Log.d("tagil","xx"+telephonyManager.getImei());

                    //      String key = Integer.toHexString(Integer.parseInt(number1) + Integer.parseInt(ss.format(d))) + ss.format(d);
                    //   URL url=new URL("http://ynamly.biz/reklama3/adds/insert_add.php");
                    URL url = new URL("http://"+network.address+"/Reklama/chat/insert_user.php");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setDoOutput(true);
                    con.setRequestMethod("POST");
                    OutputStream outputStream = con.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("number", "UTF-8") + "=" + URLEncoder.encode("ASD", "UTF-8") ;
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream in;
                    in = con.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String s = "";
                    String id = "";
                    while ((s = br.readLine()) != null) {
                        id = id + s;
                    }
                    Log.d("namegelya", "asds"+id);
                    db.insert_myID(id);
                    br.close();
                    in.close();
                    con.disconnect();
                    Thread.currentThread().interrupt();
                    finish();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

}
