package bowling;

public class BowlingApplication {

    public static void main(String[] args) {

        UserName userName = InputView.inputUserName();
        int score = InputView.inputUserScore(NormalFrame.MIN_NORMAL_FRAME_NUMBER);
        Frames frames = Frames.start(Pin.from(score));
        ResultView.printResult(userName, frames);

        Bowling bowling = new Bowling(userName, frames);

        while (!bowling.isFinished()) {
            score = InputView.inputUserScore(bowling.currentFrameNumber());
            bowling.bowl(Pin.from(score));
            ResultView.printResult(userName, frames);
        }
    }
}
