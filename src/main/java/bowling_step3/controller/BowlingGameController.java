package bowling_step3.controller;

import bowling_step3.domain.*;
import bowling_step3.view.InputUi;
import bowling_step3.view.OutputUi;

import java.util.stream.IntStream;

public class BowlingGameController {

    private static final int BOWLING_MAX_NUMBER = 10;

    private static Player player;

    private static GameFrames gameFrames;

    public static void run() {
        player = Player.of(InputUi.inputPlayer());
        OutputUi.printInitFrame(player);

        gameFrames = GameFrames.init();
        IntStream.range(1, BOWLING_MAX_NUMBER)
                .forEach(i -> createNomalFrame(i, NomalFrame.init()));

        createFinalFrame(FinalFrame.init());
        InputUi.close();
    }

    private static int printResult(int count, Frame frame) {
        int countOfKnockDown = InputUi.inputFrame(count);

        Pitch pitch = Pitch.of(countOfKnockDown);
        frame.add(pitch);
        gameFrames.put(count, frame);
        OutputUi.printInitBowling(player, gameFrames);

        return countOfKnockDown;
    }

    private static boolean isStrikeOrSpare(int countOfKnockDown) {
        return countOfKnockDown == BOWLING_MAX_NUMBER;
    }

    private static void checkOfSpare(int sum) {
        if (!isStrikeOrSpare(sum)) {
            return;
        }
    }

    private static void createNomalFrame(int count, Frame frame) {
        IntStream.rangeClosed(1, 2)
                .map(i -> printResult(count, frame))
                .anyMatch(BowlingGameController::isStrikeOrSpare);
    }

    private static void createFinalFrame(Frame frame) {
        int countOfKnockDown = printResult(BOWLING_MAX_NUMBER, frame);

        if (!isStrikeOrSpare(countOfKnockDown)) {
            int nextCountOfBowlingKnockDown = printResult(BOWLING_MAX_NUMBER, frame);
            checkOfSpare(countOfKnockDown + nextCountOfBowlingKnockDown);
            return;
        }
        printResult(BOWLING_MAX_NUMBER, frame);
        printResult(BOWLING_MAX_NUMBER, frame);
    }
}