package bowling.model;

import bowling.model.frame.Frame;

public class BowlingGame {

    private Frame firstFrame;
    private Frame currentFrame;

    BowlingGame() {
        this.firstFrame = Frame.initialize();
        this.currentFrame = firstFrame;
    }

    BowlingGame play(Pins downPins) {
        if (currentFrame.isGameOver()) {
            throw new IllegalStateException("게임이 종료되었습니다.");
        }
        currentFrame = currentFrame.bowl(downPins);
        System.out.println(firstFrame.printResult());
        return this;
    }

    public void print() {

    }
}