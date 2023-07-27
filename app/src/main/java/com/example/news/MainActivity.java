package com.example.news;

import androidx.activity.result.ActivityResult;
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
    private Uri imageUri;
    private Bitmap imageBitmap;

    private ActivityResultLauncher<Intent> cameraLauncher;
    private ActivityResultLauncher<String> galleryLauncher;
    public interface OnItemClickListener {
        void onItemClick(News news);
    }

    public int CHOOSEPHOTO = 0;
    List<News> newsList;
    NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                this::onCameraResult);

        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                this::onGalleryResult);

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
        ImageButton btnSelectedImage = viewDialog.findViewById(R.id.btnSelectImage);
        btnSelectedImage.setOnClickListener(view -> {
            showImageSelectDialog();
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

                News news = new News(title, url, imageUri != null ? imageUri.toString() : imageUrl);
                if (imageUri != null) {
                    news = new News(title, url, imageUri.toString());
                } else if (imageBitmap != null) {
                    news = new News(title, url, null);
                    news.setImageBitmap(imageBitmap);
                }

                newsList.add(news);

                // Reset
                imageUri = null;
                imageBitmap = null;
//                imageUrl = edtImage.getText().toString();
//                News news = new News(title, url, imageUrl);
//                newsList.add(news);
                Toast.makeText(viewDialog.getContext(), "thêm dữ liệu thành công",
                        Toast.LENGTH_SHORT).show();
                alert.dismiss();
            }
        });
    }
    private void onCameraResult(ActivityResult result) {
        if (result.getResultCode() == Activity.RESULT_OK) {
            Intent data = result.getData();
            imageBitmap = (Bitmap) data.getExtras().get("data");
            Bitmap photo = (Bitmap) data.getExtras().get("data");
        }
    }

    private void onGalleryResult(Uri uri) {
        imageUri = uri;
    }
    private void showImageSelectDialog() {

        new AlertDialog.Builder(this)
                .setItems(new String[]{"Chụp ảnh", "Chọn ảnh", "Huỷ"}, (dialog, which) -> {
                    if (which == 0) {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        cameraLauncher.launch(cameraIntent);
                    } else if (which == 1) {
                        galleryLauncher.launch("image/*");
                    }
                }).show();
    }

}