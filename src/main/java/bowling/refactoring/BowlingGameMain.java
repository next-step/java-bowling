package bowling.refactoring;

import bowling.refactoring.domain.*;
import bowling.refactoring.view.InputView;
import bowling.refactoring.view.ResultView;
import bowling.refactoring.view.dto.*;

import java.util.List;
import java.util.stream.*;

public class BowlingGameMain {

    public static void main(String[] args) {
        int playerCount = InputView.inputPlayerCount();
        List<String> names = InputView.inputPlayerName(playerCount);
        BowlingGame bowlingGame = new BowlingGame(names);
        createScoreBoard(bowlingGame.players());

        for (int i = 0; i <= 9; i++) {
            playFrame(i, bowlingGame);
        }
    }

    private static void playFrame(int i, BowlingGame bowlingGame) {
        for (Player player : bowlingGame.players()) {
            bowl(i, bowlingGame, player);
        }

    }

    private static void bowl(int i, BowlingGame bowlingGame, Player player) {
        while (!player.isEndedFrame(i)) {
            int fallenPinCount = InputView.inputFallenPinCounts(player.name());
            player.bowl(fallenPinCount);
            createScoreBoard(bowlingGame.players());
        }
    }

    private static void createScoreBoard(List<Player> players) {
        ResultView.printScoreBoard(players.stream()
                .map(ResultDto::of)
                .collect(Collectors.toList()));
    }
}
