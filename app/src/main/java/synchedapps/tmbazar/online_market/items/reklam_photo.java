package synchedapps.tmbazar.online_market.items;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.viewpagerindicator.CirclePageIndicator;


import synchedapps.tmbazar.R;
import synchedapps.tmbazar.online_market.network.Api;

public class reklam_photo extends AppCompatActivity {
Toolbar p;
    ViewPager mPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reklam_photo);
        p=(Toolbar)findViewById(R.id.toolbar_photo);
        setSupportActionBar(p);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        mPager = (ViewPager) findViewById(R.id.viewpagerdetail);
        mPager.setAdapter(new viewpageradapter(reklam_photo.this, show_details.images));
        CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.small_indicator_realtor_showdetails);
        indicator.setViewPager(mPager);
        Intent i=getIntent();

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == android.R.id.home) {
            finish();
        }
        return true;
    }
}
