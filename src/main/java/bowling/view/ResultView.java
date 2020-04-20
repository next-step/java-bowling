package bowling.view;

import static bowling.domain.Frame.NULL_FRAME;
import static bowling.domain.Round.FINAL_ROUND;

import bowling.domain.BowlingGame;
import bowling.domain.Frame;

public class ResultView {

  private static final String FRAMES_META_DATA = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
  private static final String EMPTY_FRAME = "      |";
  private static final String NAME_FORMAT = "|  %s |";
  private static final String FRAME_STATUS_FORMAT = " %-5s|";

  public static void printBowlingScoreTable(BowlingGame bowlingGame) {
    System.out.println(FRAMES_META_DATA);
    StringBuilder builder = getFrameStatus(bowlingGame);

    System.out.println(builder);
  }

  private static StringBuilder getFrameStatus(BowlingGame bowlingGame) {
    StringBuilder builder = new StringBuilder(String.format(NAME_FORMAT, bowlingGame.getPlayerName()));
    Frame frame = bowlingGame.getFrame();
    int frameCount = 0;

    while (frame != NULL_FRAME) {
      frameCount++;
      builder.append(String.format(FRAME_STATUS_FORMAT, frame.visualize()));
      frame = frame.getNextFrame();
    }

    while (frameCount < FINAL_ROUND) {
      frameCount++;
      builder.append(EMPTY_FRAME);
    }
    return builder;
  }
}
