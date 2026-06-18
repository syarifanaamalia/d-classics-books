package com.example.dclassicsbooks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class BooksActivity extends AppCompatActivity {
    DrawerLayout drawerLayoutBooks;
    ImageView btnMenuBooksPage, btnCloseBooksPage;
    TextView tabNonFiction, tabFiction;
    RecyclerView rvAllBooks;
    AllBooksAdapter adapter;
    List<Book> allBooksList;
    LinearLayout btnNavHomeBooks, btnNavStoreBooks, btnNavLogoutBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        drawerLayoutBooks = findViewById(R.id.drawerLayoutBooks);
        btnMenuBooksPage = findViewById(R.id.btnMenuBooksPage);
        btnCloseBooksPage = findViewById(R.id.btnCloseBooksPage);
        tabNonFiction = findViewById(R.id.tabNonFiction);
        tabFiction = findViewById(R.id.tabFiction);
        rvAllBooks = findViewById(R.id.rvAllBooks);
        btnNavHomeBooks = findViewById(R.id.btnNavHomeBooks);
        btnNavStoreBooks = findViewById(R.id.btnNavStoreBooks);
        btnNavLogoutBooks = findViewById(R.id.btnNavLogoutBooks);

        loadBooks();

        adapter = new AllBooksAdapter(allBooksList);
        rvAllBooks.setLayoutManager(new LinearLayoutManager(this));
        rvAllBooks.setAdapter(adapter);

        filter("Non-Fiction");

        btnMenuBooksPage.setOnClickListener(v -> drawerLayoutBooks.openDrawer(GravityCompat.START));

        btnCloseBooksPage.setOnClickListener(v -> drawerLayoutBooks.closeDrawer(GravityCompat.START));

        btnNavHomeBooks.setOnClickListener(v -> {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        });
        btnNavStoreBooks.setOnClickListener(v -> {
            startActivity(new Intent(this, StoresActivity.class));
            finish();
        });
        btnNavLogoutBooks.setOnClickListener(v -> {
            SharedPreferences.Editor editor = getSharedPreferences("MyApp", MODE_PRIVATE).edit();
            editor.clear();
            editor.apply();
            startActivity(new Intent(this, LoginActivity.class));
            finishAffinity();
        });

        tabNonFiction.setOnClickListener(v -> {
            tabNonFiction.setBackgroundColor(Color.parseColor("#2C4554"));
            tabNonFiction.setTextColor(Color.WHITE);
            tabFiction.setBackgroundResource(R.drawable.edittext_bg);
            tabFiction.setTextColor(Color.BLACK);
            filter("Non-Fiction");
        });

        tabFiction.setOnClickListener(v -> {
            tabFiction.setBackgroundColor(Color.parseColor("#2C4554"));
            tabFiction.setTextColor(Color.WHITE);
            tabNonFiction.setBackgroundResource(R.drawable.edittext_bg);
            tabNonFiction.setTextColor(Color.BLACK);
            filter("Fiction");
        });
    }

    private void loadBooks() {
        allBooksList = new ArrayList<>();

        allBooksList.add(new Book("The Psychology of Money", "Morgan Housel", "Rp110.000", R.drawable.the_psychology_of_money, R.drawable.rating5, "Non-Fiction", "The Psychology of Money by Morgan Housel explains that financial success is not just about knowledge or intelligence, but largely about behavior and how people manage their emotions around money. Through 19 short stories, the book highlights how real-life financial decisions are influenced by personal experiences, mindset, ego, and social factors."));
        allBooksList.add(new Book("Atomic Habits", "James Clear", "Rp120.000", R.drawable.atomic_habits, R.drawable.rating4, "Non-Fiction", "Atomic Habits by James Clear explains that small, consistent changes can lead to remarkable long-term results through the power of compounded habits. While minor improvements may seem insignificant at first, they accumulate over time to create meaningful transformation."));
        allBooksList.add(new Book("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", "Rp180.000", R.drawable.sapiens, R.drawable.rating5, "Non-Fiction", "Sapiens explores the history of humanity from prehistoric times to the modern era, tracing the evolution of Homo sapiens and the key revolutions that shaped human society."));
        allBooksList.add(new Book("The Subtle Art of Not Giving a F*ck", "Mark Manson", "Rp150.000", R.drawable.the_subtle_art, R.drawable.rating5, "Non-Fiction", "The Subtle Art of Not Giving a F*ck by Mark Manson challenges the idea that constant positivity leads to happiness, arguing instead that life's struggles and limitations must be accepted."));
        allBooksList.add(new Book("Educated", "Tara Westover", "Rp123.500", R.drawable.educated, R.drawable.rating4, "Non-Fiction", "Tara Westover's memoir Educated tells the story of a girl raised in a survivalist family with no formal schooling, birth records, or medical care, who grows up learning practical skills instead of academics."));
        allBooksList.add(new Book("Making Shapely Fiction", "Jerome Stern", "Rp76.500", R.drawable.making_shapely_fiction, R.drawable.rating4, "Non-Fiction", "Making Shapely Fiction by Jerome Stern is a practical guide to writing short stories, introducing writers to a variety of story shapes and structures that can be used to craft engaging fiction."));

        allBooksList.add(new Book("The Hobbit", "J. R. R. Tolkien", "Rp230.000", R.drawable.the_hobbit, R.drawable.rating4, "Fiction", "The classic fantasy novel about Bilbo Baggins, a comfort-loving hobbit whose quiet life is turned upside down when he joins the wizard Gandalf and thirteen dwarves on a journey to reclaim treasure."));
        allBooksList.add(new Book("The Blanket Cats", "Kiyoshi Shigematsu", "Rp150.000", R.drawable.the_blanket_cats, R.drawable.rating4, "Fiction", "In a small shop in Tokyo, a unique pet shop allows customers to rent special cats known as the Blanket Cats for three days and two nights to help them navigate life challenges."));
        allBooksList.add(new Book("The Floating World", "Axie Oh", "Rp125.000", R.drawable.the_floating_world, R.drawable.rating4, "Fiction", "A cyberpunk-inspired sci-fi adventure where a young woman must navigate a dangerous city and confront her past to survive in a unforgiving world."));
        allBooksList.add(new Book("The Night Circus", "Erin Morgenstern", "Rp161.500", R.drawable.the_night_circus, R.drawable.rating5, "Fiction", "The Night Circus arrives without warning. It is simply there, when yesterday it was not. Within the black-and-white striped canvas tents is an utterly unique experience full of breathtaking amazements."));
        allBooksList.add(new Book("Harry Potter and the Sorcerer's Stone", "J. K. Rowling", "Rp112.000", R.drawable.harry_potter, R.drawable.rating5, "Fiction", "Harry Potter and the Sorcerer's Stone is the first novel in the Harry Potter series, following a young wizard who discovers his magical heritage on his eleventh birthday."));
        allBooksList.add(new Book("To Kill A Mockingbird", "Harper Lee", "Rp97.000", R.drawable.to_kill_a_mockingbird, R.drawable.rating4, "Fiction", "A gripping, heart-wrenching, and wholly remarkable tale of coming-of-age in a South poisoned by virulent prejudice, it views a world of great beauty and savage inequities through the eyes of a young girl."));
    }

    private void filter(String category) {
        List<Book> temp = new ArrayList<>();
        for (Book b : allBooksList) {
            if (b.getCategory().equals(category)) temp.add(b);
        }
        adapter.updateData(temp);
    }
}