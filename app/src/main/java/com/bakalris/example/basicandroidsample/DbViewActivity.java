package com.bakalris.example.basicandroidsample;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DbViewActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_view);

        mTextView = (TextView) findViewById(R.id.db_content);

        printDatabase();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabDeleteAll);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAll();
                printDatabase();
            }
        });

    }

    private void printDatabase() {
        mTextView.setText("");

        List<Kofola> bottles= Kofola.listAll(Kofola.class);
        for (Kofola jedna: bottles
                ) {

            if(jedna.equals(Kofola.getInstance())){
                mTextView.append(jedna.getId()+": Iba ja som ta prava Kofola !!+");

            };
            mTextView.append(jedna.getId()+": "+jedna.toString()+"\n\n");
        }
    }

    private void deleteAll() {
        Kofola.deleteAll(Kofola.class);
        Toast.makeText(DbViewActivity.this, "Deletet all Kofolas", Toast.LENGTH_SHORT).show();
    }
}
