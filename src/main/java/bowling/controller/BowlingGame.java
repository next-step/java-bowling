package bowling.controller;

import bowling.domain.PlayerName;
import bowling.domain.result.GameResults;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class BowlingGame {
    public static void start() {
        int playerCount = InputView.getPlayerCount();
        ResultView.print(GameResults.createWithPlayerNames(getPlayerNames(playerCount)));
    }

    private static List<PlayerName> getPlayerNames(int playerCount) {
        return IntStream.rangeClosed(1, playerCount)
                .mapToObj(count -> InputView.getPlayerName(count))
                .map(PlayerName::new)
                .collect(toList());
    }
}