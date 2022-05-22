package bowling.controller;

import bowling.domain.Game;

public class BowlingGameController {

    public Game createFrames(String name) {
        return new Game(name);
    }

}
