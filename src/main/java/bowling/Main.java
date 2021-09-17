package bowling;

import static bowling.domain.frame.Frames.FINAL_FRAME_ROUND;
import static bowling.domain.frame.Frames.FIRST_FRAME_ROUND;
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

        IntStream.range(FIRST_FRAME_ROUND, FINAL_FRAME_ROUND)
            .forEach(round -> normalView(user, firstFrame, round));
        Frame lastFrame = Frame.getLastFrame(firstFrame);
        finalView(user, firstFrame, lastFrame);
    }

    private static void normalView(User user, Frame firstFrame, int index) {
        Frame nowFrame = Frame.getLastFrame(firstFrame);
        printSingleScore(user, Frames.creatByFirstFrame(firstFrame), index, nowFrame);
        if (nowFrame.isNextScore()) {
            printSingleScore(user, Frames.creatByFirstFrame(firstFrame), index, nowFrame);
        }
        nowFrame.createNextFrame();
    }

    private static void printSingleScore(User user, Frames frames, int index, Frame nowFrame) {
        Pin pin = Pin.of(inputNextFrameShot(index));
        nowFrame.updateScoreByPin(pin);
        printResult(user, frames);
    }

    private static void finalView(User user, Frame firstFrame, Frame lastFrame) {
        printSingleScore(user, Frames.creatByFirstFrame(firstFrame), FINAL_FRAME_ROUND, lastFrame);
        printSingleScore(user, Frames.creatByFirstFrame(firstFrame), FINAL_FRAME_ROUND, lastFrame);
        if (lastFrame.isNextScore()) {
            printSingleScore(user, Frames.creatByFirstFrame(firstFrame), FINAL_FRAME_ROUND, lastFrame);
        }
    }

}
