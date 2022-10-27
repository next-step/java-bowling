package bowling;

import bowling.domain.Frames;
import bowling.domain.Pins;
import bowling.domain.PlayerName;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Main {
    public static void main(String[] args) {
        PlayerName playerName = new PlayerName(InputView.scanName());

        Frames frames = Frames.init();
        ResultView.printFrames(playerName, frames);
        while (!frames.isOver()) {
            Pins pins = Pins.of(InputView.scanFallenPins(frames.lastFrameNumber()));

            frames = frames.bowl(pins);
            ResultView.printFrames(playerName, frames);

            if (frames.isLastFrameFinished()) {
                frames = frames.next();
            }
        }

        InputView.closeScan();
    }
}
