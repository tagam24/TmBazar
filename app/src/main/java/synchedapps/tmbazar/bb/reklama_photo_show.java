package synchedapps.tmbazar.bb;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import synchedapps.tmbazar.R;
import synchedapps.tmbazar.ViewPagers.images_show_adapter;
import synchedapps.tmbazar.adapters.status_beylekiler_show_details;
import synchedapps.tmbazar.bb.Realtor.realtor_show_details;
import synchedapps.tmbazar.bb.Realtor.small_realtor_show_details;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

public class reklama_photo_show extends AppCompatActivity {
    private static ViewPager mPager;
    Menu menu;
    String fr;
    private static int currentPage = 0;
    private static final Integer[] XMEN= {R.drawable.footbal2,R.drawable.beylekiler2,R.drawable.home2,R.drawable.cafe,R.drawable.car1};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reklama_photo_show);
        Intent i=getIntent();
        fr=i.getStringExtra("table");
        Log.d("namegelyartable",fr);
        init();

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_photo_show_reklama);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }

    private void init() {
        for(int i=0;i<XMEN.length;i++)
            XMENArray.add(XMEN[i]);
        mPager = (ViewPager) findViewById(R.id.viewpager_for_photo_show_reklama);
       if(fr.equals("cars"))if( Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) mPager.setAdapter(new images_show_adapter(ShowDetailsadds.images,getBaseContext())); else
           mPager.setAdapter(new images_show_adapter(small_car_show_details.images,getBaseContext()));

        if(fr.equals("home")||fr.equals("beylekiler")||fr.equals("likedbeylekiler")||fr.equals("likedhome"))if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) mPager.setAdapter(new images_show_adapter(realtor_show_details.images,getBaseContext()));
        else  mPager.setAdapter(new images_show_adapter(small_realtor_show_details.images,getBaseContext()));
        if(fr.equals("vip"))mPager.setAdapter(new images_show_adapter(vip_show_details.images,getBaseContext()));
        if(fr.equals("myAdds"))mPager.setAdapter(new images_show_adapter(status_car_show_details.images,getBaseContext()));
        if(fr.equals("0"))mPager.setAdapter(new images_show_adapter(status_beylekiler_show_details.images,getBaseContext()));
       CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator_photo_show_reklama);
        indicator.setViewPager(mPager);




     ///   Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.photo_show_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == android.R.id.home) {
           finish();
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
