package bowling;

import bowling.domain.FallenPins;
import bowling.domain.Frames;
import bowling.domain.PlayerName;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Main {
    public static void main(String[] args) {
        PlayerName playerName = new PlayerName(InputView.scanName());

        Frames frames = Frames.init();
        ResultView.printFrames(playerName, frames);
        while (!frames.isOver()) {
            FallenPins fallenPins = FallenPins.of(InputView.scanFallenPins(frames.lastFrameNumber()));

            frames = frames.bowl(fallenPins);
            ResultView.printFrames(playerName, frames);

            if (frames.isLastFrameFinished()) {
                frames = frames.next();
            }
        }

        InputView.closeScan();
    }
}
