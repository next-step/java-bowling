package bowling.controller;

import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGame {
    public void startGame(){
        String playerName = InputView.inputPlayerName();
        Player playerPlayer = new Player(playerName);
        while (!playerPlayer.isFinish()) {
//            bowlingPlayer.nextFrame(InputView.inputFramePitching(bowlingPlayer.nextNumber(0)));
            ResultView.printResult();
        }
    }
}
