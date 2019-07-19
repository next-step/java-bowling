package bowling.model;

import bowling.model.frame.FrameNumber;
import bowling.model.frame.Frames;
import bowling.model.frame.Results;

class GameEngine {

    private Frames gameEngine;

    GameEngine() {
        this.gameEngine = Frames.initialize();
    }

    void play(DownPin downPin) {
        gameEngine.saveBowling(downPin);
    }

    Results results() {
        return gameEngine.getResult();
    }

    boolean isGameOver() {
        return gameEngine.isGameOver();
    }

    FrameNumber getCurrentNumber() {
        return gameEngine.getCurrentNumber();
    }
}