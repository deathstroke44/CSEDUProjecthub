package com.example.cseduprojecthub;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    private Profile_Fragment.OnFragmentInteractionListener mListener;
    private DatabaseReference mDatabase;
    private User curr;
    private FirebaseAuth mAuth;
    private TextView t1,t2,t3,t4,t5;
    private ImageView imageView;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =inflater.inflate(R.layout.fragment_blank, container, false);
        curr=new User();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mAuth=FirebaseAuth.getInstance();
        final FirebaseUser currentUser = mAuth.getCurrentUser();
        t1=view.findViewById(R.id.t1);
        t2=view.findViewById(R.id.t2);
        t3=view.findViewById(R.id.t3);
        t4=view.findViewById(R.id.t4);
        t5=view.findViewById(R.id.t5);
        imageView=view.findViewById(R.id.pic);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot users : dataSnapshot.getChildren())
                {
                    User temp=new User();
                    temp=users.getValue(User.class);
                    if(temp.getMail().equals(currentUser.getEmail()))
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
                        Glide.with(getContext()).using(new FirebaseImageLoader())
                                .load(forestRef)
                                .transform(new CircleTransform(getContext()))
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .into(imageView);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }

}
