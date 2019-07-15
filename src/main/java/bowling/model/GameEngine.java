package bowling.model;

import bowling.model.frame.FrameNumber;
import bowling.model.frame.Frames;
import bowling.model.frame.Results;

class GameEngine {

    private Frames gameEngine;

    GameEngine() {
        this.gameEngine = Frames.initialize();
    }

    GameEngine play(Pins downPins) {
        if (gameEngine.isGameOver()) {
            throw new GameOverException();
        }
        gameEngine = gameEngine.saveBowling(downPins);
        return this;
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