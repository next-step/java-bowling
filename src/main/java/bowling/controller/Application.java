package bowling.controller;

import bowling.domain.bowling.BowlingPinsGroup;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.view.InputView;

public class Application {

    public static void main(String[] args) {
        Player player = new Player(InputView.inputPlayerName());
        Frames frames = Frames.initiate();
/*
        OutputView.printDefaultScoreBoard(player);
*/
        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();
        while (frames.hasNextFrame()) {
            //  frames.bowl(InputView.inputPitch(frames), bowlingPinsGroup);


        }
    }
}
