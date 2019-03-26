package synchedapps.tmbazar.online_market.post_tab;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.ArrayList;

import synchedapps.tmbazar.R;
import synchedapps.tmbazar.online_market.Constants;

import synchedapps.tmbazar.online_market.adapters.adapter_cities;
import synchedapps.tmbazar.online_market.adapters.adapter_postmarkets;
import synchedapps.tmbazar.online_market.models.model_premarkets;
import synchedapps.tmbazar.online_market.network.get_minishops;
import synchedapps.tmbazar.online_market.network.get_shops;

/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class fragment_postmarkets extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    adapter_postmarkets ad;
    ArrayList<model_premarkets> list;
    SearchView searchView;
    MenuItem searchItem;
    Toolbar toolbar;
    RecyclerView recyclerView1;
    LinearLayoutManager linearLayoutManagerh;
    adapter_cities ad1;
    ArrayList<String> list1;
   public static   Handler s1=new Handler(),s2=new Handler();
 SwipeRefreshLayout s;
    View view;
    public fragment_postmarkets() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_cities, container, false);
        recyclerView1 = (RecyclerView) view.findViewById(R.id.cities);
        linearLayoutManagerh = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(linearLayoutManagerh);
        list1=new ArrayList<>();
        list1.add("Ähli");
        list1.add("Aşgabat");
        list1.add("Mary");
        list1.add("Daşoguz");
        list1.add("Türkmenabat");
        list1.add("Köneürgenç");
        list1.add("Baýramaly");
        list1.add("Türkmenbaşy");
        list1.add("Balkanabat");
        ad1=new adapter_cities(getActivity(),list1);
        recyclerView1.setAdapter(ad1);





        recyclerView = (RecyclerView)view.findViewById(R.id.postmarkets);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        ad=new adapter_postmarkets(getActivity(), Constants.shopses);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(ad);
        s=(SwipeRefreshLayout)view.findViewById(R.id.swipem);
        s.setOnRefreshListener(this);
        s1=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==1){
                    ad.setData(Constants.shopses);
                s.setRefreshing(false);}
            }
        };
        s.setRefreshing(true);   get_shops.get_Data();

        return  view;

    }


    @Override
    public void onRefresh() {
        s.setRefreshing(false);
    }
}
