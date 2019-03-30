package com.project.fabricio.algoritmosjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Configurando Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Algorítmos em JAVA");
        setSupportActionBar(toolbar);
    }
}
