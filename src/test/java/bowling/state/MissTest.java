package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import bowling.pin.Pin;
import bowling.pin.PinTest;
import bowling.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MissTest {

  @DisplayName("미스 판별이 되엇을때 미스 마크를 출력하는지 확인한다.")
  @ParameterizedTest
  @CsvSource(value = {"1,8,1|8", "2,5,2|5", "3,4,3|4", "4,4,4|4", "5,3,5|3", "8,0,8|0"})
  void missMark(int first, int second, String mark) {
    Miss miss = new Miss(PinTest.from(first), PinTest.from(second));
    assertThat(miss.scoreMessage()).isEqualTo(mark);
  }

  @DisplayName("미스 판별이 되엇을때 쓰러트린 핀 개수를 출력하는지 확인한다.")
  @ParameterizedTest
  @CsvSource(value = {"1,8", "2,5", "3,4", "4,4", "5,3", "8,0"})
  void missTotalPin(int first, int second) {
    Miss miss = new Miss(PinTest.from(first), PinTest.from(second));
    assertThat(miss.totalPin()).isEqualTo(Pin.from(first + second));
  }

  @DisplayName("미스 판별이 되엇을때 쓰러트린 스코어를 확인한다.")
  @ParameterizedTest
  @CsvSource(value = {"1,8", "2,5", "3,4", "4,4", "5,3", "8,0"})
  void missScore(int first, int second) {
    Miss miss = new Miss(PinTest.from(first), PinTest.from(second));
    assertThat(miss.score()).isEqualTo(Score.miss(first + second));
  }

  @DisplayName("스트라이크와 스페어일때 각각 점수 합산을 확인한다.")
  @Test
  void scoreCalculate() {
    assertAll(
        () -> assertThat(
            new Miss(PinTest.from(1), PinTest.from(2)).calculateScore(Score.strike()))
            .isEqualTo(Score.miss(13)),

        () -> assertThat(
            new Miss(PinTest.from(1), PinTest.from(2)).calculateScore(Score.spare()))
            .isEqualTo(Score.miss(11))
    );
  }
}