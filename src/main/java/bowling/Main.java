package bowling;

import static bowling.view.InputView.inputNextFrameShot;
import static bowling.view.InputView.inputUsername;
import static bowling.view.ResultView.printResult;
import static bowling.view.ResultView.printStartBoard;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;
import bowling.domain.score.Pin;
import bowling.domain.user.User;

public class Main {

    public static void main(String[] args) {
        User user = User.of(inputUsername());
        Frame firstFrame = NormalFrame.createFirstFrame();
        printStartBoard(user);


        for (int i=1; i<=9; i++) {
            Frame nowFrame = Frame.getLastFrame(firstFrame);
            printSingleScore(user, Frames.creatByFirstFrame(firstFrame), i, nowFrame);
            if (nowFrame.isNextScore()) {
                printSingleScore(user, Frames.creatByFirstFrame(firstFrame), i, nowFrame);
            }
            nowFrame.createNextFrame();
        }
    }

    private static void printSingleScore(User user, Frames frames, int i, Frame nowFrame) {
        Pin pin = Pin.of(inputNextFrameShot(i));
        nowFrame.updateScoreByPin(pin);
        printResult(user, frames);
    }

}
