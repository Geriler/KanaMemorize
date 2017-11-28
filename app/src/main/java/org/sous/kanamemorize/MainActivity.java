package org.sous.kanamemorize;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    static public String nameAlph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }
    public void btnOnClick (View view) {
        Intent intent = new Intent(MainActivity.this, SymbolActivity.class);
        switch (view.getId()) {
            case R.id.hiraBtn:
                nameAlph = "Hiragana";
                startActivity(intent);
                break;
            case R.id.kataBtn:
                nameAlph = "Katakana";
                startActivity(intent);
                break;
        }
    }
    public void tutorialBtnOnClick (View view) {

    }
}