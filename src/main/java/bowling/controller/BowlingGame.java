package bowling.controller;

import bowling.domain.BowlingMachine;
import bowling.domain.Player;
import bowling.domain.PlayerName;
import bowling.view.BowlingView;

public class BowlingGame {

    public void playGame() {
        String inputPlayerName = BowlingView.inputPlayerName();
        Player player = Player.of(PlayerName.of(inputPlayerName));
        BowlingView.displayBowlingDefaultFrameView(player);
        BowlingMachine bowlingMachine = BowlingMachine.of(player);
        bowlingMachine.startGame();
    }
}
