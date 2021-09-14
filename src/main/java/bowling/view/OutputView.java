package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.player.Player;
import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class OutputView {
    private static final String TABLE_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String COLUMN_INIT = "|";
    private static final String COLUMN_SEPARATOR = "  %3s |";
    private static final String EMPTY_SCORE = "  ";
    private static final int MAX_FRAME_NO = 10;

    public void printBoard(Player player) {
        System.out.println(TABLE_HEADER);
        System.out.printf(COLUMN_INIT + COLUMN_SEPARATOR, player.getPlayerName());

        player.getFrames()
                .forEach(frame ->
                        System.out.printf(COLUMN_SEPARATOR, displays(frame))
                );

        IntStream.range(0, MAX_FRAME_NO - player.getFrames().size())
                .forEach(i ->
                        System.out.printf(COLUMN_SEPARATOR, EMPTY_SCORE)
                );

        System.out.println();
    }

    private String displays(Frame frame) {

        List<String> scores = new ArrayList<>();
        if (frame.hasFirstCount()) {
            scores.add(display(frame.firstCount()));
        }
        if (frame.hasSecondCount()) {
            scores.add(display(frame.firstCount(), frame.secondCount()));
        }
        if (frame.hasBonusFirst()) {
            scores.add(display(frame.bonusFirstCount()));
        }
        if (frame.hasBonusSecond()) {
            scores.add(display(frame.bonusSecondCount()));
        }

        if (scores.isEmpty()) {
            return EMPTY_SCORE;
        }
        return StringUtils.join(scores, "|");
    }

    private String display(int firstCount) {
        if (firstCount == 10) {
            return "x";
        }
        if (firstCount == 0) {
            return "-";
        }
        return String.valueOf(firstCount);
    }

    private String display(int firstCount, int secondCount) {
        if (firstCount + secondCount == 10) {
            return "/";
        }
        return display(secondCount);
    }
}
