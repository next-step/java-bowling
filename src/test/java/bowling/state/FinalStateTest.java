package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FinalStateTest {

  FinalState finalState;

  @BeforeEach
  void setup() {
    finalState = new FinalState();
  }

  @Test
  void 한번만_투구하면_게임이_안끝난다() {
    assertThat(finalState.roll(5).isFinish()).isFalse();
  }

  @Test
  void 두번째_투구결과가_Miss이면_게임이_끝난다() {
    assertThat(finalState.roll(5).roll(4).isFinish()).isTrue();
  }

  @Test
  void 두번째_투구결과가_Spare면_게임이_안끝난다() {
    assertThat(finalState.roll(5).roll(5).isFinish()).isFalse();
  }

  @Test
  void 스페어후에_한번더_칠수있다() {
    assertThat(finalState.roll(5).roll(5).roll(10).isFinish()).isTrue();
  }

  @Test
  void 스페어_후에_한번_더_굴릴수_있다() {
    assertThat(finalState.roll(5).roll(5).roll(5).isFinish()).isTrue();
  }

  @Test
  void 스타라이크_후에_한번_더_굴릴수_있다() {
    assertThat(finalState.roll(10).roll(5).roll(5).isFinish()).isTrue();
  }

  @Test
  void 세번다_스트라이크를_칠_수_있다() {
    assertThat(finalState.roll(10).roll(10).roll(10).isFinish()).isTrue();
  }

}