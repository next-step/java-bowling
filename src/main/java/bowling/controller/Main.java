package bowling.controller;

import bowling.model.Frames;
import bowling.model.Player;
import bowling.model.ShotResult;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Main {
    public static void main(String[] args) {
        Player player = new Player(InputView.readPlayerName());

        Frames frames = new Frames();
        OutputView.printFrames(frames, player.toString());

        do {
            ShotResult shotResult = player.pitch(InputView.readPinDownCount(frames.currentPlayingFrameIndex()));
            frames.record(shotResult);
            OutputView.printFrames(frames, player.toString());
        } while (!frames.isOver());
    }
}
