package bowling_step4.domain.frame;

import bowling_step4.domain.Pin;
import bowling_step4.domain.state.Spare;
import bowling_step4.domain.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NormalFrameTest {


  @Test
  @DisplayName("일반 프레임은 최대 9번 진행할 수 있다.")
  void valid() {
    assertThatThrownBy(() -> new NormalFrame(9))
        .isInstanceOf(IllegalArgumentException.class);
  }


  @Test
  @DisplayName("다음 프레임으로 가는 지 확인")
  void next() {
    Frame frame = NormalFrame.createFirst();
    Frame nextFrame = frame.next();

    assertThat(nextFrame).isEqualTo(new NormalFrame(1));
  }


  @Test
  @DisplayName("첫 프레임에 스트라이크를 쳤을 경우 확인")
  void playStrike() {
    NormalFrame frame = NormalFrame.createFirst();
    frame.play(new Pin(10));
    assertThat(frame.getState()).isInstanceOf(Strike.class);
  }


  @Test
  @DisplayName("첫 프레임에 스페어를 쳤을 경우 확인")
  void playSpare() {
    NormalFrame frame = NormalFrame.createFirst();
    frame.play(new Pin(7));
    frame.play(new Pin(3));
    assertThat(frame.getState()).isInstanceOf(Spare.class);
  }


  @Test
  @DisplayName("Normal 프레임에서는 투구 기회가 2번뿐이다.")
  void playOnlyTwoCount() {
    NormalFrame frame = NormalFrame.createFirst();
    frame.play(new Pin(3));
    frame.play(new Pin(4));

    assertThatThrownBy(() -> frame.play(new Pin(3)))
        .isInstanceOf(IllegalArgumentException.class);
  }


  @Test
  @DisplayName("첫 프레임에 스트라이크를 쳤을 경우 더이상 해당 프레임에서는 투구 기회가 없다.")
  void playOnelyOneCountIfStrike() {
    NormalFrame frame = NormalFrame.createFirst();
    frame.play(new Pin(10));

    assertThatThrownBy(() -> frame.play(new Pin(2)))
        .isInstanceOf(IllegalArgumentException.class);
  }


  @ParameterizedTest
  @CsvSource(value = { "4, 2, 6"})
  @DisplayName("점수 계산 확인")
  void calculate(int first, int second, int expectScore) {
    Frame frame = NormalFrame.createFirst();
    frame = frame.play(new Pin(first));
    frame.play(new Pin(second));

    assertThat(frame.getScore().getScore()).isEqualTo(expectScore);

  }

}