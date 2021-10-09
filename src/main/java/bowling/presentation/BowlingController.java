package bowling.presentation;

import bowling.domain.Player;
import bowling.domain.Players;
import bowling.presentation.input.PlayerCountInputView;
import bowling.presentation.input.PlayerInputView;
import bowling.presentation.output.FrameOutputView;
import bowling.service.FramesService;

import java.util.ArrayList;
import java.util.List;

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
        printBowlingStart(players);
//        FramesService.create().execute(player);
    }

    private void printBowlingStart(Players players) {
        FrameOutputView.create().print(players);
    }


}
