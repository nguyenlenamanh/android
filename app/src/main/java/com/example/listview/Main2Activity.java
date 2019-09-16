package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    EditText etName, etPrice;
    CheckBox checkBox;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etName = findViewById(R.id.etName);
        etPrice = findViewById(R.id.etPrice);
        checkBox = findViewById(R.id.cbSelectedEdit);
        button = findViewById(R.id.btnSave);

        final Intent intent = getIntent();

        if(!intent.hasExtra("edit")) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  Food food = new Food(R.drawable.apple,etName.getText().toString(),etPrice.getText().toString(),checkBox.isChecked());

                  Bundle bundle = new Bundle();
                  bundle.putSerializable("food",food);
                  Intent intent1 = new Intent();
                  intent1.putExtra("add",bundle);
                  setResult(RESULT_OK,intent1);
                  finish();
                }
            });
        }else {
            final Bundle bundle = intent.getBundleExtra("edit");
            final Food food = (Food)bundle.getSerializable("food");

            etName.setText(food.getName());
            etPrice.setText(food.getPrice());
            checkBox.setChecked(food.getSelected());

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    food.setPrice(etPrice.getText().toString());
                    food.setName(etName.getText().toString());
                    food.setSelected(checkBox.isChecked());

                    Intent intent1 = new Intent();
                    intent1.putExtra("edit",bundle);
                    setResult(RESULT_OK,intent1);
                    finish();
                }
            });
        }
    }
}
