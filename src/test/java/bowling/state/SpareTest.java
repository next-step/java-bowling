package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.pin.Pin;
import bowling.pin.PinTest;
import bowling.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SpareTest {

  @DisplayName("스페어 판별이 되엇을때 스페어 마크를 출력하는지 확인한다.")
  @ParameterizedTest
  @CsvSource(value = {"1,9,1|/", "2,8,2|/", "3,7,3|/", "4,6,4|/", "5,5,5|/", "6,4,6|/", "7,3,7|/",
      "8,2,8|/", "9,1,9|/"})
  void spareMark(int first, int second, String mark) {
    Spare spare = new Spare(PinTest.from(first), PinTest.from(second));
    assertThat(spare.scoreMessage()).isEqualTo(mark);
  }

  @DisplayName("스페어 판별이 되엇을때 쓰러트린 핀 개수를 출력하는지 확인한다.")
  @ParameterizedTest
  @CsvSource(value = {"1,9", "2,8", "3,7", "4,6", "5,5", "6,4", "7,3", "8,2", "9,1"})
  void spareTotalPin(int first, int second) {
    Spare spare = new Spare(PinTest.from(first), PinTest.from(second));
    assertThat(spare.totalPin()).isEqualTo(Pin.from(10));
  }

  @DisplayName("스페어 판별이 되엇을때 스코어를 확인한다.")
  @ParameterizedTest
  @CsvSource(value = {"1,9", "2,8", "3,7", "4,6", "5,5", "6,4", "7,3", "8,2", "9,1"})
  void spareScore(int first, int second) {
    Spare spare = new Spare(PinTest.from(first), PinTest.from(second));
    assertThat(spare.score()).isEqualTo(Score.spare());
  }
}