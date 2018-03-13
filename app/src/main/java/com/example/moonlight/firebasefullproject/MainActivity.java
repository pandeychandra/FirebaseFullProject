package com.example.moonlight.firebasefullproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView email;
    NavigationView navigationView;
    FirebaseAuth firebaseAuth;
    String emai;
    String name, phone, adress;
    DatabaseReference databaseReference;
    TextView nameFir;
    DatabaseReference newRef;
    ArrayList<String> arrayList;

    // Root Database Name for Firebase Database.
    public static final String Database_Path = "Student_Details_Database";
    private DatabaseReference mRef;
    FloatingActionButton button;
    List<StudentDetails> list = new ArrayList<>();

    RecyclerView recyclerView;

    RecyclerView.Adapter adapter ;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name = getIntent().getStringExtra("name");

        phone = getIntent().getStringExtra("phone");
        adress = getIntent().getStringExtra("location");
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        button=(FloatingActionButton)findViewById(R.id.fab);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        progressDialog = new ProgressDialog(MainActivity.this);

        progressDialog.setMessage("Loading Data from Firebase Database");

        progressDialog.show();


        firebaseAuth = FirebaseAuth.getInstance();
        String currentUserID = firebaseAuth.getInstance().getCurrentUser().getUid();
        String b=currentUserID+"he";

       // mRef = FirebaseDatabase.getInstance().getReference("Student_Details_Database").child(currentUserID+"he");
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path).child(currentUserID).child(currentUserID+"he");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {


                  // String  studentDetails = (String) dataSnapshot.getValue("location").toString();
                    String  na = String.valueOf(dataSnapshot.child("location").getValue());
                    String  nphn = String.valueOf(dataSnapshot.child("location").getValue());
                    String nadress = String.valueOf(dataSnapshot.child("location").getValue());

                    //list.add(studentDetails);
                }

                adapter = new RecyclerViewAdapter(MainActivity.this, list);

                recyclerView.setAdapter(adapter);

                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();

            }
        });
        //loadUserInformation();



        View header = navigationView.getHeaderView(0);
        email = (TextView) header.findViewById(R.id.nameFirst);
        nameFir = (TextView) header.findViewById(R.id.emailFirst);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firebaseAuth.getCurrentUser() != null){
                    Intent i=new Intent(MainActivity.this,PostEventActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), " user profile", Toast.LENGTH_SHORT).show();
                   ;
                }else {
                    Intent i=new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(i);
                    //Toast.makeText(getApplicationContext(), "Plz login to set user profile", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


    public void loadUserInformation() {





       final String currentUserID = firebaseAuth.getInstance().getCurrentUser().getUid();

        newRef = databaseReference.child(currentUserID);
        newRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    try {
                      //String carid = dataSnapshot.child("Student_Details_Database").child(currentUserID).child("location").getValue(String.class);
                        String userDetails = dataSnapshot.getValue().toString();
                       String  na = String.valueOf(dataSnapshot.child("location").getValue());

                        //email.setText(na);

                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        }






    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth.getCurrentUser() != null){
            email.setText(firebaseAuth.getCurrentUser().getEmail().toString());
           loadUserInformation();
        }else {
       Toast.makeText(getApplicationContext(), "Plz login to set user profile", Toast.LENGTH_SHORT).show();
   }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
       int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            firebaseAuth.signOut();

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
