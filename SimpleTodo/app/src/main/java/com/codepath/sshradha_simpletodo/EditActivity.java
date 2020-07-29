package com.codepath.sshradha_simpletodo;

import com.codepath.sshradha_simpletodo.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    EditText editText;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editText = findViewById(R.id.editItem);
        saveButton = findViewById(R.id.updateBtn);

        getSupportActionBar().setTitle("Edit item");

        String txt = getIntent().getStringExtra(MainActivity.KEY_ITEM_TEXT);
        editText.setText(txt);

        //when the user is done editing, the button is clicked
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create the intent
                Intent i = new Intent();
                //pass the results (edits)
                i.putExtra(MainActivity.KEY_ITEM_TEXT, editText.getText().toString());
                i.putExtra(MainActivity.KEY_ITEM_POSITION, getIntent().getExtras().getInt(MainActivity.KEY_ITEM_POSITION));
                //set the results
                setResult(RESULT_OK, i);
                //finish the activity
                finish();
            }
        });
    }
}
