package camp.nextstep.edu.rebellion.bowling.view;

import camp.nextstep.edu.rebellion.bowling.domain.score.ScoreBoard;
import camp.nextstep.edu.rebellion.bowling.util.StringUtil;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

public class ResultView {
    private static final String FORMAT = "| %3s\t";
    private static final String PIPE = "|";
    private static final String NAME = "NAME";
    private static final String ENTER = "\n";
    private ResultView() {}

    public static void print(ScoreBoard board) {
        StringBuilder output = new StringBuilder();
        List<String> symbols = board.getResultSymbol();
        output
                .append(generateHeader(symbols.size()))
                .append(ENTER)
                .append(generateFrameStatus(board.getPlayerName(), symbols))
                .append(ENTER)
                .append(generateScore(board.getHisScore()));

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

    private static String generateFrameStatus(String playerName, List<String> symbols) {
        StringBuilder builder = new StringBuilder();
        builder.append(wrapFormat(playerName));
        symbols.forEach(s -> builder.append(wrapFormat(s)));
        builder.append(PIPE);
        return builder.toString();
    }

    private static String generateScore(List<String> hitScores) {
        StringBuilder builder = new StringBuilder();
        builder.append(wrapFormat(Strings.EMPTY));
        hitScores.forEach(s -> builder.append(wrapFormat(s)));
        builder.append(PIPE);
        return builder.toString();
    }

    private static String wrapFormat(String str) {
        return String.format(FORMAT, str);
    }
}
