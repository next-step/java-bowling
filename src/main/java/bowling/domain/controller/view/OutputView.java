package bowling.domain.controller.view;

import bowling.domain.bowl.Bowl;
import bowling.domain.bowling.Bowling;
import bowling.domain.frame.Frame;

import java.util.List;

public class OutputView {

    private static final StringBuilder STRING_BUILDER = new StringBuilder();
    private static final int NUMBER_OF_FRAME = 10;
    private static final String BOARD_OF_HEAD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n|";
    private static final String BOARD_FORMAT = "%4s |";


    private OutputView() {
    }

    public static void showBowling(Bowling bowling) {
        System.out.println(BOARD_OF_HEAD);
        append(viewFormat(bowling.nameOfParticipant()));
        List<Frame> frames = bowling.frames();


    }

    public static String String(Bowl bowl) {
        return "";
    }

    public static void append(String string) {
        STRING_BUILDER.append(string);
    }

    public static String viewFormat(String string) {
        return String.format(BOARD_FORMAT, string);
    }

}
