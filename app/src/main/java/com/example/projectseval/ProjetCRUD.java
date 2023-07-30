package com.example.projectseval;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjetCRUD extends AppCompatActivity {
    Project currentProject=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projet_crud);
    }

    private void findProject() {
        EditText editID=findViewById(R.id.editTextID);
        String sid=editID.getText().toString();
        if(sid.isEmpty()){
            return;
        }

        EditText editTitle=findViewById(R.id.editTextTitre);
        EditText editNJ1=findViewById(R.id.editTextNJ1);
        EditText editNJ2=findViewById(R.id.editTextNJ2);
        EditText editNJ3=findViewById(R.id.editTextNJ3);
        EditText editNJ4=findViewById(R.id.editTextNJ4);


        Call <List<Project>> call = RetrofitClient.getInstance()
                .getMyApi()
                .findProject(sid);
        call.enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call <List<Project>> call, Response<List<Project>> response) {
                List<Project> myProject = response.body();

                if(myProject.size()==0) {
                    currentProject=null;
                    Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                currentProject=myProject.get(0);


                editTitle.setText(myProject.get(0).getTitle());
                editNJ1.setText(String.valueOf(myProject.get(0).getNoteJ1()));
                editNJ2.setText(String.valueOf(myProject.get(0).getNoteJ2()));
                editNJ3.setText(String.valueOf(myProject.get(0).getNoteJ3()));
                editNJ4.setText(String.valueOf(myProject.get(0).getNoteJ4()));
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG)
                        .show();
            }

        });
    }

    private void createProject(){
        EditText editID=findViewById(R.id.editTextID);
        EditText editTitle=findViewById(R.id.editTextTitre);
        if(editID.getText().toString().isEmpty()){
            return;
        }

        Project p=new Project();
        p.setId(editID.getText().toString());
        p.setTitle(editTitle.getText().toString());

        Call< Project> call = RetrofitClient.getInstance()
                .getMyApi().createProject(p);

        call.enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ProjetCRUD.this, "Project created successfully!", Toast.LENGTH_SHORT).show();
                    currentProject=p;
                }
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
                Toast.makeText(ProjetCRUD.this, "ERROR: "+t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
    private void updateProject(){
        if(currentProject==null){
            return;
        }
        EditText editID=findViewById(R.id.editTextID);
        String sid=editID.getText().toString();
        if(sid.isEmpty()){
            return;
        }

        EditText editTitle=findViewById(R.id.editTextTitre);
        EditText editNJ1=findViewById(R.id.editTextNJ1);
        EditText editNJ2=findViewById(R.id.editTextNJ2);
        EditText editNJ3=findViewById(R.id.editTextNJ3);
        EditText editNJ4=findViewById(R.id.editTextNJ4);


        Project p=currentProject;

        p.setTitle(editTitle.getText().toString());
        p.setNoteJ1( Integer.parseInt(editNJ1.getText().toString()));
        p.setNoteJ2(Integer.parseInt(editNJ2.getText().toString()));
        p.setNoteJ3(Integer.parseInt(editNJ3.getText().toString()));
        p.setNoteJ4(Integer.parseInt(editNJ4.getText().toString()));
        p.total=p.getTotal();

        Call < Project> call = RetrofitClient.getInstance()
                .getMyApi().updateProject(sid,p);

        call.enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ProjetCRUD.this, "Project updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
                Toast.makeText(ProjetCRUD.this, "ERROR: "+t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    private void deleteProject(){
        EditText editID=findViewById(R.id.editTextID);
        String sid=editID.getText().toString();
        if(sid.isEmpty()){
            return;
        }



        Call <Void> call = RetrofitClient.getInstance()
                .getMyApi().deleteProject(sid);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ProjetCRUD.this, "Project deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ProjetCRUD.this, "ERROR: "+t.getMessage(), Toast.LENGTH_LONG).show();
                //Log.e("ERROR: ", t.getMessage());
            }
        });
    }
    public void addProject(View view) {
        createProject();
    }

    public void find(View view) {
        findProject();
    }

    public void update(View view) {
        updateProject();
    }

    public void delete(View view) {
        deleteProject();
    }
}