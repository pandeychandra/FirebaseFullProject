package com.example.moonlight.firebasefullproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostEventActivity extends AppCompatActivity {
    EditText name,loc,phn;
    String n,l,p;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    public static final String Database_Path = "Student_Details_Database";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_event);
        name=(EditText)findViewById(R.id.colzName);
        loc=(EditText)findViewById(R.id.ColzLoc);
        firebaseAuth=FirebaseAuth.getInstance();
        phn=(EditText)findViewById(R.id.colzPhn);
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);


    }
    public void click(View view)
    {
        n=name.getText().toString();
        l=loc.getText().toString();
        p=phn.getText().toString();

        String currentUserID = firebaseAuth.getInstance().getCurrentUser().getUid();
        StudentDetails studentDetails=new StudentDetails();
        studentDetails.setStudentName(n);

        // Adding phone number into class function object.
        studentDetails.setStudentPhoneNumber(p);
        studentDetails.setLocation(l);
       // studentDetails.setUid(currentUserID);

        // Getting the ID from firebase database.
        String StudentRecordIDFromServer = databaseReference.push().getKey();

        // Adding the both name and number values using student details class object using ID.
        databaseReference.child(currentUserID).child(currentUserID+"he").setValue(studentDetails);

        // Showing Toast message after successfully data submit.
        //Toast.makeText(RegisterationActivity.this, "Data Inserted Successfully into Firebase Database", Toast.LENGTH_LONG).show();


    }
}
