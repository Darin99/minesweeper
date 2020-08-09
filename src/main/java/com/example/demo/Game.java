package com.example.demo;

import java.io.IOException;

public class Game {

    private GameLogic logic;

    public Game() {
        this.setLogic(new GameLogic());
    }

    public GameLogic getLogic() {
        return logic;
    }

    public void setLogic(GameLogic logic) {
        this.logic = logic;
    }

    public void init() {
        System.out.println("Enter the Difficulty Level");
        System.out.println("Press 0 for BEGINNER (9 * 9 Cells and 10 Mines)");
        System.out.println("Press 1 for INTERMEDIATE (16 * 16 Cells and 40 Mines)");
        System.out.println("Press 2 for ADVANCED (24 * 24 Cells and 99 Mines)");
    }

    public void start() throws IOException {
       this.getLogic().loadField();
       this.getLogic().play();
    }
}