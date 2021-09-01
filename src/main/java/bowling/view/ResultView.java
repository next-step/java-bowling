package bowling.view;

import bowling.dto.FrameDto;
import bowling.dto.ResultDto;
import bowling.frame.Frame;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ResultView {

  private static final int START_INCLUSIVE = 1;
  private static final int END_EXCLUSIVE = 11;
  private static final int END_BLANK_MAX = 10;
  private static final int LIMIT_SCORE_LENGTH = 3;
  private static final String VALUE_NAME_DIGITS = "|  %3s   |";
  private static final String VALUE_DIGITS_NORMAL = "  %3s   |";
  private static final String VALUE_DIGITS_FINAL = "  %3s |";
  private static final String HEADER_VALUE_DIGITS = "   %02d   |";
  private static final String HEADER_NAME = "|  NAME  |";
  private static final String VALUE_BLANK = "        |";
  public static final String GUTTER = "-";
  public static final String NO_HIT = "0";

  public static void printBoard(final ResultDto result) {

    printHeader();
    printBody(result);
  }

  private static void printBody(final ResultDto result) {
    List<String> values = new ArrayList<>();

    values.add(String.format(VALUE_NAME_DIGITS, result.getName()));

    writeScoreMark(result, values);
    writeBlankMark(result, values);

    System.out.println(String.join("", values));
  }

  private static void writeBlankMark(final ResultDto result, final List<String> values) {
    IntStream.range(result.getFrames().size(), END_BLANK_MAX)
        .mapToObj(i -> VALUE_BLANK)
        .forEach(values::add);
  }

  private static void writeScoreMark(final ResultDto result, final List<String> values) {
    result.getFrames()
        .stream()
        .map((FrameDto frame) -> widthFormat(frame.getFrame()))
        .forEach(values::add);
  }

  private static String widthFormat(final Frame frame) {
    if (frame.getScore().length() > LIMIT_SCORE_LENGTH) {
      return String.format(VALUE_DIGITS_FINAL, frame.getScore().replace(NO_HIT, GUTTER));
    }
    return String.format(VALUE_DIGITS_NORMAL, frame.getScore().replace(NO_HIT, GUTTER));
  }

  private static void printHeader() {
    List<String> header = new ArrayList<>();
    header.add(HEADER_NAME);

    IntStream.range(START_INCLUSIVE, END_EXCLUSIVE)
        .forEach(round -> createHeader(header, round));

    System.out.println(String.join("", header));
  }

  private static void createHeader(final List<String> header, final int round) {
    header.add(String.format(HEADER_VALUE_DIGITS, round));
  }
}
