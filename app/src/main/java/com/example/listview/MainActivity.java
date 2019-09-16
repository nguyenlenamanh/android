package com.example.listview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button buttonAdd;
    ArrayList<Food> foods;
    FoodAdapter foodAdapter;
    final private int ADD = 999;
    final private int EDIT = 998;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lvListFood);
        buttonAdd = findViewById(R.id.btnAdd);

        foods = new ArrayList<>();

        for(int i = 0 ; i < 10 ; i++) {
            foods.add(new Food(R.drawable.apple, Integer.toString(i), Integer.toString(1000), true));
        }

        foodAdapter = new FoodAdapter(this, R.layout.food_item, foods);

        listView.setAdapter(foodAdapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, Main2Activity.class);
               startActivityForResult(intent, ADD);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("selectedfood",foods.get(i));
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("detail",bundle);
                startActivity(intent);
            }
        });

        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId() == R.id.lvListFood) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.custom_menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo contextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivityForResult(intent, ADD);
                break;
            case R.id.edit:
                int pos = contextMenuInfo.position;
                Food food = foods.get(pos);
                Bundle bundle = new Bundle();
                bundle.putSerializable("food",food);
                bundle.putInt("pos",pos);
                Intent intent1 = new Intent(MainActivity.this, Main2Activity.class);
                intent1.putExtra("edit",bundle);
                startActivityForResult(intent1, EDIT);
                break;
            case R.id.delete:
                foods.remove(contextMenuInfo.position);
                foodAdapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD) {
            if(resultCode == RESULT_OK) {
                Bundle bundle = data.getBundleExtra("add");
                Food food = (Food)bundle.getSerializable("food");

                foods.add(food);
                foodAdapter.notifyDataSetChanged();
            }
        }
        else if(requestCode == EDIT) {
            if(resultCode == RESULT_OK) {
                Bundle bundle = data.getBundleExtra("edit");
                Food food = (Food)bundle.getSerializable("food");

                int pos = bundle.getInt("pos");
                Food needEdit = foods.get(pos);
                needEdit.setSelected(food.getSelected());
                needEdit.setName(food.getName());
                needEdit.setPrice(food.getPrice());

                foodAdapter.notifyDataSetChanged();
            }
        }
    }
}
