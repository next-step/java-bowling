package step3.controller;

import step3.domain.Frame;
import step3.domain.Frames;
import step3.domain.NormalFrame;
import step3.view.InputView;
import step3.view.ResultView;

public class BowlingGameController {
    private static final Integer START_FRAME_NUMBER = 1;
    private static final Integer END_FRAME_NUMBER = 10;

    public void runGame() {
        String userName = InputView.requireUserName();

        Frame frame = new NormalFrame(START_FRAME_NUMBER);
        Frames frames = new Frames();
        while (!frame.isGameEnd()) {
            int fallenPin = InputView.requireThrowBallNum(frame.number());
            frame = frame.bowl(fallenPin);
            frame = frames.add(frame);

            printResult(userName, frames, frame);
        }
    }

    private void printResult(String userName, Frames frames, Frame frame) {
        ResultView.printHeader();
        ResultView.printUserName(userName);
        ResultView.printResult(frames, frame);
        ResultView.prinBlank();
        ResultView.printScoreResult(frames, frame);
    }
}
