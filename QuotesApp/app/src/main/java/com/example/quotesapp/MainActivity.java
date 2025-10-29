package com.example.quotesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView quoteTextView;
    private Button nextButton;
    private Button shareButton;
    private Random random;
    private ArrayList<String> quoteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Random object
        random = new Random();

        // Initialize quote list
        quoteList = new ArrayList<>();
        quoteList.add("Never be afraid to raise your voice for honesty and truth and compassion against injustice and lying and greed. If people all over the world... would do this, it would change the earth. – William Faulkner");
        quoteList.add("The only way to do great work is to love what you do. – Steve Jobs");
        quoteList.add("Believe you can and you're halfway there. – Theodore Roosevelt");
        quoteList.add("The future belongs to those who believe in the beauty of their dreams. – Eleanor Roosevelt");
        quoteList.add("You miss 100% of the shots you don't take. – Wayne Gretzky");
        quoteList.add("The best way to predict the future is to create it. – Peter Drucker");
        quoteList.add("Don't watch the clock; do what it does. Keep going. – Sam Levenson");
        quoteList.add("The only limit to our realization of tomorrow will be our doubts of today. – Franklin D. Roosevelt");
        quoteList.add("Keep your face always toward the sunshine—and shadows will fall behind you. – Walt Whitman");
        quoteList.add("The way to get started is to quit talking and begin doing. – Walt Disney");
        quoteList.add("Your time is limited, so don't waste it living someone else's life. – Steve Jobs");

        // Bind UI components
        quoteTextView = findViewById(R.id.quoteTextView);
        nextButton = findViewById(R.id.nextButton);
        shareButton = findViewById(R.id.shareButton);

        // Display initial random quote
        displayRandomQuote();

        // Set OnClickListener for nextButton
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayRandomQuote();
            }
        });

        // Set OnClickListener for shareButton
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareQuote();
            }
        });
    }

    private void displayRandomQuote() {
        int randomIndex = random.nextInt(quoteList.size());
        String randomQuote = quoteList.get(randomIndex);
        quoteTextView.setText(randomQuote);
    }

    private void shareQuote() {
        String currentQuote = quoteTextView.getText().toString();
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, currentQuote);
        startActivity(Intent.createChooser(shareIntent, "Bagikan Kutipan Melalui..."));
    }
}
