package com.example.dclassicsbooks;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class BookDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        ImageView btnBack = findViewById(R.id.btnBack);
        ImageView imgCover = findViewById(R.id.imgCoverDetail);
        ImageView imgRating = findViewById(R.id.imgRatingDetail);
        TextView tvTitle = findViewById(R.id.tvTitleDetail);
        TextView tvAuthor = findViewById(R.id.tvAuthorDetail);
        TextView tvCategory = findViewById(R.id.tvCategoryBadge);
        TextView tvPrice = findViewById(R.id.tvPriceDetail);
        TextView tvSynopsis = findViewById(R.id.tvSynopsisDetail);
        EditText etAddress = findViewById(R.id.etAddress);
        EditText etPhone = findViewById(R.id.etPhone);
        Button btnBuy = findViewById(R.id.btnBuy);

        tvTitle.setText(getIntent().getStringExtra("title"));
        tvAuthor.setText(getIntent().getStringExtra("author"));
        tvPrice.setText(getIntent().getStringExtra("price"));
        tvCategory.setText(getIntent().getStringExtra("category"));
        tvSynopsis.setText(getIntent().getStringExtra("synopsis"));
        imgCover.setImageResource(getIntent().getIntExtra("image", 0));
        imgRating.setImageResource(getIntent().getIntExtra("ratingImage", 0));

        btnBack.setOnClickListener(v -> finish());

        btnBuy.setOnClickListener(v -> {
            String address = etAddress.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();

            if (address.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please complete your delivery address and phone number.", Toast.LENGTH_SHORT).show();
            } else {
                new AlertDialog.Builder(this)
                        .setTitle("Payment Successful")
                        .setMessage("Thank you! Your book will be shipped to your address.")
                        .setPositiveButton("OK", (dialog, which) -> finish())
                        .show();
            }
        });
    }
}