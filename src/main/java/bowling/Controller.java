package bowling;

import bowling.domain.Game;
import bowling.exception.InvalidNumberOfFallenPinsException;
import bowling.exception.MaximumSumExceededException;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Controller {
    public static void main(String[] args) {
        String player = InputView.inputNameOfPlayer();
        Game game = new Game();

        ResultView.printLabel();
        ResultView.printScore(player, game.getGameRecords());

        for (int frame = 1; frame <= 10; frame++) {
            playSecondAttemptsInFrame(game, frame, player);
        }
    }

    private static void playSecondAttemptsInFrame(final Game game, final int frame, final String player) {
        int countOfAttempts = 2;
        while (countOfAttempts > 0) {
            countOfAttempts -= playOneOfSecondAttemptsInFrame(game, frame);

            ResultView.printLabel();
            ResultView.printScore(player, game.getGameRecords());
        }
    }

    private static int playOneOfSecondAttemptsInFrame(final Game game, final int frame) {
        try {
            int numberOfFallenPins = Integer.parseInt(InputView.inputNumberOfFallenPinsInFrame(frame));
            game.play(frame, numberOfFallenPins);
            if (numberOfFallenPins == 10) {
                return 2;
            }
            return 1;
        } catch (InvalidNumberOfFallenPinsException e) {
            System.out.println(e.getMessage());
            return playOneOfSecondAttemptsInFrame(game, frame);
        } catch (MaximumSumExceededException e) {
            System.out.println(e.getMessage());
            return playOneOfSecondAttemptsInFrame(game, frame);
        }
    }
}
