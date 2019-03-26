package synchedapps.tmbazar.online_market.post_tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


import synchedapps.tmbazar.R;
import synchedapps.tmbazar.online_market.adapters.adapter_categories;
import synchedapps.tmbazar.online_market.models.model_premarkets;

/**
 * A simple {@link Fragment} subclass.

 * to handle interaction events.
 * create an instance of this fragment.
 */
public class categories1 extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    adapter_categories ad;
    ArrayList<model_premarkets> list;

    View view;
    public categories1() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_categorie, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.categories);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        list=new ArrayList<>();
        model_premarkets m=new model_premarkets("Oglan eşikleri",R.drawable.online);
        list.add(m);
        m=new model_premarkets("Zenan eşikleri",R.drawable.online);
        list.add(m);
        m=new model_premarkets("Çaga eşikleri",R.drawable.online);
        list.add(m);
        m=new model_premarkets("Azyk harytlary",R.drawable.online);
        list.add(m);
        m=new model_premarkets("Öý goşlary",R.drawable.online);
        list.add(m);
        m=new model_premarkets("Mebeller",R.drawable.online);
        list.add(m);
        m=new model_premarkets("Elektronika",R.drawable.online);
        list.add(m);
        m=new model_premarkets("Şaý sepler",R.drawable.online);
        list.add(m);
        m=new model_premarkets("Kosmetika",R.drawable.online);
        list.add(m);
        m=new model_premarkets("Beýlekiler",R.drawable.online);
        list.add(m);
        m=new model_premarkets("Restoranlar",R.drawable.online);
        list.add(m);
        recyclerView.setLayoutManager(linearLayoutManager);
        ad=new adapter_categories(getActivity(),list);
        recyclerView.setAdapter(ad);

        return  view;

    }


}
