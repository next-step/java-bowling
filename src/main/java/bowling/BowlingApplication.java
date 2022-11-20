package bowling;

public class BowlingApplication {

    public static void main(String[] args) {

        UserName userName = InputView.inputUserName();
        Frames frames = Frames.start();
        ResultView.printResult(userName, frames);

        Bowling bowling = new Bowling(userName, frames);

        while (!bowling.isFinished()) {
            int score = InputView.inputUserScore(bowling.currentFrameNumber());
            bowling.bowl(Pin.from(score));
            ResultView.printResult(userName, frames);
        }
        System.out.println("frames = " + frames.getScore());
    }
}
