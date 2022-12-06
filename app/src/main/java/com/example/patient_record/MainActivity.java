package com.example.patient_record;

//Source
//https://www.geeksforgeeks.org/how-to-delete-data-in-sqlite-database-in-android/


import static androidx.core.location.LocationManagerCompat.getCurrentLocation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //creating variables for editText, buttons and dbHandler
    private EditText fNameEdt, lNameEdt, ageEdt, p_visitEdt;
    private Button addPatientBtn, readPatientsBtn;
    private DBHandler dbHandler;

    //variables for gps location
    private Button LocationButton;
    private LocationRequest locationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing all variables
        fNameEdt = findViewById(R.id.idEdtfirstName);
        lNameEdt = findViewById(R.id.idEdtlastName);
        ageEdt = findViewById(R.id.idEdtAge);
        p_visitEdt = findViewById(R.id.idEdtVisit);
        addPatientBtn = findViewById(R.id.idBtnAddRecord);
        readPatientsBtn = findViewById(R.id.idBtnReadPatient);

        //location button
        LocationButton = findViewById(R.id.EnableLocation);




        //creating a new dbhandler class and passing our context to it.
        dbHandler = new DBHandler(MainActivity.this);

        //making the button listen to click event
        addPatientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get data from all edit text fields.
                String firstName = fNameEdt.getText().toString();
                String lastName = lNameEdt.getText().toString();
                String Age = ageEdt.getText().toString();
                String Visit = p_visitEdt.getText().toString();


                //Validating if the text fields are empty or not
                if(firstName.isEmpty() && lastName.isEmpty() && Age.isEmpty() && Visit.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all data...", Toast.LENGTH_SHORT).show();
                    return;
                }

                //calling a new method to add new course to sqlite data and pass all the values to it.
                dbHandler.addNewPatient(firstName, lastName, Age, Visit);

                //Displaying the data with a toast message.
                Toast.makeText(MainActivity.this, "Patient has been added.", Toast.LENGTH_SHORT).show();
                fNameEdt.setText("");
                lNameEdt.setText("");
                ageEdt.setText("");
                p_visitEdt.setText("");
            }
        });

        //applying onClickListener for responding to the view patients button
        readPatientsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //opening a new activity using Intent trick
                Intent i = new Intent(MainActivity.this, ViewPatients.class);
                startActivity(i);
            }
        });

        //applying onClickListener for responding to the view the location page
        LocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //opening a new activity using Intent trick
                Intent i = new Intent(MainActivity.this, ViewLocation.class);
                startActivity(i);
            }
        });
    }

}