package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String favoritedBookNamesKey = "favoritedBookNamesKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = findViewById(R.id.gridview);
        final BooksAdapter booksAdapter = new BooksAdapter(this, books);
        gridView.setAdapter(booksAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Book book = books[position];
                book.toggleFavorite();

                booksAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        final ArrayList<Integer> favoritedBookNames = new ArrayList<>();
        for (Book book : books) {
            if (book.getIsFavorite()) {
                favoritedBookNames.add(book.getName());
            }
        }

        outState.putIntegerArrayList(favoritedBookNamesKey, favoritedBookNames);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        final ArrayList<Integer> favoritedBookNames =
                savedInstanceState.getIntegerArrayList(favoritedBookNamesKey);

        for (int bookName : favoritedBookNames) {
            for (Book book : books) {
                if (book.getName() == bookName) {
                    book.setIsFavorite(true);
                    break;
                }
            }
        }
    }

    private Book[] books = {
            new Book(R.string.abc_an_amazing_alphabet_book, R.string.dr_seuss, R.drawable.abc),
            new Book(R.string.are_you_my_mother, R.string.dr_seuss, R.drawable.areyoumymother),
            new Book(R.string.where_is_babys_belly_button, R.string.karen_katz, R.drawable.whereisbabysbellybutton),
            new Book(R.string.on_the_night_you_were_born, R.string.nancy_tillman, R.drawable.onthenightyouwereborn),
            new Book(R.string.hand_hand_fingers_thumb, R.string.dr_seuss, R.drawable.handhandfingersthumb),
            new Book(R.string.the_very_hungry_caterpillar, R.string.eric_carle, R.drawable.theveryhungrycaterpillar),
            new Book(R.string.the_going_to_bed_book, R.string.sandra_boynton, R.drawable.thegoingtobedbook),
            new Book(R.string.oh_baby_go_baby, R.string.dr_seuss, R.drawable.ohbabygobaby),
            new Book(R.string.the_tooth_book, R.string.dr_seuss, R.drawable.thetoothbook),
            new Book(R.string.one_fish_two_fish_red_fish_blue_fish, R.string.dr_seuss, R.drawable.onefish)
    };
}
