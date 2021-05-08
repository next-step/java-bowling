package bowling.domain.state.running;

import bowling.domain.state.State;
import bowling.domain.state.finished.Miss;
import bowling.domain.state.finished.Spare;
import bowling.domain.turn.FallenPins;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FirstShotTest {
  State state;

  @BeforeEach
  void setup() {
    state = FirstShot.of(new FallenPins(8));
  }

  @Test
  @DisplayName("종료 확인")
  void finishedTest() {
    Assertions.assertThat(state.isFinished()).isFalse();
  }

  @Test
  @DisplayName("출력 확인")
  void showTest() {
    Assertions.assertThat(state.show()).isEqualTo("8");
  }

  @Test
  @DisplayName("스페어로의 변환 확인")
  void changeToSpareTest() {
    Assertions.assertThat(state.bowl(new FallenPins(2))).isInstanceOf(Spare.class);
  }

  @Test
  @DisplayName("일반 결과로의 변환 확인")
  void changeToMissTest() {
    Assertions.assertThat(state.bowl(new FallenPins(1))).isInstanceOf(Miss.class);
  }

}