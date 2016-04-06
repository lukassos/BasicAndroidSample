package com.bakalris.example.basicandroidsample;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.database_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            deleteAll();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
