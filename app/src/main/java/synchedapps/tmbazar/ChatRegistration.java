package synchedapps.tmbazar;/*package com.example.user.prohe;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.prohe.Database.Db;
import com.example.user.prohe.bb.MainActivity;
import com.example.user.prohe.chat.Mainchat;

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
import java.util.Date;

public class ChatRegistration extends AppCompatActivity {
    EditText code, number, username;
    Button send;
    static int i = 1;
    String number1, kod;
    Db db;
    TelephonyManager telephonyManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new Db(MainActivity.ctx);
        Log.d("dbmyid", db.getmyID());
        if (db.getmyID() == null || db.getmyID().equals("")) {
            setContentView(R.layout.register);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            final LinearLayout linearLayout_1 = (LinearLayout) findViewById(R.id.layout_chat_register_kod);
            linearLayout_1.setVisibility(View.INVISIBLE);

            Register();

        } else {
            Intent intent = new Intent(this, Mainchat.class);
            startActivity(intent);
        }

    }

    public void send_sms(String number) {
        Date d = new Date();
        SimpleDateFormat ff = new SimpleDateFormat("ss");
        ff.format(d);
        Log.d("d", "girdi");
        int k = Integer.parseInt(number) + Integer.parseInt(ff.format(d));
        ;
        Log.d("hexa", Integer.toHexString(k));
        kod = Integer.toHexString(k);
        kod = "1";
        if (kod.length() > 4) kod = kod.substring(0, 4);
        Log.d("code", kod);
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage( number, null, kod, null, null);
        Toast.makeText(getApplicationContext(), "SMS nomeriňize ugradyldy smsa garaşyň!.",
                Toast.LENGTH_LONG).show();


    }

    protected void verifier() {
        String koder = code.getText().toString();
        if (koder.equals(kod)) {
            Toast.makeText(getBaseContext(), "Bildirish goshuldy", Toast.LENGTH_LONG).show();
            i = 3;
            Register();
            Intent intent = new Intent(this, Mainchat.class);
            intent.putExtra("from", "0");
            startActivity(intent);
        } else {
            number.setEnabled(true);
            send.setText("sms ugrat");
            i = 1;
            Toast.makeText(getBaseContext(), "Girizilen Kod yalnysh tazelden girizing!", Toast.LENGTH_SHORT).show();
        }
    }

    protected void sendSMSMessage() {

        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)) {
            if ((ActivityCompat.shouldShowRequestPermissionRationale(ChatRegistration.this,
                    Manifest.permission.SEND_SMS))) {

            } else {
                ActivityCompat.requestPermissions(ChatRegistration.this,
                        new String[]{Manifest.permission.SEND_SMS},
                        1);
            }
        } else {
            Log.e("Else", "Else");
            send_sms(number1);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("geldi", "geldi");
        switch (requestCode) {
            case 1: {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        send_sms(number1);
                        Log.d("sms", "send");
                    } else {
                        Toast.makeText(getApplicationContext(), "The app was not allowed to read or write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    public void Register() {
        Thread t = new Thread(new Runnable() {
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
                    telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                    Log.d("tagil","xx"+telephonyManager.getImei());

              //      String key = Integer.toHexString(Integer.parseInt(number1) + Integer.parseInt(ss.format(d))) + ss.format(d);
                    //   URL url=new URL("http://ynamly.biz/reklama3/adds/insert_add.php");
                    URL url = new URL("http://u21940xgr.ha002.t.justns.ru/reklama/chat/insert_user.php");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setDoOutput(true);
                    con.setRequestMethod("POST");
                    OutputStream outputStream = con.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("number", "UTF-8") + "=" + URLEncoder.encode(telephonyManager.getImei(), "UTF-8") ;
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
                    Intent intent = new Intent(ChatRegistration.this, Mainchat.class);
                    startActivity(intent);
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
*/