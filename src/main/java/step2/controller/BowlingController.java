package step2.controller;

import step2.domain.Frame;
import step2.domain.Frames;
import step2.domain.Pitch;
import step2.domain.Player;
import step2.view.InputView;
import step2.view.ResultView;

public class BowlingController {

    private static final int ONE = 1;

    public void start() {
        Player player = InputView.getPlayer();

        ResultView resultView = new ResultView(player);

        resultView.printEmptyRecords();

        Frames frames = Frames.init();
        while (!frames.isFinish()) {
            Frame frame = frames.nextFrame();
            int round = frames.getSize() + ONE;
            bowlFrame(resultView, frame, round);
            frames.bowl(frame);
        }
    }

    private void bowlFrame(ResultView resultView, Frame frame, int round) {
        while (!frame.isFinish()) {
            Pitch pitch = Pitch.from(InputView.getScore(round));
            frame.bowl(pitch);
            resultView.printScoreBoard(frame, round);
        }
    }
}
