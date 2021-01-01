package step2.controller;

import step2.domain.frame.Frame;
import step2.domain.frame.Frames;
import step2.domain.Pitch;
import step2.view.InputView;
import step2.view.ResultView;

public class BowlingController {

    private static final int ONE = 1;

    public void start() {
        ResultView.setPlayer(InputView.getPlayer());

        ResultView.printEmptyRecords();
//
//        Frames frames = Frames.init();
//        while (!frames.isFinish()) {
//            Frame frame = frames.nextFrame();
//            int round = frames.getSize() + ONE;
//            bowlFrame(frame, round);
//            frames.bowl(frame);
//        }
    }

    private void bowlFrame(Frame frame, int round) {
//        while (!frame.isFinish()) {
//            Pitch pitch = Pitch.from(InputView.getScore(round));
//            frame.bowl(pitch);
//            ResultView.printScoreBoard(frame, round);
//        }
    }
}
