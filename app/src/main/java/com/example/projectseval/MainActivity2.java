package com.example.projectseval;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity implements  OnItemClickListener{
    Progect_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getProjects();



    }
    @Override
    public void onResume(){
        super.onResume();
        getProjects();
        //Toast.makeText(getApplicationContext(), "resumed", Toast.LENGTH_LONG).show();
    }

    public void getProjects(){
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RecyclerView rcv = findViewById(R.id.recyclerViewProjects);

        ArrayList<Project> projectList = new ArrayList<>();

        Call<List<Project>> call = RetrofitClient.getInstance().getMyApi().getProjects();
        call.enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                if (response.isSuccessful()) {
                    List<Project> projects = response.body();
                    if (projects != null) {
                        projectList.addAll(projects);
                        adapter=new Progect_Adapter(MainActivity2.this,projectList,MainActivity2.this);
                        rcv.setAdapter(adapter);
                        rcv.setLayoutManager(new LinearLayoutManager(MainActivity2.this));
                    }
                } else {
                    Toast.makeText(MainActivity2.this, "null result", Toast.LENGTH_SHORT).show();
                }
            }



            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                // Handle failure
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RecyclerView rcv = findViewById(R.id.recyclerViewProjects);
        Intent intent = new Intent(MainActivity2.this,Editer_Notes.class);
        String id = ((TextView) rcv.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.editTextTextPersonName3)).getText().toString();
        intent.putExtra("id",id);
        startActivity(intent);
    }

    public void gererEtmodfer(View view) {
        Intent intent = new Intent(MainActivity2.this,MainActivity.class);
        startActivity(intent);
    }
}