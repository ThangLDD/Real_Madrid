package com.example.realmadrid.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.realmadrid.MainActivity;
import com.example.realmadrid.R;
import com.example.realmadrid.database.DatabaseHelper;
import com.example.realmadrid.databinding.ActivityAddPlayerBinding;
import com.example.realmadrid.model.Player;

import java.util.Calendar;

public class AddPlayerActivity extends AppCompatActivity {

    private ActivityAddPlayerBinding addPlayerBinding;
    private DatabaseHelper databaseHelper;
    private String[] positions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPlayerBinding = ActivityAddPlayerBinding.inflate(getLayoutInflater());
        setContentView(addPlayerBinding.getRoot());

        initSpinner();

        databaseHelper = new DatabaseHelper(this);
        addPlayerBinding.actAddBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog
                        = new DatePickerDialog(AddPlayerActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        addPlayerBinding.actAddBirthday.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        addPlayerBinding.actBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = addPlayerBinding.actAddName.getText().toString();
                String birthday = addPlayerBinding.actAddBirthday.getText().toString();
                int kit = Integer.parseInt(addPlayerBinding.actAddKit.getText().toString());
                String position = addPlayerBinding.actAddPosition.getSelectedItem().toString();

                Player player = new Player(name, birthday, kit, position);
                databaseHelper.addPlayer(player);
                startActivity(new Intent(AddPlayerActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void initSpinner() {
        positions = new String[]{"Striker", "Midfielder", "Defender", "Goalkeeper"};
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.item_spinner, positions);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addPlayerBinding.actAddPosition.setAdapter(arrayAdapter);
    }
}