package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class BowlingsTest {

  @Test
  void nextPlayer() {
    Bowlings bowlings = Bowlings.ofNames(Arrays.asList("PCS", "ABC"));
    assertThat(bowlings).isNotNull();

    String name = bowlings.nextPlayer();
    assertThat(name).isEqualTo("PCS");

    bowlings.roll(10);
    name = bowlings.nextPlayer();
    assertThat(name).isEqualTo("ABC");

    bowlings.roll(8);
    name = bowlings.nextPlayer();
    assertThat(name).isEqualTo("ABC");
  }
}
