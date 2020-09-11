package bowling.view;

import static java.lang.System.out;

import bowling.domain.Bowling;
import java.util.List;

public class OutputView {

  public static final String NAME_HEADER = "| NAME |";
  public static final String FRAME_HEADER = "  %02d  |";

  public static final String NON_PITCH_FORMAT = "      |";
  public static final String PLAYER_FORMAT = "|  %s |";
  public static final String FIRST_FORMAT = "  %s   |";
  public static final String SECOND_FORMAT = "  %s |";

  private final String player;

  public OutputView(String player) {
    this.player = player;
  }

  public void render() {
    writeHeader();
    renderNameWithRolls();
  }


  public void render(Bowling bowling) {
    writeHeader();
    writeNameWithRolls(bowling.symbols());
  }

  private void writeHeader() {
    out.print(NAME_HEADER);
    for (int i = 0; i < 10; i++) {
      out.printf(FRAME_HEADER, i + 1);
    }
    out.print("\n");
  }

  private void renderNameWithRolls() {
    out.printf(PLAYER_FORMAT, player);
    writeNonPitch(0);
  }

  private void writeNameWithRolls(List<String> symbols) {
    System.out.printf(PLAYER_FORMAT, player);
    writeSymbols(symbols);
    writeNonPitch(symbols.size());
  }

  private void writeSymbols(List<String> symbols) {
    for (String symbol : symbols) {
      out.print(format(symbol));
    }
  }

  private void writeNonPitch(int size) {
    for (int i = size; i < 10; i++) {
      out.print(NON_PITCH_FORMAT);
    }
    out.print("\n");
  }

  static String format(String symbol) {
    if (symbol.length() == 1) {
      return String.format(FIRST_FORMAT, symbol);
    }

    return String.format(SECOND_FORMAT, symbol);
  }

}
