package bowling.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FinalFrameTest {

  @DisplayName("마지막 프레임 2구를 굴렸을때 해당하는 스코어를 반환한다.")
  @Test
  void finalTwoPitch() {
    Frame frame = new FinalFrame();
    frame.play(8)
        .play(1);
    assertThat(frame.getScore()).isEqualTo("8|1");
  }

  @DisplayName("마지막 프레임에서 2구에 스페어 이상 처리가 안된 상태에서 추가로 3구를 굴릴 경우, 에러가 발생한다.")
  @Test
  void validationOverTwoPitch() {
    Frame frame = new FinalFrame();
    assertThatThrownBy(() ->
        frame.play(1)
            .play(8)
            .play(10)
    ).isInstanceOf(RuntimeException.class);
  }

  @DisplayName("마지막 프레임 3구를 굴렸을때 해당하는 스코어를 반환한다.")
  @ParameterizedTest
  @CsvSource(value = {
      "10,10,10,X|X|X",
      "10,10,7,X|X|7",
      "10,2,8,X|2|/",
      "8,2,10,8|/|X"
  })
  void finalThreePitch(int pin1, int pin2, int pin3, String score) {
    Frame frame = new FinalFrame();
    frame.play(pin1)
        .play(pin2)
        .play(pin3);
    assertThat(frame.getScore()).isEqualTo(score);
  }

  @DisplayName("마지막 프레임에서 3구를 초과하게 굴렸을 경우, 에러가 발생한다.")
  @Test
  void validationOverThreePitch() {
    Frame frame = new FinalFrame();
    assertThatThrownBy(() ->
        frame.play(10)
            .play(10)
            .play(10)
            .play(10)
    ).isInstanceOf(RuntimeException.class);
  }

}