package bowling.controller;

import bowling.domain.*;
import bowling.view.InputUi;
import bowling.view.OutputUi;

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

    private static void createNomalFrame(int count, Frame frame) {
        if (IntStream.rangeClosed(1, 2)
                .map(i -> printResult(count, frame))
                .anyMatch(BowlingGameController::isStrikeOrSpare)) {
            return;
        }
    }

    private static void createFinalFrame(Frame frame) {
        for (int i = 0; i < 3; i++) {
            if (!isStrikeOrSpare(printResult(BOWLING_MAX_NUMBER, frame))) {
                printResult(BOWLING_MAX_NUMBER, frame);
                InputUi.close();
                return;
            }
        }
    }
}