package com.example.moonlight.firebasefullproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FullProfile extends AppCompatActivity {
    public static final String Database_Path = "Student_Details_Database";
    TextView t1,t2;
    String name;
    //
    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;
    DatabaseReference mFirebaseDbReferenceUserChild;
    DatabaseReference mFirebaseDbReferenceCurrentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_profile);
        t1=(TextView)findViewById(R.id.textView);
        t2=(TextView)findViewById(R.id.textView2);
     //databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);
         mFirebaseAuth = FirebaseAuth.getInstance();


         mFirebaseUser = mFirebaseAuth.getCurrentUser();

        mFirebaseDbReferenceUserChild = FirebaseDatabase.getInstance().getReference("Student_Details_Database");
        mFirebaseDbReferenceCurrentUser= mFirebaseDbReferenceUserChild.child(mFirebaseUser.getUid());
   /* }
    public  void button(View view)
    {
        loadUserInformation();


    }
    public void loadUserInformation () {*/
   mFirebaseDbReferenceCurrentUser.addValueEventListener(new ValueEventListener() {
       @Override
       public void onDataChange(DataSnapshot dataSnapshot) {
           try {
           name = String.valueOf(dataSnapshot.child("studentName").getValue());

               //String userDetails = dataSnapshot.getValue().toString();
               t1.setText(name);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }

       @Override
       public void onCancelled(DatabaseError databaseError) {

       }
   });
       /* mFirebaseDbReferenceCurrentUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    String value = dataSnapshot.getValue().toString();
                    //id = Integer.parseInt(value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



*/

    }
}
