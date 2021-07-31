package com.nodz.roompractice.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.nodz.roompractice.R;

public class AddNoteActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE = "com.nodz.roompractice.TITLE";
    public static final String EXTRA_DESC = "com.nodz.roompractice.DESC";
    public static final String EXTRA_PRIOR = "com.nodz.roompractice.PRIOR";
    public static final String EXTRA_ID = "com.nodz.roompractice.ID";


    private EditText etTitle;
    private EditText etDesc;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        etTitle = findViewById(R.id.et_title);
        etDesc = findViewById(R.id.et_description);
        numberPicker = findViewById(R.id.number_picker_priority);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        if(getIntent().hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            etTitle.setText(getIntent().getStringExtra(EXTRA_TITLE));
            etDesc.setText(getIntent().getStringExtra(EXTRA_DESC));
            numberPicker.setValue(getIntent().getIntExtra(EXTRA_PRIOR,1));
        }
        setTitle("Add Note");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_notescreen_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote() {
    String title = etTitle.getText().toString();
    String desc = etDesc.getText().toString();
    int priority = numberPicker.getValue();

    if (title.trim().isEmpty() || desc.trim().isEmpty()) {
        Toast.makeText(this, "Please fill the data", Toast.LENGTH_SHORT).show();
        return;
    }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_DESC, desc);
        intent.putExtra(EXTRA_PRIOR, priority);

        int id = getIntent().getIntExtra(EXTRA_ID,-1);

        if(id != -1){
            intent.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, intent);

        finish();

    }
}