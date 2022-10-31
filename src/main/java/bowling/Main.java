package bowling;

import bowling.domain.frame.Frames;
import bowling.domain.pin.FallenPin;
import bowling.domain.player.PlayerName;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Main {
    public static void main(String[] args) {
        PlayerName playerName = new PlayerName(InputView.scanName());

        Frames frames = Frames.init();
        ResultView.printFrames(playerName, frames);
        while (!frames.isOver()) {
            int fallenPins = InputView.scanFallenPinCount(frames.lastFrameNumber());
            frames = bowl(frames, fallenPins);
            ResultView.printFrames(playerName, frames);
        }

        InputView.closeScan();
    }

    private static Frames bowl(Frames frames, int fallenPins) {
        try {
            return frames.bowl(FallenPin.of(fallenPins));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return frames;
        }
    }
}
