package bowling.controller;

import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

import static bowling.domain.frame.NormalFrame.FIRST_FRAME_NO;
import static bowling.domain.frame.NormalFrame.LAST_FRAME_NO;

public class BowlingController {
    private Player player;

    public void run() {
        player = new Player(InputView.requestPlayerName());
        ResultView.printBoard(player.exportPlayerDTO());
        for (int i = FIRST_FRAME_NO; i <= LAST_FRAME_NO; i++) {
            playBowling(i);
        }
    }

    private void playBowling(int frameNo) {
        while(!player.isNthFrameFinished(frameNo)){
            player.bowl(InputView.requestFallingPins(frameNo), frameNo);
            ResultView.printBoard(player.exportPlayerDTO());
        }
    }
}
