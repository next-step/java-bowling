package bowling.domain.frame;

import bowling.domain.turn.FallenPins;
import bowling.error.CannotMakeFrameException;
import bowling.error.CannotThrowBallException;
import bowling.error.InvalidFallenPinsException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class NormalFrameTest {

  @ParameterizedTest
  @ValueSource(ints = {1, 9})
  @DisplayName("생성 가능")
  void createTest(int round) {
    Frame frame = Frame.of(round);

    Assertions.assertThat(frame).isInstanceOf(NormalFrame.class);
  }

  @ParameterizedTest
  @ValueSource(ints = 10)
  @DisplayName("생성 가능")
  void invalidCreateToNormalFrameTest(int round) {
    Frame frame = Frame.of(round);

    Assertions.assertThat(frame).isNotInstanceOf(NormalFrame.class);
  }

  @ParameterizedTest
  @ValueSource(ints = {0, 11})
  @DisplayName("생성 불가능")
  void invalidCreateTest(int round) {
    Assertions.assertThatThrownBy(() -> Frame.of(round)).isInstanceOf(CannotMakeFrameException.class);
  }

  @ParameterizedTest
  @CsvSource(value = {"1,9", "2,8"})
  @DisplayName("2번 합해서 10 이내는 무조건 성공")
  void validTest(int firstShot, int secondShot) {
    Frame frame = Frame.of(1);
    frame.bowl(new FallenPins(firstShot));
    frame.bowl(new FallenPins(secondShot));
  }

  @ParameterizedTest
  @CsvSource(value = {"1,10", "2,9"})
  @DisplayName("2번 합해서 10 초과는 무조건 실패")
  void invalidPinsTest(int firstShot, int secondShot) {
    Frame frame = Frame.of(1);
    frame.bowl(new FallenPins(firstShot));

    Assertions.assertThatThrownBy(() -> frame.bowl(new FallenPins(secondShot)))
      .isInstanceOf(InvalidFallenPinsException.class);
  }

  @ParameterizedTest
  @CsvSource(value = {"1,2,3", "4,3,2"})
  @DisplayName("3번은 무조건 실패")
  void invalidBallsTest(int firstShot, int secondShot, int thirdShot) {
    Frame frame = Frame.of(1);
    frame.bowl(new FallenPins(firstShot));
    frame.bowl(new FallenPins(secondShot));

    Assertions.assertThatThrownBy(() -> frame.bowl(new FallenPins(thirdShot)))
      .isInstanceOf(CannotThrowBallException.class);
  }

  @Test
  @DisplayName("진행 확인")
  void runningTest() {
    Frame frame = Frame.of(1);
    frame.bowl(new FallenPins(1));

    Assertions.assertThat(frame.checkFinished()).isFalse();
  }

  @Test
  @DisplayName("종료 확인")
  void finishedTest() {
    Frame frame = Frame.of(1);
    frame.bowl(new FallenPins(10));

    Assertions.assertThat(frame.checkFinished()).isTrue();
  }

  @Test
  @DisplayName("종료 확인2")
  void finishedRoundTest() {
    Frame frame = Frame.of(1);
    frame.bowl(new FallenPins(5));
    frame.bowl(new FallenPins(1));

    Assertions.assertThat(frame.checkFinished()).isTrue();
  }
}