package com.example.cseduprojecthub;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Session.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Session extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private DatabaseReference mDatabase;
    private ArrayList<User> userlist=new ArrayList<>();

    private ArrayList<User> filtered_list=new ArrayList<>();
    private ArrayList<String> year_choice=new ArrayList<>();
    private String fn_choice="NONE";
    private Spinner year_search;
    Context cn;
    public Session() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view= inflater.inflate(R.layout.fragment_session, container, false);
        year_search=view.findViewById(R.id.sess);
        year_choice.add("NONE");
        fn_choice="NONE";
        for(int i=1990;i<=2019;i++)
        {

            String ss=i+"-";
            if((i+1)<2000)
            {
                ss+=""+((i+1)-1900);
            }
            else if((i+1)==2000)
            {
                ss+="00";
            }
            else if((i+1)<=2009)
            {
                ss+="0"+(i+1-2000);
            }
            else
            {
                ss+=(i+1-2000);
            }

            year_choice.add(ss);
        }




        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userlist.clear();
                filtered_list.clear();
                for(DataSnapshot users : dataSnapshot.getChildren())
                {

                    User temp=new User();
                    temp=users.getValue(User.class);
                    if(temp.Session.equals(fn_choice) || fn_choice.equals("NONE"))
                    {
                        filtered_list.add(temp);
                    }
                    userlist.add(temp);


                }
                adapter=new SessionAdapter(filtered_list,view.getContext());

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        ArrayAdapter<String> aadapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,year_choice);
        aadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year_search.setAdapter(aadapter);



        year_search.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //year_choice.set(0,"None");
                String fin=parent.getItemAtPosition(position).toString();
                fn_choice=fin;
                filtered_list.clear();
                for(User user:userlist)
                {
                    if(user.Session.equals(fn_choice) ||fn_choice.equals("NONE"))
                    {
                        filtered_list.add(user);
                    }
                }
                adapter=new SessionAdapter(filtered_list,view.getContext());

                recyclerView.setAdapter(adapter);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        recyclerView=view.findViewById(R.id.recycler);
        cn=view.getContext();
        getActivity().setTitle("CSEDU ProjectHub");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        recyclerView.setHasFixedSize(true);
        adapter=new SessionAdapter(userlist,view.getContext());

        recyclerView.setAdapter(adapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
