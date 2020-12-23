package bowling.controller;

import bowling.domain.*;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingController {

    private BowlingController() {}

    public static void startGame() {
        List<Player> players = enterPlayers();
        ResultView.printHeader(players);
        bowling(players.get(0));
    }

    private static List<Player> enterPlayers() {
        return InputView.enterUserNames().stream()
                .map(Player::from)
                .collect(Collectors.toList());
    }

    private static void bowling(Player player) {
        Frames frames = Frames.init();
        while (!frames.isEnd()) {
            int pinCount = InputView.enterPinCount(frames.getCurrentNumber());
            frames.pitch(pinCount);
            ResultView.printPlayerScoreboard(player.getName(), frames.getFrames());
        }
    }
}
