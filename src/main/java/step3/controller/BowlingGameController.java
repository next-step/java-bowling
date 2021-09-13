package step3.controller;

import step3.domain.Frame;
import step3.domain.Frames;
import step3.domain.NormalFrame;
import step3.view.InputView;
import step3.view.ResultView;

public class BowlingGameController {
    public void runGame() {
        String userName = InputView.requireUserName();
        int frameNum = 1;
        Frame frame = new NormalFrame(frameNum);
        Frames frames = new Frames();
        while (!frame.isGameEnd()) {
            int fallenPin = InputView.requireThrowBallNum(frameNum);
            frame = frame.bowl(fallenPin);
            frameNum = frame.number();

            addFrame(frames, frame);
            ResultView.printHeader();
            ResultView.printUserName(userName);
            ResultView.printResult(frames, frame);
        }
    }

    private void addFrame(Frames frames, Frame frame) {
        if (frame.isFinish()) {
            frames.add(frame);
        }
    }
}
