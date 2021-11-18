package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class PhoneActivity extends AppCompatActivity {
    EditText editText_name;
    RadioGroup radioGroup_gender;
    EditText editText_phone_number;
    ImageView imageView;
    int picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        editText_name = findViewById(R.id.editText_name);
        imageView = findViewById(R.id.imageView);

        radioGroup_gender = findViewById(R.id.radioGroup_gender);
        picture =R.drawable.unspecified;

        radioGroup_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if(i==R.id.radio_male){
                    imageView.setImageResource(R.drawable.male);
                    picture = R.drawable.male;
                }
                else if(i==R.id.radio_female){
                    imageView.setImageResource(R.drawable.female);
                    picture =R.drawable.female;
                }
                else if(i==R.id.radio_unspecified){
                    imageView.setImageResource(R.drawable.unspecified);
                    picture =R.drawable.unspecified;
                }
            }
        });

        editText_phone_number = findViewById(R.id.editText_phone_number);
    }

    @Override
    public void onBackPressed() {

    }

    public void save(View v){
        Intent intent = new Intent(PhoneActivity.this,MainActivity.class);
        String name = editText_name.getText().toString();
        String phone_number=editText_phone_number.getText().toString();

        if(name.equals("") || phone_number.equals("")){
            finish();
        }

        intent.putExtra("User_name",name);
        intent.putExtra("User_gender", picture);
        intent.putExtra("User_number",phone_number);
        setResult(RESULT_OK,intent);
        finish();

    }

    public void cancel(View v){

        finish();
    }


}