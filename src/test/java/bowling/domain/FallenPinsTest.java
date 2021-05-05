package bowling.domain;

import bowling.domain.turn.FallenPins;
import bowling.error.InvalidFallenPinsException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


class FallenPinsTest {
  public static final FallenPins existingPin = new FallenPins(8);

  @ParameterizedTest
  @ValueSource(ints = {1, 9, 10})
  @DisplayName("0~10 사이의 핀을 쓰러트렸을 때 저장해야 함")
  void createTest(int pins) {
    FallenPins score = new FallenPins(pins);

    Assertions.assertThat(score.pins()).isEqualTo(pins);
  }

  @ParameterizedTest
  @ValueSource(ints = {-1, 11})
  @DisplayName("그 이외에는 추가되면 안된다")
  void invalidCreateTest(int pins) {
    Assertions.assertThatThrownBy(() -> new FallenPins(pins)).isInstanceOf(InvalidFallenPinsException.class);
  }

  @ParameterizedTest
  @CsvSource(value = {"1,10", "2,9", "7,5"})
  @DisplayName("두번째 샷 추가 불가능한 경우 테스트")
  void invalidAddingOtherPinCheckTest(int firstShotPins, int secondShotPins) {
    FallenPins firstShot = new FallenPins(firstShotPins);
    FallenPins secondShot = new FallenPins(secondShotPins);

    Assertions.assertThatThrownBy(() -> firstShot.checkAddable(secondShot)).isInstanceOf(InvalidFallenPinsException.class);
  }

  @ParameterizedTest
  @CsvSource(value = {"1,9", "2,8", "7,3"})
  @DisplayName("두번째 샷 추가 가능한 경우 테스트")
  void addingOtherPinCheckTest(int firstShotPins, int secondShotPins) {
    FallenPins firstShot = new FallenPins(firstShotPins);
    FallenPins secondShot = new FallenPins(secondShotPins);

    firstShot.checkAddable(secondShot);

  }
}