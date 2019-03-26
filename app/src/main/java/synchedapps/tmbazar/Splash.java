package synchedapps.tmbazar;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.bb.MainActivity;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class Splash extends AwesomeSplash {
Db db;
    //DO NOT OVERRIDE onCreate()!
    //if you need to start some services do it in initSplash()!

    @Override
    public void initSplash(ConfigSplash configSplash) {
 db=new Db(getApplicationContext());
     db.deletedb();
			/* you don't have to override every property */

        //Customize Circular Reveal

        configSplash.setBackgroundColor(R.color.colorPrimary); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(1500); //int ms
        configSplash.setRevealFlagX(10);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(20); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.drawable.launc1);
        configSplash.setAnimLogoSplashDuration(2000);//int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.Bounce); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)


        //Customize Path
      //  configSplash.setPathSplash(DROID_LOGO); //set path String
        configSplash.setOriginalHeight(200); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(200); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(1000);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.colorAccent); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(2000);
        configSplash.setPathSplashFillColor(R.color.colorPrimaryDark); //path object filling color


        //Customize Title
        configSplash.setTitleSplash("TMBAZAR"); //change your app name here
        configSplash.setTitleTextColor(R.color.white);
        configSplash.setTitleTextSize(30f);
        configSplash.setAnimTitleDuration(1500);
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);
        configSplash.setTitleFont("fonts/FFF_Tusj.ttf"); //provide string to your font located in assets/fonts/

    }
    @Override
    public void animationsFinished() {
       // Toast.makeText(Splash.this, "Go to MainActivity now!!", Toast.LENGTH_SHORT).show();
        //transit to another activity here
        //or do whatever you want

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE); NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()){
            Intent i=new Intent(this, MainActivity.class);
            startActivity(i);}else Toast.makeText(getBaseContext(),"Internet ýok",Toast.LENGTH_LONG);

    }
}