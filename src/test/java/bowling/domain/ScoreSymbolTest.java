package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ScoreSymbolTest {

  @Test
  void strike_test() {
    ScoreSymbol result = ScoreSymbol.symbol(10, true);
    assertThat(result).isEqualTo(ScoreSymbol.STRIKE);
  }

  @Test
  void spare_test() {
    ScoreSymbol result = ScoreSymbol.symbol(10, false);
    assertThat(result).isEqualTo(ScoreSymbol.SPARE);
  }

  @Test
  void gutter_test() {
    ScoreSymbol result = ScoreSymbol.symbol(0, true);
    assertThat(result).isEqualTo(ScoreSymbol.GUTTER);
  }

  @Test
  void miss_test() {
    ScoreSymbol result = ScoreSymbol.symbol(4, true);
    assertThat(result).isEqualTo(ScoreSymbol.MISS);
  }
}
