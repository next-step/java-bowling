package bowling.view;

import bowling.domain.BowlResult;
import bowling.domain.BowlingGame;
import bowling.domain.FinalFrame;
import bowling.domain.Frame;
import bowling.domain.FrameInfo;
import bowling.domain.NormalFrame;
import bowling.domain.RegularResult;
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


  public static void printBowlingScoreTable(BowlingGame bowlingGame) {
    System.out.println(FRAMES_META_DATA);
    StringBuilder builder = getFrameStatus(bowlingGame);

    System.out.println(builder);
  }

  private static StringBuilder getFrameStatus(BowlingGame bowlingGame) {
    StringBuilder builder = new StringBuilder(String.format(NAME_FORMAT, bowlingGame.getPlayerName()));
    Frame frame = bowlingGame.getFrame();

    while (!frame.isFinalFrame()) {
      builder.append(String.format(FRAME_STATUS_FORMAT, visualize(frame)));
      frame = frame.getNextFrame();
    }

    builder.append(String.format(FRAME_STATUS_FORMAT, visualize(frame)));
    return builder;
  }

  private static String visualize(Frame frame) {
    if (frame instanceof NormalFrame) {
      return visualize((NormalFrame) frame);
    }

    if (frame instanceof FinalFrame) {
      return visualize((FinalFrame) frame);
    }

    return NOT_PLAYED_SIGN;
  }

  private static String visualize(NormalFrame frame) {
    FrameInfo frameInfo = frame.getFrameInfo();
    RegularResult regularResult = frameInfo.getRegularResult();

    return visualize(regularResult);
  }

  private static String visualize(FinalFrame frame) {
    String visualized = visualize(frame.getRegularResult());
    String bonusVisualized = visualize(frame.getBonusResult());

    if (!bonusVisualized.isEmpty()) {
      visualized = String.join(SCORE_DELIMITER, visualized, bonusVisualized);
    }

    return visualized;
  }

  private static String visualize(BowlResult bowlResult) {
    Trial first = bowlResult.getFirst();
    Trial second = bowlResult.getSecond();
    if (!second.isPlayed()) {
      return visualizeTrial(first);
    }

    return String.join(SCORE_DELIMITER, visualizeTrial(first), visualizeSecondTrial(first, second));
  }

  private static String visualizeTrial(Trial first) {
    if (!first.isPlayed()) {
      return NOT_PLAYED_SIGN;
    }

    if (first.isStrike()) {
      return STRIKE_SIGN;
    }

    if (first.isGutter()) {
      return GUTTER_SIGN;
    }

    return String.valueOf(first.getPinCount());
  }

  private static String visualizeSecondTrial(Trial first, Trial second) {
    if (second.isSpare(first)) {
      return SPARE_SIGN;
    }

    return visualizeTrial(second);
  }
}
