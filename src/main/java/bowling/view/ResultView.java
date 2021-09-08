package bowling.view;

import bowling.dto.ResultDto;
import bowling.dto.ScoreResultDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {

  private static final int START_INCLUSIVE = 1;
  private static final int END_EXCLUSIVE = 11;
  private static final int END_BLANK_MAX = 10;
  private static final int LIMIT_SCORE_LENGTH = 3;
  private static final int VALUE_NOTHING = -1;
  private static final String VALUE_NAME_DIGITS = "|  %3s   |";
  private static final String VALUE_DIGITS_NORMAL = "  %3s   |";
  private static final String VALUE_DIGITS_FINAL = "  %3s |";
  private static final String HEADER_VALUE_DIGITS = "   %02d   |";
  private static final String VALUE_DIGITS = "   %3d  |";
  private static final String HEADER_NAME = "|  NAME  |";
  private static final String DEFAULT_BLANK = "|        |";
  private static final String VALUE_BLANK = "        |";
  private static final String GUTTER = "-";
  private static final String NO_HIT = "0";
  private static final String DEFAULT_SCORE_VALUE = "-1";
  private static final String DEFAULT_SCORE_BLANK = "  ";

  public static void printBoard(final ResultDto result) {
    printBody(result);
    printBodyScore(result);
  }

  public static void printHeader() {
    List<String> header = new ArrayList<>();
    header.add(HEADER_NAME);

    IntStream.range(START_INCLUSIVE, END_EXCLUSIVE)
        .forEach(round -> createHeader(header, round));

    System.out.println(String.join("", header));
  }

  private static void createHeader(final List<String> header, final int round) {
    header.add(String.format(HEADER_VALUE_DIGITS, round));
  }

  private static String widthFormat(final String scoreMark) {
    if (scoreMark.length() > LIMIT_SCORE_LENGTH) {
      return String.format(VALUE_DIGITS_FINAL, scoreMark.replace(NO_HIT, GUTTER));
    }
    return String.format(VALUE_DIGITS_NORMAL, scoreMark.replace(NO_HIT, GUTTER));
  }

  private static String widthFormatScore(final int score) {
    return String.format(VALUE_DIGITS, score).replace(DEFAULT_SCORE_VALUE, DEFAULT_SCORE_BLANK);
  }

  private static void printBody(final ResultDto result) {
    List<String> values = new ArrayList<>();

    values.add(String.format(VALUE_NAME_DIGITS, result.getName()));

    writeScoreMark(result, values);
    writeBlankMark(result.getFrames().size(), values);

    System.out.println(String.join("", values));
  }

  private static void writeBlankMark(final int startIndex, final List<String> values) {
    IntStream.range(startIndex, END_BLANK_MAX)
        .mapToObj(i -> VALUE_BLANK)
        .forEach(values::add);
  }

  private static void writeScoreMark(final ResultDto result, final List<String> values) {
    result.getFrames()
        .stream()
        .map(frame -> widthFormat(frame.getScoreMark()))
        .forEach(values::add);
  }

  private static void printBodyScore(final ResultDto result) {
    List<String> values = new ArrayList<>();
    values.add(DEFAULT_BLANK);

    writeScoreValue(getScoreTotalResult(result), values);
    writeBlankMark(getScoreTotalResult(result).size(), values);

    System.out.println(String.join("", values));
  }

  private static List<String> getScoreTotalResult(final ResultDto result) {
    return result.getFrames()
        .stream()
        .filter(frameDto -> frameDto.getScore() != VALUE_NOTHING)
        .map((ScoreResultDto frame) -> widthFormatScore(frame.getTotalScore()))
        .collect(Collectors.toList());
  }

  private static void writeScoreValue(final List<String> scoreBoardResult,
      final List<String> result) {
    result.addAll(scoreBoardResult);
  }
}
