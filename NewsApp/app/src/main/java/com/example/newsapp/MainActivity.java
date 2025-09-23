package com.example.newsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import androidx.annotation.NonNull;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // private WebView webView;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    drawerLayout = findViewById(R.id.drawer_layout);
    navigationView = findViewById(R.id.nav_view);
    // webView = findViewById(R.id.webView);
    toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawerLayout.addDrawerListener(drawerToggle);
    drawerToggle.syncState();

    // WebSettings webSettings = webView.getSettings();
    // webSettings.setJavaScriptEnabled(true);
    // webView.setWebViewClient(new WebViewClientController());
    // webView.loadUrl("https://www.cnnindonesia.com/");

    // Tampilkan fragment default (CNN Indonesia)
    showNewsFragment("https://www.cnnindonesia.com/");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                String url = "";
                String title = "News App";
                if (id == navigationView.getMenu().findItem(R.id.nav_cnnindo).getItemId()) {
                    url = "https://www.cnnindonesia.com/";
                    title = "CNN Indonesia";
                } else if (id == navigationView.getMenu().findItem(R.id.nav_liputan6).getItemId()) {
                    url = "https://www.liputan6.com/";
                    title = "Liputan6";
                } else if (id == navigationView.getMenu().findItem(R.id.nav_detik).getItemId()) {
                    url = "https://www.detik.com/";
                    title = "Detik";
                } else if (id == navigationView.getMenu().findItem(R.id.nav_kompas).getItemId()) {
                    url = "https://www.kompas.com/";
                    title = "Kompas";
                } else if (id == navigationView.getMenu().findItem(R.id.nav_metrotv).getItemId()) {
                    url = "https://www.metrotvnews.com/";
                    title = "MetroTV News";
                } else if (id == navigationView.getMenu().findItem(R.id.nav_tvone).getItemId()) {
                    url = "https://www.tvonenews.com/";
                    title = "TVOneNews";
                }
                toolbar.setTitle(title);
                showNewsFragment(url);
                drawerLayout.closeDrawers();
                return true;
            }
        });

    }

    private void showNewsFragment(String url) {
        Fragment fragment = NewsWebViewFragment.newInstance(url);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}