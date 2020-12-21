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

    private static final int STRIKE_NUMBER = 10;

    private static Player player;

    public static void run() {
        player = Player.of(InputUi.inputPlayer());
        OutputUi.printInitFrame(player);

        Frames frames = new Frames();
        IntStream.range(1, BOWLING_MAX_NUMBER)
                .forEach(i -> setBowlingKnockDown(i, frames));

        finalFrame(frames);

        InputUi.close();
    }

    private static void setBowlingKnockDown(int number, Frames frames) {
        BowlingKnockDown bowlingKnockDown = frames.initFirst(number, InputUi.inputFrame(number));
        OutputUi.printBowling(player, frames);

        int countOfBowlingKnockDown = bowlingKnockDown.getCountOfBowlingKnockDown();
        if (countOfBowlingKnockDown == STRIKE_NUMBER) {
            return;
        }

        int nextCountOfBowlingKnockDown = InputUi.inputFrame(number);
        int sum = countOfBowlingKnockDown + nextCountOfBowlingKnockDown;
        if (sum > BOWLING_MAX_NUMBER) {
            throw new BowlingMaxCountException();
        }

        frames.initNext(number, bowlingKnockDown, nextCountOfBowlingKnockDown);
        OutputUi.printBowling(player, frames);
    }

    private static void finalFrame(Frames frames) {
        BowlingKnockDown bowlingKnockDown = frames.initFirst(BOWLING_MAX_NUMBER, InputUi.inputFrame(BOWLING_MAX_NUMBER));
        OutputUi.printBowling(player, frames);

        int countOfBowlingKnockDown = bowlingKnockDown.getCountOfBowlingKnockDown();
        if (countOfBowlingKnockDown < BOWLING_MAX_NUMBER) {
            int nextCountOfBowlingKnockDown = InputUi.inputFrame(BOWLING_MAX_NUMBER);
            int sum = countOfBowlingKnockDown + nextCountOfBowlingKnockDown;

            frames.initNext(BOWLING_MAX_NUMBER, bowlingKnockDown, nextCountOfBowlingKnockDown);
            OutputUi.printBowling(player, frames);
            if (sum < BOWLING_MAX_NUMBER) {
                InputUi.close();
                return;
            }
        }

        bowlingKnockDown = frames.initNext(BOWLING_MAX_NUMBER, bowlingKnockDown, InputUi.inputFrame(BOWLING_MAX_NUMBER));
        OutputUi.printBowling(player, frames);

        frames.initNext(BOWLING_MAX_NUMBER, bowlingKnockDown, InputUi.inputFrame(BOWLING_MAX_NUMBER));
        OutputUi.printBowling(player, frames);
    }
}
