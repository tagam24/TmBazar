package synchedapps.tmbazar.filter_model;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import synchedapps.tmbazar.R;
import synchedapps.tmbazar.bb.add_adds_cars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class add_car_model extends AppCompatActivity  implements SearchView.OnQueryTextListener, SearchView.OnCloseListener{
RecyclerView mar;
    private MenuItem searchItem;
    private SearchManager searchManager;
    private SearchView searchView;
    LinearLayoutManager linearLayoutManager;
    adapter_marka a;
    adapter_model b;
    List<Integer> logo=new ArrayList<>();
    public  static Handler s;
    public static int index=add_adds_cars.index;
    static  ArrayList<String> list;
    ArrayList<Integer> d;
    String s1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car_model);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_model_add_reklama);
        setSupportActionBar(toolbar);

   getSupportActionBar().setDisplayShowHomeEnabled(true);
 getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mar=(RecyclerView)findViewById(R.id.recycleview_model_add_marka);
        linearLayoutManager = new LinearLayoutManager(getBaseContext());
        mar.setLayoutManager(linearLayoutManager);
        Intent i=getIntent();


         s1=i.getStringExtra("name");
        Log.d("listcategory",add_adds_cars.category);

       list = new ArrayList<>();
        if(s1.equals("marka"))setTitle("Marka");else setTitle("Model");
       
  if(s1.equals("marka")) { set_drawable(); list.addAll(Arrays.asList(getResources().getStringArray(R.array.car_marka)));a=new adapter_marka(d,list,this);mar.setAdapter(a);} else set_model();


        s=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==1){
                    finish();
                }
            }
        };
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_car_menu, menu);
        searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
        // searchView.setSearchableInfo
        //     (searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        searchView.requestFocus();
        return true;}

    @Override
    public boolean onClose() {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d("new ",newText);
       if(s1.equals("marka"))a.filterData(newText);
        else b.filterData(newText);
        return false;
    }
    public void set_drawable(){
        d=new ArrayList<>();
     d.add(0,R.drawable.x_alfa);
        d.add(1,R.drawable.x_audi);
        d.add(2,R.drawable.bmw);
        d.add(3,R.drawable.x_buick);
        d.add(4,R.drawable.x_cmc);
        d.add(5,R.drawable.x_caterpil);
        d.add(6,R.drawable.x_changan);
        d.add(7,R.drawable.x_chevrol);
        d.add(8,R.drawable.x_crys);
        d.add(9,R.drawable.x_citroen);
        d.add(10,R.drawable.x_cmc1);
        d.add(11,R.drawable.nomark);
        d.add(12,R.drawable.x_daewoo);
        d.add(13,R.drawable.x_daf);
        d.add(14,R.drawable.x_dodge);
        d.add(15,R.drawable.nomark);
        d.add(16,R.drawable.x_fiat);
        d.add(17,R.drawable.ford);
        d.add(18,R.drawable.x_forland);
        d.add(19,R.drawable.nomark);
        d.add(20,R.drawable.nomark);
        d.add(21,R.drawable.nomark);
        d.add(22,R.drawable.x_howo);
        d.add(23,R.drawable.x_hyundai);
        d.add(24,R.drawable.x_infiniti);
        d.add(25,R.drawable.x_isuzu);
        d.add(26,R.drawable.x_iveco);
        d.add(27,R.drawable.nomark);
        d.add(28,R.drawable.x_jeep);
        d.add(29,R.drawable.kamaz);
        d.add(30,R.drawable.kia);
        d.add(31,R.drawable.nomark);
        d.add(32,R.drawable.nomark);
        d.add(33,R.drawable.lada);
        d.add(34,R.drawable.x_land);
        d.add(35,R.drawable.lexus);
        d.add(36,R.drawable.x_lifan);
        d.add(37,R.drawable.x_man);
        d.add(38,R.drawable.maz);
        d.add(39,R.drawable.nomark);
        d.add(40,R.drawable.mercedes);
        d.add(41,R.drawable.mitsubishi);
        d.add(42,R.drawable.x_new_holland);
        d.add(43,R.drawable.x_mtz);
        d.add(44,R.drawable.nissan);
        d.add(45,R.drawable.opel);
        d.add(46,R.drawable.x_pegaut);
        d.add(47,R.drawable.x_pontiag);
        d.add(48,R.drawable.nomark);
        d.add(49,R.drawable.nomark);
        d.add(50,R.drawable.x_renault);
        d.add(51,R.drawable.nomark);
        d.add(52,R.drawable.nomark);
        d.add(53,R.drawable.nomark);
        d.add(54,R.drawable.x_skoda);
        d.add(55,R.drawable.toyota);
        d.add(56,R.drawable.uaz);
        d.add(57,R.drawable.nomark);
        d.add(58,R.drawable.walkswagen);
        d.add(59,R.drawable.x_volvo);
        d.add(60,R.drawable.uaz);
        d.add(61,R.drawable.x_yamaha);
        d.add(62,R.drawable.nomark);
        d.add(63,R.drawable.zil);
        d.add(64,R.drawable.nomark);
        d.add(65,R.drawable.x_moskwic);






    }
    void set_model(){
        list.addAll(Arrays.asList(getResources().getStringArray(R.array.car_marka)));
        for(int k=0; k<list.size(); k++){
            if(list.get(k).equals(add_adds_cars.category)){ index=k;}
        }
list=new ArrayList<>();
            if (index == 0) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.ALFA_ROMEO_model)));
            }
            if (index == 1) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Audi_model)));
            }
            if (index == 2) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.BMW_model)));
            }
            if (index == 3) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Buick_model)));
            }
            if (index == 4) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.CAMC_model)));
            }
            if (index == 5) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Caterpillar_model)));
            }
            if (index == 6) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Changan_model)));
            }
            if (index == 7) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Chevrolet_model)));
            }
            if (index == 8) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.chrysler_model)));
            }
            if (index == 9) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Citroen_model)));
            }
            if (index == 10) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.CMC_model)));
            }
            if (index == 11) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Container_model)));
            }
            if (index == 12) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Daewoo_model)));
            }
            if (index == 13) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.DAF_model)));
            }
            if (index == 14) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Dodge_model)));
            }
            if (index == 15) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Fekon_model)));
            }
            if (index == 16) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Fiat_model)));
            }
            if (index == 17) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Ford_model)));
            }
            if (index == 18) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Forland_model)));
            }
            if (index == 19) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Gaz_model)));
            }
            if (index == 20) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Golden_dragon_model)));
            }
            if (index == 21) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Honda_model)));
            }
            if (index == 22) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Howo_model)));
            }
            if (index == 23) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Hyundai_model)));
            }
            if (index == 24) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Infiniti_model)));
            }
            if (index == 25) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Isuzy_model)));
            }
            if (index == 26) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Iveco_model)));
            }
            if (index == 27) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.JCB_model)));
            }
            if (index == 28) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.jeep_model)));
            }
            if (index == 29) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.kamaz_model)));
            }
            if (index == 30) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.kia_model)));
            }
            if (index == 31) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.kogel_model)));
            }
            if (index == 32) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.kuba_model)));
            }
            if (index == 33) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.lada_model)));
            }
            if (index == 34) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Landrover_model)));
            }
            if (index == 35) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.lexus_model)));
            }
            if (index == 36) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.lifan_model)));
            }
            if (index == 37) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.man_model)));
            }
            if (index == 38) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.maz_model)));
            }
            if (index == 39) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Mazda_model)));
            }
            if (index == 40) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.mercedes_model)));
            }
            if (index == 41) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Mitsubishi_model)));
            }
            if (index == 42) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.New_Holland_model)));
            }
            if (index == 43) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.MTZ_model)));
            }
            if (index == 44) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Nissan_model)));
            }
            if (index == 45) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.opel_model)));
            }
            if (index == 46) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.peuget_model)));
            }
            if (index == 47) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Pontiac_model)));
            }
            if (index == 48) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.prisep_model)));
            }
            if (index == 49) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Raf_model)));
            }
            if (index == 50) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.renault_model)));
            }
            if (index == 51) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.saipa_model)));
            }
            if (index == 52) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Scania_model)));
            }
            if (index == 53) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Shimtz_model)));
            }
            if (index == 54) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Skoda_model)));
            }
            if (index == 55) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.toyota_model)));
            }
            if (index == 56) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.uaz_model)));
            }
            if (index == 57) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Ural_model)));
            }
            if (index == 58) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Wolkswagen_model)));
            }
            if (index == 59) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.volvo_model)));
            }
            if (index == 60) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.waz_model)));
            }
            if (index == 61) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.yamaha_model)));
            }

            if (index == 62) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.ZAZ_model)));
            }
            if (index == 63) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.Zil_model)));
            }
            if (index == 64) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.zonda_model)));
            }
            if (index == 65) {
                list.addAll(Arrays.asList(getResources().getStringArray(R.array.moskwic_model)));
            }
        b=new adapter_model(list,this);
        mar.setAdapter(b);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
