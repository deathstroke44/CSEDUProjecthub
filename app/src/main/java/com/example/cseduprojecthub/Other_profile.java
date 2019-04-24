package com.example.cseduprojecthub;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Other_profile extends AppCompatActivity {

    private Profile_Fragment.OnFragmentInteractionListener mListener;
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabase1;
    private User curr;
    private FirebaseAuth mAuth;
    private TextView t1,t2,t3,t4,t5,t6;
    private ImageView imageView;
    public static String omail="Omi";
    public static String oname="omi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);
        curr=new User();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mDatabase1 = FirebaseDatabase.getInstance().getReference().child("Projects");
        mAuth=FirebaseAuth.getInstance();
        final FirebaseUser currentUser = mAuth.getCurrentUser();
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t4=findViewById(R.id.t4);
        t5=findViewById(R.id.t5);
        t6=findViewById(R.id.summary);
        imageView=findViewById(R.id.pic);
        mDatabase1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String S="";
                int cnt=1;
                for(DataSnapshot users : dataSnapshot.getChildren())
                {
                    ProjectCard temp=new ProjectCard();
                    temp=users.getValue(ProjectCard.class);
                    if(temp.author.equals(oname))
                    {
                        S=S+"["+cnt+"]"+temp.projectName+", "+temp.year+", "+temp.paperLink+"\n\n";
                        cnt+=1;
                    }

                }
                t6.setText(S);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot users : dataSnapshot.getChildren())
                {
                    User temp=new User();
                    temp=users.getValue(User.class);
                    if(temp.getMail().equals(omail))
                    {
                        curr=temp;
                        t1.setText("Name: "+curr.Name);
                        t2.setText("Session: "+curr.Session);
                        t3.setText("Contact: "+curr.Contact);
                        t4.setText("Research Field: "+curr.Research);
                        t5.setText(("Mail: "+curr.Mail));
                        StorageReference storageRef;// = FirebaseStorage.getInstance().getReference();
                        StorageReference forestRef;

                        storageRef = FirebaseStorage.getInstance().getReference();
                        forestRef = storageRef.child("profile/"+curr.Mail+".jpg");
                        Glide.with(Other_profile.this).using(new FirebaseImageLoader())
                                .load(forestRef)
                                .transform(new CircleTransform(Other_profile.this))
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .into(imageView);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
