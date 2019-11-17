import game.BowlingGame;
import game.Frame;
import view.InputView;
import view.ResultView;

public class BowlingApplication {
    public static void main(String[] args) {
        String name = InputView.inputPlayer();
        BowlingGame bowlingGame = new BowlingGame(name);
        ResultView.printInitialScoreBoard(bowlingGame);
        for (int frameIndex = 1; frameIndex <= 10; frameIndex++) {
            int firstScore = InputView.inputFrame(frameIndex);
            Frame frame = new Frame();
            bowlingGame.addFrame(frame);
            frame.addScore(firstScore);

            ResultView.printScoreBoard(bowlingGame);
            int secondScore = 0;
            if (firstScore < 10) {
                secondScore = InputView.inputFrame(frameIndex);
                frame.addScore(secondScore);
                ResultView.printScoreBoard(bowlingGame);
            }
            if (frameIndex == 10 && firstScore + secondScore == 10) {
                int bonusScore = InputView.inputFrame(frameIndex);
                frame.addBonus(bonusScore);
                ResultView.printScoreBoard(bowlingGame);
            }
        }
    }
}
