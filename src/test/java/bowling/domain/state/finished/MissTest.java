package bowling.domain.state.finished;

import bowling.domain.state.State;
import bowling.domain.turn.FallenPins;
import bowling.error.CannotThrowBallException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class MissTest {
  State state;

  @BeforeEach
  void setup(){
    state = Miss.of(new FallenPins(8), new FallenPins(1));
  }

  @Test
  @DisplayName("종료 확인")
  void finishedTest(){
    Assertions.assertThat(state.isFinished()).isTrue();
  }

  @Test
  @DisplayName("출력 확인")
  void showTest(){
    Assertions.assertThat(state.show()).isEqualTo("8|1");
  }

  @Test
  @DisplayName("추가로 던지려 하면 무조건 에러")
  void invalidBowlTest(){
    Assertions.assertThatThrownBy(() -> state.bowl(new FallenPins(10))).isInstanceOf(CannotThrowBallException.class);
  }
}