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

    public static final int START_PEOPLE_CURSOR = 0;
    public static final int START_FRAME_CURSOR = 0;


    private static int peopleCursor = START_PEOPLE_CURSOR;
    private static int frameCursor = START_FRAME_CURSOR;
    public static void runBowlingGame() {
        int peopleCount = InputView.inputParticipatePeople();

        BowlingGames bowlingGames = BowlingGames.of(initBowlingGames(peopleCount));


        while (!bowlingGames.isBowlingGameEnd()) {
            Point pointPitch = InputView.inputPitchBowl(bowlingGames.getPlayerName(peopleCursor));
            bowlingGames.pitch(peopleCursor, pointPitch);
            OutputView.printResult(bowlingGames.getBowlingDto());

            increasePeopleCursor(bowlingGames);
            isPitchedFrameLastPeople(bowlingGames);

        }
    }

    private static void increasePeopleCursor(BowlingGames bowlingGames) {
        if (bowlingGames.isLastPeople(peopleCursor, frameCursor)) {
            peopleCursor++;
        }
    }

    private static void isPitchedFrameLastPeople(BowlingGames bowlingGames) {
        if (bowlingGames.isLastFramePeople(frameCursor)) {
            peopleCursor = 0;
            frameCursor++;
        }
    }

    private static List<Bowling> initBowlingGames(int peopleCount) {
        return Stream.generate(() ->
                Bowling.of(InputView.inputPlayerName(), Frames.init())).limit(peopleCount)
                .collect(Collectors.toList());
    }


}
