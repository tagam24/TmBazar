package synchedapps.tmbazar.online_market.items;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import synchedapps.tmbazar.R;
import synchedapps.tmbazar.online_market.network.Api;


public class viewpager_adapter extends PagerAdapter {
    private LayoutInflater inflater;
    ArrayList<String> images;
    private Context context;
   String table;
    public viewpager_adapter(Context context, ArrayList<String> images) {
        this.context = context;
        this.images = images;
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
    public Object instantiateItem(ViewGroup view, final int position) {
       View myImageLayout = inflater.inflate(R.layout.card_for_image, view, false);
        ImageView myImage = (ImageView) myImageLayout
                .findViewById(R.id.image_for_viewpager_for_beylekiler_show_details);
       if(images.get(position).length()>5) Glide.with(context)
                .load("http://"+ Api.url+"images/"+images.get(position))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(myImage);
        Log.d("url",Api.url+"images/"+images.get(position));
        myImage.setTag(position);
        myImage.setClickable(true);
        myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,reklam_photo.class);
                intent.putExtra("image",images.get(position));
                context.startActivity(intent);
            }
        });
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }
    public  void notif(ArrayList<String> images ){
        this.images=images;
        notifyDataSetChanged();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
