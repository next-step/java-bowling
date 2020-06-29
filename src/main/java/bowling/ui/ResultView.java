package bowling.ui;

import bowling.BowlingGame;
import bowling.domain.Player;
import bowling.domain.ResultType;

import java.util.stream.Collectors;

public class ResultView {
    private static final String BOARD_HEADER_FIRST_COLUMN = "| %4s |";
    private static final String NAME = "NAME";
    private static final String ROUND_FORMAT = "  %02d  |";
    private static final String RESULT_FORMAT = "  %-3s |";

    public static void printBoardBeforeStart(Player player) {
        System.out.println(getHeaderRow());
        System.out.println(getPlayerRow(player));
    }

    private static String getHeaderRow() {
        StringBuilder sb = new StringBuilder(String.format(BOARD_HEADER_FIRST_COLUMN, NAME));
        for (int round = BowlingGame.FIRST_ROUND; round <= BowlingGame.FINAL_ROUND; round++) {
            sb.append(String.format(ROUND_FORMAT, round));
        }
        return sb.toString();
    }

    private static String getPlayerRow(Player player) {
        StringBuilder sb = new StringBuilder(String.format(BOARD_HEADER_FIRST_COLUMN, player.getName()));
        player.getGameResult()
                .forEach(r ->
                        sb.append(String.format(RESULT_FORMAT,
                                r.stream().map(ResultType::getSymbol).collect(Collectors.joining("|"))))
                );
        for (int round = player.getCurrentRound(); round < BowlingGame.FINAL_ROUND; round++) {
            sb.append(String.format(RESULT_FORMAT, ""));
        }
        return sb.toString();
    }
}
