package com.example.myapps;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class landingPage extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_landing_page);

        button = findViewById(R.id.buttonGet);

        // Menambahkan listener untuk menangani klik tombol
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat intent untuk berpindah ke MainActivity
                Intent intent = new Intent(landingPage.this, Login.class);
                startActivity(intent);
            }
        });
    }
}