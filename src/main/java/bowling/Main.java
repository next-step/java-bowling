package bowling;

import static bowling.view.InputView.inputNextFrameShot;
import static bowling.view.InputView.inputUsername;
import static bowling.view.ResultView.printResult;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.score.Pin;
import bowling.domain.user.User;

public class Main {

    public static void main(String[] args) {
        User user = User.of(inputUsername());
        Frames frames = Frames.init();
        printResult(user, frames);

        Frame frame = frames.first();
        for (int i=0; i<9; i++) {
            Pin pin = Pin.of(inputNextFrameShot(i));
            frame.updateScoreByPin(pin);
            printResult(user, frames);


        }
    }

    public static void normalView(User user, Frame frame) {

    }

}
