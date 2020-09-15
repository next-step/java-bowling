package bowling.view;

import static java.lang.System.out;

import bowling.domain.Bowling;
import bowling.domain.Score;
import java.util.Collections;
import java.util.List;

public class OutputView {

  public static final String NAME_HEADER = "| NAME |";
  public static final String PLAYER_HEADER = "|  %s |";
  public static final String SCORE_HEADER = "|      |";

  public static final String FRAME_HEADER = "  %02d  |";
  public static final String NON_PITCH_FORMAT = "      |";
  public static final String FIRST_FORMAT = "  %s   |";
  public static final String SECOND_FORMAT = "  %s |";
  public static final String FINAL_FORMAT = " %s|";
  public static final String SCORE_FORMAT = "  %d  |";


  private final String player;

  public OutputView(String player) {
    this.player = player;
  }

  static String format(String symbol) {
    if (symbol.length() == 1) {
      return String.format(FIRST_FORMAT, symbol);
    }

    if (symbol.length() == 5) {
      return String.format(FINAL_FORMAT, symbol);
    }

    return String.format(SECOND_FORMAT, symbol);
  }

  public void render() {
    writeHeader();
    writeNameWithSymbols(Collections.emptyList());
    writeScores(Collections.emptyList());
  }

  public void render(Bowling bowling) {
    writeHeader();
    writeNameWithSymbols(bowling.symbols());
    writeScores(bowling.scores()); // TODO Scores
  }

  private void writeHeader() {
    out.print(NAME_HEADER);
    for (int i = 0; i < 10; i++) {
      out.printf(FRAME_HEADER, i + 1);
    }
    out.print("\n");
  }

  private void writeNameWithSymbols(List<String> symbols) {
    System.out.printf(PLAYER_HEADER, player);
    writeSymbols(symbols);
    writeNonPitch(symbols.size());
  }

  private void writeSymbols(List<String> symbols) {
    for (String symbol : symbols) {
      out.print(format(symbol));
    }
  }

  private void writeScores(List<Score> scores) {
    System.out.print(SCORE_HEADER);
    for (Score score : scores) {
      writeScore(score);
    }
    writeNonPitch(scores.size());
  }

  private void writeScore(Score score) {
    out.print(score == null ? NON_PITCH_FORMAT : String.format(SCORE_FORMAT, score.value()));
  }

  private void writeNonPitch(int size) {
    for (int i = size; i < 10; i++) {
      out.print(NON_PITCH_FORMAT);
    }
    out.print("\n");
  }

}
