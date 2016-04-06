package com.bakalris.example.basicandroidsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class DbViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_view);

        TextView textView = (TextView) findViewById(R.id.db_content);

        List<Kofola> bottles= Kofola.listAll(Kofola.class);
        for (Kofola jedna: bottles
             ) {

            if(jedna.equals(Kofola.getInstance())){
                textView.append(jedna.getId()+": Iba ja som ta prava Kofola !!+");

            };
            textView.append(jedna.getId()+": "+jedna.toString()+"\n\n");
        }
    }
}
