package bowling_step4.view;

import bowling_step4.domain.BowlingGame;
import bowling_step4.domain.BowlingGames;
import bowling_step4.domain.Score;
import bowling_step4.domain.frame.FinalFrame;
import bowling_step4.domain.frame.Frame;
import bowling_step4.domain.frame.NormalFrame;
import bowling_step4.domain.state.State;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {

  public static final int MAX_FRAME_INDEX = 9;
  public static final String DIVIDER = "|";
  private static final String HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

  public void printResult(BowlingGames bowlingGames) {

    for (BowlingGame bowlingGame : bowlingGames.getBowlingGames()) {
      printResultDetail(bowlingGame.getPlayer().getName(), bowlingGame.getFrames().getFrames(),
          bowlingGame.getScore());
    }
  }

  public void printResultDetail(String playerName, List<Frame> frames, List<Score> scores) {
    printHeader();

    System.out.print(DIVIDER);
    printText(playerName);
    printFrames(frames);
    printRemainDivider(frames.size());

    System.out.print(DIVIDER);
    printText("");
    printScores(scores);
    printRemainDivider(scores.size());

  }

  private void printText(String str) {
    System.out.print(center(str, 6));
    System.out.print(DIVIDER);
  }

  private String center(String s, int size) {
    char pad = ' ';
      if (s == null || size <= s.length()) {
          return s;
      }

    StringBuilder sb = new StringBuilder(size);
    for (int i = 0; i < (size - s.length()) / 2; i++) {
      sb.append(pad);
    }
    sb.append(s);
    while (sb.length() < size) {
      sb.append(pad);
    }
    return sb.toString();
  }

  public void printEmptyResult(List<String> playerNames) {
    for (String playerName : playerNames) {
      printResultDetail(playerName, new ArrayList<>(), new ArrayList<>());
    }

  }

  private void printHeader() {
    System.out.println(HEADER);
  }


  private void printFrames(List<Frame> frames) {
    for (int i = 0; i < frames.size(); i++) {
      printFrame(frames.get(i));
    }

  }

  private void printFrame(Frame frame) {
    if (!isFinalFrame(frame)) {
      printNormalFrame(frame);
      return;

    }
    printFinalFrame(frame);
  }

  private void printFinalFrame(Frame frame) {
    printText(makeStatesToString(((FinalFrame) frame).getStates()));
  }

  public static String makeStatesToString(List<State> states) {
    return states.stream()
        .map(State::toString)
        .collect(Collectors.joining(DIVIDER));
  }

  private void printNormalFrame(Frame frame) {
    NormalFrame normalFrame = (NormalFrame) frame;
    printText(normalFrame.getState().toString());
  }

  private boolean isFinalFrame(Frame frame) {
    return frame.getPlayCount() == 9;
  }


  private void printRemainDivider(int size) {
    for (int i = size; i <= MAX_FRAME_INDEX; i++) {
      printText("");
    }
    System.out.print(System.lineSeparator());
  }

  private void printScores(List<Score> scores) {
    scores.forEach(score -> printText(String.valueOf(score.getScore())));
  }

}
