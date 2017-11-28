package org.sous.kanamemorize;

import android.annotation.TargetApi;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;

public class SymbolActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private TextToSpeech tts;
    private Button btnSound;
    private Symbol checkedSymbol;
    private Symbol result;
    private GroupSymbols groupSymbols;
    private String[] hiraAlph = {"あ", "い", "う", "え", "お", "か", "き", "く", "け", "こ",
            "さ", "し", "す", "せ", "そ", "た", "ち", "つ", "て", "と",
            "な", "に", "ぬ", "ね", "の", "は", "ひ", "ふ", "へ", "ほ",
            "ま", "み", "む", "め", "も", "や", "ゆ", "よ",
            "ら", "り", "る", "れ", "ろ", "わ", "を", "ん"};
    private String[] kataAlph = {"ア", "イ", "ウ", "エ", "オ", "カ", "キ", "ク", "ケ", "コ",
            "サ", "シ", "ス", "セ", "ソ", "タ", "チ", "ツ", "テ", "ト",
            "ナ", "ニ", "ヌ", "ネ", "ノ", "ハ", "ヒ", "フ", "ヘ", "ホ",
            "マ", "ミ", "ム", "メ", "モ", "ヤ", "ユ", "ヨ",
            "ラ", "リ", "ル", "レ", "ロ", "ワ", "ヲ", "ン"};
    private String[] enAlph = {"a", "i", "u", "e", "o", "ka", "ki", "ku", "ke", "ko",
            "sa", "shi", "su", "se", "so", "ta", "ti", "tsu", "te", "to",
            "na", "ni", "nu", "ne", "no", "ha", "hi", "fu", "he", "ho",
            "ma", "mi", "mu", "me", "mo", "ya", "yu", "yo",
            "ra", "ri", "ru", "re", "ro", "wa", "wo", "n"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symbol);
        getSupportActionBar().hide();
        tts = new TextToSpeech(this, this);
        btnSound = (Button)findViewById(R.id.btnSound);

        groupSymbols = new GroupSymbols();
        if (MainActivity.nameAlph.equals("Hiragana")) {
            for (int i = 0; i < enAlph.length; i++) {
                groupSymbols.addSymbol(new Symbol(hiraAlph[i], enAlph[i]));
            }
        }
        else if (MainActivity.nameAlph.equals("Katakana")) {
            for (int i = 0; i < enAlph.length; i++) {
                groupSymbols.addSymbol(new Symbol(kataAlph[i], enAlph[i]));
            }
        }
        checkedSymbol = groupSymbols.getNextSymbol();
        TextView textValue = (TextView)findViewById(R.id.symbolText);
        textValue.setText(checkedSymbol.getValue());
    }
    //region TTS
    public void soundPlay (View view) {
        String utteranceId=this.hashCode() + "";
        tts.speak(checkedSymbol.getValue(), TextToSpeech.QUEUE_FLUSH, null, utteranceId);
    }
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            Locale locale = new Locale("ja");
            int result = tts.setLanguage(locale);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Извините, этот язык не поддерживается");
            } else {
                btnSound.setEnabled(true);
            }
        } else {
            Log.e("TTS", "Ошибка!");
        }
    }
    //endregion
    public void checkSymbol (View view) {
        EditText editText = (EditText)findViewById(R.id.editText);
        if (checkedSymbol.checkTranscription(editText.getText().toString())) {
            Toast.makeText(this, "Правильно", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Не правильно", Toast.LENGTH_SHORT).show();
        }
        editText.setText(null);
        result = groupSymbols.getNextSymbol();
        if (result == null) {
            Toast.makeText(this, "CONGRATULATIONS!!!", Toast.LENGTH_LONG).show();
            finish();
        }
        else {
            checkedSymbol = result;
            TextView textValue = (TextView)findViewById(R.id.symbolText);
            textValue.setText(checkedSymbol.getValue());
        }
    }
}