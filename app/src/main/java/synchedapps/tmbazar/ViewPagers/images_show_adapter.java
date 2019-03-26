package synchedapps.tmbazar.ViewPagers;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import synchedapps.tmbazar.Models.ImagesClicker;
import synchedapps.tmbazar.Network.network;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.bb.MainActivity;
import synchedapps.tmbazar.bb.vip_show_details;
import synchedapps.tmbazar.com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler;

import java.util.ArrayList;

public class images_show_adapter extends PagerAdapter {
    private ArrayList<ImagesClicker> image_resource;
    private Context cntx;
    private LayoutInflater layoutInflater;

    public images_show_adapter(ArrayList<ImagesClicker> list, Context cntx)
    {
            this.image_resource=list;
            this.cntx=cntx;
    }

    @Override
    public int getCount() {
        return image_resource.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(FrameLayout)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) cntx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view=layoutInflater.inflate(R.layout.card_for_image_adapter_for_show_details,container,false);
        ImageView imageView=(ImageView)item_view.findViewById(R.id.image_for_viewpager_for_show_details);
      imageView.setOnTouchListener(new ImageMatrixTouchHandler(MainActivity.ctx));
       if(image_resource.get(position).getImgG()!=null|| vip_show_details.flazhok!=1) Glide.with(cntx)
                .load("http://"+ network.address+"/Reklama/adds/images/"+image_resource.get(position).getImgG()).asBitmap().centerCrop()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView); else imageView.setImageBitmap(image_resource.get(position).getImg1());
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout)object);
    }
}
