package bowling;

import bowling.domain.Frames;
import bowling.view.InputView;
import bowling.view.OutputView;

public class GameController {

    public static void main(String[] args) {
        Frames frames = new Frames();

        while (!frames.gameFinished()) {
            frames.bowl(InputView.nextPin(frames.currentFrameNumber()));

            OutputView.printFrameResult(frames.results());
        }
    }

}
