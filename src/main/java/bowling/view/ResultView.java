package bowling.view;

import bowling.domain.FrameResult;
import bowling.domain.FrameResults;
import bowling.domain.bowlresult.BowlResult;
import bowling.domain.BowlingGame;
import bowling.domain.BowlingGames;
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
      FrameResults results = bowlingGame.produceResults();
      printFrameStatus(results, bowlingGame.getPlayerName());
      printScore(results);
    }
  }

  private static void printFrameStatus(FrameResults results, String playerName) {
    StringBuilder builder = new StringBuilder(String.format(NAME_FORMAT, playerName));

    for (FrameResult result : results.getFrameResults()) {
      builder.append(String.format(FRAME_STATUS_FORMAT, visualize(result)));
    }

    System.out.println(builder);
  }

  private static String visualize(FrameResult result) {
    String visualized = visualize(result.getRegularResult());
    String bonusVisualized = visualize(result.getBonusResult());

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

  private static void printScore(FrameResults results) {
    StringBuilder builder = new StringBuilder(SCORE_DELIMITER);
    builder.append(String.format(FRAME_STATUS_FORMAT, NOT_PLAYED_SIGN));

    for (FrameResult result : results.getFrameResults()) {
      builder.append(String.format(FRAME_STATUS_FORMAT, visualizeScore(result.getScore())));
    }
    System.out.println(builder);
  }

  private static String visualizeScore(Score score) {
    if (score == Score.ofNull()) {
      return NOT_PLAYED_SIGN;
    }

    return Integer.toString(score.getScore());
  }
}
