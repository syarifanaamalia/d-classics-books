package com.example.dclassicsbooks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_detail);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        );

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(android.R.id.content),
                (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                });

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
                new AlertDialog.Builder(this)
                        .setTitle("Validation Error")
                        .setMessage("Address and phone number must be filled.")
                        .setPositiveButton("OK", null)
                        .show();
                return;
            }

            if (!phone.matches("\\d+")) {
                new AlertDialog.Builder(this)
                        .setTitle("Validation Error")
                        .setMessage("Phone number must be numeric.")
                        .setPositiveButton("OK", null)
                        .show();
                return;
            }

            new AlertDialog.Builder(this)
                    .setTitle("Payment Successful")
                    .setMessage("A confirmation email has been sent to your email.")
                    .setPositiveButton("OK", (dialog, which) -> {
                        Intent intent = new Intent(BookDetailActivity.this, BooksActivity.class);
                        startActivity(intent);
                        finish();
                    })
                    .show();
        });
    }
}