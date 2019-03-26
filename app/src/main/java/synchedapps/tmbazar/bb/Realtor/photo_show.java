package synchedapps.tmbazar.bb.Realtor;

import android.content.Intent;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import synchedapps.tmbazar.Network.network;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.bb.lenta_show_details;

public class photo_show extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_show);
        ImageView i=(ImageView)findViewById(R.id.image);
        Intent ij=getIntent();

        Glide.with(this)
                .load("http://"+ network.address+"/Reklama/adds/images/"+ij.getStringExtra("image")).asBitmap().centerCrop()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(i);
    }
}
