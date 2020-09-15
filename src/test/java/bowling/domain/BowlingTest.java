package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class BowlingTest {

  @Test
  void strike_roll() {
    Bowling first = Bowling.first(10);
    assertThat(first.nextFrame()).isEqualTo(2);
  }

  @Test
  void pitching_roll() {
    Bowling first = Bowling.first(8);
    assertThat(first.nextFrame()).isEqualTo(1);
  }

  @Test
  void gutter_roll() {
    Bowling first = Bowling.first(0);
    assertThat(first.nextFrame()).isEqualTo(1);
  }

  @Test
  void spare_roll() {
    Bowling first = Bowling.first(8);
    first.roll(2);
    assertThat(first.nextFrame()).isEqualTo(2);
  }

  @Test
  void open_roll() {
    Bowling first = Bowling.first(8);
    first.roll(1);
    assertThat(first.nextFrame()).isEqualTo(2);
  }

  @Test
  void open_with_gutter_roll() {
    Bowling first = Bowling.first(8);
    first.roll(0);
    assertThat(first.nextFrame()).isEqualTo(2);
  }

  @Test
  void next_roll() {
    Bowling first = Bowling.first(10);
    first.roll(10);
    assertThat(first.nextFrame()).isEqualTo(3);
  }

  @Test
  void perfect() {
    Bowling perfect = Bowling.first(10);
    for (int i = 0; i < 11; i++) {
      perfect.roll(10);
    }
    assertThat(perfect.symbols()
        .stream()
        .collect(Collectors.joining())).isEqualTo("XXXXXXXXXX|X|X");
  }

  @Test
  void perfect_9frame() {
    Bowling perfect = Bowling.first(10);
    for (int i = 0; i < 8; i++) {
      perfect.roll(10);
    }
    assertThat(perfect.symbols()
        .stream()
        .collect(Collectors.joining())).isEqualTo("XXXXXXXXX");
  }

  /**
   * <div>
   *   | NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
   *   |  PJS |  X   |  8|/ |  8|1 |      |      |      |      |      |      |      |
   *   |      |  20  |  38  |  47  |      |      |      |      |      |      |      |
   * </div>
   */
  @Test
  void score() {
    Bowling bowling = Bowling.first(10);
    assertThat(bowling.scores()).isEqualTo(Collections.EMPTY_LIST);

    bowling.roll(8);
    assertThat(bowling.scores()).isEqualTo(Collections.EMPTY_LIST);

    bowling.roll(2);
    assertThat(bowling.scores()).isEqualTo(Arrays.asList(new Score(20)));

    bowling.roll(8);
    assertThat(bowling.scores()).isEqualTo(Arrays.asList(new Score(20), new Score(38)));

    bowling.roll(1);
    assertThat(bowling.scores()).isEqualTo(Arrays.asList(new Score(20), new Score(38), new Score(47)));
  }
}
