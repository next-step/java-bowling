package bowling;

public class BowlingApplication {

    public static void main(String[] args) {

        UserName userName = InputView.inputUserName();
        Frames frames = Frames.start();

        Bowling bowling = new Bowling(userName, frames);

        ResultView.printResult(bowling);

        while (!bowling.isFinished()) {
            int score = InputView.inputUserScore(bowling.currentFrameNumber());
            bowling.bowl(Pin.from(score));
            ResultView.printResult(bowling);
        }
    }
}
