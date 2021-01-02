package step2.controller;

import step2.domain.frame.Frame;
import step2.domain.frame.Frames;
import step2.domain.frame.NormalFrame;
import step2.view.InputView;
import step2.view.ResultView;

public class BowlingController {

    private static final int ONE = 1;

    public void start() {
        ResultView.setPlayer(InputView.getPlayer());

        ResultView.printEmptyRecords();

        Frames frames = Frames.init();
        Frame frame = NormalFrame.init();
        while (!frames.isFinish()) {
            bowlFrame(frame);
            frame.getRound();
//            frame.bowl(pitch);
//            ResultView.printScoreBoard(frame, round);
            frames.bowl(frame);
        }
    }

    private Frame bowlFrame(Frame frame) {
        while (!frame.isFinish()) {
            int round = frame.getRound() + ONE;
            frame = frame.bowl(InputView.getScore(round));
            ResultView.printScoreBoard(frame, round);
        }
        return frame;
    }
}
