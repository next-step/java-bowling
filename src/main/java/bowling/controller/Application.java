package bowling.controller;

import bowling.domain.bowling.BowlingPinsGroup;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Application {

    public static void main(String[] args) {
        Player player = new Player(InputView.inputPlayerName());
        Frames frames = Frames.initiate();

        OutputView.printDefaultScoreBoard(player);

        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();
        while (frames.hasNextFrame()) {
            Frame frame = frames.getCurrentFrame();
            bowlingPinsGroup = bowlingPinsGroup.next(frame);
            frame.bowl(InputView.inputPitch(frame), bowlingPinsGroup);
            //OutputView.printBowlingScoreBoard(frames, player);
            frames.moveToNextFrame(frame);
        }
    }
}
