package bowling;

import bowling.domain.Frames;
import bowling.exception.EndedFrameException;
import bowling.exception.InvalidNumberOfFallenPinsException;
import bowling.exception.MaximumSumExceededException;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Controller {
    public static void main(String[] args) {
        Frames frames = new Frames(InputView.inputNameOfPlayer());
        ResultView.printBeforeGame(frames.getPlayer());

        playGame(frames);
    }

    private static void playGame(final Frames frames) {
        for (int frame = 1; frame < 11; frame++) {
            playFrame(frames, frame);
        }
    }

    private static void playFrame(final Frames frames, final int frame) {
        boolean flag = false;
        while (!flag) {
            flag = play(frames, frame);
        }
    }

    private static boolean play(final Frames frames, final int frame) {
        try {
            int numberOfFallenPins = Integer.parseInt(InputView.inputNumberOfFallenPinsInFrame(frame));
            boolean flag = frames.playFrame(numberOfFallenPins);
            ResultView.printGameInProgress(frames.getPlayer(), frames.getGameRecords());
            return flag;
        } catch (InvalidNumberOfFallenPinsException e) {
            System.out.println(e.getMessage());
            return play(frames, frame);
        } catch (MaximumSumExceededException e) {
            System.out.println(e.getMessage());
            return play(frames, frame);
        } catch (EndedFrameException e) {
            return true;
        }
    }
}
