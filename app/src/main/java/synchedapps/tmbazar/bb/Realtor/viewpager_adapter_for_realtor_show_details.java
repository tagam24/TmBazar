package synchedapps.tmbazar.bb.Realtor;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import synchedapps.tmbazar.Models.ImagesClicker;
import synchedapps.tmbazar.Network.network;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.bb.reklama_photo_show;

import java.util.ArrayList;

public class viewpager_adapter_for_realtor_show_details extends PagerAdapter {

    private ArrayList<ImagesClicker> images;
    private LayoutInflater inflater;
    private Context context;
   String table;
    public viewpager_adapter_for_realtor_show_details(Context context, ArrayList<ImagesClicker> images,String table) {
        this.context = context;
        this.images = images;
        this.table=table;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        try {
            return images.size();
        }catch (IllegalStateException s){
            return  0;
        }

    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
       View myImageLayout = inflater.inflate(R.layout.card_for_image_adapter_for_beylekiler_show_details, view, false);
        ImageView myImage = (ImageView) myImageLayout
                .findViewById(R.id.image_for_viewpager_for_beylekiler_show_details);
        Glide.with(context)
                .load("http://"+ network.address+"/Reklama/adds/images/"+images.get(position).getImgG())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(myImage);
        myImage.setTag(images.get(position).getId());
        myImage.setClickable(true);
        myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,reklama_photo_show.class);
                intent.putExtra("table",table);
                context.startActivity(intent);
            }
        });
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
