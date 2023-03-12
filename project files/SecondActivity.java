package com.example.mycalendar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends Activity {

    Button btnSave;
    EditText editText;
    TextView textdate;
    private final int GET_GALLERY_IMAGE = 200;
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        btnSave = findViewById(R.id.btnSave);
        editText = findViewById(R.id.editText);
        textdate = findViewById(R.id.textdate);
        imageview = findViewById(R.id.imageView);


        Intent inIntent = getIntent();

        final String text = inIntent.getStringExtra("selectedDate");
        textdate.setText(text);

        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent outintent = new Intent(getApplicationContext(), MainActivity.class);
                final String input = editText.getText().toString();
                //outintent.putExtra("sel", selectedValue);
                outintent.putExtra("text1", input);
                setResult(RESULT_OK, outintent);
                finish();
            }
        });

        imageview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();
            imageview.setImageURI(selectedImageUri);

        }
    }
}

