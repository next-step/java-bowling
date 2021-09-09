package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.Person;
import bowling.domain.Status;
import bowling.domain.frame.info.FrameInfo;

public class ResultView {

    public static final String SPLIT_BAR = " | ";

    public static void showFrameInfo(FrameInfo frame) {
        System.out.println(frame.currentFrameNumber() + "프레임 투구 ");
    }

    public static void showHead() {
        System.out.println(SPLIT_BAR + " NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    public static void showScoreBoard(Frames frames) {
        for (Frame frame : frames) {
            showCurrentFrameScore(frame);
        }
        System.out.println();
    }

    public static void showCurrentFrameScore(Frame frame) {
        if (frame.pinStatus() == Status.MISS) {
            System.out.print(frame.numberOfDownedPins() + SPLIT_BAR);
            return;
        }

        System.out.print(frame.pinStatus().getLetter() + SPLIT_BAR);
    }

    public static void showPersonNameOnBoard(Person person) {
        System.out.print(SPLIT_BAR + person.name() + SPLIT_BAR);
    }
}
