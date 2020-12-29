package bowling_step3.controller;


import bowling_step3.domain.BowlingGame;
import bowling_step3.domain.BowlingGames;
import bowling_step3.domain.Player;
import bowling_step3.view.InputUi;
import bowling_step3.view.OutputUi;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingGameController {
    private static final int LAST_FRAME = 10;

    private static BowlingGames bowlingGames;

    public static void run() {
        int countOfPlayer = InputUi.inputCountOfPlayer();

        List<Player> players =
                IntStream.rangeClosed(1, countOfPlayer)
                        .mapToObj(InputUi::inputPlayer)
                        .map(Player::of)
                        .collect(Collectors.toList());

        OutputUi.printInitHeader();

        bowlingGames = BowlingGames.of(players);
        OutputUi.printInitFrame(bowlingGames);

        IntStream.rangeClosed(1, LAST_FRAME)
                .forEach(i -> playBowling(bowlingGames, i));

        InputUi.close();
    }

    private static boolean isMatchFrameCount(int frameCount, int index) {
        return frameCount == index;
    }

    private static void playFrame(BowlingGame bowlingGame, int index) {
        while (isMatchFrameCount(bowlingGame.getFrameCount(), index)) {
            int countOfKnockDown = InputUi.inputFrame(bowlingGame.getPlayer());
            bowlingGame.pitch(countOfKnockDown);
            OutputUi.printInitBowling(bowlingGames.getBowlingGames());
        }
    }

    private static void playBowling(BowlingGames bowlingGames, int index) {
        bowlingGames.getBowlingGames()
                .forEach(bowlingGame -> playFrame(bowlingGame, index));
    }
}



