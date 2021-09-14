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
            frame = addFrame(frames, frame);
            frameNum = frame.number();

            ResultView.printHeader();
            ResultView.printUserName(userName);
            ResultView.printResult(frames, frame);
            ResultView.prinBlank();
            ResultView.printScoreResult(frames, frame);
        }

    }

    private Frame addFrame(Frames frames, Frame frame) {
        if (frame.isFinish() && frame.number() != 10) {
            frames.add(frame);
            frame = frame.createFrame();
            return frame;
        }
        if (frame.number() == 10) {
            frame = frame.createFrame();
        }
        return frame;
    }
}
