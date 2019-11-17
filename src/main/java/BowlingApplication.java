import game.BowlingGame;
import game.Frame;
import view.InputView;
import view.ResultView;

import static game.Frames.FINAL_FRAME;
import static score.Score.MAX_SCORE;

public class BowlingApplication {
    public static void main(String[] args) {
        String name = InputView.inputPlayer();
        BowlingGame bowlingGame = new BowlingGame(name);
        ResultView.printInitialScoreBoard(bowlingGame);
        for (int frameIndex = 1; frameIndex < FINAL_FRAME; frameIndex++) {
            runNormalFrame(bowlingGame, frameIndex);
        }
        runFinalFrame(bowlingGame);
    }

    private static void runNormalFrame(BowlingGame bowlingGame, int frameIndex) {
        int firstScore = InputView.inputFrame(frameIndex);
        Frame frame = Frame.of(firstScore);
        bowlingGame.addFrame(frame);
        ResultView.printScoreBoard(bowlingGame);
        if (firstScore < MAX_SCORE) {
            int secondScore = InputView.inputFrame(frameIndex);
            frame.addScore(secondScore);
            ResultView.printScoreBoard(bowlingGame);
        }
    }

    private static void runFinalFrame(BowlingGame bowlingGame) {
        int firstScore = InputView.inputFrame(FINAL_FRAME);
        Frame frame = Frame.finalOf(firstScore);
        bowlingGame.addFrame(frame);
        ResultView.printScoreBoard(bowlingGame);

        int secondScore = 0;
        if (firstScore < MAX_SCORE) {
            secondScore = InputView.inputFrame(FINAL_FRAME);
            frame.addScore(secondScore);
            ResultView.printScoreBoard(bowlingGame);
        }
        if (firstScore == MAX_SCORE) {
            secondScore = InputView.inputFrame(FINAL_FRAME);
            frame.addBonus(secondScore);
            ResultView.printScoreBoard(bowlingGame);
        }
        if (secondScore == MAX_SCORE || firstScore + secondScore == MAX_SCORE) {
            int bonusScore = InputView.inputFrame(FINAL_FRAME);
            frame.addBonus(bonusScore);
            ResultView.printScoreBoard(bowlingGame);
        }
    }
}
