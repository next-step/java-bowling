package bowling.domain.state.running;

import bowling.domain.state.State;
import bowling.domain.state.finished.Strike;
import bowling.domain.turn.FallenPins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {
  State state;

  @BeforeEach
  void setup() {
    state = Ready.of();
  }

  @Test
  @DisplayName("종료 확인")
  void finishedTest() {
    assertThat(state.isFinished()).isFalse();
  }

  @Test
  @DisplayName("출력 확인")
  void showTest() {
    assertThat(state.show()).isEqualTo("");
  }

  @Test
  @DisplayName("스트라이크로의 변환 확인")
  void changeToSpareTest() {
    assertThat(state.bowl(new FallenPins(10))).isInstanceOf(Strike.class);
  }

  @Test
  @DisplayName("첫 샷 진행으로의 변환 확인")
  void changeToFirstShotTest() {
    assertThat(state.bowl(new FallenPins(5))).isInstanceOf(FirstShot.class);
  }
}