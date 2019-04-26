package com.e.fragementlcyle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private FrameLayout frameLayout;
    private String TAG ="TAG";
    private  FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);
        frameLayout =  findViewById(R.id.container_fragement);
        addFragement();
    }

    private void addFragement() {

        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        MyFragement myFragement = new MyFragement();
        transaction.add(R.id.container_fragement,myFragement);
        transaction.commit();
        Button replce = findViewById(R.id.fragement_change);
        replce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Toast.makeText(MainActivity.this, "button click", Toast.LENGTH_SHORT).show();
                SecondFragement secondFragement = new SecondFragement();
                transaction.replace(R.id.container_fragement,secondFragement);
                // transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        Button remove = findViewById(R.id.fragement_remove);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "button click", Toast.LENGTH_SHORT).show();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragment =   fragmentManager.findFragmentById(R.id.container_fragement);
                fragmentTransaction.remove(fragment);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    public void onBackPressed() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentById(R.id.container_fragement);
        if(fragment != null)
        {
            Toast.makeText(this, "insdie if", Toast.LENGTH_SHORT).show();
            transaction.remove(fragment);
            transaction.commit();
        }
        else
        {
            Toast.makeText(this, "inside else", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }

    }
}
