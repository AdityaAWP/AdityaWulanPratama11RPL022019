package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailMovie extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    MovieModel movieModel;



    Bundle extras;
    String title;
    String date;
    String deskripsi;
    String path;

    TextView tvjudul;
    ImageView ivposter;
    TextView tvdesc;
    Button btnfavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        extras = getIntent().getExtras();
        tvjudul = (TextView)findViewById(R.id.tvjudul);
        tvdesc = (TextView)findViewById(R.id.txtdeskripsi);
        ivposter = (ImageView) findViewById(R.id.ivposter);

        btnfavorite = (Button) findViewById(R.id.btnfavorite);
        if (extras != null) {
            title = extras.getString("judul");
            date = extras.getString("date");
            deskripsi = extras.getString("deskripsi");
            path = extras.getString("path");
            tvjudul.setText(title);
            tvdesc.setText(deskripsi);
            Glide.with(DetailMovie.this)
                    .load(path)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivposter);
            // and get whatever type user account id is
        }
        Realm.init(DetailMovie.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);


        btnfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieModel = new MovieModel();
                movieModel.setDesc(deskripsi);
                movieModel.setJudul(title);
                movieModel.setPath(path);
                movieModel.setReleaseDate(date);

                realmHelper = new RealmHelper(realm);
                realmHelper.save(movieModel);
                Toast.makeText(DetailMovie.this, "Success Add To Favourite", Toast.LENGTH_SHORT).show();

            }
        });

    }
}