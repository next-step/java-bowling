package bowling.domain;

import bowling.error.InvalidFallenPinsException;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


class PinsTest {
  public static final Pins existingPin = new Pins(8);

  @ParameterizedTest
  @ValueSource(ints = {1,9,10})
  @DisplayName("0~10 사이의 핀을 쓰러트렸을 때 저장해야 함")
  void createTest(int pins){
    Pins score = new Pins(pins);

    Assertions.assertThat(score.pins()).isEqualTo(pins);
  }

  @ParameterizedTest
  @ValueSource(ints = {-1,11})
  @DisplayName("그 이외에는 추가되면 안된다")
  void invalidCreateTest(int pins){
    Assertions.assertThatThrownBy(() -> new Pins(pins)).isInstanceOf(InvalidFallenPinsException.class);
  }

  @ParameterizedTest
  @CsvSource(value = {"1,9", "2,8"})
  @DisplayName("두번째 샷 추가 가능해야 한다")
  void addingOtherPinCheckTest(int firstShotPins, int secondShotPins){
    Pins firstShot = new Pins(firstShotPins);
    Pins secondShot = new Pins(secondShotPins);

    Assertions.assertThat(firstShot.addingOtherPins(secondShot).pins()).isEqualTo(firstShotPins+secondShotPins);
  }

  @ParameterizedTest
  @CsvSource(value = {"1,10", "2,9", "7,5"})
  @DisplayName("두번째 샷 추가 불가능한 경우 테스트")
  void invalidAddingOtherPinCheckTest(int firstShotPins, int secondShotPins){
    Pins firstShot = new Pins(firstShotPins);
    Pins secondShot = new Pins(secondShotPins);

    Assertions.assertThatThrownBy(() ->firstShot.addingOtherPins(secondShot).pins()).isInstanceOf(InvalidFallenPinsException.class);
  }
}