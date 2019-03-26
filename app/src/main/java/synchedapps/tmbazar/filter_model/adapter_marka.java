package synchedapps.tmbazar.filter_model;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import synchedapps.tmbazar.R;
import synchedapps.tmbazar.bb.add_adds_cars;

import java.util.ArrayList;
import java.util.List;

import static synchedapps.tmbazar.R.id;
import static synchedapps.tmbazar.R.layout;

public class adapter_marka extends RecyclerView.Adapter<adapter_marka.Reklama_viewholder> {
    static ArrayList<String> data;
    Context cntx;
    List<String> idlar = new ArrayList<>();
    ArrayList<String > data1=new ArrayList<>();
    ArrayList<Integer> dra=new ArrayList<>();
    ArrayList<Integer> dra1=new ArrayList<>();
    String table;


    public class Reklama_viewholder extends RecyclerView.ViewHolder {
    TextView t1;
        ImageView i;

        public Reklama_viewholder(View itemView) {
            super(itemView);
            i=(ImageView)itemView.findViewById(id.img_mark);
          t1=(TextView)itemView.findViewById(R.id.text_car_model_filter);



            //  time=(TextView)itemView.findViewById(id.item_time);
            // description=(TextView)itemView.findViewById(id.item_description);

        }
    }

    public adapter_marka(ArrayList<Integer> drawable,ArrayList <String> items, Context ctx) {
        this.data = items;
        this.dra=drawable;
        this.cntx = ctx;
        this.dra1.addAll(drawable);
        this.data1.addAll(items);
    }

    @Override
    public adapter_marka.Reklama_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_car_marka_add, parent, false);
        final adapter_marka.Reklama_viewholder view = new adapter_marka.Reklama_viewholder(v);
        return view;

    }

    @Override
    public void onBindViewHolder(adapter_marka.Reklama_viewholder holder, final int position) {
        Log.d("image.pos",""+position);
      if(dra.size()>position) if(dra.get(position)!=0)holder.i.setImageResource(dra.get(position));
        holder.t1.setText(data.get(position));
        holder.t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_adds_cars.textView_marka.setText(data.get(position));
                add_adds_cars.category=data.get(position);
               //add_adds_cars.index=position;
                add_car_model.s.sendEmptyMessage(1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public  void setData(ArrayList<String> list){
        this.data=list;
        notifyDataSetChanged();
        Log.d("data","get");

    }
    public void filterData(String query) {
        query = query.toLowerCase();
       data.clear();
        dra.clear();
        if (query.isEmpty()) {
            data.addAll(data1);
            dra.addAll(dra1);
        }
        else {
            for(int i=0; i<data1.size();i++){
                if(data1.get(i).toLowerCase().contains(query.toLowerCase())){
                    data.add(data1.get(i));
                    dra.add(dra1.get(i));
                }
            }

        }

        notifyDataSetChanged();
    }

}
