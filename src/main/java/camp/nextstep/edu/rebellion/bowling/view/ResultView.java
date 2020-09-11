package camp.nextstep.edu.rebellion.bowling.view;

import camp.nextstep.edu.rebellion.bowling.domain.score.BowlingGameScoreBoard;
import camp.nextstep.edu.rebellion.bowling.domain.score.PersonalScoreBoard;
import camp.nextstep.edu.rebellion.bowling.util.StringUtil;

import java.util.stream.Collectors;

import static camp.nextstep.edu.rebellion.bowling.util.StringUtil.*;

public class ResultView {
    private static final String NAME = "NAME";

    private ResultView() {}

    public static void print(BowlingGameScoreBoard board) {
        StringBuilder output = new StringBuilder();
        output.append(generateHeader(board.getTotalFrames())).append(ENTER);
        output.append(
                board.getPersonalScoreBoards()
                        .stream()
                        .map(PersonalScoreBoard::generateForPrint)
                        .collect(Collectors.joining(ENTER)));

        System.out.println(output.toString());
    }

    private static String generateHeader(int cols) {
        StringBuilder builder = new StringBuilder();
        builder.append(wrapFormat(NAME));

        for (int i = 0; i < cols; i++) {
            builder.append(wrapFormat(StringUtil.parseInt(i + 1)));
        }

        builder.append(PIPE);
        return builder.toString();
    }

    private static String wrapFormat(String str) {
        return String.format(FORMAT, str);
    }
}
