package synchedapps.tmbazar.online_market.items;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

import synchedapps.tmbazar.Database.Db;

import synchedapps.tmbazar.R;
import synchedapps.tmbazar.online_market.Constants;
import synchedapps.tmbazar.online_market.network.Api;


public class show_details extends AppCompatActivity implements ViewPagerEx.OnPageChangeListener {
    TextView content,price,item_name;
    ViewPager mPager;
    Button call;
    Menu menu;
    MenuItem item;
    String loved;
    Db db;
    String id;
    String skidka; Intent i;
    public static ArrayList<String > images;
    Toolbar toolbar;
    SliderLayout mDemoSlider;
    viewpager_adapter v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_details2);
        toolbar=(Toolbar)findViewById(R.id.toolbar_details);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Constants.shop_name);
        item_name=(TextView)findViewById(R.id.item_name);
        content = (TextView) findViewById(R.id.Content);
       mPager = (ViewPager) findViewById(R.id.viewpager_detail);
        price = (TextView) findViewById(R.id.item_price);
        call = (Button) findViewById(R.id.call_btn);
        Typeface tp= Typeface.createFromAsset(getAssets(),"fonts/Panton.otf");
        Typeface tp1= Typeface.createFromAsset(getAssets(),"fonts/Panton-SemiBold.otf");;
        content.setTypeface(tp);
        item_name.setTypeface(tp1);
        price.setTypeface(tp1);
    //    mDemoSlider=(SliderLayout)findViewById(R.id.slider_show);
      i=getIntent();
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:" + i.getStringExtra("number")));
                startActivity(call);
            }
        });
        images=new ArrayList<>();
        images.add(i.getStringExtra("image1"));
        images.add(i.getStringExtra("image2"));
        images.add(i.getStringExtra("image3"));
        id=i.getStringExtra("id");
v=new viewpager_adapter(show_details.this, images);
        Log.d("imagesize",""+images.size()+"image2/"+images.get(1));
 mPager.setAdapter(v);
  CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.small_indicator_realtor_show_details);
     indicator.setViewPager(mPager);
        item_name.setText(i.getStringExtra("name"));
    content.setText(i.getStringExtra("content"));
        price.setText(i.getStringExtra("price"));
        skidka=i.getStringExtra("skidka");
      //  daimaija();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == android.R.id.home) {
           // mDemoSlider.removeAllViews();
           onBackPressed();

        }

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
     //   mDemoSlider.removeAllViews();
        images.clear();
        v.notif(images);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;

        return true;
    }
    void daimaija(){
        for(final String name : images){
            DefaultSliderView textSliderView = new DefaultSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .image("http://"+ Api.url+"images/"+name)
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {

                            Intent i=new Intent(show_details.this,reklam_photo.class);
                            startActivity(i);
                        }
                    });

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(10000);
        mDemoSlider.addOnPageChangeListener(this);




    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
