package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Name;

import java.util.List;

public class ResultView {
    private static final String SCORE_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String BAR = "|";

    public void printResult(Name name, List<Frame> frames) {
        System.out.println(SCORE_HEADER);
        System.out.print(BAR);

        System.out.print(addBlankForName(name.name()));
        frames.stream()
                .map(Frame::toString)
                .forEach((frame) -> System.out.print(BAR + frame));

        System.out.println(BAR);
    }

    private String addBlankForName(String name) {
        return "  " + name + " ";
    }
}
