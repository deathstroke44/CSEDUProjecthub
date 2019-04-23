package com.example.cseduprojecthub;

import android.content.Context;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER




    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    //Edit Protion Start
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private DatabaseReference mDatabase;
    private Spinner year_search;
    private String final_choice="None";
    private EditText prefix;
    private Button search;
    ArrayList<String> year_choice=new ArrayList<>();
    List<ProjectCard> list=new ArrayList<>();
    List<ProjectCard> filtered_list=new ArrayList<>();
    List<ProjectCard> filtered_list1=new ArrayList<>();
    Context cn;
    //Edit Protion End

    void init()
    {
        year_choice.clear();
        year_choice.add("Enter Year");
        for(int i=2018;i>=1992;i--)
        {
            year_choice.add(""+i);
        }
    }
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_home, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Projects");
        year_search=view.findViewById(R.id.year);
        prefix=view.findViewById(R.id.prefix);
        search=view.findViewById(R.id.search);
        init();
        ArrayAdapter<String> aadapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,year_choice);
        aadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year_search.setAdapter(aadapter);

        //My Edit
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                filtered_list.clear();
                for(DataSnapshot users : dataSnapshot.getChildren())
                {
                    ProjectCard temp=new ProjectCard();
                    temp=users.getValue(ProjectCard.class);
                    list.add(temp);
                    if(final_choice.equals("None") || final_choice.equals("Enter Year") || final_choice.equals(""+temp.year))
                    {
                        filtered_list.add(temp);
                    }


                }
                filtered_list1.clear();
                String pre=prefix.getText().toString();
                //Toast.makeText(view.getContext(),pre,20).show();
                for(ProjectCard pr:filtered_list)
                {
                    //Toast.makeText(view.getContext(),pr.projectName,20).show();
                    if(pr.projectName.toLowerCase().startsWith(pre.toLowerCase()))
                    {
                        filtered_list1.add(pr);
                    }
                }

                adapter=new ProjectCardAdapter(filtered_list1,view.getContext());

                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
        adapter=new ProjectCardAdapter(list,view.getContext());

        recyclerView.setAdapter(adapter);
        year_search.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                year_choice.set(0,"None");
                String fin=parent.getItemAtPosition(position).toString();
                final_choice=fin;
                if(!fin.equals("Enter Year") && !fin.equals("None"))
                {
                    int x=Integer.parseInt(fin);
                    filtered_list.clear();
                    for(ProjectCard pr:list)
                    {
                        if(pr.year==x)
                        {
                            filtered_list.add(pr);
                        }
                    }
                    filtered_list1.clear();
                    String pre=prefix.getText().toString();
                    //Toast.makeText(view.getContext(),pre,20).show();
                    for(ProjectCard pr:filtered_list)
                    {
                        //Toast.makeText(view.getContext(),pr.projectName,20).show();
                        if(pr.projectName.toLowerCase().startsWith(pre.toLowerCase()))
                        {
                            filtered_list1.add(pr);
                        }
                    }
                    adapter=new ProjectCardAdapter(filtered_list1,view.getContext());

                    recyclerView.setAdapter(adapter);

                }
                else
                {
                    filtered_list.clear();
                    for(ProjectCard pr:list)
                    {
                        filtered_list.add(pr);
                    }
                    filtered_list1.clear();
                    String pre=prefix.getText().toString();
                    //Toast.makeText(view.getContext(),pre,20).show();
                    for(ProjectCard pr:filtered_list)
                    {
                        //Toast.makeText(view.getContext(),pr.projectName,20).show();
                        if(pr.projectName.toLowerCase().startsWith(pre.toLowerCase()))
                        {
                            filtered_list1.add(pr);
                        }
                    }
                    adapter=new ProjectCardAdapter(filtered_list1,view.getContext());

                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtered_list1.clear();
                String pre=prefix.getText().toString();
                //Toast.makeText(view.getContext(),pre,20).show();
                for(ProjectCard pr:filtered_list)
                {
                    //Toast.makeText(view.getContext(),pr.projectName,20).show();
                    if(pr.projectName.toLowerCase().startsWith(pre.toLowerCase()))
                    {
                        filtered_list1.add(pr);
                    }
                }
                adapter=new ProjectCardAdapter(filtered_list1,view.getContext());

                recyclerView.setAdapter(adapter);
            }

        });

        //My Edit

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event

}
