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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.sViewHolder>{

    List<User> list=new ArrayList<>();
    private Context context;
    static String URL = "https://arxiv.org/pdf/1706.03762.pdf";

    public SessionAdapter(List<User> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public class sViewHolder extends RecyclerView.ViewHolder
    {
        public TextView t;
        ImageView imageView;
        public CardView cv;
        public User user;
        public sViewHolder(View itemView) {
            super(itemView);
            t=itemView.findViewById(R.id.username);
            imageView=itemView.findViewById(R.id.prpic);
            cv=(CardView) itemView.findViewById(R.id.cv);

        }


    }

    @NonNull
    @Override
    public SessionAdapter.sViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_card,parent,false);
        return new sViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final SessionAdapter.sViewHolder sViewHolder, int i) {
        final User obj=list.get(i);
        sViewHolder.t.setText(obj.Name);
        sViewHolder.user=obj;
        StorageReference storageRef;// = FirebaseStorage.getInstance().getReference();
        StorageReference forestRef;

        storageRef = FirebaseStorage.getInstance().getReference();
        forestRef = storageRef.child("profile/"+obj.Mail+".jpg");
        Glide.with(context).using(new FirebaseImageLoader())
                .load(forestRef)
                .transform(new CircleTransform(context))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(sViewHolder.imageView);
        sViewHolder.t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Other_profile.omail=obj.Mail;
                Other_profile.oname=obj.Name;
                Intent myIntent = new Intent(context, Other_profile.class);
                context.startActivity(myIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }
}
