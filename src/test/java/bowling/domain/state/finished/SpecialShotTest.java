package bowling.domain.state.finished;

import bowling.domain.state.State;
import bowling.domain.turn.FallenPins;
import bowling.error.CannotThrowBallException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SpecialShotTest {

  State state;

  @BeforeEach
  void setup() {
    state = SpecialShot.of(new FallenPins(10));
  }

  @Test
  @DisplayName("종료 확인")
  void finishedTest() {
    assertThat(state.isFinished()).isTrue();
  }

  @Test
  @DisplayName("추가로 던지려 하면 무조건 에러")
  void invalidBowlTest() {
    assertThatThrownBy(() -> state.bowl(new FallenPins(10))).isInstanceOf(CannotThrowBallException.class);
  }
}
