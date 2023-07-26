package com.example.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.news.MainActivity;
import com.example.news.R;
import com.example.news.models.News;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    List<News> newsList;

    public NewsAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    public NewsAdapter(List<News> newsList, MainActivity.OnItemClickListener onItemClickListener) {
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater =LayoutInflater.from(context);
        View newsView = inflater.inflate(R.layout.item_news, parent, false);
        return new ViewHolder(newsView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.tvTitle.setText(news.getTitle());
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);
        Glide.with(holder.imgImage)
                .load(news.getImageUrl())
                .into(holder.imgImage);
    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(News news);
    }
    private OnItemClickListener listener;

    public NewsAdapter(List<News> list, OnItemClickListener listener) {
        this.newsList = list;
        this.listener = listener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView imgImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            imgImage = itemView.findViewById(R.id.imgNews);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    listener.onItemClick(newsList.get(position));
                }
            });
        }

    }
}
