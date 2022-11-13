package bowling;

public class BowlingApplication {

    public static void main(String[] args) {

        UserName userName = InputView.inputUserName();
        int score = InputView.inputUserScore(NormalFrame.MIN_FRAME_NUMBER);
        Frames frames = Frames.start(Pin.from(score));
        ResultView.printResult(userName, frames);

    }
}
