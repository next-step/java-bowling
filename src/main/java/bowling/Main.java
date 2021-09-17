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
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        User user = User.of(inputUsername());
        Frame firstFrame = NormalFrame.createFirstFrame();
        printStartBoard(user);

        IntStream.range(1, 10)
            .forEach(round -> normalView(user, firstFrame, round));
        Frame lastFrame = Frame.getLastFrame(firstFrame);
        finalView(user, firstFrame, lastFrame);
    }

    private static void normalView(User user, Frame firstFrame, int i) {
        Frame nowFrame = Frame.getLastFrame(firstFrame);
        printSingleScore(user, Frames.creatByFirstFrame(firstFrame), i, nowFrame);
        if (nowFrame.isNextScore()) {
            printSingleScore(user, Frames.creatByFirstFrame(firstFrame), i, nowFrame);
        }
        nowFrame.createNextFrame();
    }

    private static void printSingleScore(User user, Frames frames, int i, Frame nowFrame) {
        Pin pin = Pin.of(inputNextFrameShot(i));
        nowFrame.updateScoreByPin(pin);
        printResult(user, frames);
    }

    private static void finalView(User user, Frame firstFrame, Frame lastFrame) {
        printSingleScore(user, Frames.creatByFirstFrame(firstFrame), 10, lastFrame);
        printSingleScore(user, Frames.creatByFirstFrame(firstFrame), 10, lastFrame);
        if (lastFrame.isNextScore()) {
            printSingleScore(user, Frames.creatByFirstFrame(firstFrame), 10, lastFrame);
        }
    }

}
