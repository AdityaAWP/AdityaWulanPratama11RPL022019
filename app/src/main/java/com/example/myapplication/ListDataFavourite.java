package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class ListDataFavourite extends AppCompatActivity {
    Realm realm;
    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private ArrayList<Model> DataArrayList; //kit add kan ke adapter
    private ImageView tambah_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        DataArrayList = new ArrayList<>();
        adapter = new DataAdapter(DataArrayList, new DataAdapter.Callback() {
            @Override
            public void onClick(int position) {
                Model movie = DataArrayList.get(position);
                Intent intent = new Intent(getApplicationContext(), DetailMovie.class);
                intent.putExtra("judul",movie.original_title);
                intent.putExtra("date",movie.release_date);
                intent.putExtra("deskripsi",movie.overview);
                intent.putExtra("path",movie.poster_path);
                startActivity(intent);
            }

            @Override
            public void test() {

            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListDataFavourite.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
    public List<MovieModel> getAllMahasiswa() {
        RealmResults<MovieModel> results = realm.where(MovieModel.class).findAll();
        return results;
    }


}
