package bowling.controller;

import bowling.domain.BowlingMachine;
import bowling.domain.Frame;
import bowling.domain.Player;
import bowling.domain.PlayerName;
import bowling.view.BowlingView;

public class BowlingGame {

    public void playGame() {
        String inputPlayerName = BowlingView.inputPlayerName();
        Player player = Player.of(PlayerName.of(inputPlayerName));

        BowlingMachine bowlingMachine = BowlingMachine.of(player);
        Frame frame = bowlingMachine.startGame();
    }
}
