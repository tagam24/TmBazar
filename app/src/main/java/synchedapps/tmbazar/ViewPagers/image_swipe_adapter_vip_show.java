package synchedapps.tmbazar.ViewPagers;


import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import synchedapps.tmbazar.Models.ImagesClicker;
import synchedapps.tmbazar.Network.network;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.bb.reklama_photo_show;
import synchedapps.tmbazar.bb.vip_show_details;

import java.util.ArrayList;

public class image_swipe_adapter_vip_show extends PagerAdapter {
    private ArrayList<ImagesClicker> image_resource;
    private Context cntx;
    private LayoutInflater layoutInflater;
    String table;

    public image_swipe_adapter_vip_show(ArrayList<ImagesClicker> list, Context cntx,String table)
    {
            this.image_resource=list;
            this.cntx=cntx;
            this.table=table;

    }

    @Override
    public int getCount() {

        return image_resource.size();

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(LinearLayout)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       layoutInflater=(LayoutInflater) cntx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view=layoutInflater.inflate(R.layout.image_slider,container,false);
        ImageView imageView=(ImageView)item_view.findViewById(R.id.image_vip_show);
        try{  if(vip_show_details.flazhok==0)   Glide.with(cntx)
                .load("http://"+ network.address+"/Reklama/adds/images/"+image_resource.get(position).getImgG()).asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
        else imageView.setImageBitmap(image_resource.get(position).getImg1());
        imageView.setTag(image_resource.get(position).getId());
        imageView.setClickable(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!table.equals("myAdds")&&!table.equals("0")){
                Intent intent=new Intent(cntx,reklama_photo_show.class);
                intent.putExtra("table",table);
                 cntx.startActivity(intent);}
            }
        });} catch (IllegalStateException ss){}
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
