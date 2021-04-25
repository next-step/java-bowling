package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.Name;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {

  private static final String SEPARATOR = "|";
  private static final String LINE_SEPARATOR = System.lineSeparator();
  private static final String BLANK = " ";
  private static final String EMPTY = "";
  private static final int MAX_BLANK_SIZE = 6;
  private static final int TOTAL_FRAME_SIZE = 10;
  private static final char SPACE = ' ';

  public static void showScoreBoard(Frames frames, Name name) {
    roundSection();
    stateSection(frames, name);
    scoreSection(frames);
  }

  private static void roundSection() {
    System.out.print(SEPARATOR + alignText("NAME") + SEPARATOR);
    IntStream.rangeClosed(1, 10)
        .mapToObj(i -> alignText(String.format("%02d", i)) + SEPARATOR)
        .forEach(System.out::print);
    System.out.print(LINE_SEPARATOR);
  }

  private static void stateSection(Frames frames, Name player) {
    System.out.print(SEPARATOR + alignText(player.name()) + SEPARATOR);
    System.out.println(stateResult(frames.frames()) + SEPARATOR);
  }

  private static String stateResult(List<Frame> frames) {
    return frames.stream()
        .map(ResultView::eachFrameState)
        .collect(Collectors.joining(SEPARATOR));
  }

  private static String eachFrameState(Frame frame) {
    if (frame.isEmpty()) {
      return alignText(BLANK);
    }
    return alignText(String.join(SEPARATOR, frame.frameState()));
  }

  private static void scoreSection(Frames frames) {
    System.out.print(SEPARATOR + alignText(BLANK) + SEPARATOR);
    System.out.print(scoreResult(frames.frameScores()) + scoreSeparator(frames.frameScores()));
    System.out.println(emptyScore(frames.frameScores().size()));
  }

  private static String scoreResult(List<Integer> scores) {
    return scores.stream()
        .map(score -> alignText(String.valueOf(score)))
        .collect(Collectors.joining(SEPARATOR));
  }

  private static String scoreSeparator(List<Integer> scores) {
    return scores.isEmpty() ? EMPTY : SEPARATOR;
  }

  private static String emptyScore(int size) {
    int emptyFrameSize = TOTAL_FRAME_SIZE - size;
    return IntStream.rangeClosed(0, emptyFrameSize)
        .mapToObj(i -> alignText(BLANK))
        .collect(Collectors.joining(SEPARATOR));
  }

  private static String alignText(String text) {
    StringBuilder sb = new StringBuilder();
    sb.setLength((MAX_BLANK_SIZE - text.length()) / 2);
    sb.append(text);
    sb.setLength(MAX_BLANK_SIZE);
    return sb.toString().replace('\0', SPACE);
  }
}
