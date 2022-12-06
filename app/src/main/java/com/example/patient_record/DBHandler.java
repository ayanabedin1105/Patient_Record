//Part of code has been influenced by  https://www.geeksforgeeks.org/how-to-read-data-from-sqlite-database-in-android/

package com.example.patient_record;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    //creating a constant variable from the database
    private static final String DB_NAME = "patientDB";
    
    //below int is our database version
    private static final int DB_VERSION = 1;
    
    //variable for table name
    private static final String TABLE_NAME = "patient_sys";
    
    //variable for id column
    private static final String ID_COL = "id";

    //variable for patients first name
    private static final String FIRST_NAME_COL = "f_name";
    
    //variable for patients lastname.
    private static final String LAST_NAME_COL = "l_name";
    
    //variable name for patients age
    private static final String AGE_COL = "age";
    
    //variable for visit reason
    private static final String VISIT_COL = "p_visit";

    
    
    //creating a constructor for the database handler
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Here we are creating an sqlite query
        //setting our columns names along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIRST_NAME_COL + " TEXT,"
                + LAST_NAME_COL + " TEXT,"
                + AGE_COL + " TEXT,"
                + VISIT_COL + " TEXT)";

        //At last call a exec sql method to execute above sql query
        db.execSQL(query);

    }//end OnCreate

    //method to add patients name to our sqlite database.
    public void addNewPatient(String firstName, String lastName, String age, String visit) {
        //here we create a variable for our sqlite database and calling writable
        //method as we write data in our database
        SQLiteDatabase db = this.getWritableDatabase();

        //creating a variable for content values.
        ContentValues values = new ContentValues();

        //Here we pass all values along with its key and value pair.
        values.put(FIRST_NAME_COL, firstName);
        values.put(LAST_NAME_COL, lastName);
        values.put(AGE_COL, age);
        values.put(VISIT_COL, visit);

        //After adding all values we pass the content values to the table.
        db.insert(TABLE_NAME, null, values);

        //close the database after adding database
        db.close();

    }//end addNewPatient


    //creating a new method for reading all the patients data.
    public ArrayList<PatientModal> readPatients() {
        //creating a database for reading our database
        SQLiteDatabase db = this.getReadableDatabase();

        //creating a Cursor with query to read data from database.
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        //creating a new array list
        ArrayList<PatientModal> patientModalArrayList = new ArrayList<>();

        //moving cursor to first position
        if(c.moveToFirst()) {
            do {
                //adding the data from the cursor to the arraylist
                patientModalArrayList.add(new PatientModal(c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4)));
            } while (c.moveToNext());
            //moving cursor to next
        }

        //close the cursor
        //returning the arraylist
        c.close();
        return patientModalArrayList;
    }//end readPatient Array

//    //Updating Patients data provided
    public void updatePatientsRecord(String originalFirstName, String fName, String lName, String age, String p_visit) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // here we are passing all values
        // along with its key and value pair.
        values.put(FIRST_NAME_COL, fName);
        values.put(LAST_NAME_COL, lName);
        values.put(AGE_COL, age);
        values.put(VISIT_COL, p_visit);

        //calling the update method to update the database and passing our values
        db.update(TABLE_NAME, values, "f_name=?", new String[]{originalFirstName});
        db.close();
    }//end updatePatientsRecord

    //deleting records from the table
    public void deleteRecord(String firstName) {

        //making the database writable
        SQLiteDatabase db = this.getWritableDatabase();

        //method to delete patients record using the patient's first name
        db.delete(TABLE_NAME,"f_name=?", new String[]{firstName});
        //close the database
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //this method is used to check if the table already exists.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
