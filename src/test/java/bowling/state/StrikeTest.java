package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StrikeTest {

  @Test
  void 스트라이크는_프레임_끝() {
    State state = new Strike();
    assertThat(state.isFinish()).isTrue();
  }

}