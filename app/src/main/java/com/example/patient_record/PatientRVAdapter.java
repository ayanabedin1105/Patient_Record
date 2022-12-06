package com.example.patient_record;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PatientRVAdapter extends RecyclerView.Adapter<PatientRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<PatientModal> patientModalArrayList;
    private Context context;

    // creating a constructor
    public PatientRVAdapter(ArrayList<PatientModal> courseModalArrayList, Context context) {
        this.patientModalArrayList = courseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        PatientModal modal = patientModalArrayList.get(position);
        holder.firstNameRV.setText(modal.getfName());
        holder.lastNameRV.setText(modal.getlName());
        holder.ageRV.setText(modal.getAge());
        holder.p_visitRV.setText(modal.getP_visit());



        //add on click listener for the recycler view item.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling an intent
                Intent i = new Intent(context, updatePatientActivity.class);

                //passing all the updated values
                i.putExtra("f_name", modal.getfName());
                i.putExtra("l_name", modal.getlName());
                i.putExtra("age", modal.getAge());
                i.putExtra("p_visit", modal.getP_visit());

                //starting the activity
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return patientModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView firstNameRV, lastNameRV, ageRV, p_visitRV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            firstNameRV = itemView.findViewById(R.id.idRVfirstName);
            lastNameRV = itemView.findViewById(R.id.idRVlastName);
            ageRV = itemView.findViewById(R.id.idRVage);
            p_visitRV = itemView.findViewById(R.id.idRVpurposeVisit);
        }
    }
}
