package com.example.suduko;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button startGameButton = findViewById(R.id.startGameButton);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDifficultyDialog();
            }
        });
    }

    private void showDifficultyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Difficulty Level")
                .setItems(new String[]{"Easy", "Medium", "Hard"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String level = "";
                        switch (which) {
                            case 0:
                                level = "Easy";
                                break;
                            case 1:
                                level = "Medium";
                                break;
                            case 2:
                                level = "Hard";
                                break;
                        }
                        startGameWithLevel(level);
                    }
                });
        builder.create().show();
    }

    private void startGameWithLevel(String level) {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        intent.putExtra("level", level);
        startActivity(intent);
    }
}
