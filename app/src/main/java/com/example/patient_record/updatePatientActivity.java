package com.example.patient_record;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class updatePatientActivity extends AppCompatActivity {

    // variables for our edit text, button, strings and dbhandler class.
    private EditText firstNameEdt, lastNameEdt, ageEdt, p_visitEdt;
    private Button updatePatientBtn, deletePatientBtn;
    private DBHandler dbHandler;
    String firstName, lastName, age, p_visit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_patient);

        // initializing all our variables.
        firstNameEdt = findViewById(R.id.idEdtfirstName);
        lastNameEdt = findViewById(R.id.idEdtlastName);
        ageEdt = findViewById(R.id.idEdtAge);
        p_visitEdt = findViewById(R.id.idEdtVisit);
        updatePatientBtn = findViewById(R.id.idBtnUptRecord);
        deletePatientBtn = findViewById(R.id.idBtnDelRecord);

        // on below line we are initialing our dbhandler class.
        dbHandler = new DBHandler(updatePatientActivity.this);

        // on below lines we are getting data which
        // we passed in our adapter class.
        firstName = getIntent().getStringExtra("f_name");
        lastName = getIntent().getStringExtra("l_name");
        age = getIntent().getStringExtra("age");
        p_visit = getIntent().getStringExtra("p_visit");

        // setting data to edit text
        // of our update activity.
        firstNameEdt.setText(firstName);
        lastNameEdt.setText(lastName);
        ageEdt.setText(age);
        p_visitEdt.setText(p_visit);

        // adding on click listener to our update record button.
        updatePatientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // inside this method call the updatePatientRecord from DBHandler
                // method and passing all our edit text values.
                dbHandler.updatePatientsRecord(
                        firstName,
                        firstNameEdt.getText().toString(),
                        lastNameEdt.getText().toString(),
                        ageEdt.getText().toString(),
                        p_visitEdt.getText().toString());

                // displaying a toast message that our course has been updated.
                Toast.makeText(updatePatientActivity.this, "Patient Record Updated.", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(updatePatientActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        //adding a button for delete patients record
        deletePatientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the delete method from DBHandler
                dbHandler.deleteRecord(firstName);
                Toast.makeText(updatePatientActivity.this, "Record Deleted.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(updatePatientActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
