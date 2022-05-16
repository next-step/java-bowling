package bowling;

import bowling.domain.Frame;
import bowling.domain.Game;
import bowling.exception.InvalidNumberOfFallenPinsException;
import bowling.exception.MaximumSumExceededException;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Controller {
    public static final int TOTAL_COUNT_OF_ATTEMPTS = 2;
    public static final int NO_COUNT_OF_ATTEMPTS_LEFT = 0;
    public static final int ONE_COUNT_OF_ATTEMPTS = 1;

    public static void main(String[] args) {
        Game game = new Game(InputView.inputNameOfPlayer());

        ResultView.printLabel();
        ResultView.printScore(game.getPlayer(), game.getGameRecords());

        for (int frame = 1; frame <= 10; frame++) {
            playSecondAttemptsInFrame(game, frame);
        }
    }

    private static void playSecondAttemptsInFrame(final Game game, final int frame) {
        int countOfAttempts = TOTAL_COUNT_OF_ATTEMPTS;
        while (countOfAttempts > NO_COUNT_OF_ATTEMPTS_LEFT) {
            countOfAttempts -= playOneOfSecondAttemptsInFrame(game, frame);

            ResultView.printLabel();
            ResultView.printScore(game.getPlayer(), game.getGameRecords());
        }
    }

    private static int playOneOfSecondAttemptsInFrame(final Game game, final int frame) {
        try {
            int numberOfFallenPins = Integer.parseInt(InputView.inputNumberOfFallenPinsInFrame(frame));
            game.play(frame, numberOfFallenPins);
            if (numberOfFallenPins == Frame.MAX_NUMBER_OF_PIN) {
                return TOTAL_COUNT_OF_ATTEMPTS;
            }
            return ONE_COUNT_OF_ATTEMPTS;
        } catch (InvalidNumberOfFallenPinsException e) {
            System.out.println(e.getMessage());
            return playOneOfSecondAttemptsInFrame(game, frame);
        } catch (MaximumSumExceededException e) {
            System.out.println(e.getMessage());
            return playOneOfSecondAttemptsInFrame(game, frame);
        }
    }
}
