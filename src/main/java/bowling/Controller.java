package bowling;

import bowling.domain.Frames;
import bowling.domain.LastFrame;
import bowling.domain.NormalFrame;
import bowling.exception.EndedFrameException;
import bowling.exception.InvalidNumberOfFallenPinsException;
import bowling.exception.MaximumSumExceededException;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Controller {
    public static final int FINAL_FRAME = 10;

    public static void main(String[] args) {
        Frames frames = new Frames(InputView.inputNameOfPlayer());
        ResultView.printBeforeGame(frames.getPlayer());

        playGame(frames);
    }

    private static void playGame(final Frames frames) {
        for (int frame = 1; frame <= FINAL_FRAME; frame++) {
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
            boolean flag = playFrame(frames, frame, numberOfFallenPins);
            ResultView.printGameInProgress(frames.getPlayer(), frames.getGameRecords(), frames.score());
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

    private static boolean playFrame(final Frames frames, final int frame, final int numberOfFallenPins) {
        if (frame == FINAL_FRAME) {
            return frames.playFrame(numberOfFallenPins, new LastFrame());
        }
        return frames.playFrame(numberOfFallenPins, new NormalFrame());
    }
}
