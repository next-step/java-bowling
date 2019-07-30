package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.Score;
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

  @Test
  void 마지막_프레임이_끝이나지_않으면_DEFAULT스코어를_반환한다() {
    FinalState state = new FinalState();
    state.roll(4);
    assertThat(state.score()).isEqualTo(Score.defaultScore());
  }

  @Test
  void 마지막_프레임이_끝나면_마지막_프레임의_합을_리턴한다_Miss일때() {
    FinalState state = new FinalState();
    state.roll(4);
    state.roll(5);
    assertThat(state.score()).isEqualTo(new Score(9, 0));
  }

  @Test
  void 마지막_프레임이_끝나면_마지막_프레임의_합을_리턴한다_Strike일때() {
    FinalState state = new FinalState();
    state.roll(10);
    state.roll(4);
    state.roll(6);
    assertThat(state.score()).isEqualTo(new Score(20, 0));
  }

  @Test
  void 마지막_프레임이_끝나면_마지막_프레임의_합을_리턴한다_모두_strike일때() {
    FinalState state = new FinalState();
    state.roll(10);
    state.roll(10);
    state.roll(10);
    assertThat(state.score()).isEqualTo(new Score(30, 0));
  }

  @Test
  void 마지막_프레임이_끝나면_마지막_프레임의_합을_리턴한다_spare일때() {
    FinalState state = new FinalState();
    state.roll(4);
    state.roll(6);
    state.roll(10);
    assertThat(state.score()).isEqualTo(new Score(20, 0));
  }

  @Test
  void 이전_Score에_추가점수를_더한_후에도_추가점수가_더필요한경우_디폴트프레임을_리턴한다() {
    FinalState state = new FinalState();
    state.roll(8);

    assertThat(state.addScore(new Score(10,2))).isEqualTo(Score.defaultScore());
  }

  @Test
  void 이전_Score에_추가점수를_더한_후_계산이_완료된_Score는_완료된_score를_리턴한다_spree() {
    FinalState state = new FinalState();
    state.roll(3);
    state.roll(5);

    assertThat(state.addScore(new Score(10,1))).isEqualTo(new Score(13,0));
  }

  @Test
  void 이전_Score에_추가점수를_더한_후_계산이_완료된_Score는_완료된_score를_리턴한다_strike() {
    FinalState state = new FinalState();
    state.roll(10);
    state.roll(10);
    state.roll(10);

    assertThat(state.addScore(new Score(10,2))).isEqualTo(new Score(30,0));
  }
}