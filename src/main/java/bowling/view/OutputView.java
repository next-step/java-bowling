package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.player.Player;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreType;
import com.sun.deploy.util.StringUtils;
import org.springframework.util.ObjectUtils;

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
        if (!ObjectUtils.isEmpty(frame.getScore1())) {
            scores.add(display(frame.getScore1()));
        }
        if (!ObjectUtils.isEmpty(frame.getScore2())) {
            scores.add(display(frame.getScore2()));
        }
        if (!ObjectUtils.isEmpty(frame.getScore3())) {
            scores.add(display(frame.getScore3()));
        }

        if (scores.isEmpty()) {
            return EMPTY_SCORE;
        }
        return StringUtils.join(scores, "|");
    }

    private String display(Score score) {
        if (score.getScoreType().equals(ScoreType.STRIKE)) {
            return "x";
        }
        if (score.getScoreType().equals(ScoreType.SPARE)) {
            return "/";
        }
        if (score.getScoreType().equals(ScoreType.GUTTER)) {
            return "-";
        }
        return String.valueOf(score.getScore());
    }
}
