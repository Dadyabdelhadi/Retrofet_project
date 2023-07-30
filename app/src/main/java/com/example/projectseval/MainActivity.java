package com.example.projectseval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView projectsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        projectsListView = findViewById(R.id.projects_list);
        //getProjects();
    }

    @Override
    public void onResume(){
        super.onResume();
        getProjects();
        //Toast.makeText(getApplicationContext(), "resumed", Toast.LENGTH_LONG).show();
    }

    private void getProjects() {
        Call < List < Project >> call = RetrofitClient.getInstance().getMyApi().getProjects();
        call.enqueue(new Callback< List < Project >>() {
            @Override
            public void onResponse(Call < List < Project >> call, Response< List < Project >> response) {
                List < Project > myProjectsList = response.body();
                String[] oneProjects = new String[myProjectsList.size()];

                TextView tcount=findViewById(R.id.textViewCount);
                tcount.setText(String.valueOf(myProjectsList.size()));

                for (int i = 0; i < myProjectsList.size(); i++) {
                    oneProjects[i] = myProjectsList.get(i).getId()+ " - " +myProjectsList.get(i).getTitle();

                }


                projectsListView.setAdapter(new ArrayAdapter< String >(getApplicationContext(), android.R.layout.simple_list_item_1, oneProjects));
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }




    public void afficher(View view) {
        //updateProject();
        Intent intent = new Intent(this, ProjetCRUD.class);
        startActivity(intent);


    }

    public void editerLesnotes(View view) {
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        startActivity(intent);
    }
}


