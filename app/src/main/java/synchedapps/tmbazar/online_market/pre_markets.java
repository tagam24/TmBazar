package synchedapps.tmbazar.online_market;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import synchedapps.tmbazar.R;
import synchedapps.tmbazar.online_market.adapters.adapter_premarkets;
import synchedapps.tmbazar.online_market.models.model_premarkets;

public class pre_markets extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    adapter_premarkets ad;
    ArrayList<model_premarkets> list;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premarket);
        toolbar=(Toolbar)findViewById(R.id.toolbarpre);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Online Market");
        recyclerView = (RecyclerView)findViewById(R.id.pre_markets);
        linearLayoutManager = new LinearLayoutManager(this);
        list=new ArrayList<>();
        model_premarkets m=new model_premarkets("Bazarlar",R.drawable.bazar);
        list.add(m);
        m=new model_premarkets("Söwda Merkezler",R.drawable.gyzyldan1);
        list.add(m);
        m=new model_premarkets("Dükanlar",R.drawable.kigili_resim);
        list.add(m);
        m=new model_premarkets("Restoranlar",R.drawable.restoran);
        list.add(m);
        m=new model_premarkets("Awtoşaýlar",R.drawable.zapcast);
        list.add(m);
        m=new model_premarkets("Gurluşyk harytlar",R.drawable.stroy);
        list.add(m);
        m=new model_premarkets("Salonlar",R.drawable.salonkrasoty);
        list.add(m);
        ad=new adapter_premarkets(this,list);
        recyclerView.setLayoutManager(linearLayoutManager);
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
