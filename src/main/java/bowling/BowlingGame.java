package bowling;

import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGame {




    public static void main(String[] args) {
        run();
    }

    private static void run() {

        ResultView resultView = new ResultView();

        Player player = InputView.player();

        Frames frames = Frames.of(player);
        resultView.bowlingBoard(frames);

        while (frames.isPlay()) {
            frames = frames.play(InputView.score(frames));
            frames.scoreInit();
            resultView.bowlingBoard(frames);
        }
    }

}
