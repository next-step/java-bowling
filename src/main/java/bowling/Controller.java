package bowling;

import bowling.domain.Game;
import bowling.exception.EndedFrameException;
import bowling.exception.InvalidNumberOfFallenPinsException;
import bowling.exception.MaximumSumExceededException;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Controller {
    public static void main(String[] args) {
        Game Game = new Game(InputView.inputNameOfPlayer());
        ResultView.printLabel();
        ResultView.printScore(Game.getPlayer(), Game.getGameRecords());

        playGame(Game);
    }

    private static void playGame(final Game Game) {
        for (int frame = 1; frame < 11; frame++) {
            playFrame(Game, frame);
        }
    }

    private static void playFrame(final Game Game, final int frame) {
        boolean flag = false;
        while (!flag) {
            flag = play(Game, frame);
        }
    }

    private static boolean play(final Game Game, final int frame) {
        try {
            int numberOfFallenPins = Integer.parseInt(InputView.inputNumberOfFallenPinsInFrame(frame));
            boolean flag = Game.playFrame(frame, numberOfFallenPins);
            ResultView.printLabel();
            ResultView.printScore(Game.getPlayer(), Game.getGameRecords());
            return flag;
        } catch (InvalidNumberOfFallenPinsException e) {
            System.out.println(e.getMessage());
            return play(Game, frame);
        } catch (MaximumSumExceededException e) {
            System.out.println(e.getMessage());
            return play(Game, frame);
        } catch (EndedFrameException e) {
            return true;
        }
    }
}
