package bowling.controller;

import bowling.domain.BowlingGame;

public class Main {
    public static void main(String[] args) {
        BowlingGame bowlingGame = new BowlingGame();
        bowlingGame.startGame();
    }
}
