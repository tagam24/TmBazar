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
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler;
import synchedapps.tmbazar.online_market.network.Api;


public class viewpageradapter extends PagerAdapter {
    private LayoutInflater inflater;
    ArrayList<String> images;
    private Context context;
   String table;
    public viewpageradapter(Context context, ArrayList<String> images) {
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
       View myImageLayout = inflater.inflate(R.layout.card_for_image_adapter_for_show_details,view,false);
        ImageView imageView=(ImageView)myImageLayout.findViewById(R.id.image_for_viewpager_for_show_details);
        imageView.setOnTouchListener(new ImageMatrixTouchHandler(MainActivity.ctx));
        Glide.with(context)
                .load("http://"+ Api.url+"images/"+images.get(position))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
        Log.d("url",Api.url+"images/"+images.get(position));
        imageView.setTag(position);
        imageView.setClickable(true);
        imageView.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
