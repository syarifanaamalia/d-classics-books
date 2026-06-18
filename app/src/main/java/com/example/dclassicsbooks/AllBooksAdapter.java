package com.example.dclassicsbooks;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AllBooksAdapter extends RecyclerView.Adapter<AllBooksAdapter.ViewHolder> {
    private List<Book> bookList;

    public AllBooksAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void updateData(List<Book> newList) {
        this.bookList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.tvTitle.setText(book.getTitle());
        holder.tvAuthor.setText(book.getAuthor());
        holder.tvPrice.setText(book.getPrice());
        holder.imgBook.setImageResource(book.getImage());
        holder.imgRating.setImageResource(book.getRatingImage());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), BookDetailActivity.class);
            intent.putExtra("title", book.getTitle());
            intent.putExtra("author", book.getAuthor());
            intent.putExtra("price", book.getPrice());
            intent.putExtra("image", book.getImage());
            intent.putExtra("ratingImage", book.getRatingImage());
            intent.putExtra("category", book.getCategory());
            intent.putExtra("synopsis", book.getSynopsis());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() { return bookList.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBook, imgRating;
        TextView tvTitle, tvAuthor, tvPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBook = itemView.findViewById(R.id.imgBook);
            imgRating = itemView.findViewById(R.id.imgRating);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}