package bowling.view;

import bowling.domain.FrameMark;
import bowling.domain.Name;

import java.util.List;

public class ResultView {
    private static final String PRINT_FORMAT = "  %-4s";
    private static final String SCORE_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String DELIMETER = "|";

    public void printMark(Name name, List<FrameMark> frameMarks) {
        System.out.println(SCORE_HEADER);
        System.out.print(DELIMETER);

        System.out.printf(PRINT_FORMAT, name.name());
        frameMarks.stream()
                .map(FrameMark::marks)
                .map(marks -> String.join(DELIMETER, marks))
                .forEach((mark) -> System.out.print(DELIMETER + String.format(PRINT_FORMAT, mark)));

        System.out.println(DELIMETER);
    }
}
