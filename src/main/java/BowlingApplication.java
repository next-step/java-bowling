import game.BowlingGame;
import game.Frame;
import view.InputView;
import view.ResultView;

public class BowlingApplication {
    public static void main(String[] args) {
        String name = InputView.inputPlayer();
        BowlingGame bowlingGame = new BowlingGame(name);
        ResultView.printInitialScoreBoard(bowlingGame);
        for (int frameIndex = 1; frameIndex <= 9; frameIndex++) {
            runNormalFrame(bowlingGame, frameIndex);
        }
        runFinalFrame(bowlingGame);
    }

    private static void runNormalFrame(BowlingGame bowlingGame, int frameIndex) {
        int firstScore = InputView.inputFrame(frameIndex);
        Frame frame = Frame.of(firstScore);
        bowlingGame.addFrame(frame);
        ResultView.printScoreBoard(bowlingGame);
        if (firstScore < 10) {
            int secondScore = InputView.inputFrame(frameIndex);
            frame.addScore(secondScore);
            ResultView.printScoreBoard(bowlingGame);
        }
    }

    private static void runFinalFrame(BowlingGame bowlingGame) {
        int firstScore = InputView.inputFrame(10);
        Frame frame = Frame.finalOf(firstScore);
        bowlingGame.addFrame(frame);
        ResultView.printScoreBoard(bowlingGame);

        int secondScore = 0;
        if (firstScore < 10) {
            secondScore = InputView.inputFrame(10);
            frame.addScore(secondScore);
            ResultView.printScoreBoard(bowlingGame);
        }
        if (firstScore == 10) {
            secondScore = InputView.inputFrame(10);
            frame.addBonus(secondScore);
            ResultView.printScoreBoard(bowlingGame);
        }
        if (secondScore == 10 || firstScore + secondScore == 10) {
            int bonusScore = InputView.inputFrame(10);
            frame.addBonus(bonusScore);
            ResultView.printScoreBoard(bowlingGame);
        }
    }
}
