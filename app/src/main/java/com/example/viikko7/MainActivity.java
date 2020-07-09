package com.example.viikko7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    Context context = null;
    TextView text;
    EditText text2;
    EditText fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        text = findViewById(R.id.textView);
        fileName = findViewById(R.id.editText3);
        text2 = findViewById(R.id.editText2);
        text2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                text.setText(text2.getText());
            }
        });

    }



    public void readFile(View v) {

        String file = fileName.getText().toString();
        try {
            InputStream IS = context.openFileInput(file);

            BufferedReader BR = new BufferedReader(new InputStreamReader(IS));
            String string = "";

            while ((string=BR.readLine())!= null) {
                text.setText(string);
            }
            IS.close();

        } catch(IOException e) {
            System.out.println("Död");

        } finally {
            System.out.println("Done");
        }
    }

    public void writeFile(View v) {
        String string = text2.getText().toString();

        String file = fileName.getText().toString();
        try {

            OutputStreamWriter OW = new OutputStreamWriter(context.openFileOutput(file, Context.MODE_PRIVATE));
            //https://stackoverflow.com/questions/8696883/why-use-context-mode-private-or-context-mode-writable
            OW.write(string);
            OW.close();

        } catch(IOException e) {
            System.out.println("Död");

        } finally {
            System.out.println("Done");
        }
    }
}