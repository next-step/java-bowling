package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Player;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {

  private static final String SEPARATOR = "|";
  private static final String LINE_SEPARATOR = System.lineSeparator();
  private static final String BLANK = " ";
  private static final int MAX_BLANK_SIZE = 6;
  private static final char SPACE = ' ';

  public static void showScoreBoard(Frames frames, Player player) {
    roundSection();
    stateSection(frames, player);
  }

  private static void roundSection() {
    System.out.print(SEPARATOR + alignText("NAME") + SEPARATOR);
    IntStream.rangeClosed(1, 10)
        .mapToObj(i -> alignText(String.format("%02d", i)) + SEPARATOR)
        .forEach(System.out::print);
    System.out.print(LINE_SEPARATOR);
  }

  public static void stateSection(Frames frames, Player player) {
    System.out.print(SEPARATOR + alignText(player.name()) + SEPARATOR);
    System.out.println(stateResult(frames.frames()) + SEPARATOR);
  }

  private static String stateResult(List<Frame> frames) {
    return frames.stream()
        .map(ResultView::eachFrameResult)
        .collect(Collectors.joining(SEPARATOR));
  }

  private static String eachFrameResult(Frame frame) {
    if (frame.isEmpty()) {
      return alignText(BLANK);
    }
    return alignText(frame.frameSymbol());
  }

  private static String alignText(String text) {
    StringBuilder sb = new StringBuilder();
    sb.setLength((MAX_BLANK_SIZE - text.length()) / 2);
    sb.append(text);
    sb.setLength(MAX_BLANK_SIZE);
    return sb.toString().replace('\0', SPACE);
  }
}
