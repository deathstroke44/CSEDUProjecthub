package com.example.cseduprojecthub;

import android.app.DownloadManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

    public class DownloadSongService extends IntentService {
        private static final String DOWNLOAD_PATH = "com.spartons.androiddownloadmanager_DownloadSongService_Download_path";
        private static final String DESTINATION_PATH = "com.spartons.androiddownloadmanager_DownloadSongService_Destination_path";
        public DownloadSongService() {
            super("DownloadSongService");
        }
        public Intent getDownloadService(final @NonNull Context callingClassContext, final @NonNull String downloadPath, final @NonNull String destinationPath) {
            return new Intent(callingClassContext, DownloadSongService.class)
                    .putExtra(DOWNLOAD_PATH, downloadPath)
                    .putExtra(DESTINATION_PATH, destinationPath);
        }
        @Override
        protected void onHandleIntent(@Nullable Intent intent) {
            String downloadPath = intent.getStringExtra(DOWNLOAD_PATH);
            String destinationPath = intent.getStringExtra(DESTINATION_PATH);
            startDownload(downloadPath, destinationPath);
        }
        private void startDownload(String downloadPath, String destinationPath) {
            Uri uri = Uri.parse(downloadPath); // Path where you want to download file.
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);  // Tell on which network you want to download file.
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);  // This will show notification on top when downloading the file.
            request.setTitle("Downloading a file"); // Title for notification.
            request.setVisibleInDownloadsUi(true);
            request.setDestinationInExternalPublicDir(destinationPath, uri.getLastPathSegment());  // Storage directory path
            ((DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(request); // This will start downloading
        }
    }

    DownloadManager downloadManager;

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
                //new DownloadTask(PDF_Viewer.this,pr.paperLink);
                downloadManager=(DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri=Uri.parse(pr.paperLink);
                DownloadManager.Request request=new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference=downloadManager.enqueue(request);

            }
        });
        //webView.loadUrl(url);


    }
}
