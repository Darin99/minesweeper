package com.example.demo;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoApplicationTest {

    private Game game;
    private GameLogic gameLogic;

    @Test
    public void constructorsShouldBeCreatedCorrectly() {
        this.game = new Game();
        this.gameLogic = new GameLogic();
    }
}