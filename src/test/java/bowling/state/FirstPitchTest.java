package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FirstPitchTest {

  @DisplayName("첫번째 공에 10을 주입하면 스트라이크가 반환되는지 확인한다.")
  @Test
  void startBallIsStrike() {
    FirstPitch startBall = new FirstPitch();
    State status = startBall.nextPitch(10);
    assertThat(status.isFinish()).isTrue();
  }

  @DisplayName("첫번째 공에 10이 아닌 숫자를 주입하면 스트라이크가 아닌지 확인한다.")
  @Test
  void startBallIsNotStrike() {
    FirstPitch startBall = new FirstPitch();
    State status = startBall.nextPitch(9);
    assertThat(status.isFinish()).isFalse();
  }

  @DisplayName("첫번째 공을 꾸렬 쓰러트린 핀의 수를 확인한다.")
  @ParameterizedTest
  @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
  void startBallIsNotStrikeTotalPin(int pinCount) {
    FirstPitch startBall = new FirstPitch();
    startBall.nextPitch(pinCount);
    assertThat(startBall.totalPin()).isEqualTo(pinCount);
  }
}