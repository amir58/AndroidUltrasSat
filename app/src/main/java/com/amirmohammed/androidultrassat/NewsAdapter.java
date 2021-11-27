package com.amirmohammed.androidultrassat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amirmohammed.androidultrassat.news.ArticlesItem;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    List<ArticlesItem> articlesItemList;

    public NewsAdapter(List<ArticlesItem> articlesItemList) {
        this.articlesItemList = articlesItemList;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        ArticlesItem article = articlesItemList.get(position);

        holder.textView.setText(article.getTitle());

        Glide.with(holder.imageView).load(article.getUrlToImage())
                .placeholder(R.drawable.image_not_found).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return articlesItemList.size();
    }


    class NewsHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.news_image);
            textView = itemView.findViewById(R.id.news_title);
        }
    }
}
