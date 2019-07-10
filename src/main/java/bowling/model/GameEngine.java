package bowling.model;

import bowling.exception.GameOverException;
import bowling.model.frame.Frame;
import bowling.model.frame.FrameNumber;

public class GameEngine {

    private Frame firstFrame;
    private Frame currentFrame;

    GameEngine() {
        this.firstFrame = Frame.initialize();
        this.currentFrame = firstFrame;
    }

    GameEngine play(Pins downPins) {
        if (currentFrame.isGameOver()) {
            throw new GameOverException();
        }
        currentFrame = currentFrame.bowl(downPins);
        return this;
    }

    FrameNumber getCurrentNumber() {
        return currentFrame.getNumber();
    }

    boolean isGameOver() {
        return currentFrame.isGameOver();
    }

    String getCurrentStates() {
        return firstFrame.printResult();
    }
}