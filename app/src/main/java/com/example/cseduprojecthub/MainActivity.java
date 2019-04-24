package com.example.cseduprojecthub;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText mail,pass;
    private Button submit;
    final FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref=database.getReference("Projects");
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mail=findViewById(R.id.mail);
        pass=findViewById(R.id.pass);
        submit=findViewById(R.id.submit);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null)
        {
            Intent myIntent = new Intent(MainActivity.this, home.class);

            MainActivity.this.startActivity(myIntent);
            finish();
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String m=mail.getText().toString();
                String p=pass.getText().toString();
                if(!(m==null || p==null || m.length()<=7 || p.length()<=5))
                {
                    SignIn(m,p);
                }
            }
        });




        //new DownloadTask(MainActivity.this, URL);
        //super.onCreate(savedInstanceState);
        for(int i=0;i<=0;i++)
        {
           // ref.push().setValue(new ProjectCard());
        }
        //setContentView(R.layout.activity_main);

        String URL = "https://arxiv.org/pdf/1706.03762.pdf";
        //Intent myIntent = new Intent(MainActivity.this, home.class);
        //MainActivity.this.startActivity(myIntent);
        //finish();
        //new DownloadTask(MainActivity.this, URL);

    }
    private void SignIn(String m,String p)
    {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(m,p)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information

                        FirebaseUser user = mAuth.getCurrentUser();


                        FirebaseAuth auth= FirebaseAuth.getInstance();
                        for(int i=0;i<=5;i++)
                        {
                            //ref.push().setValue(new ProjectCard());
                        }


                        String URL = "https://arxiv.org/pdf/1706.03762.pdf";
                        Intent myIntent = new Intent(MainActivity.this, home.class);

                        MainActivity.this.startActivity(myIntent);
                        finish();

                    } else {
                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();

                    }

                    // ...
                }
            });
    }
}
