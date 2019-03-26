package synchedapps.tmbazar.online_market.pre_market;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.concurrent.CopyOnWriteArrayList;

import synchedapps.tmbazar.R;
import synchedapps.tmbazar.online_market.Constants;

import synchedapps.tmbazar.online_market.adapters.adapter_markets;
import synchedapps.tmbazar.online_market.network.get_bazarlar;

/**
 * A simple {@link Fragment} subclass.

 * to handle interaction events.
 * create an instance of this fragment.
 */
public class fragment_markets extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    adapter_markets ad;
   public static   Handler s1;

    View view;
    public fragment_markets() {
    }
SwipeRefreshLayout s6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_markets, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.markets);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        ad=new adapter_markets(getActivity(), Constants.bazarlars);
        s6=(SwipeRefreshLayout)view.findViewById(R.id.swipep);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(ad);
        s6.setOnRefreshListener(this);
        s1=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==1){}
                ad.setData(Constants.bazarlars);
                s6.setRefreshing(false);
            }
        };
        s6.setRefreshing(true);
        get_bazarlar.get_Data();

        return  view;

    }
    @Override
    public void onRefresh() {
        s6.setRefreshing(false);
    }

}
