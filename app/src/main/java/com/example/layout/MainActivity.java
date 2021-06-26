package com.example.layout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Item> items = new ArrayList<Item>(
            Arrays.asList(
                    new Item(1, "Nguyễn Trúc Giang", "5/12/2000", 1, "Trưởng phòng", 1000),
                    new Item(2, "Đào Lê Hải", "1/1/2000", 0, "Thủ kho",  1200),
                    new Item(3, "Nguyễn Xuân Hoàng", "24/2/2000", 0, "CEO vip pro", 1500),
                    new Item(4, "Bành Văn Kỳ", "15/8/2000", 0, "Trưởng ban Hacker", 1400),
                    new Item(5, "Chu Hữu Mạnh", "1/1/1998", 0, "CEO Lan đột biến", 1800),
                    new Item(6, "Lovanxay Sithien", "1/1/1997", 0, "Youtuber", 1100)
            )
    );

    private GridLayout glItems;
    private EditText etSearch;
    private Button btnClear;
    private FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        glItems = (GridLayout) findViewById(R.id.activity_main_items);
        etSearch = (EditText) findViewById(R.id.activity_main_search);
        btnClear = (Button) findViewById(R.id.activity_main_clear);
        btnAdd = (FloatingActionButton) findViewById(R.id.activity_main_add);

        //Hiển thị danh sách
        showList();

        //Sự kiện thay đổi text trong khung tìm kiếm
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                showList(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Sự kiện nhấn nút X
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSearch.setText("");
            }
        });

        //Sự kiện nút thêm
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddActivity();
            }
        });
    }

    //Áp dụng khung menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_add:
                openAddActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Mở khung chỉnh sửa và xóa
    public void openMorePopup(int id) {
        for (int i = 0; i < items.size(); i++)
            if (items.get(i).getId() == id) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", items.get(i).getId());
                MoreFragment moreFragment = new MoreFragment();
                moreFragment.setArguments(bundle);
                moreFragment.setMainActivity(this);

                moreFragment.show(getSupportFragmentManager(), "MORE_POPUP");
                break;
            }
    }

    //Mở khung thêm mới
    public void openAddActivity() {
        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra("mode", 0);
        startActivityForResult(intent, 0);
    }

    //Mở khung chỉnh sửa
    public void openEditActivity(int id) {
        for (int i = 0; i < items.size(); i++)
            if (items.get(i).getId() == id) {
                Item item = items.get(i);
                Intent intent = new Intent(this, AddActivity.class);
                intent.putExtra("mode", 1);
                intent.putExtra("id", item.getId());
                intent.putExtra("name", item.getName());
                intent.putExtra("dob", item.getDob());
                intent.putExtra("gender", item.getGender());
                intent.putExtra("position", item.getPosition());
                intent.putExtra("salary", item.getSalary());
                startActivityForResult(intent, 1);
                break;
            }
    }

    //Lấy dữ liệu từ activity add
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                String name = data.getStringExtra("name");
                String dob = data.getStringExtra("dob");
                int gender = data.getIntExtra("gender", 0);
                String position = data.getStringExtra("position");
                float salary = data.getFloatExtra("salary", 0);
                addItem(name, dob, gender, position, salary);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // Write your code if there's no result
            }
        }
        else if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                int id = data.getIntExtra("id", 0);
                String name = data.getStringExtra("name");
                String dob = data.getStringExtra("dob");
                int gender = data.getIntExtra("gender", 0);
                String position = data.getStringExtra("position");
                float salary = data.getFloatExtra("salary", 0);
                editItem(id, name, dob, gender, position, salary);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // Write your code if there's no result
            }
        }
    }

    //Thêm vào dữ liệu
    public void addItem(String name, String dob, int gender, String position, float salary) {
        items.add(new Item(items.get(items.size() - 1).getId() + 1, name, dob, gender, position, salary));
        showList();
    }

    //Chỉnh sửa dữ liệu
    public void editItem(int id, String name, String dob, int gender, String position, float salary) {
        for (int i = 0; i < items.size(); i++)
            if (items.get(i).getId() == id) {
                Item item = new Item();
                item.setId(items.get(i).getId());
                item.setName(name);
                item.setDob(dob);
                item.setGender(gender);
                item.setPosition(position);
                item.setSalary(salary);
                items.remove(i);
                items.add(i, item);
                showList();
                break;
        }
    }

    //Xóa dữ liệu
    public void removeItem(int id) {
        int index = -1;

        for(int i = 0; i < items.size(); i++)
            if (items.get(i).getId() == id)
                index = i;

        if(index != 1) {
            items.remove(items.remove(index));
            showList();
        }
    }


    private void showList() {
        showList(etSearch.getText().toString());
    }

    //Hiển thị danh sách
    private void showList(String keyword) {
        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        for (Fragment fragment : fragmentManager.getFragments())
            fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();

        fragmentTransaction = fragmentManager.beginTransaction();

        for (int i = 0; i < items.size(); i++) {
            if (keyword == "" || items.get(i).getName().toLowerCase().contains(keyword.toLowerCase()) || items.get(i).getPosition().toLowerCase().contains(keyword.toLowerCase())) {
                ItemFragment itemFragment = new ItemFragment();

                Bundle bundle = new Bundle();
                bundle.putInt("id", items.get(i).getId());
                bundle.putString("name", items.get(i).getName());
                bundle.putString("dob", items.get(i).getDob());
                bundle.putInt("gender", items.get(i).getGender());
                bundle.putString("position", items.get(i).getPosition());
                bundle.putFloat("salary", items.get(i).getSalary());
                itemFragment.setArguments(bundle);
                itemFragment.setMainActivity(this);

                fragmentTransaction.add(R.id.activity_main_items, itemFragment);
            }
        }
        fragmentTransaction.commit();
    }
}