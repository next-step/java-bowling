package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class StateTest {

  @Test
  void strike_roll() {
    State strike = State.of(10);
    assertThat(strike).isInstanceOf(Strike.class);
    assertThat(strike.symbol()).isEqualTo("X");
  }

  @Test
  void pitching_roll() {
    State pitching = State.of(8);
    assertThat(pitching).isInstanceOf(Pitching.class);
    assertThat(pitching.symbol()).isEqualTo("8");
  }

  @Test
  void gutter_roll() {
    State gutter = State.of(0);
    assertThat(gutter).isInstanceOf(Pitching.class);
    assertThat(gutter.symbol()).isEqualTo("-");
  }

  @Test
  void spare_roll() {
    State pitching = State.of(8);
    assertThat(pitching).isInstanceOf(Pitching.class);
    assertThat(pitching.symbol()).isEqualTo("8");

    State spare = pitching.roll(2);

    assertThat(spare).isInstanceOf(Spare.class);
    assertThat(spare.symbol()).isEqualTo("8|/");
  }

  @Test
  void open_roll() {
    State pitching = State.of(8);
    assertThat(pitching).isInstanceOf(Pitching.class);
    assertThat(pitching.symbol()).isEqualTo("8");

    State open = pitching.roll(1);

    assertThat(open).isInstanceOf(Open.class);
    assertThat(open.symbol()).isEqualTo("8|1");
  }

  @Test
  void open_with_gutter_roll() {
    State pitching = State.of(8);
    assertThat(pitching).isInstanceOf(Pitching.class);
    assertThat(pitching.symbol()).isEqualTo("8");

    State open = pitching.roll(0);

    assertThat(open).isInstanceOf(Open.class);
    assertThat(open.symbol()).isEqualTo("8|-");
  }

}
