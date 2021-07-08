package com.example.realmadrid.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.realmadrid.MainActivity;
import com.example.realmadrid.R;
import com.example.realmadrid.database.DatabaseHelper;
import com.example.realmadrid.databinding.ActivityEditPlayerBinding;
import com.example.realmadrid.model.Player;

import java.util.Calendar;

public class EditPlayerActivity extends AppCompatActivity {

    private ActivityEditPlayerBinding editPlayerBinding;
    private DatabaseHelper databaseHelper;
    private String[] positions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editPlayerBinding = ActivityEditPlayerBinding.inflate(getLayoutInflater());
        setContentView(editPlayerBinding.getRoot());

        initSpinner();
        databaseHelper = new DatabaseHelper(this);
        Intent intent = getIntent();
        Player player = (Player) intent.getSerializableExtra("player");
        if (player != null) {
            editPlayerBinding.actEditId.setText(player.getId() + "");
            editPlayerBinding.actEditName.setText(player.getName());
            editPlayerBinding.actEditBirthday.setText(player.getBirthday());
            editPlayerBinding.actEditKit.setText(player.getKit() + "");
            int pos = 0;
            for (int i = 0; i < positions.length; i++) {
                if (positions[i].equals(player.getPosition())) {
                    pos = i;
                    break;
                }
            }
            editPlayerBinding.actEditPosition.setSelection(pos);
        }

        editPlayerBinding.actEditBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog
                        = new DatePickerDialog(EditPlayerActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editPlayerBinding.actEditBirthday.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        editPlayerBinding.actBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (databaseHelper.deletePlayer(player.getId()) > 0) {
                    Toast.makeText(EditPlayerActivity.this,
                            "Delete successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditPlayerActivity.this,
                            MainActivity.class));
                    finish();
                }
            }
        });

        editPlayerBinding.actBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(editPlayerBinding.actEditId.getText().toString());
                String name = editPlayerBinding.actEditName.getText().toString();
                String birthday = editPlayerBinding.actEditBirthday.getText().toString();
                int kit = Integer.parseInt(editPlayerBinding.actEditKit.getText().toString());
                String position = editPlayerBinding.actEditPosition.getSelectedItem().toString();
                Player player1 = new Player(id, name, birthday, kit, position);
                if (databaseHelper.updatePlayer(player1) > 0) {
                    Toast.makeText(EditPlayerActivity.this,
                            "Edit successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditPlayerActivity.this,
                            MainActivity.class));
                    finish();
                }
            }
        });
    }

    private void initSpinner() {
        positions = new String[]{"Striker", "Midfielder", "Defender", "Goalkeeper"};
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.item_spinner, positions);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editPlayerBinding.actEditPosition.setAdapter(arrayAdapter);
    }
}