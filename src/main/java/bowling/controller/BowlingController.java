package bowling.controller;

import bowling.domain.BowlingGame;

public class BowlingController {

    private BowlingGame bowlingGame;

    public void inputName(String name) {
        bowlingGame = new BowlingGame(name);
    }
}
