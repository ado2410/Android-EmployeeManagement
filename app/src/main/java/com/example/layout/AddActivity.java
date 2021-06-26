package com.example.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddActivity extends AppCompatActivity {
    int id = 0;
    private EditText etName;
    private EditText etDob;
    private RadioGroup rgGender;
    private EditText etPosition;
    private EditText etSalary;
    private FloatingActionButton btnAdd;
    private FloatingActionButton btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etName = (EditText) findViewById(R.id.activity_add_txt_name);
        etDob = (EditText) findViewById(R.id.activity_add_txt_dob);
        rgGender = (RadioGroup) findViewById(R.id.activity_add_txt_gender);
        etPosition = (EditText) findViewById(R.id.activity_add_txt_position);
        etSalary = (EditText) findViewById(R.id.activity_add_txt_salary);
        btnAdd = (FloatingActionButton) findViewById(R.id.activity_add_add);
        btnBack = (FloatingActionButton) findViewById(R.id.activity_add_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Điền thông tin chỉnh sửa
        Intent intentSender = getIntent();
        if (intentSender.getIntExtra("mode", 0) == 1) {
            id = intentSender.getIntExtra("id", 0);
            etName.setText(intentSender.getStringExtra("name"));
            etDob.setText(intentSender.getStringExtra("dob"));

            RadioButton rbMale = (RadioButton) findViewById(R.id.activity_add_rb_gender_0);
            RadioButton rbFemale = (RadioButton) findViewById(R.id.activity_add_rb_gender_1);
            if (intentSender.getIntExtra("gender", 0) == 0) {
                rbMale.setChecked(true);
                rbFemale.setChecked(false);
            }
            else {
                rbMale.setChecked(false);
                rbFemale.setChecked(true);
            }

            etPosition.setText(intentSender.getStringExtra("position"));
            etSalary.setText(Float.toString(intentSender.getFloatExtra("salary", 0)));
        }

        //Nhấn nút thêm
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etName.getText().toString() != "") {
                    Intent intent = new Intent();
                    intent.putExtra("id", id);
                    intent.putExtra("name", etName.getText().toString());
                    intent.putExtra("dob", etDob.getText().toString());

                    int genderId = rgGender.getCheckedRadioButtonId();
                    intent.putExtra("gender", genderId == R.id.activity_add_rb_gender_0 ? 0 : 1);

                    intent.putExtra("position", etPosition.getText().toString());
                    intent.putExtra("salary", Float.parseFloat(etSalary.getText().toString()));
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}
