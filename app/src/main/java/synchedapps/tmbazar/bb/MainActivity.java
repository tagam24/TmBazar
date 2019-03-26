package synchedapps.tmbazar.bb;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.Duzgun;
import synchedapps.tmbazar.Network.Connector;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.ViewPagers.TablayoutViewPagerAdapter;
import synchedapps.tmbazar.bb.Beylekiler.Bildiris_gos_Navigation.bildiris_gos_navidation;
import synchedapps.tmbazar.bb.Beylekiler.beylekiler_tab;
import synchedapps.tmbazar.bb.Realtor.realtor_tab;
import synchedapps.tmbazar.bb.halanlarym_reklama.halanlarym_tab;
import synchedapps.tmbazar.chat.Mainchat;
import synchedapps.tmbazar.dil;
import synchedapps.tmbazar.fragment.fragment_adds;
import synchedapps.tmbazar.fragment.fragment_vip;
import synchedapps.tmbazar.notificaton;
import synchedapps.tmbazar.online_market.cities;
import synchedapps.tmbazar.online_market.post_tab.post_tabmarket;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.karan.churi.PermissionManager.PermissionManager;

import java.util.ArrayList;

import static synchedapps.tmbazar.notificaton.setBadge;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    PermissionManager permission;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TabLayout tabLayout;
    ViewPager viewPager;
    public static int tabs_number_lenta, tabs_number_main = 0;
    Db db;
    public static Context ctx;
    public static String name_toolbar;
    public static Handler progress;
    ProgressDialog loading;
    public static boolean conn = true;
    static byte crt = 0;
    static int creation = 0;
    public static boolean flazhok = true;
    dil d = new dil();
    public static Handler not = new Handler();
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    MenuItem i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_draw1);
        ctx = this;
        Log.d("MainOncreate", "  ");
        db = new Db(getBaseContext());
    //  FirebaseMessaging.getInstance().subscribeToTopic("test");
        //    FirebaseInstanceId.getInstance();
        Log.d("token1",""+ FirebaseInstanceId.getInstance().getToken());
        SQLiteDatabase dbs = db.getWritableDatabase();
        //db.onUpgrade(dbs, 1, 1);
        dil di = new dil();
        di.set_text();
        if(db.get_dil().equals(""))db.insert_tm();
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        ;
//Log.d("phone imei",telephonyManager.getDeviceId());

        // stopService(new Intent(this,notificaton.class));
        // startService(new Intent(this,notificaton.class));
        toolbar = (Toolbar) findViewById(R.id.toolbar_esasy_sahypa);
        setSupportActionBar(toolbar);
        db.chatMsgdeleter();
       // add();
        permission = new PermissionManager() {
        };
        permission.checkAndRequestPermissions(this);
        getSupportActionBar().setTitle(dil.tm_toolbar_title_esasy_sahypa);
        toolbar.inflateMenu(R.menu.menu_main1);
        drawerLayout = (DrawerLayout) findViewById(R.id.nav_draw_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(toogle);
        toogle.syncState();
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        tabLayout = (TabLayout) findViewById(R.id.tablayout_esasy_sahypa);
        viewPager = (ViewPager) findViewById(R.id.ViewPager_esasy_sahypa);
        final TablayoutViewPagerAdapter adapter = new TablayoutViewPagerAdapter(getSupportFragmentManager());
    /*    progress = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1)
                   loading = ProgressDialog.show(MainActivity.this, "Yüklenyär...", "Garaşyň...");
                if (msg.what == 2) {*/
//                 if (creation == 0) loading.dismiss();
                    creation = 1;
                    db.date_checker("bannerlenta");
                    adapter.AddFragment(new fragment_vip(), "VIP");
                    adapter.AddFragment(new fragment_adds(), dil.tm_bildirisler);
                    viewPager.setAdapter(adapter);
                    tabLayout.setupWithViewPager(viewPager);
                    TabLayout.Tab tab = tabLayout.getTabAt(tabs_number_main);
                    tab.select();
                    chek();
                    //   if(conn==false) Toast.makeText(MainActivity.this,"Internet yok",Toast.LENGTH_LONG).show();
                    Log.d("con", "" + conn);
          /*    //  }
            }
        };*/

    //    db.add_deleter("home");
  //      db.add_deleter("beylekiler");
    //    db.add_deleter("futbol");
      //  db.add_deleter("tasinlikler");
     //   db.add_deleter("gymmatly");
        //db.add_deleter("likedAdds");
     //   db.add_deleter("likedhome");
     //   db.add_deleter("likedbeylekiler");
     //   db.add_deleter("liked_gyzykly");
        /*if (creation == 0) {
            progress.sendEmptyMessage(1);
            new Connector();
        } else {
            progress.sendEmptyMessage(2);
        }*/

        db.getMAxs("home");
        Menu menu = navigationView.getMenu();
        i1 = (MenuItem) menu.findItem(R.id.nav1_menu_1);
        i1.setTitle(dil.tm_nav_Esasy_sahypa);
        i2 = (MenuItem) menu.findItem(R.id.nav1_menu_2);
        i2.setTitle(dil.tm_nav_avtoulaglar);
        i3 = (MenuItem) menu.findItem(R.id.nav1_menu_3);
        i3.setTitle(dil.tm_nav_realtor);
        i4 = (MenuItem) menu.findItem(R.id.nav1_menu_4);
        i4.setTitle(dil.tm_nav_beylekiler);
        i5 = (MenuItem) menu.findItem(R.id.nav1_menu_5);
        i5.setTitle(dil.tm_nav_futbal);
        i6 = (MenuItem) menu.findItem(R.id.nav1_menu_6);
        i6.setTitle(dil.tm_nav_tasinlekler);
        i7 = (MenuItem) menu.findItem(R.id.nav1_menu_7);
        i7.setTitle(dil.tm_nav_gyzyldan_gymmatly);
        i8 = (MenuItem) menu.findItem(R.id.nav1_menu_8);
        i8.setTitle(dil.tm_navbildiri_gos);
        i9 = (MenuItem) menu.findItem(R.id.nav1_menu_9);
        i9.setTitle(dil.tm_nav_dili);
        i10 = (MenuItem) menu.findItem(R.id.nav1_menu_10);
        i10.setTitle(dil.tm_nav_duzgunnama);
        i11 = (MenuItem) menu.findItem(R.id.nav1_menu_11);
        i11.setTitle(dil.tm_nav_habarlas);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case (R.id.nav1_menu_1):

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                    case (R.id.nav1_menu_2):
                        Intent intent5 = new Intent(getApplicationContext(), car_tab.class);
                        startActivity(intent5);
                        break;
                    case (R.id.nav1_menu_3):
                        Intent intent9 = new Intent(getApplicationContext(), realtor_tab.class);
                        startActivity(intent9);
                        break;
                    case (R.id.nav1_menu_4):
                        Intent intent10 = new Intent(getApplicationContext(), beylekiler_tab.class);
                        startActivity(intent10);
                        break;
                    case (R.id.nav1_menu_5):
                        Intent intent2 = new Intent(getApplicationContext(), lenta_tab.class);
                        tabs_number_lenta=0;
                        startActivity(intent2);
                        break;
                    case (R.id.nav1_menu_6):
                        Intent intent3 = new Intent(getApplicationContext(), lenta_tab.class);
                        tabs_number_lenta=1;
                        startActivity(intent3);
                        break;
                    case (R.id.nav1_menu_7):
                        Intent intent4 = new Intent(getApplicationContext(), post_tabmarket.class);
                        startActivity(intent4);
                        break;
                    case (R.id.nav1_menu_8):
                        Intent intent6 = new Intent(getApplicationContext(), bildiris_gos_navidation.class);
                        startActivity(intent6);
                        break;
                    case (R.id.nav1_menu_9):
                        d.dil(MainActivity.ctx);
                        break;
                    case (R.id.nav1_menu_10):
                        Intent intent12 = new Intent(getApplicationContext(), Duzgun.class);
                        startActivity(intent12);
                        break;
                    case (R.id.nav1_menu_11):
                        Intent intent8 = new Intent(getApplicationContext(), Mainchat.class);
                        startActivity(intent8);
                        break;
                }

                return false;
            }
        });
        Menu menu1=navigationView.getMenu();
        MenuItem item=menu1.getItem(7);
        SpannableString spanString = new SpannableString(item.getTitle().toString());
        spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.red)), 0, spanString.length(), 0);
        item.setTitle(spanString);
    }

    @Override
    protected void onStart() {
try      {  startService(new Intent(this, notificaton.class));}catch (IllegalStateException ss){}

        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.toString().equals("LIKE")) {
            Intent intent = new Intent(getApplicationContext(), halanlarym_tab.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            moveTaskToBack(true);
        }
    }

    @Override
    protected void onPostResume() {
        Log.d("resumed", "resume");
        super.onPostResume();
    }

   // @Override
 //   protected void onResume() {
        // addBadge(this,30);
     //   setBadge(getBaseContext(), 0);
       // try {
      //      Badges.removeBadge(MainActivity.ctx);
     //       // Alternative way
      //      Badges.setBadge(MainActivity.ctx, 0);
     //   } catch (BadgesNotSupportedException badgesNotSupportedException) {
  //          Log.d("asdsds", badgesNotSupportedException.getMessage());
//        }
 //       //  stopService(new Intent(this,notificaton.class));
      //  try {
 //           fragment_adds.c.sendEmptyMessage(1);
  //      } catch (NullPointerException ess) {
    //    }
//        super.onResume();
  //  }

    @Override
    protected void onStop() {

        //  a8ddBadge(this,30);
        super.onStop();
     //   startService(new Intent(this, notificaton.class));

    }


    @Override
    protected void onDestroy() {
        //  startService(new Intent(this,notificaton.class));
        super.onDestroy();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        // permission.checkResult(requestCode,permissions, grantResults);
        //To get Granted Permission and Denied Permission

        Log.d("permission", "gelyar");

        ArrayList<String> granted = permission.getStatus().get(0).granted;
        ArrayList<String> denied = permission.getStatus().get(0).denied;
        for (String s : granted) {
            Log.d("grantd", s);
        }
        for (String s : denied) {
            Log.d("denied", s);
        }
    }

    public void chek() {
        boolean permission;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
         //  permission = Settings.System.canWrite(this);
            permission=true;
            // setBadge(this,3);Log.d("permission1","granted");
        } else {
            setBadge(this, 3);
            Log.d("permission2", "granted");
           // permission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_SETTINGS) == PackageManager.PERMISSION_GRANTED;
            permission=true;
        }
        if (permission) {
            setBadge(this, 3);
            Log.d("permission", "granted");
        } else {
            Log.d("permission", "asking");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + this.getPackageName()));
                this.startActivityForResult(intent, 1);
            } else {
                Log.d("permission3", "asking");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_SETTINGS}, 1);
            }
        }
    }

}
