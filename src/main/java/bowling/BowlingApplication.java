package bowling;

public class BowlingApplication {

    public static void main(String[] args) {

        UserName userName = InputView.inputUserName();
        Frames frames = Frames.start();

        BowlingGame bowlingGame = new BowlingGame(userName, frames);

        ResultView.printResult(bowlingGame);

        while (!bowlingGame.isFinished()) {
            int score = InputView.inputUserScore(bowlingGame.currentFrameNumber());
            bowlingGame.bowl(Pin.from(score));
            ResultView.printResult(bowlingGame);
        }
    }
}
