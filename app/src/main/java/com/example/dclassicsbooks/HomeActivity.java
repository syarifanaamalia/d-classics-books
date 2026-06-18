package com.example.dclassicsbooks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

public class HomeActivity extends AppCompatActivity {

    TextView tvGreeting;
    ViewPager2 viewPager2;
    DrawerLayout drawerLayout;
    ImageView btnMenu, btnClose;
    ImageView btnNext, btnPrev;
    RecyclerView rvBooks;
    LinearLayout btnNavHome, btnNavBooks, btnMenuStore, btnNavLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvGreeting = findViewById(R.id.tvGreeting);
        viewPager2 = findViewById(R.id.viewPager);
        drawerLayout = findViewById(R.id.drawerLayout);
        btnMenu = findViewById(R.id.btnMenu);
        btnClose = findViewById(R.id.btnClose);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);
        rvBooks = findViewById(R.id.rvBooks);

        btnNavHome = findViewById(R.id.btnNavHome);
        btnNavBooks = findViewById(R.id.btnNavBooks);
        btnMenuStore = findViewById(R.id.btnMenuStore);
        btnNavLogout = findViewById(R.id.btnNavLogout);

        SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
        String username = prefs.getString("USERNAME", "User");
        tvGreeting.setText("Hello, " + username + "!");

        int[] images = { R.drawable.banner1, R.drawable.banner2, R.drawable.banner3 };

        BannerAdapter adapter = new BannerAdapter(images);
        viewPager2.setAdapter(adapter);

        viewPager2.setPageTransformer((page, position) -> {
            page.setAlpha(0.7f + (1 - Math.abs(position)));
            page.setScaleY(0.85f + (1 - Math.abs(position)) * 0.15f);
        });

        btnMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        btnClose.setOnClickListener(v -> drawerLayout.closeDrawer(GravityCompat.START));

        btnNext.setOnClickListener(v -> {
            int next = viewPager2.getCurrentItem() + 1;
            if (next < adapter.getItemCount()) viewPager2.setCurrentItem(next);
        });

        btnPrev.setOnClickListener(v -> {
            int prev = viewPager2.getCurrentItem() - 1;
            if (prev >= 0) viewPager2.setCurrentItem(prev);
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                int last = viewPager2.getAdapter().getItemCount() - 1;
                if (position == 0) {
                    btnPrev.setBackgroundResource(R.drawable.bg_button_prev_inactive);
                    btnNext.setBackgroundResource(R.drawable.bg_button_next);
                } else if (position == last) {
                    btnPrev.setBackgroundResource(R.drawable.bg_button_prev);
                    btnNext.setBackgroundResource(R.drawable.bg_button_next_inactive);
                } else {
                    btnPrev.setBackgroundResource(R.drawable.bg_button_prev);
                    btnNext.setBackgroundResource(R.drawable.bg_button_next);
                }
            }
        });

        btnPrev.setBackgroundResource(R.drawable.bg_button_prev_inactive);
        btnNext.setBackgroundResource(R.drawable.bg_button_next);

        String[] titles = { "The Floating World", "The Psychology of Money", "The Hobbit", "The Blanket Cats" };
        String[] authors = { "Axie Oh", "Morgan Housel", "J. R. R. Tolkien", "Kiyoshi Shigematsu" };
        String[] prices = { "Rp125.000", "Rp110.000", "Rp230.000", "Rp150.000" };
        int[] bookImages = { R.drawable.the_floating_world, R.drawable.the_psychology_of_money, R.drawable.the_hobbit, R.drawable.the_blanket_cats };

        rvBooks.setLayoutManager(new LinearLayoutManager(this));
        rvBooks.setAdapter(new BookAdapter(titles, authors, prices, bookImages));

        btnMenuStore.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, StoresActivity.class)));

        btnNavBooks.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, BooksActivity.class)));

        btnNavLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = getSharedPreferences("MyApp", MODE_PRIVATE).edit();
            editor.clear();
            editor.apply();
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            finishAffinity();
        });
    }
}