package bowling.presentation;

import bowling.domain.Player;
import bowling.domain.Players;
import bowling.presentation.input.PlayerCountInputView;
import bowling.presentation.input.PlayerInputView;
import bowling.service.BowlingService;

public class BowlingController {

    private BowlingController() {
    }

    public static BowlingController create() {
        return new BowlingController();
    }

    public void execute() {
        int playerCount = PlayerCountInputView.create().input();
        Players players = Players.create();
        for (int i = 0; i < playerCount; i++) {
            players.add(Player.from(PlayerInputView.create(i + 1).input()));
        }
        BowlingService.create(players).start();
    }

}
