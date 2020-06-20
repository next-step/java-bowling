package bowling.controller;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Application {

    public static void main(String[] args) {
        Player player = new Player(InputView.inputPlayerName());
        Frames frames = Frames.initiate();
        OutputView.printDefaultScoreBoard(player);
        while (frames.hasNextFrame()) {
            frames.bowl(InputView.inputPitch(frames));
            OutputView.printBowlingScoreBoard(frames, player);
            frames.moveToNextFrame();
        }
    }
}
