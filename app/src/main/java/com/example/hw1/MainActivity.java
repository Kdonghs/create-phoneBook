package com.example.hw1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_MENU = 101;
    ViewGroup contain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contain = (ViewGroup) findViewById(R.id.contact_linearLayout);
        Button exit = findViewById(R.id.button_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Would like to exit the App?");
                builder.setPositiveButton("Yes,EXIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        finish();
                    }
                });
                builder.setNegativeButton("NO, CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        return;
                    }
                });
                builder.show();
            }
        });
    }

    public void addContact(View v){
        Intent intent = new Intent(this,PhoneActivity.class);
        startActivityForResult(intent,REQUEST_CODE_MENU);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE_MENU) {

            if (resultCode == RESULT_OK) {
                String getName = data.getStringExtra("User_name");
                int getId = data.getIntExtra("User_gender", 0);
                String getNumber = data.getStringExtra("User_number");

                ViewGroup container = findViewById(R.id.contact_linearLayout);
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE) ;
                ViewGroup viewGroup = (ViewGroup) layoutInflater.inflate(R.layout.phone_info,container,false);

                ImageView imageView=viewGroup.findViewById(R.id.imageView);
                TextView textView=viewGroup.findViewById(R.id.textView_name);
                TextView textView2=viewGroup.findViewById(R.id.textView_phone_number);
                Button button=viewGroup.findViewById(R.id.button_call);

                imageView.setImageResource(getId);
                textView.setText(getName);
                textView2.setText(getNumber);
                button.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent myIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+getNumber));
                        startActivity(myIntent);
                    }
                });

                container.addView(viewGroup);

            }
        }
    }


    @Override
    public void onBackPressed() {

    }

}

