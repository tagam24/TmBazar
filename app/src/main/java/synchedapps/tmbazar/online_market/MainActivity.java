package synchedapps.tmbazar.online_market;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import synchedapps.tmbazar.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(this,cities.class);
        startActivity(intent);
    }
}
