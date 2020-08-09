package com.example.demo;

import java.io.IOException;
import java.util.Random;

public class GameLogic {

    private final String MINE_SYMBOL = "*";

    private int difficultyLvl;
    private String[][] field;
    private String[][] displayField;
    private ReadDataImpl readData;
    private Boolean[][] checkedCells;

    public GameLogic() {
        this.setReadData(new ReadDataImpl());
    }


    public int getDifficultyLvl() {
        return difficultyLvl;
    }

    public void setDifficultyLvl(int difficultyLvl) {
        this.difficultyLvl = difficultyLvl;
    }

    public String[][] getField() {
        return field;
    }

    public void setField(String[][] field) {
        this.field = field;
    }

    public String[][] getDisplayField() {
        return displayField;
    }

    public void setDisplayField(String[][] displayField) {
        this.displayField = displayField;
    }

    public ReadDataImpl getReadData() {
        return readData;
    }

    public void setReadData(ReadDataImpl readData) {
        this.readData = readData;
    }

    public Boolean[][] getCheckedCells() {
        return checkedCells;
    }

    public void setCheckedCells(Boolean[][] checkedCells) {
        this.checkedCells = checkedCells;
    }

    public void loadField() throws IOException {
        this.setDifficultyLvl(Integer.parseInt(this.getReadData().readData()));
        this.checkLvlDifficulty(this.getDifficultyLvl());
        this.printField(this.getDisplayField());
    }

    public void play() throws IOException {

        while (true) {

            int number = this.isVictory();

            if (number == 0) {
                this.printField(this.getField());
                System.out.println("You win!");
                break;
            }

            System.out.println("Enter your move, (row, column)");
            System.out.println("->");

            String[] tokens = this.getReadData().getTokens();

            int row = Integer.parseInt(tokens[0]);
            int col = Integer.parseInt(tokens[1]);

            if (this.getField()[row][col].equals(MINE_SYMBOL)) {

                this.printField(this.getField());
                System.out.println("You Lost!");
                break;

            }

            this.showNumberOfMinesOrCLearEmptyCells(row, col);
            this.printField(this.getDisplayField());
        }
    }

    private void showNumberOfMinesOrCLearEmptyCells(int row, int col) {

        int mines = this.sumOfMines(row, col, this.getField());

        if (mines != 0) {

            if (inRange(row, col, this.getField())) {
                this.getDisplayField()[row][col] = "" + mines;
                this.getField()[row][col] = "" + mines;
            }

        } else {

            if (inRange(row, col, this.getField())) {

                if (!this.getCheckedCells()[row][col]) {

                    this.getDisplayField()[row][col] = " ";
                    this.getField()[row][col] = " ";
                    this.getCheckedCells()[row][col] = true;
                    this.showNumberOfMinesOrCLearEmptyCells(row - 1, col - 1);
                    this.showNumberOfMinesOrCLearEmptyCells(row - 1, col);
                    this.showNumberOfMinesOrCLearEmptyCells(row - 1, col + 1);
                    this.showNumberOfMinesOrCLearEmptyCells(row, col + 1);
                    this.showNumberOfMinesOrCLearEmptyCells(row + 1, col + 1);
                    this.showNumberOfMinesOrCLearEmptyCells(row + 1, col);
                    this.showNumberOfMinesOrCLearEmptyCells(row + 1, col - 1);
                    this.showNumberOfMinesOrCLearEmptyCells(row, col - 1);
                }
            }
        }
    }

    private void checkLvlDifficulty(int difficultyLvl) {

        if (difficultyLvl == 0) {
            this.fillInTheFields(9);
            this.generateMines(10);


        } else if (difficultyLvl == 1) {
            this.fillInTheFields(16);
            this.generateMines(40);

        } else {
            this.fillInTheFields(24);
            this.generateMines(99);
        }
    }

    private void fillInTheFields(int num) {
        this.setField(new String[num][num]);
        this.setDisplayField(new String[num][num]);
        this.setCheckedCells(new Boolean[num][num]);

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                this.getField()[i][j] = "-";
                this.getDisplayField()[i][j] = "-";
                this.getCheckedCells()[i][j] = false;
            }
        }
    }

    private void generateMines(int mines) {
        for (int i = 0; i < mines; i++) {
            Random random = new Random();
            int row = random.nextInt(this.getField().length);
            int col = random.nextInt(this.getField().length);

            if (!this.getField()[row][col].equals(MINE_SYMBOL)) {
                this.getField()[row][col] = MINE_SYMBOL;
            }
        }
    }

    private void printField(String[][] field) {
        System.out.println("Current Status on Board: ");
        for (int i = 0; i < field.length; i++) {
            if (i == 0) {
                System.out.print("      " + i);
            } else {
                if (i < 10) {
                    System.out.print("  " + i);
                } else {
                    System.out.print(" " + i);
                }
            }
        }
        System.out.println();
        System.out.println();
        for (int row = 0; row < field.length; row++) {
            if (row < 10) {
                System.out.print(" " + row + "    ");
            } else {
                System.out.print(row + "    ");
            }

            for (int col = 0; col < field[row].length; col++) {
                System.out.print(field[row][col] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private int isVictory() {

        int notClearedTiles = 0;

        for (int i = 0; i < this.getField().length; i++) {
            for (int j = 0; j < this.getField()[i].length; j++) {
                if (this.getField()[i][j].equals("-")) {
                    notClearedTiles++;
                }
            }
        }
        return notClearedTiles;
    }

    private int sumOfMines(int row, int col, String[][] matrix) {

        int mines = 0;

        if (inRange(row - 1, col - 1, matrix) && matrix[row - 1][col - 1].equals("*")) {
            mines++;
        }

        if (inRange(row - 1, col, matrix) && matrix[row - 1][col].equals("*")) {
            mines++;
        }

        if (inRange(row - 1, col + 1, matrix) && matrix[row - 1][col + 1].equals("*")) {
            mines++;
        }

        if (inRange(row, col + 1, matrix) && matrix[row][col + 1].equals("*")) {
            mines++;
        }

        if (inRange(row + 1, col + 1, matrix) && matrix[row + 1][col + 1].equals("*")) {
            mines++;
        }

        if (inRange(row + 1, col, matrix) && matrix[row + 1][col].equals("*")) {
            mines++;
        }

        if (inRange(row + 1, col - 1, matrix) && matrix[row + 1][col - 1].equals("*")) {
            mines++;
        }

        if (inRange(row, col - 1, matrix) && matrix[row][col - 1].equals("*")) {
            mines++;
        }

        return mines;
    }

    private boolean inRange(int row, int col, String[][] matrix) {
        return row < matrix.length && row >= 0 && col < matrix[row].length && col >= 0;
    }
}