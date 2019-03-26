package synchedapps.tmbazar.online_market;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import synchedapps.tmbazar.R;
import synchedapps.tmbazar.online_market.adapters.adapter_cities;

public class cities extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    adapter_cities ad;
    ArrayList<String> list;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
      //  toolbar=(Toolbar)findViewById(R.id.toolbar_cities);
      //  setSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setTitle("Şäherler");
        recyclerView = (RecyclerView) findViewById(R.id.cities);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        list=new ArrayList<>();
        list.add("Aşgabat");
        list.add("Mary");
        list.add("Daşoguz");
        list.add("Türkmenabat");
        list.add("Köneürgenç");
        list.add("Baýramaly");
        list.add("Türkmenbaşy");
        list.add("Balkanabat");
        ad=new adapter_cities(this,list);
        recyclerView.setAdapter(ad);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == android.R.id.home) {
            finish();

        }
        return true;
    }
}
