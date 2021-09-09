package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Person;
import bowling.domain.Status;

public class ResultView {

    public static void showFrame(Frame frame) {
        System.out.println(frame.currentFrame() + "프레임 투구 ");
    }

    public static void showHead() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    public static void showScoreBoard(Frames frames) {
        for (Frame frame : frames) {
            showCurrentFrameScore(frame);
        }
        System.out.println();
    }

    public static void showCurrentFrameScore(Frame frame) {
        if (frame.pinStatus() == Status.MISS) {
            System.out.print(frame.numberOfDownedPins() + " | ");
            return;
        }

        System.out.print(frame.pinStatus().getLetter() + " | ");
    }

    public static void showPersonNameOnBoard(Person person) {
        System.out.print("|  " + person.name() + "  |");
    }
}
