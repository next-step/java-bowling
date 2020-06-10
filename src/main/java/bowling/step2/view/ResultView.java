package bowling.step2.view;

import bowling.step2.domain.*;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class ResultView {
    private static final ResultView INSTANCE = new ResultView();
    private static final String NEW_LINE = System.lineSeparator();
    private static final String NUMBERS_FORMAT = "| NAME |%s|";
    private static final String SCORES_FORMAT = "| %4s |%s|";

    private ResultView () {}

    public static ResultView getInstance () {
        return INSTANCE;
    }

    public void printFrames (Frames frames, Players players) {
        System.out.printf(
            NEW_LINE + "%s" + NEW_LINE + "%s" + NEW_LINE,
            frameNumbers(frames),
            frameScores(frames, players)
        );
    }

    private String frameNumbers (Frames frames) {
        return String.format(
            NUMBERS_FORMAT,
            frames.stream()
                  .map(Frame::getValue)
                  .map(frameNumber -> String.format(" %02d ", frameNumber))
                  .collect(joining("|"))
        );
    }

    private String frameScores (Frames frames, Players players) {
        return players.stream()
                      .map(playerName -> String.format(
                          SCORES_FORMAT,
                          playerName,
                          frames.scoresOfPlayerStream(playerName)
                                .map(this::scoreOf)
                                .collect(joining("|"))
                      ))
                      .collect(joining(NEW_LINE));
    }

    private String scoreOf (FrameScore frameScore) {
        if (frameScore.isStrike()) {
            return ScoreType.STRIKE.getValue();
        }
        List<Score> scores = frameScore.stream()
                                       .limit(2L)
                                       .collect(toList());
        return String.format("%s|%s",
                scores.get(0).getValue(),
                secondScoreOf(frameScore.isSpared(), scores.get(1).getValue())
        );
    }

    private String secondScoreOf (boolean spared, int secondScore) {
        if (secondScore == Score.MIN_SCORE) {
            return ScoreType.GUTTER.getValue();
        }
        if (spared) {
            return ScoreType.SPARED.getValue();
        }
        return String.valueOf(secondScore);
    }

}