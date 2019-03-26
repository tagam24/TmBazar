package synchedapps.tmbazar.bb.Beylekiler;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import synchedapps.tmbazar.R;

import java.util.ArrayList;

public class viewpager_adapter_for_beylekiler_show_details extends PagerAdapter {

    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private Context context;

    public viewpager_adapter_for_beylekiler_show_details(Context context, ArrayList<Integer> images) {
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
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View myImageLayout = inflater.inflate(R.layout.card_for_image_adapter_for_beylekiler_show_details, view, false);
        ImageView myImage = (ImageView) myImageLayout
                .findViewById(R.id.image_for_viewpager_for_beylekiler_show_details);
        myImage.setImageResource(images.get(position));
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
