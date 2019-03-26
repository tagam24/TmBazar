package synchedapps.tmbazar.Network;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import synchedapps.tmbazar.bb.MainActivity;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by User on 22.06.2018.
 */


public class Connector {
    public boolean connection= true;
 static byte crt=0;
    public Connector(){
        Date d=new Date();
        final SimpleDateFormat ff=new SimpleDateFormat("ss");
        final  int  sek= Integer.parseInt(ff.format(d));
        Log.d("ester",""+sek);

        Thread test=new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                while(connection){
                    int s=Integer.parseInt(ff.format(new Date()));
                    if(sek>s)s=s+60;
                  if (s-sek>5) { connection=false;}
                }
           }
                finally {
                   if(crt==0) {
                       MainActivity.progress.sendEmptyMessage(2); crt=1;}
                    Thread.currentThread().interrupt();}
            }

        });
        test.start();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {hostAvailable("www.google.com", 80);}}
    public void hostAvailable(final String host, final int port) {
        Thread get=new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                    try (Socket socket = new Socket()) {
                    socket.connect(new InetSocketAddress(host, port));
                     MainActivity.conn=true;
                        Log.d("Internet","connect");
                   if(crt==0) {MainActivity.progress.sendEmptyMessage(2); crt=1;}
                    Thread.currentThread().interrupt();
                } catch (IOException e) {
                    System.out.println(e);
                    Thread.currentThread().interrupt();
                }finally {

                }
            }});
      get.start();
    }

}
