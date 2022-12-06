package com.example.patient_record;

//Source
//https://www.geeksforgeeks.org/how-to-delete-data-in-sqlite-database-in-android/
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewPatients extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<PatientModal> patientModalArrayList;
    private DBHandler dbHandler;
    private PatientRVAdapter patientRVAdapter;
    private RecyclerView patientsRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patients);

        // initializing our all variables.
        patientModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewPatients.this);

        // getting our patients array list from db handler class.
        patientModalArrayList = dbHandler.readPatients();

        // passing our array lost to our adapter class.
        patientRVAdapter = new PatientRVAdapter(patientModalArrayList, ViewPatients.this);
        patientsRV = findViewById(R.id.idRVPatients);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewPatients.this, RecyclerView.VERTICAL, false);
        patientsRV.setLayoutManager(linearLayoutManager);

        // setting the adapter to recycler view.
        patientsRV.setAdapter(patientRVAdapter);
    }
}
