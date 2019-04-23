package com.example.cseduprojecthub;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProjectCardAdapter extends RecyclerView.Adapter<ProjectCardAdapter.sViewHolder>{

    List<ProjectCard> list=new ArrayList<>();
    private Context context;
    static String URL = "https://arxiv.org/pdf/1706.03762.pdf";

    public ProjectCardAdapter(List<ProjectCard> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public class sViewHolder extends RecyclerView.ViewHolder
    {
        public TextView t1,t2,t3,t4,t5;
        public String pdf_url;
        public Button b5,b6;
        public CardView cv;
        public ProjectCard projectCard;
        public sViewHolder(View itemView) {
            super(itemView);
            t1=(TextView) itemView.findViewById(R.id.t1);
            t2=(TextView) itemView.findViewById(R.id.t2);
            t3=(TextView) itemView.findViewById(R.id.t3);
            t4=(TextView) itemView.findViewById(R.id.t4);
            b5=(Button) itemView.findViewById(R.id.b5);
            b6=(Button) itemView.findViewById(R.id.b6);
            cv=(CardView) itemView.findViewById(R.id.cv);

        }


    }

    @NonNull
    @Override
    public ProjectCardAdapter.sViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_card,parent,false);
        return new sViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProjectCardAdapter.sViewHolder sViewHolder, int i) {
        final ProjectCard obj=list.get(i);
        sViewHolder.t1.setText(obj.projectName);
        sViewHolder.projectCard=obj;
        //sViewHolder.t2.setText(obj.projectDescroption);
        sViewHolder.t2.setText("Author: "+obj.author);
        //sViewHolder.t3.setText(obj.paperLink);
        sViewHolder.t3.setText("Year: "+obj.year);
        //sViewHolder.t4.setText(obj.githubLink);
        sViewHolder.t4.setText("Research Field: "+obj.topic);
        sViewHolder.pdf_url=obj.paperLink;
        sViewHolder.b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadTask(context, sViewHolder.pdf_url);
            }
        });
        sViewHolder.b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PDF_Viewer.pdf_url=obj.paperLink;
                PDF_Viewer.pr=obj;
                Intent myIntent = new Intent(context, PDF_Viewer.class);
                context.startActivity(myIntent);

            }
        });
        sViewHolder.b5.setVisibility(View.GONE);
        //sViewHolder.b6.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }
}
