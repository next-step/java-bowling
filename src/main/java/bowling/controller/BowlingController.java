package bowling.controller;

import bowling.domain.Player;
import bowling.view.InputView;

import static bowling.domain.frame.Frame.FIRST_FRAME_NO;
import static bowling.domain.frame.Frame.LAST_FRAME_NO;

public class BowlingController {
    private Player player;

    private void playBowling(int frameNo) {
        while(!player.isNthFrameFinished(frameNo)){
            player.bowl(InputView.requestFallingPins(frameNo), frameNo);
            ResultView.printFrames(player.exportPlayerDTO());
        }
    }

    public void run() {
        player = new Player(InputView.requestPlayerName());
        ResultView.printFrames(player.exportPlayerDTO());
        for (int i = FIRST_FRAME_NO; i < = LAST_FRAME_NO; i++) {
            playBowling(i);
        }
    }
}
