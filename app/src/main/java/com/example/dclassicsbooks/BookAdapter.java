package com.example.dclassicsbooks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    String[] titles, authors, prices;
    int[] images;

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public BookAdapter(String[] titles, String[] authors, String[] prices, int[] images) {
        this.titles = titles;
        this.authors = authors;
        this.prices = prices;
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(titles[position]);
        holder.tvAuthor.setText(authors[position]);
        holder.tvPrice.setText(prices[position]);
        holder.imgBook.setImageResource(images[position]);

        if (position == 0) {
            holder.imgRating.setImageResource(R.drawable.rating5);
        } else if (position == 1) {
            holder.imgRating.setImageResource(R.drawable.rating5);
        } else if (position == 2) {
            holder.imgRating.setImageResource(R.drawable.rating4);
        } else {
            holder.imgRating.setImageResource(R.drawable.rating4);
        }
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBook, imgRating;
        TextView tvTitle, tvAuthor, tvPrice;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            imgBook = itemView.findViewById(R.id.imgBook);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            imgRating = itemView.findViewById(R.id.imgRating);

            itemView.setOnClickListener(v -> {
                if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}