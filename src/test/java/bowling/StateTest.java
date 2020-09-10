package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class StateTest {

  @Test
  void strike_symbol() {
    State strike = State.of(10);
    assertThat(strike).isInstanceOf(Strike.class);
    assertThat(strike.symbol()).isEqualTo("X");
  }

  @Test
  void pitching_symbol() {
    State pitching = State.of(8);
    assertThat(pitching).isInstanceOf(Pitching.class);
    assertThat(pitching.symbol()).isEqualTo("8");
  }

  @Test
  void gutter_symbol() {
    State gutter = State.of(0);
    assertThat(gutter).isInstanceOf(Pitching.class);
    assertThat(gutter.symbol()).isEqualTo("-");
  }

  @Test
  void spare_symbol() {
    State pitching = State.of(8);
    assertThat(pitching).isInstanceOf(Pitching.class);
    assertThat(pitching.symbol()).isEqualTo("8");

    State spare = pitching.roll(2);

    assertThat(spare).isInstanceOf(Spare.class);
    assertThat(spare.symbol()).isEqualTo("8|/");
  }

  @Test
  void open_symbol() {
    State pitching = State.of(8);
    assertThat(pitching).isInstanceOf(Pitching.class);
    assertThat(pitching.symbol()).isEqualTo("8");

    State open = pitching.roll(1);

    assertThat(open).isInstanceOf(Open.class);
    assertThat(open.symbol()).isEqualTo("8|1");
  }

  @Test
  void open_with_gutter_symbol() {
    State pitching = State.of(8);
    assertThat(pitching).isInstanceOf(Pitching.class);
    assertThat(pitching.symbol()).isEqualTo("8");

    State open = pitching.roll(0);

    assertThat(open).isInstanceOf(Open.class);
    assertThat(open.symbol()).isEqualTo("8|-");
  }

  @Test
  void invalid_under_0pins() {
    assertThatThrownBy(() -> State.of(-1))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("0개핀 이상 입력하세요. 현재 -1핀이 입력되었습니다.");
  }

  @Test
  void invalid_over_10pins() {
    assertThatThrownBy(() -> State.of(11))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("10개핀을 초과할 수 없습니다. 현재 11핀이 입력되었습니다.");
  }
}
