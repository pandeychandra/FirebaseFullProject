package com.example.moonlight.firebasefullproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    Button button;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=(EditText)findViewById(R.id.email_login);
        password=(EditText)findViewById(R.id.password_login);
        firebaseAuth=FirebaseAuth.getInstance();
        button=(Button)findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog p= ProgressDialog.show(LoginActivity.this,"Wait for a minute","Waiting",true);
                (firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()))
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {


                                if(task.isSuccessful())

                                {

                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                                    startActivity(intent);
                                    p.dismiss();
                                    Toast.makeText(getApplicationContext(),"login sucessful",Toast.LENGTH_SHORT).show();

                                }else
                                {
                                    Toast.makeText(getApplicationContext(),"login  is not  sucessful",Toast.LENGTH_SHORT).show();
                                    p.dismiss();
                                }

                            }
                        });

            }
        });

    }
    public  void sign(View view)
    {
        Intent i=new Intent(LoginActivity.this,RegisterationActivity.class);
        startActivity(i);
    }


}
