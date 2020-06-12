package bowling.step2.view;

import bowling.step2.domain.*;
import bowling.step2.domain.frame.Frame;
import bowling.step2.domain.frame.NormalFrame;
import bowling.step2.domain.scores.NormalScores;
import bowling.step2.domain.scores.Scores;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class ResultView {
    private static final ResultView INSTANCE = new ResultView();
    private static final String NEW_LINE = System.lineSeparator();
    private static final String NUMBERS_FORMAT = "| NAME |%s|";
    private static final String SCORES_FORMAT = "| %4s |  %s|";

    private ResultView () {}

    public static ResultView getInstance () {
        return INSTANCE;
    }

    public void printFrames (PlayerFrames playerFrames, Players players) {
        System.out.printf(
            "%s" + NEW_LINE + "%s" + NEW_LINE + NEW_LINE,
            frameNumbers(),
            frameScores(playerFrames, players)
        );
    }

    private String frameNumbers () {
        return String.format(
            NUMBERS_FORMAT,
            IntStream.rangeClosed(1, Frames.LAST_FRAME)
                     .mapToObj(frameNumber -> String.format("  %02d  ", frameNumber))
                      .collect(joining("|"))
        );
    }

    private String frameScores (PlayerFrames playerFrames, Players players) {
        return players.stream()
                      .map(playerName -> String.format(
                          SCORES_FORMAT, playerName,
                          playerFrames.getPreviewOf(playerName)
                                      .map(frame -> String.format("%-4s", scoreOf(frame)))
                                      .collect(joining("|  "))
                      ))
                      .collect(joining(NEW_LINE));
    }

    private String scoreOf (Frame frame) {
        if (frame == null) {
            return "";
        }
        return eachScoreOf(frame.getScores()
                                  .stream()
                                  .collect(toList()));
    }

    private String eachScoreOf (List<Score> scores) {
        System.out.println(scores);
        return IntStream.rangeClosed(0, scores.size())
                 .mapToObj(index -> scores.get(index) != null
                                        ? toScoreType(scores, index)
                                        : null)
                 .filter(Objects::nonNull)
                 .collect(joining("|"));
    }

    private String toScoreType (List<Score> scores, int index) {
        if (scores.get(index) == Score.valueOf(Score.MIN_SCORE)) {
            return ScoreType.GUTTER.getValue();
        }
        if (scores.get(index) == Score.getStrike()) {
            return ScoreType.STRIKE.getValue();
        }
        if (index == 2 && NormalScores.isSparedOf(scores)) {
            return ScoreType.SPARED.getValue();
        }
        return scores.get(index).toString();
    }

}