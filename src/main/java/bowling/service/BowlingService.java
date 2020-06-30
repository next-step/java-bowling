package bowling.service;

import bowling.domain.Player;
import bowling.domain.PlayerName;
import bowling.view.BowlingView;

public class BowlingService {

    public void playBowling() {

        PlayerName playerName = PlayerName.of(BowlingView.inputPlayerName());
        Player player = Player.of(playerName);




    }
}
