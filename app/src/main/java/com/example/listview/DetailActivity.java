package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView tvName, tvPrice;
    CheckBox cbSelected;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("detail");
        Food food = (Food) bundle.getSerializable("selectedfood");

        tvName = findViewById(R.id.tvNameDetail);
        tvPrice = findViewById(R.id.tvPriceDetail);
        cbSelected = findViewById(R.id.cbSelectedDetail);

        btnBack = findViewById(R.id.btnBackToMain);

        tvName.setText(food.getName());
        tvPrice.setText(food.getPrice());
        cbSelected.setChecked(food.getSelected());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
