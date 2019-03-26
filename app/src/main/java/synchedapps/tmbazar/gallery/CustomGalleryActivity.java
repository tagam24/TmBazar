package synchedapps.tmbazar.gallery;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import synchedapps.tmbazar.R;
import synchedapps.tmbazar.bb.Beylekiler.beylekiler_add_reklama;
import synchedapps.tmbazar.bb.Realtor.realtor_add_reklama;
import synchedapps.tmbazar.bb.add_adds_cars;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;


public class CustomGalleryActivity extends AppCompatActivity {
	GridView gridGallery;
	Handler handler;
	Handler h;
	GalleryAdapter adapter;
	private static final int REQUEST_ID_READ_PERMISSION = 100;
	private static final int REQUEST_ID_WRITE_PERMISSION = 200;
	ImageView imgNoMedia;
	Button btnGalleryOk;
	public  static Context ctx;
	String action;
	private ImageLoader imageLoader;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.gallery);
ctx=this;
		action = "luminous.ACTION_MULTIPLE_PICK";
		initImageLoader();
		init();
		if (action == null) {
			finish();}

        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            if ((ActivityCompat.shouldShowRequestPermissionRationale(CustomGalleryActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(CustomGalleryActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE))) {

            } else {
                ActivityCompat.requestPermissions(CustomGalleryActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
        }else {
            Log.e("Else","Else");
     h.sendEmptyMessage(1);
        }








    }
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
Log.d("Onreq",""+requestCode);
		switch (requestCode) {
			case 1: {
				for (int i = 0; i < grantResults.length; i++) {
					if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
						h.sendEmptyMessage(1);
					} else {
						Toast.makeText(CustomGalleryActivity.this, "The app was not allowed to read or write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
					}
				}
			}
		}
	}













	private void initImageLoader() {
		try {
			String CACHE_DIR = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/.temp_tmp";
			new File(CACHE_DIR).mkdirs();

			File cacheDir = StorageUtils.getOwnCacheDirectory(getBaseContext(),
					CACHE_DIR);

			DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
					.cacheOnDisc(true).imageScaleType(ImageScaleType.EXACTLY)
					.bitmapConfig(Bitmap.Config.RGB_565).build();
			ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(
					getBaseContext()).
					discCache(new UnlimitedDiskCache(cacheDir))
					.defaultDisplayImageOptions(defaultOptions)
					.memoryCache(new WeakMemoryCache());

			ImageLoaderConfiguration config = builder.build();
			imageLoader = ImageLoader.getInstance();
			imageLoader.init(config);

		} catch (Exception e) {

		}
	}

	private void init() {

		handler = new Handler();
		gridGallery = (GridView) findViewById(R.id.gridGallery);
		gridGallery.setFastScrollEnabled(true);
		adapter = new GalleryAdapter(getApplicationContext(), imageLoader);
		PauseOnScrollListener listener = new PauseOnScrollListener(imageLoader,
				true, true);
		gridGallery.setOnScrollListener(listener);

		if (action.equalsIgnoreCase(Action.ACTION_MULTIPLE_PICK)) {

			findViewById(R.id.llBottomContainer).setVisibility(View.VISIBLE);
			gridGallery.setOnItemClickListener(mItemMulClickListener);
			adapter.setMultiplePick(true);

		} else if (action.equalsIgnoreCase(Action.ACTION_PICK)) {

			findViewById(R.id.llBottomContainer).setVisibility(View.GONE);
			gridGallery.setOnItemClickListener(mItemSingleClickListener);
			adapter.setMultiplePick(false);

		}

		gridGallery.setAdapter(adapter);
		imgNoMedia = (ImageView) findViewById(R.id.imgNoMedia);

		btnGalleryOk = (Button) findViewById(R.id.btnGalleryOk);
		btnGalleryOk.setOnClickListener(mOkClickListener);
//handler goymaly
		h=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				if(msg.what==1){
			new Thread() {

			@Override
			public void run() {
				Looper.prepare();
				handler.post(new Runnable() {

					@Override
					public void run() {
						adapter.addAll(getGalleryPhotos());
						checkImageStatus();
					}
				});
				Looper.loop();
			};

		}.start();}}
		};
	}

	private void checkImageStatus() {
		if (adapter.isEmpty()) {
			imgNoMedia.setVisibility(View.VISIBLE);
		} else {
			imgNoMedia.setVisibility(View.GONE);
		}
	}

	View.OnClickListener mOkClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			ArrayList<CustomGallery> selected = adapter.getSelected();

			String[] allPath = new String[selected.size()];
			for (int i = 0; i < allPath.length; i++) {
				allPath[i] = selected.get(i).sdcardPath;
			}
			Log.d("all_pathlenth",""+allPath.length);
			Intent i=getIntent();

			if(i.getStringExtra("image").equals("cars")){add_adds_cars.filepath=allPath;
			add_adds_cars.setImageView.sendEmptyMessage(1);}
			if(i.getStringExtra("image").equals("realtor")){
				realtor_add_reklama.filepath=allPath;
				realtor_add_reklama.setImageView.sendEmptyMessage(1);
			}
			if(i.getStringExtra("image").equals("beylekiler")){
				beylekiler_add_reklama.filepath=allPath;
				beylekiler_add_reklama.setImageView.sendEmptyMessage(1);}
			/*Intent data = new Intent().putExtra("all_path", allPath);
			setResult(RESULT_OK, data);*/
			finish();

		}
	};
	AdapterView.OnItemClickListener mItemMulClickListener = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> l, View v, int position, long id) {
			adapter.changeSelection(v, position);

		}
	};

	AdapterView.OnItemClickListener mItemSingleClickListener = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> l, View v, int position, long id) {
			CustomGallery item = adapter.getItem(position);
			Intent data = new Intent().putExtra("single_path", item.sdcardPath);
			setResult(RESULT_OK, data);
			finish();
		}
	};

	private ArrayList<CustomGallery> getGalleryPhotos() {
		ArrayList<CustomGallery> galleryList = new ArrayList<CustomGallery>();

		try {
			final String[] columns = { MediaStore.Images.Media.DATA,
					MediaStore.Images.Media._ID };
			final String orderBy = MediaStore.Images.Media._ID;

			Cursor imagecursor = managedQuery(
					MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns,
					null, null, orderBy);

			if (imagecursor != null && imagecursor.getCount() > 0) {

				while (imagecursor.moveToNext()) {
					CustomGallery item = new CustomGallery();

					int dataColumnIndex = imagecursor
							.getColumnIndex(MediaStore.Images.Media.DATA);

					item.sdcardPath = imagecursor.getString(dataColumnIndex);

					galleryList.add(item);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// show newest photo at beginning of the list
		Collections.reverse(galleryList);
		return galleryList;
	}




























}
