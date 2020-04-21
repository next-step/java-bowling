package bowling.domain;

import static bowling.domain.Round.FINAL_ROUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RoundTest {
  @ParameterizedTest
  @ValueSource(ints = {-1, 11})
  public void testIllegalArgument(int round) {
    assertThatThrownBy(() -> Round.of(round))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void testFinalRound() {
    Round finalRound = Round.of(FINAL_ROUND);

    assertThat(finalRound.isFinal()).isTrue();
    assertThatThrownBy(finalRound::next)
        .isInstanceOf(IllegalArgumentException.class);
  }
}
