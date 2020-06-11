package bowling.step2.view;

import bowling.step2.domain.*;

import java.util.List;
import java.util.stream.Collectors;

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

    public void printFrames (Frames frames, Players players) {
        System.out.printf(
            "%s" + NEW_LINE + "%s" + NEW_LINE + NEW_LINE,
            frameNumbers(frames),
            frameScores(frames, players)
        );
    }

    private String frameNumbers (Frames frames) {
        return String.format(
            NUMBERS_FORMAT,
            frames.stream()
                  .map(Frame::getValue)
                  .map(frameNumber -> String.format("  %02d  ", frameNumber))
                  .collect(joining("|"))
        );
    }

    private String frameScores (Frames frames, Players players) {
        return players.stream()
                      .map(playerName -> String.format(
                          SCORES_FORMAT,
                          playerName,
                          frames.scoresOfPlayerStream(playerName)
                                .map(frameScore -> String.format("%-4s", scoreOf(frameScore)))
                                .collect(joining("|  "))
                      ))
                      .collect(joining(NEW_LINE));
    }

    private String scoreOf (FrameScore frameScore) {
        if (frameScore.isStrike()) {
            return ScoreType.STRIKE.getValue();
        }
        if (frameScore.size() == 0) {
            return "";
        }
        if (frameScore.size() == 1) {
            return frameScore.get(0).toString();
        }
        return String.format("%s|%s", frameScore.get(0), secondScoreOf(frameScore));
    }

    private String secondScoreOf (FrameScore frameScore) {
        Score secondScore = frameScore.get(1);
        if (secondScore == Score.valueOf(Score.MIN_SCORE)) {
            return ScoreType.GUTTER.getValue();
        }
        if (frameScore.isSpared()) {
            return ScoreType.SPARED.getValue();
        }
        return secondScore.toString();
    }

}