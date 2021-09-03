package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.pin.Pin;
import bowling.pin.PinTest;
import bowling.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class NextPitchTest {

  @DisplayName("처음공과 두번째공을 굴려서 스페어가 반환되는지 확인한다.")
  @ParameterizedTest
  @CsvSource(value = {"1,9,/", "2,8,/", "3,7,/", "4,6,/", "5,5,/", "6,4,/", "7,3,/", "8,2,/",
      "9,1,/"})
  void firstBallIsSpare(int first, int second, String spareMark) {
    NextPitch firstBall = new NextPitch(PinTest.from(first));

    assertThat(firstBall.nextPitch(second).scoreMessage()).contains(first + "|" + spareMark);
  }

  @DisplayName("처음공과 두번째공을 굴려서 미스가 반환되는지 확인한다.")
  @ParameterizedTest
  @CsvSource(value = {"1,8", "2,7", "3,6", "4,5", "5,4", "6,3", "7,2", "8,1"})
  void firstBallIsMiss(int first, int second) {
    NextPitch firstBall = new NextPitch(PinTest.from(first));
    assertThat(firstBall.nextPitch(second).scoreMessage()).contains(first + "|" + second);
  }

  @DisplayName("처음공과 두번째공을 굴려서 쓰러트린 핀수가 반환되는지 확인한다.")
  @ParameterizedTest
  @CsvSource(value = {"1,8,9", "2,7,9", "3,6,9", "4,5,9", "5,4,9", "6,3,9", "7,2,9", "8,1,9"})
  void firstBallIsMissTotalCount(int first, int second, int total) {
    NextPitch firstBall = new NextPitch(PinTest.from(first));
    assertThat(firstBall.nextPitch(second).totalPin()).isEqualTo(Pin.from(total));
  }

  @DisplayName("두번째공을 굴렸을때 이전 공의 결과가 스페어 이상이면 합산된 스코어가 나온다.")
  @Test
  void nextPitchCalculate() {
    NextPitch firstBall = new NextPitch(PinTest.from(5));
    firstBall.nextPitch(5);
    Score score = firstBall.calculateScore(Score.spare());
    assertThat(score.scoreValue().getScore()).isEqualTo(15);
  }

  @DisplayName("이번공이 스트라이크 혹은 스페어가 아닐때 합산을 하려고 하면 에러가 발생한다.")
  @Test
  void validationNextPitchCalculate() {
    NextPitch firstBall = new NextPitch(PinTest.from(5));
    firstBall.nextPitch(5);

    assertThatThrownBy(() -> firstBall.calculateScore(Score.miss(5)))
        .isInstanceOf(RuntimeException.class);
  }
}