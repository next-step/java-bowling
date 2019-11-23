import bowling.BowlingGame;
import bowling.frame.Frame;
import view.InputView;
import view.ResultView;

import static bowling.Frames.FRAME_COUNT;
import static bowling.score.rollling.Pin.MAX_PIN_NUMBER;

public class BowlingApplication {
    public static void main(String[] args) {
        String name = InputView.inputPlayer();
        BowlingGame bowlingGame = new BowlingGame(name);
        ResultView.printInitialScoreBoard(bowlingGame);
        Frame before = runFirstFrame(bowlingGame, 1);
        for (int frameIndex = 2; frameIndex < FRAME_COUNT; frameIndex++) {
            before = runNormalFrame(bowlingGame, frameIndex, before);
        }
        runFinalFrame(bowlingGame, before);
    }

    private static Frame runFirstFrame(BowlingGame bowlingGame, int frameIndex) {
        int firstScore = InputView.inputFrame(frameIndex);
        Frame frame = Frame.of(firstScore);
        bowlingGame.addFrame(frame);
        ResultView.printScoreBoard(bowlingGame);
        if (firstScore < MAX_PIN_NUMBER) {
            int secondScore = InputView.inputFrame(frameIndex);
            frame.addScore(secondScore);
            ResultView.printScoreBoard(bowlingGame);
        }
        return frame;
    }

    private static Frame runNormalFrame(BowlingGame bowlingGame, int frameIndex, Frame before) {
        int firstScore = InputView.inputFrame(frameIndex);
        Frame frame = Frame.of(firstScore, before);
        bowlingGame.addFrame(frame);
        ResultView.printScoreBoard(bowlingGame);
        if (firstScore < MAX_PIN_NUMBER) {
            int secondScore = InputView.inputFrame(frameIndex);
            frame.addScore(secondScore);
            ResultView.printScoreBoard(bowlingGame);
        }
        return frame;
    }

    private static void runFinalFrame(BowlingGame bowlingGame, Frame before) {
        int firstScore = InputView.inputFrame(FRAME_COUNT);
        Frame frame = Frame.finalOf(firstScore, before);
        bowlingGame.addFrame(frame);
        ResultView.printScoreBoard(bowlingGame);

        int secondScore = 0;
        if (firstScore < MAX_PIN_NUMBER) {
            secondScore = InputView.inputFrame(FRAME_COUNT);
            frame.addScore(secondScore);
            ResultView.printScoreBoard(bowlingGame);
        }
        if (firstScore == MAX_PIN_NUMBER) {
            secondScore = InputView.inputFrame(FRAME_COUNT);
            frame.addBonus(secondScore);
            ResultView.printScoreBoard(bowlingGame);
        }
        if (secondScore == MAX_PIN_NUMBER || firstScore + secondScore == MAX_PIN_NUMBER) {
            int bonusScore = InputView.inputFrame(FRAME_COUNT);
            frame.addBonus(bonusScore);
            ResultView.printScoreBoard(bowlingGame);
        }
    }
}
