package bowling.view;

import java.util.Optional;
import java.util.stream.Collectors;

import bowling.engine.Frame;
import bowling.engine.ScoreBoard;
import bowling.engine.Sequence;
import bowling.engine.Shot;

public class OutputView {
    private static final String HEADER = "| NAME |  0 1  |  0 2  |  0 3  |  0 4  |  0 5  |  0 6  |  0 7  |  0 8  |  0 9  |  1 0  |";
    private static final String LINE = "|";
    private static final String NAME_FORMAT = LINE + "%5s ";
    private static final int NORMAL_SCORE_LENGTH = 3;
    private static final String SCORE_FORMAT = LINE + "  %-3s  ";
    private static final String FINAL_SCORE_FORMAT = LINE + " %-5s ";
    private static final String EMPTY_SCORE = LINE + "       ";

    public static void printScoreBoard(ScoreBoard board) {
        System.out.println(HEADER);
        System.out.printf(NAME_FORMAT, board.name());
        for(int i = 0; i < Sequence.LAST; i++) {
            Optional<Frame> frame = board.elementOfOptional(i);
            String frameScore = frame.map(OutputView::parseFrameScore)
                    .map(OutputView::formatScore)
                    .orElse(EMPTY_SCORE);
            System.out.print(frameScore);
        }
        System.out.println(LINE);
    }

    public static String formatScore(String score) {
        if (score.length() > NORMAL_SCORE_LENGTH) {
            return String.format(FINAL_SCORE_FORMAT, score);
        }

        return String.format(SCORE_FORMAT, score);
    }

    public static String parseFrameScore(Frame frame) {
        return frame.stream()
                .map(Shot::toString)
                .collect(Collectors.joining(LINE));
    }
}
