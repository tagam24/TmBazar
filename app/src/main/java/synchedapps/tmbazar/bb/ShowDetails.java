package synchedapps.tmbazar.bb;

import android.support.v7.app.AppCompatActivity;

public class ShowDetails extends AppCompatActivity {
    /*Toolbar toolbar;
    String id="",category="",price="",year="",time="",model="",
            kuzow="",probeg="",color="",mator="",karopka="",
            location="",kredit="",obmen="",description="",
            number="",Watched="",watch="",date="",vip="",satyldy="",image="";
    TextView name1,location1,description1,price1,image1,watched,date1,time1,nomer;
    ViewPager viewPager;
    FloatingActionButton fab;
    FloatingActionButton like;
    ArrayList<ImagesClicker> images;
    AppBarLayout appBarLayout;
    Db db;
    Liked_getter lg=new Liked_getter();
     image_swipe_adapter_vip_show imageSwipeAdapterVipShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.vipshow);
        // else setContentView(R.layout.activity_small_lenta_show_details);

        if(getSupportActionBar()!=null)
        {getSupportActionBar().setHomeButtonEnabled(true);getSupportActionBar().setDisplayHomeAsUpEnabled(true);}
        db=new Db(this);
        Gui();
        init();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent=new Intent(Intent.ACTION_DIAL);
                startActivity(intent);}});
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.insert_Likedadds("liked_adds",id,category,model,price,description,location,vip,time,number,Watched,date,year,kredit,
                        obmen,satyldy,probeg,kuzow,color,karopka,mator,image);
                Snackbar.make(view, "like basyldy", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });



    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == android.R.id.home) {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }

        return true;
    }
 void Gui(){
     toolbar=(Toolbar)findViewById(R.id.toolbar_vip_show) ;
     name1=(TextView)findViewById(R.id.vip_show_name_title);
     location1=(TextView)findViewById(R.id.vip_show_place);
     description1=(TextView)findViewById(R.id.mazmuny_vip_show);
     watched=(TextView) findViewById(R.id.watch_vip_show);
     date1=(TextView)findViewById(R.id.sene_vip_show) ;
     time1=(TextView)findViewById(R.id.sagat_vip_show) ;
     nomer=(TextView)findViewById(R.id.vip_show_phone_number);
     viewPager=(ViewPager)findViewById(R.id.pager_vip_show);
     fab = (FloatingActionButton) findViewById(R.id.fab);
     like = (FloatingActionButton) findViewById(R.id.yurek_button);
     setSupportActionBar(toolbar);

     getSupportActionBar().setDisplayShowHomeEnabled(true);
     getSupportActionBar().setDisplayHomeAsUpEnabled(true);
     getSupportActionBar().setDisplayShowTitleEnabled(true);

     appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_vip);
 }
 void init(){
     if(MainActivity.conn) lg.getter("vip",id);
     Intent intent=getIntent();
     id=intent.getStringExtra("id");


     imageSwipeAdapterVipShow=new image_swipe_adapter_vip_show(images,ShowDetails.this);
     viewPager.setAdapter(imageSwipeAdapterVipShow);
     name1.setText(m.getName());
     description1.setText(m.getDescription());
     nomer.setText(m.getNumber());
     time1.setText(m.getTime());
     date1.setText(m.getDate());
     getSupportActionBar().setTitle(m.getName());
     if(MainActivity.conn) {if(m.getWatch().equals("0")){
         liked_sender l=new liked_sender();
         l.send_liked("vip","watched",id);
         int k=Integer.parseInt(m.getWatched())+1;
         db.lwatched("vip","watched",id,k);
         watched.setText(""+k);}
     }
     if (!m.getWatch().equals("0")) watched.setText(m.getWatched());

 }
*/
}
