package com.example.cseduprojecthub;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class PDF_Viewer extends AppCompatActivity {

    WebView webView;
    static String pdf_url;
    static ProjectCard pr;
    TextView name,year,desc,plink,glink;
    Button b5,b6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String url = "http://docs.google.com/gview?embedded=true&url="+ pdf_url;
        setContentView(R.layout.activity_pdf);
        name=findViewById(R.id.name);
        name.setText(pr.projectName);
        year=findViewById(R.id.year);
        year.setText(pr.author+", "+pr.year+", "+pr.topic);
        desc=findViewById(R.id.desc);
        desc.setText(pr.projectDescroption);
        plink=findViewById(R.id.plink);
        plink.setText("Paper Link: "+pr.paperLink);

        glink=findViewById(R.id.glink);
        glink.setText("Github Link: "+pr.githubLink);
        b5=findViewById(R.id.b5);
        b6=findViewById(R.id.b6);
        webView=(WebView) findViewById(R.id.webview);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl(url);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadTask(PDF_Viewer.this,pr.paperLink);
            }
        });
        //webView.loadUrl(url);


    }
}
