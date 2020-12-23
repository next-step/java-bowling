package bowling.controller;

import bowling.domain.BowlingKnockDown;
import bowling.domain.Frames;
import bowling.domain.Player;
import bowling.exception.BowlingMaxCountException;
import bowling.view.InputUi;
import bowling.view.OutputUi;

import java.util.stream.IntStream;

public class BowlingGameController {

    private static final int BOWLING_MAX_NUMBER = 10;

    private static Player player;

    public static void run() {
        player = Player.of(InputUi.inputPlayer());
        OutputUi.printInitFrame(player);

        Frames frames = new Frames();
        IntStream.range(1, BOWLING_MAX_NUMBER)
                .forEach(i -> nomalFrame(i, frames));

        finalFrame(frames);
        InputUi.close();
    }

    private static boolean isStrikeOrSpare(int number) {
        return number == BOWLING_MAX_NUMBER;
    }

    private static BowlingKnockDown initBowling(int number, Frames frames) {
        BowlingKnockDown bowlingKnockDown = new BowlingKnockDown(InputUi.inputFrame(number));
        frames.put(number, bowlingKnockDown);
        OutputUi.printBowling(player, frames);
        return bowlingKnockDown;
    }

    private static BowlingKnockDown nextBowling(BowlingKnockDown bowlingKnockDown, int number, Frames frames) {
        bowlingKnockDown = new BowlingKnockDown(bowlingKnockDown.getCurrentOfKnockDown(), InputUi.inputFrame(number));
        frames.put(number, bowlingKnockDown);
        OutputUi.printBowling(player, frames);
        return bowlingKnockDown;
    }

    private static void nomalFrame(int number, Frames frames) {
        BowlingKnockDown bowlingKnockDown = initBowling(number, frames);

        int countOfBowlingKnockDown = bowlingKnockDown.getCurrentOfKnockDown();
        if (isStrikeOrSpare(countOfBowlingKnockDown)) {
            return;
        }

        bowlingKnockDown = nextBowling(bowlingKnockDown, number, frames);
        int nextCountOfBowlingKnockDown = bowlingKnockDown.getNextOfKnockDown();
        int sum = countOfBowlingKnockDown + nextCountOfBowlingKnockDown;
        if (sum > BOWLING_MAX_NUMBER) {
            throw new BowlingMaxCountException();
        }
    }

    private static void bonusBall(BowlingKnockDown bowlingKnockDown, Frames frames) {
        int bonusOfKnockDown = InputUi.inputFrame(BOWLING_MAX_NUMBER);
        bowlingKnockDown =
                new BowlingKnockDown(bowlingKnockDown.getCurrentOfKnockDown(),
                        bowlingKnockDown.getNextOfKnockDown(),
                        bonusOfKnockDown);
        frames.put(BOWLING_MAX_NUMBER, bowlingKnockDown);
        OutputUi.printBowling(player, frames);
    }

    private static void bowlingKnockDown(Frames frames, BowlingKnockDown bowlingKnockDown) {
        bowlingKnockDown = nextBowling(bowlingKnockDown, BOWLING_MAX_NUMBER, frames);
        int sum = bowlingKnockDown.getCurrentOfKnockDown() + bowlingKnockDown.getNextOfKnockDown();
        if (!isStrikeOrSpare(sum)) {
            InputUi.close();
        }

        if (isStrikeOrSpare(sum)) {
            bonusBall(bowlingKnockDown, frames);
        }
    }


    private static void finalFrame(Frames frames) {
        BowlingKnockDown bowlingKnockDown = initBowling(BOWLING_MAX_NUMBER, frames);
        int countOfBowlingKnockDown = bowlingKnockDown.getCurrentOfKnockDown();
        if (!isStrikeOrSpare(countOfBowlingKnockDown)) {
            bowlingKnockDown(frames, bowlingKnockDown);
            return;
        }

        bowlingKnockDown = nextBowling(bowlingKnockDown, BOWLING_MAX_NUMBER, frames);
        bonusBall(bowlingKnockDown, frames);
    }
}
