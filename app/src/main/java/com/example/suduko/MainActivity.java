package com.example.suduko;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText[][] cells = new EditText[9][9];
    private int[][] solution;
    private int[][] puzzle;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.resultText);
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        gridLayout.setRowCount(9);
        gridLayout.setColumnCount(9);

        // Initialize cells
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                EditText editText = new EditText(this);
                editText.setTextSize(18);
                editText.setGravity(Gravity.CENTER);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                editText.setFilters(new android.text.InputFilter[]{
                        new android.text.InputFilter.LengthFilter(1)
                });

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = 100;
                params.height = 100;
                params.setMargins(1, 1, 1, 1);
                params.rowSpec = GridLayout.spec(i);
                params.columnSpec = GridLayout.spec(j);
                editText.setLayoutParams(params);

                editText.setBackgroundResource(R.drawable.cell_border);
                gridLayout.addView(editText);
                cells[i][j] = editText;
            }
        }

        // Set up buttons
        Button submitButton = findViewById(R.id.submitButton);
        //Button restartButton = findViewById(R.id.restartButton);
        Button newGameButton = findViewById(R.id.newGameButton);
        Button showAnswerButton = findViewById(R.id.showAnswerButton);

        submitButton.setOnClickListener(v -> checkSolution());
        //restartButton.setOnClickListener(v -> resetBoard());
        newGameButton.setOnClickListener(v -> startNewGame());
        showAnswerButton.setOnClickListener(v -> showAnswer());

        startNewGame();
    }

    // Start a new game
    private void startNewGame() {
        solution = new int[9][9];
        puzzle = new int[9][9];
        generateFullSolution(solution);
        copyArray(solution, puzzle);
        removeNumbers(puzzle, 40);
        fillGrid(puzzle, false);
        resultText.setText("Sudoku Game");
    }

    // Reset board
    private void resetBoard() {
        fillGrid(puzzle, true);
        resultText.setText("Try again.");
    }

    // Show the solution on the grid
    private void showAnswer() {
        fillGrid(solution, false); // Fill grid with the solution and disable input
        resultText.setText("Here's the solution!");
    }

    // Fill grid with the puzzle, hiding empty cells
    private void fillGrid(int[][] puzzle, boolean reset) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!reset && puzzle[i][j] == 0) {
                    cells[i][j].setText("");
                    cells[i][j].setEnabled(true);
                    cells[i][j].setTextColor(Color.RED);
                } else {
                    cells[i][j].setText(String.valueOf(puzzle[i][j]));
                    cells[i][j].setEnabled(false);
                    cells[i][j].setTextColor(Color.BLACK);
                }
            }
        }
    }

    // Generate a completed Sudoku board
    private void generateFullSolution(int[][] board) {
        solveBoard(board);
    }

    // Solve Sudoku using backtracking
    private boolean solveBoard(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveBoard(board)) return true;
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    // Remove random numbers to create the puzzle
    private void removeNumbers(int[][] board, int count) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int row, col;
            do {
                row = random.nextInt(9);
                col = random.nextInt(9);
            } while (board[row][col] == 0);
            board[row][col] = 0;
        }
    }

    // Check if a number is safe to place
    private boolean isSafe(int[][] board, int row, int col, int num) {
        for (int x = 0; x < 9; x++) {
            if (board[row][x] == num || board[x][col] == num ||
                    board[row - row % 3 + x / 3][col - col % 3 + x % 3] == num) {
                return false;
            }
        }
        return true;
    }

    // Copy array
    private void copyArray(int[][] source, int[][] destination) {
        for (int i = 0; i < 9; i++) {
            System.arraycopy(source[i], 0, destination[i], 0, 9);
        }
    }

    // Check if the solution is correct
    private void checkSolution() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String value = cells[i][j].getText().toString();
                if (value.isEmpty() || Integer.parseInt(value) != solution[i][j]) {
                    resultText.setText("Incorrect solution. Try again.");
                    return;
                }
            }
        }
        resultText.setText("Congratulations! Correct solution.");
    }
}
