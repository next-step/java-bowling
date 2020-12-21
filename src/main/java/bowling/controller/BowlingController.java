package bowling.controller;

import bowling.domain.Player;
import bowling.domain.frame.Frames;
import bowling.domain.game.Bowling;
import bowling.domain.game.BowlingGamesDto;
import bowling.domain.game.BowlingGames;
import bowling.domain.point.Point;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BowlingController {


    public static void runBowlingGame() {
        int peopleCount = InputView.inputParticipatePeople();

        BowlingGames bowlingGames = BowlingGames.of(initBowlingGames(peopleCount));

        while (!bowlingGames.isBowlingGameEnd()) {
            Point pointPitch = InputView.inputPitchBowl(bowlingGames.getPlayerName());
            bowlingGames.pitch(pointPitch);

            OutputView.printResult(bowlingGames.getBowlingDto());
        }
    }

    private static List<Bowling> initBowlingGames(int peopleCount) {
        List<Bowling> bowlings = new ArrayList<>();
        for (int i = 0; i < peopleCount; i++) {
            Player player = InputView.inputPlayerName();
            Bowling bowling = Bowling.of(player, Frames.init());
            bowlings.add(bowling);
        }
        return bowlings;
    }


}
