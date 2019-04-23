package com.example.cseduprojecthub;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UploadFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class UploadFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private EditText name,description,paper,github;
    private Button submit;
    private Spinner year;
    private int y;
    private ArrayList list=new ArrayList();
    public UploadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_upload, container, false);
        for(int i=1990;i<=2019;i++)
        {
            list.add(i);
        }
        y=1990;
        name=view.findViewById(R.id.name);

        description=view.findViewById(R.id.description);
        paper=view.findViewById(R.id.paper);
        github=view.findViewById(R.id.code);
        submit=view.findViewById(R.id.submit);
        year=view.findViewById(R.id.year);
        ArrayAdapter<String> aadapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,list);
        aadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(aadapter);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nm,ds,pl,gl,yr;
                if(name.getText().toString()!=null && name.getText().toString().length()!=0)
                {
                    nm=name.getText().toString();
                }
                else
                {
                    nm="N/A";
                }
                if(description.getText().toString()!=null && description.getText().toString().length()!=0)
                {
                    ds=description.getText().toString();
                }
                else
                {
                    ds="N/A";
                }
                if(paper.getText().toString()!=null && paper.getText().toString().length()!=0)
                {
                    pl=paper.getText().toString();
                }
                else
                {
                    pl="N/A";
                }
                if(github.getText().toString()!=null && github.getText().toString().length()!=0)
                {
                    gl=github.getText().toString();
                }
                else
                {
                    gl="N/A";
                }
                ProjectCard pr=new ProjectCard();
                FirebaseAuth mauth=FirebaseAuth.getInstance();
                FirebaseUser current=mauth.getCurrentUser();

                pr.setAuthor(current.getEmail());
                pr.setProjectName(nm);
                pr.setProjectDescroption(ds);
                pr.setPaperLink(pl);
                pr.setGithubLink(gl);
                pr.setYear(y);
                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference ref=database.getReference("Projects");
                ref.push().setValue(pr);
            }
        });
        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                y=Integer.parseInt(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
