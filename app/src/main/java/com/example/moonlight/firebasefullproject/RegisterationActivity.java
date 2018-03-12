package com.example.moonlight.firebasefullproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterationActivity extends AppCompatActivity {
    EditText email,pass,name,loc,phn;
    FirebaseAuth firebaseAuth;
    ProgressDialog p;
    String n,a,phone;
    DatabaseReference databaseReference;

    // Root Database Name for Firebase Database.
    public static final String Database_Path = "Student_Details_Database";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        email = (EditText) findViewById(R.id.email1);
        name = (EditText) findViewById(R.id.name1);
        loc = (EditText) findViewById(R.id.location1);
        phn = (EditText) findViewById(R.id.phn1);
        pass = (EditText) findViewById(R.id.pass1);
        firebaseAuth = FirebaseAuth.getInstance();
        TextView e=(TextView)findViewById(R.id.text);
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);



        if(firebaseAuth.getCurrentUser() != null){

            e.setText(firebaseAuth.getCurrentUser().getEmail().toString());
            e.setTextColor(Color.BLACK);



        }else {
            Toast.makeText(getApplicationContext(), "Plz login to set user profile",Toast.LENGTH_SHORT).show();
        }
    }
    public void log(View view)
    {
        firebaseAuth.signOut();
    }
    public void register(android.view.View view)
    {
        n=name.getText().toString();
        a=loc.getText().toString();
        phone=phn.getText().toString();


        String email1 = email.getText().toString().trim();
        String password1 = pass.getText().toString().trim();

        if (TextUtils.isEmpty(email1)) {
            Toast.makeText(getApplication(), "emial is empty", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password1)) {
            Toast.makeText(getApplication(), "password is empty", Toast.LENGTH_SHORT).show();
        }
       /* p.setMessage("Registering User");
        p.show();*/
        firebaseAuth.createUserWithEmailAndPassword(email1, password1).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Register sucessful", Toast.LENGTH_SHORT).show();
                            uploadData();
                           /* Intent myintent=new Intent(RegisterationActivity.this,MainActivity.class);

                            myintent.putExtra("name",n);
                            myintent.putExtra("location",a);
                            myintent.putExtra("phone",phone);
                            startActivity(myintent);*/
                            //p.dismiss();
                        } else {
                            Toast.makeText(getApplicationContext(), "Register  is not  sucessful", Toast.LENGTH_SHORT).show();
                            //p.dismiss();
                        }

                    }
                });
    }
    public void uploadData()
    {
        String currentUserID = firebaseAuth.getInstance().getCurrentUser().getUid();
        StudentDetails studentDetails=new StudentDetails();
        studentDetails.setStudentName(n);

        // Adding phone number into class function object.
        studentDetails.setStudentPhoneNumber(phone);
        studentDetails.setLocation(a);
        studentDetails.setUid(currentUserID);

        // Getting the ID from firebase database.
        String StudentRecordIDFromServer = databaseReference.push().getKey();

        // Adding the both name and number values using student details class object using ID.
        databaseReference.child(currentUserID).setValue(studentDetails);

        // Showing Toast message after successfully data submit.
        Toast.makeText(RegisterationActivity.this, "Data Inserted Successfully into Firebase Database", Toast.LENGTH_LONG).show();

    }

}

