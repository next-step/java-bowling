package bowling.step3.view;

import bowling.step3.domain.ScoreType;
import bowling.step3.domain.scores.NormalScores;
import bowling.step3.domain.Frames;
import bowling.step3.domain.PlayerFrames;
import bowling.step3.domain.Score;
import bowling.step3.domain.frame.Frame;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class ResultView {
    private static final ResultView INSTANCE = new ResultView();
    private static final String NEW_LINE = System.lineSeparator();
    private static final String NUMBERS_FORMAT = "| NAME |%s|";
    private static final String SCORES_FORMAT = "| %4s |  %s|";

    private ResultView() {
    }

    public static ResultView getInstance() {
        return INSTANCE;
    }

    public void printFrames(PlayerFrames playerFrames) {
        System.out.printf(
            "%s" + NEW_LINE + "%s" + NEW_LINE + NEW_LINE,
            frameNumbers(),
            frameScores(playerFrames)
        );
    }

    private String frameNumbers() {
        return String.format(
            NUMBERS_FORMAT,
            IntStream.rangeClosed(1, Frames.LAST_FRAME)
                     .mapToObj(frameNumber -> String.format("  %02d  ", frameNumber))
                     .collect(joining("|"))
        );
    }

    private String frameScores(PlayerFrames playerFrames) {
        return String.format(
            SCORES_FORMAT, playerFrames.getPlayer(),
            playerFrames.getPreview()
                        .map(frame -> String.format("%-4s", scoreOf(frame)))
                        .collect(joining("|  ")));
    }

    private String scoreOf(Frame frame) {
        if (frame == null) {
            return "";
        }
        return eachScoreOf(frame.getScores()
                                .stream()
                                .collect(toList()));
    }

    private String eachScoreOf(List<Score> scores) {
        return IntStream.range(0, scores.size())
            .mapToObj(index -> scores.get(index) != null
                                 ? toScoreType(scores, index)
                                 : null)
            .filter(Objects::nonNull)
            .collect(joining("|"));
    }

    private String toScoreType(List<Score> scores, int index) {
        if (scores.get(index) == Score.valueOf(Score.MIN_SCORE)) {
            return ScoreType.GUTTER.getValue();
        }
        if (scores.get(index) == Score.getStrike()) {
            return ScoreType.STRIKE.getValue();
        }
        if (index == 1 && NormalScores.isSparedOf(scores)) {
            return ScoreType.SPARED.getValue();
        }
        return scores.get(index).toString();
    }

}