package camp.nextstep.edu.rebellion.bowling.view;

import camp.nextstep.edu.rebellion.bowling.domain.ScoreBoard;
import camp.nextstep.edu.rebellion.bowling.util.StringUtil;

public class ResultView {
    private static final String FORMAT = "| %3s\t";
    private static final String PIPE = "|";
    private ResultView() {}

    public static void print(ScoreBoard board) {
        int frameNumber = 1;

        StringBuilder title = new StringBuilder();
        StringBuilder score = new StringBuilder();

        title.append(wrapFormat("NAME"));
        score.append(wrapFormat(board.getPlayerName()));

        for (String symbol : board.getResultSymbol()) {
            title.append(wrapFormat(StringUtil.parseInt(frameNumber++)));
            score.append(wrapFormat(symbol));
        }

        title.append(PIPE);
        score.append(PIPE);

        System.out.println(title.toString());
        System.out.println(score.toString());
    }

    private static String wrapFormat(String str) {
        return String.format(FORMAT, str);
    }
}
