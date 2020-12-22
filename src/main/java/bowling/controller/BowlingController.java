package bowling.controller;

import bowling.domain.frame.Frames;
import bowling.domain.game.Bowling;
import bowling.domain.game.BowlingGames;
import bowling.domain.point.Point;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        return Stream.generate(() ->
                Bowling.of(InputView.inputPlayerName(), Frames.init())).limit(peopleCount)
                .collect(Collectors.toList());
    }


}
