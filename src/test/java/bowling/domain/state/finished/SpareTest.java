package bowling.domain.state.finished;

import bowling.domain.state.State;
import bowling.domain.turn.FallenPins;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpareTest {
  State state;

  @BeforeEach
  void setup(){
    state = Spare.of(new FallenPins(8), new FallenPins(2));
  }

  @Test
  @DisplayName("종료 확인")
  void finishedTest(){
    Assertions.assertThat(state.isFinished()).isTrue();
  }

  @Test
  @DisplayName("출력 확인")
  void showTest(){
    Assertions.assertThat(state.show()).isEqualTo("8|/");
  }

}