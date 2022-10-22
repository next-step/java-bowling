package bowling;

import bowling.domain.Frames;
import bowling.domain.PlayerName;
import bowling.domain.Score;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Main {
    public static void main(String[] args) {
        PlayerName playerName = InputView.scanName();

        Frames frames = Frames.init();
        ResultView.printFrames(playerName, frames);
        while (!frames.isOver()) {
            Score score = InputView.scanScore(frames.lastFrameNumber());

            frames = frames.bowl(score);
            ResultView.printFrames(playerName, frames);

            frames = frames.next();
        }

        InputView.closeScan();
    }
}
