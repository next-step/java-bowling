package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.pin.PinTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class NextPitchTest {

  @DisplayName("처음공과 두번째공을 굴려서 스페어가 반환되는지 확인한다.")
  @ParameterizedTest
  @CsvSource(value = {"1,9,/","2,8,/","3,7,/","4,6,/","5,5,/","6,4,/","7,3,/","8,2,/","9,1,/"})
  void firstBallIsSpare(int first, int second, String spareMark) {
    NextPitch firstBall = new NextPitch(PinTest.from(first));

    assertThat(firstBall.nextPitch(second).score()).contains(first+"|"+spareMark);
  }

  @DisplayName("처음공과 두번째공을 굴려서 미스가 반환되는지 확인한다.")
  @ParameterizedTest
  @CsvSource(value = {"1,8","2,7","3,6","4,5","5,4","6,3","7,2","8,1"})
  void firstBallIsMiss(int first, int second) {
    NextPitch firstBall = new NextPitch(PinTest.from(first));
    assertThat(firstBall.nextPitch(second).score()).contains(first+"|"+Integer.sum(first,second));
  }

  @DisplayName("처음공과 두번째공을 굴려서 쓰러트린 핀수가 반환되는지 확인한다.")
  @ParameterizedTest
  @CsvSource(value = {"1,8,9","2,7,9","3,6,9","4,5,9","5,4,9","6,3,9","7,2,9","8,1,9"})
  void firstBallIsMissTotalCount(int first, int second, int total) {
    NextPitch firstBall = new NextPitch(PinTest.from(first));
    assertThat(firstBall.nextPitch(second).totalPin()).isEqualTo(total);
  }
}