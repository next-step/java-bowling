package bowling;

import bowling.domain.Game;
import bowling.exception.EndedFrameException;
import bowling.exception.InvalidNumberOfFallenPinsException;
import bowling.exception.MaximumSumExceededException;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Controller {
    public static void main(String[] args) {
        Game game = new Game(InputView.inputNameOfPlayer());
        ResultView.printLabel();
        ResultView.printScore(game.getPlayer(), game.getGameRecords());

        playGame(game);
    }

    private static void playGame(final Game game) {
        for (int frame = 1; frame < 11; frame++) {
            playFrame(game, frame);
        }
    }

    private static void playFrame(final Game game, final int frame) {
        boolean flag = false;
        while (!flag) {
            flag = play(game, frame);
        }
    }

    private static boolean play(final Game game, final int frame) {
        try {
            int numberOfFallenPins = Integer.parseInt(InputView.inputNumberOfFallenPinsInFrame(frame));
            boolean flag = game.playFrame(frame, numberOfFallenPins);
            ResultView.printLabel();
            ResultView.printScore(game.getPlayer(), game.getGameRecords());
            return flag;
        } catch (InvalidNumberOfFallenPinsException e) {
            System.out.println(e.getMessage());
            return play(game, frame);
        } catch (MaximumSumExceededException e) {
            System.out.println(e.getMessage());
            return play(game, frame);
        } catch (EndedFrameException e) {
            return true;
        }
    }
}
