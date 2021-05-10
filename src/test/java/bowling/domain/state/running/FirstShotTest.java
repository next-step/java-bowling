package bowling.domain.state.running;

import bowling.domain.state.State;
import bowling.domain.state.finished.Miss;
import bowling.domain.state.finished.Spare;
import bowling.domain.turn.FallenPins;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FirstShotTest {
  State state;

  @BeforeEach
  void setup() {
    state = FirstShot.of(new FallenPins(8));
  }

  @Test
  @DisplayName("종료 확인")
  void finishedTest() {
    assertThat(state.isFinished()).isFalse();
  }

  @Test
  @DisplayName("출력 확인")
  void showTest() {
    assertThat(state.show()).isEqualTo("8");
  }

  @Test
  @DisplayName("스페어로의 변환 확인")
  void changeToSpareTest() {
    assertThat(state.bowl(new FallenPins(2))).isInstanceOf(Spare.class);
  }

  @Test
  @DisplayName("일반 결과로의 변환 확인")
  void changeToMissTest() {
    assertThat(state.bowl(new FallenPins(1))).isInstanceOf(Miss.class);
  }

}