package bowling.view;

import bowling.domain.bowlresult.BowlResult;
import bowling.domain.BowlingGame;
import bowling.domain.BowlingGames;
import bowling.domain.frame.FrameNode;
import bowling.domain.Score;
import bowling.domain.framestate.Spare;
import bowling.domain.Trial;

public class ResultView {

  private static final String FRAMES_META_DATA = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
  private static final String NAME_FORMAT = "|  %s |";
  private static final String FRAME_STATUS_FORMAT = " %-5s|";
  private static final String SCORE_DELIMITER = "|";
  private static final String STRIKE_SIGN = "X";
  private static final String GUTTER_SIGN = "-";
  private static final String NOT_PLAYED_SIGN = "";
  private static final String SPARE_SIGN = "/";


  public static void printBowlingScoreTable(BowlingGames bowlingGames) {
    System.out.println(FRAMES_META_DATA);
    for (BowlingGame bowlingGame : bowlingGames.getBowlingGames()) {
      printFrameStatus(bowlingGame);
      printScore(bowlingGame);
    }
  }

  private static void printFrameStatus(BowlingGame bowlingGame) {
    StringBuilder builder = new StringBuilder(String.format(NAME_FORMAT, bowlingGame.getPlayerName()));
    FrameNode frame = bowlingGame.getInitialFrame();

    while (!frame.isFinalFrame()) {
      builder.append(String.format(FRAME_STATUS_FORMAT, visualize(frame)));
      frame = frame.getNextFrame();
    }

    builder.append(String.format(FRAME_STATUS_FORMAT, visualize(frame)));
    System.out.println(builder);
  }

  private static String visualize(FrameNode frame) {
    String visualized = visualize(frame.getRegularResult());
    String bonusVisualized = visualize(frame.getBonusResult());

    if (!bonusVisualized.equals(NOT_PLAYED_SIGN)) {
      return String.join(SCORE_DELIMITER, visualized, bonusVisualized);
    }

    return visualized;
  }

  private static String visualize(BowlResult bowlResult) {
    Trial first = bowlResult.getFirst();
    Trial second = bowlResult.getSecond();
    if (!second.isPlayed()) {
      return visualizeTrial(first);
    }

    return String.join(SCORE_DELIMITER, visualizeTrial(first), visualizeSecondTrial(bowlResult));
  }

  private static String visualizeTrial(Trial first) {
    if (!first.isPlayed()) {
      return NOT_PLAYED_SIGN;
    }

    if (first.isMaxPin()) {
      return STRIKE_SIGN;
    }

    if (first.isGutter()) {
      return GUTTER_SIGN;
    }

    return String.valueOf(first.getPinCount());
  }

  private static String visualizeSecondTrial(BowlResult bowlResult) {
    if (bowlResult.getState().equals(Spare.getInstance())) {
      return SPARE_SIGN;
    }

    return visualizeTrial(bowlResult.getSecond());
  }

  private static void printScore(BowlingGame bowlingGame) {
    StringBuilder builder = new StringBuilder(SCORE_DELIMITER);
    builder.append(String.format(FRAME_STATUS_FORMAT, NOT_PLAYED_SIGN));
    FrameNode frame = bowlingGame.getInitialFrame();

    while (!frame.isFinalFrame()) {
      builder.append(String.format(FRAME_STATUS_FORMAT, visualizeScore(frame.calculateScore())));
      frame = frame.getNextFrame();
    }

    builder.append(String.format(FRAME_STATUS_FORMAT, visualizeScore(frame.calculateScore())));
    System.out.println(builder);
  }

  private static String visualizeScore(Score score) {
    if (score == Score.ofNull()) {
      return NOT_PLAYED_SIGN;
    }

    return Integer.toString(score.getScore());
  }
}
