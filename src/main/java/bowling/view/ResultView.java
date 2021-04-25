package bowling.view;

import bowling.domain.FrameResult;
import bowling.domain.Name;

import java.util.List;

public class ResultView {
    private static final String SCORE_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String DELIMETER = "|";

    public void printResult(Name name, List<FrameResult> frameResults) {
        System.out.println(SCORE_HEADER);
        System.out.print(DELIMETER);

        System.out.print(addBlankForName(name.name()));
        frameResults.stream()
                .map(FrameResult::marks)
                .map(marks -> String.join(DELIMETER, marks))
                .forEach((mark) -> System.out.print(DELIMETER + String.format("  %-4s", mark)));

        System.out.println(DELIMETER);
    }

    private String addBlankForName(String name) {
        return "  " + name + " ";
    }
}
