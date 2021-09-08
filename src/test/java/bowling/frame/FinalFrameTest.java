package bowling.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.score.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FinalFrameTest {

  private FinalFrame frame;

  @BeforeEach
  void setUp() {
    frame = new FinalFrame();
  }

  @DisplayName("마지막 프레임 2구를 굴렸을때 해당하는 스코어를 반환한다.")
  @Test
  void finalTwoPitch() {
    frame.play(8)
        .play(1);
    assertThat(frame.getScoreMessage()).isEqualTo("8|1");
  }

  @DisplayName("마지막 프레임에서 2구에 스페어 이상 처리가 안된 상태에서 추가로 3구를 굴릴 경우, 에러가 발생한다.")
  @Test
  void validationOverTwoPitch() {
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
    frame.play(pin1)
        .play(pin2)
        .play(pin3);
    assertThat(frame.getScoreMessage()).isEqualTo(score);
  }

  @DisplayName("마지막 프레임에서 3구를 초과하게 굴렸을 경우, 에러가 발생한다.")
  @Test
  void validationOverThreePitch() {
    assertThatThrownBy(() ->
        frame.play(10)
            .play(10)
            .play(10)
            .play(10)
    ).isInstanceOf(RuntimeException.class);
  }

  @DisplayName("마지막 프레임에서 2번째 공이 스페어인 경우 3번째 공의 결과값을 합산하여 반환한다.")
  @Test
  void beforeSpare() {
    Score spare = Score.spare();
    frame.play(10);

    int score = frame.frameScoreAdd(spare).getScoreDto().getScore();
    assertThat(score).isEqualTo(20);
  }

  @DisplayName("마지막 프레임에서 2번째 공이 스트라이크인 경우 3번째 공의 결과값을 합산하여 반환한다.")
  @Test
  void beforeStrike() {
    Score strike = Score.strike();
    frame.play(10).play(10);
    int score = frame.frameScoreAdd(strike).getScoreDto().getScore();

    assertThat(score).isEqualTo(30);
  }

  @DisplayName("2번째 공이 스페어 미만의 결과인 경우 라운드 종료가 반환 된다.")
  @Test
  void twoPitchesFinish() {
    frame.play(1).play(8);
    assertThat(frame.isGameEnd()).isTrue();
  }

  @DisplayName("3번째 공을 굴렸을때 결과가 생성되면 라운드 종료가 반환된다.")
  @Test
  void threePitchesFinish() {
    frame.play(1).play(9).play(1);
    assertThat(frame.isGameEnd()).isTrue();
  }
}