package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.score.Score;

import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {
    private static final String DELIMITER = "|";
    private static final String ORDINARY_SCORE_FORMAT = "%d";
    private static final String MEMBER_NAME_FORMAT = "|  %s |";
    private static final String FRAME_NUMBER_FORMAT = "  %02d  |";
    private static final String FRAME_HEADER_FORMAT = "| NAME |%s";
    private static final String LONG_SCORES_FORMAT = "%5s |";
    private static final String CALCULATED_SCORES_FORMAT = "  %-3s |";
    private static final String SCORES_LINE_FORMAT = "|      |%s";
    private static final String SHORT_SCORES_FORMAT = "%3s   |";
    private final PrintWriter output;
    private final String EMPTY_STRING = "";

    public ResultView(PrintWriter output) {
        this.output = output;
    }

    public void showFrames(String memberName, List<Frame> frames) {
        showHeader(frames.size());
        showMemberName(memberName);
        showFrames(frames);
    }

    private void showFrames(List<Frame> frames) {
        output.println(frames.stream()
                .map(this::getScores)
                .map(this::formatScores)
                .collect(Collectors.joining()));
    }

    private String getScores(Frame frame) {
        return frame.getScores().stream()
                .map(this::getScore)
                .collect(Collectors.joining(DELIMITER));
    }

    private String getScore(Score score) {
        if (score.isOrdinary()) {
            return String.format(ORDINARY_SCORE_FORMAT, score.getScore());
        }
        return score.getType().getExpression();
    }

    private String formatScores(String scores) {
        if (scores.length() == 1) {
            return String.format(SHORT_SCORES_FORMAT, scores);
        }
        return String.format(LONG_SCORES_FORMAT, scores);
    }

    private void showMemberName(String memberName) {
        output.print(String.format(MEMBER_NAME_FORMAT, memberName));
    }

    private void showHeader(int frameCount) {
        output.println(String.format(FRAME_HEADER_FORMAT, getFrameNumbers(frameCount)));
    }

    private String getFrameNumbers(int frameCount) {
        return IntStream.range(1, frameCount + 1)
                .mapToObj(frameNumber -> String.format(FRAME_NUMBER_FORMAT, frameNumber))
                .collect(Collectors.joining());
    }

    public void showCalculatedScores(List<Integer> scores) {
        output.println(String.format(SCORES_LINE_FORMAT, formatCalculatedScores(scores)));
    }

    private String formatCalculatedScores(List<Integer> scores) {
        return scores.stream()
                .map(score -> Optional.ofNullable(score).map(Object::toString).orElse(EMPTY_STRING))
                .map(score -> String.format(CALCULATED_SCORES_FORMAT, score))
                .collect(Collectors.joining());
    }
}
