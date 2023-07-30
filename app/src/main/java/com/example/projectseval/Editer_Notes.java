package com.example.projectseval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Editer_Notes extends AppCompatActivity {
    private String selectedItem;
    private int editNJ1 ;
    private int editNJ2 ;
    private int editNJ3 ;
    private int editNJ4 ;

    Project currentProject=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editer_notes);
        String id = getIntent().getStringExtra("id");

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.List_jury, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Handle the selected item here
                selectedItem = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

    }
    @Override
    public void onResume(){
        super.onResume();
        findProject();
    }

    private void findProject() {
        Project project1 = null;
        EditText idtext = findViewById(R.id.editTextTextPersonName);
        String sid= getIntent().getStringExtra("id");
        if(sid.isEmpty()){
            return;
        }

        EditText editTitle=findViewById(R.id.editTextTextPersonName2);
        EditText editNJ=findViewById(R.id.editTextNumber);



        Call<List<Project>> call = RetrofitClient.getInstance()
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
                else {
                    currentProject=myProject.get(0);
                    Toast.makeText(Editer_Notes.this, myProject.get(0).getTitle(), Toast.LENGTH_SHORT).show();

                    editTitle.setText(myProject.get(0).getTitle());
                    idtext.setText(myProject.get(0).getId());
                    editNJ1 = myProject.get(0).getNoteJ1();
                    editNJ2 = myProject.get(0).getNoteJ2();
                    editNJ3 = myProject.get(0).getNoteJ3();
                    editNJ4 = myProject.get(0).getNoteJ4();
                }

            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG)
                        .show();
            }

        });
    }


    private void updateProject(String spinerSelected){
        if(currentProject==null){
            return;
        }
        EditText titre = findViewById(R.id.editTextTextPersonName2);

        String sid = getIntent().getStringExtra("id");
        if(sid.isEmpty()){
            return;
        }

        EditText editNJ=findViewById(R.id.editTextNumber);
        Toast.makeText(this, selectedItem, Toast.LENGTH_SHORT).show();
        Project p=currentProject;
        if (spinerSelected.equalsIgnoreCase("J1")){
            p.setNoteJ1( Integer.parseInt(editNJ.getText().toString()));
            p.setNoteJ2(editNJ2);
            p.setNoteJ3(editNJ3);
            p.setNoteJ4(editNJ4);
            p.setTitle(titre.getText().toString());
            p.setId(sid);
            p.total = p.getTotal();
        } else  if (spinerSelected.equalsIgnoreCase("J2")) {
            p.setNoteJ2(Integer.parseInt(editNJ.getText().toString()));
            p.setNoteJ1(editNJ1);
            p.setNoteJ3(editNJ3);
            p.setNoteJ4(editNJ4);
            p.setTitle(titre.getText().toString());
            p.setId(sid);
            p.total = p.getTotal();

        } else if (spinerSelected.equalsIgnoreCase("J3")) {
            p.setNoteJ3(Integer.parseInt(editNJ.getText().toString()));
            p.setNoteJ2(editNJ2);
            p.setNoteJ1(editNJ1);
            p.setNoteJ4(editNJ4);
            p.setTitle(titre.getText().toString());
            p.setId(sid);
            p.total = p.getTotal();

        } else  if (spinerSelected.equalsIgnoreCase("J4")) {
            p.setNoteJ4(Integer.parseInt(editNJ.getText().toString()));
            p.setNoteJ2(editNJ2);
            p.setNoteJ3(editNJ3);
            p.setNoteJ1(editNJ1);
            p.setTitle(titre.getText().toString());
            p.setId(sid);
            p.total = p.getTotal();
            }
        Call < Project> call = RetrofitClient.getInstance()
                .getMyApi().updateProject(sid,p);

        call.enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                if(response.isSuccessful()){
                    Toast.makeText(Editer_Notes.this, "Project updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
                Toast.makeText(Editer_Notes.this, "ERROR: "+t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }



    public void onclickBtn(View view){
        updateProject(selectedItem);
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void backBtn(View view) {
        Intent intent = new Intent(Editer_Notes.this,MainActivity2.class);
        startActivity(intent);
    }
}