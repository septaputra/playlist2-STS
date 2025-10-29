package com.example.onlineimageapp;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Binding ImageViews
        ImageView onlineImage1 = findViewById(R.id.onlineImage1);
        ImageView onlineImage2 = findViewById(R.id.onlineImage2);

        // Define image URLs
        String imageUrl1 = "https://picsum.photos/800/600?random=1";
        String imageUrl2 = "https://picsum.photos/800/600?random=2";

        // Load images using Picasso with placeholder
        Picasso.get()
            .load(imageUrl1)
            .placeholder(R.drawable.logo_loading)
            .into(onlineImage1);

        Picasso.get()
            .load(imageUrl2)
            .placeholder(R.drawable.logo_loading)
            .into(onlineImage2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
