package com.example.news;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.news.adapter.NewsAdapter;
import com.example.news.models.News;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public interface OnItemClickListener {
        void onItemClick(News news);
    }
    private ActivityResultLauncher cameraLauncher;
    private ActivityResultLauncher galleryLauncher;
    public int CHOOSEPHOTO = 0;
    List<News> newsList;
    NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsList = News.getNews();
        RecyclerView rcNews = findViewById(R.id.rcNews);
        rcNews.setLayoutManager(new LinearLayoutManager(this));
        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);

        newsAdapter = new NewsAdapter(newsList, new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(News news) {
                String url = news.getUrl();

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        rcNews.setAdapter(newsAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(view);
            }
        });
    }

    public void showDialog(View v) {
        View viewDialog = getLayoutInflater().inflate(R.layout.dialog_news, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setView(viewDialog);
        AlertDialog alert = builder.create();
        alert.show();
        ImageButton btnSelectImage = viewDialog.findViewById(R.id.btnSelectImage);

        btnSelectImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                CharSequence[] options = {"Take photo", "Choose from gallery", "Exit"};

                builder.setItems(options, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (options[which].equals("Take photo")) {

                            Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            cameraLauncher.launch(takePhotoIntent);

                        } else if (options[which].equals("Choose from gallery")) {

                            Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK);

                            galleryLauncher.launch(pickPhotoIntent);

                        }

                    }

                });

                // Hiển thị dialog
                builder.show();

            }

        });
        viewDialog.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button btnSave = viewDialog.findViewById(R.id.btnSave);
                EditText edtTitle = viewDialog.findViewById(R.id.edtTitle);
                EditText edtUrl = viewDialog.findViewById(R.id.edtUrl);
                EditText edtImage = viewDialog.findViewById(R.id.edtImage);
                String title;
                String url;
                String imageUrl;
                title = edtTitle.getText().toString();
                url = edtUrl.getText().toString();
                imageUrl = edtImage.getText().toString();
                News news = new News(title, url, imageUrl);
                newsList.add(news);
                Toast.makeText(viewDialog.getContext(), "thêm dữ liệu thành công",
                        Toast.LENGTH_SHORT).show();
                alert.dismiss();
            }
        });
    }

}