package bowling.view;

import bowling.domain.FrameResult;
import bowling.domain.Name;

import java.util.List;

public class ResultView {
    private static final String SCORE_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String FRAME_DELIMETER = "|";

    public void printResult(Name name, List<FrameResult> frameResults) {
        System.out.println(SCORE_HEADER);
        System.out.print(FRAME_DELIMETER);

        System.out.print(addBlankForName(name.name()));
        frameResults.stream()
                .map(FrameResult::mark)
                .forEach((mark) -> System.out.print(FRAME_DELIMETER + String.format("  %-4s", mark)));

        System.out.println(FRAME_DELIMETER);
    }

    private String addBlankForName(String name) {
        return "  " + name + " ";
    }
}
