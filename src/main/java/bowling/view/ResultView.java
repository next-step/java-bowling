package bowling.view;


import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Pin;
import bowling.domain.Pins;
import bowling.domain.Player;
import bowling.domain.Score;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {

  private static final String SEPARATOR = "|";
  private static final String BLANK = " ";
  private static final int MAX_BLANK_SIZE = 6;
  private static final char SPACE = ' ';

  public static void showScoreBoard(Frames frames, Player player) {
    roundSection();
    scoreSection(frames, player);
  }

  private static void roundSection() {
    System.out.print(SEPARATOR + alignText("NAME") + SEPARATOR);
    IntStream.rangeClosed(1, 10)
        .mapToObj(i -> alignText(String.format("%02d", i)) + SEPARATOR)
        .forEach(System.out::print);
    System.out.println();
  }

  public static void scoreSection(Frames frames, Player player) {
    System.out.print(SEPARATOR + alignText(player.name()) + SEPARATOR);
    System.out.print(scoreResult(frames.frames()));
    System.out.println();
  }

  private static String scoreResult(List<Frame> frames) {
    return frames.stream()
              .map(ResultView::eachFrameResult)
              .collect(Collectors.joining(SEPARATOR));
  }

  private static String eachFrameResult(Frame frame) {
    if (frame.isEmpty()) {
      return alignText(BLANK);
    }
    if (frame.isFinalRound()) {
      return alignText(finalFrameResult(frame));
    }
    return alignText(normalFrameResult(frame));
  }

  private static String normalFrameResult(Frame frame) {
    Pins pins = frame.pins();
    Pin first = pins.first();
    if (frame.isFirstThrow()) {
      return score(first);
    }
    return score(first) + SEPARATOR + nextScore(pins);
  }

  private static String finalFrameResult(Frame frame) {
    Pins pins = frame.pins();
    Pin first = pins.first();
    if (frame.isFirstThrow()) {
      return score(first);
    }
    if (pins.size() == 2) {
      return score(first) + SEPARATOR + nextScore(pins);
    }
    return containBonusScore(pins);
  }

  private static String containBonusScore(Pins pins) {
    return score(pins.first()) + SEPARATOR + nextScore(pins) + SEPARATOR + score(pins.bonus());
  }

  private static String score(Pin pin) {
    Score score = Score.score(pin.pin(), TRUE);
    if (score == Score.STRIKE || score == Score.GUTTER) {
      return score.mark();
    }
    return pin.convertToString();
  }

  private static String nextScore(Pins pins) {
    Score score = Score.score(pins.countUptoSecondThrow(), FALSE);
    if (score == Score.SPARE) {
      return score.mark();
    }
    Pin second = pins.second();
    return score(second);
  }

  private static String alignText(String text) {
    StringBuilder sb = new StringBuilder();
    sb.setLength((MAX_BLANK_SIZE - text.length()) / 2);
    sb.append(text);
    sb.setLength(MAX_BLANK_SIZE);
    return sb.toString().replace('\0', SPACE);
  }
}
