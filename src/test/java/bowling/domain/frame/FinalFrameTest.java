package bowling.domain.frame;

import bowling.domain.turn.FallenPins;
import bowling.error.CannotMakeFrameException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FinalFrameTest {

  @ParameterizedTest
  @ValueSource(ints = 10)
  @DisplayName("생성 가능")
  void createTest(int round) {
    Frame frame = Frame.of(round);

    Assertions.assertThat(frame).isInstanceOf(FinalFrame.class);
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 9})
  @DisplayName("생성 불가능")
  void invalidCreateToFinalFrameTest(int round) {
    Frame frame = Frame.of(round);

    Assertions.assertThat(frame).isNotInstanceOf(FinalFrame.class);
  }

  @ParameterizedTest
  @ValueSource(ints = {0, 11})
  @DisplayName("생성 불가능")
  void invalidCreateTest(int round) {
    Assertions.assertThatThrownBy(() -> Frame.of(round)).isInstanceOf(CannotMakeFrameException.class);
  }

  @Test
  @DisplayName("1번 쳤을 때 스트라이크면 추가 가능")
  void validAddingWhenStrikeFrame() {
    Frame frame = Frame.of(10);
    frame.bowl(new FallenPins(10));
    frame.bowl(new FallenPins(1));
    Assertions.assertThat(frame.fallenPinsStatus()).isEqualTo(11);
  }

  @Test
  @DisplayName("2번 쳤을 때 합이 10이면 추가 가능")
  void validAddingOptionalFrame() {
    Frame frame = Frame.of(10);
    frame.bowl(new FallenPins(9));
    frame.bowl(new FallenPins(1));
    frame.bowl(new FallenPins(1));
    Assertions.assertThat(frame.fallenPinsStatus()).isEqualTo(11);
  }

  @Test
  @DisplayName("진행 확인")
  void runningTest() {
    Frame frame = Frame.of(10);
    frame.bowl(new FallenPins(1));

    Assertions.assertThat(frame.checkFinished()).isFalse();
  }

  @Test
  @DisplayName("진행 확인2")
  void runningWithStrikeTest() {
    Frame frame = Frame.of(10);
    frame.bowl(new FallenPins(10));

    Assertions.assertThat(frame.checkFinished()).isFalse();
  }

  @Test
  @DisplayName("진행 확인3")
  void runningWithSpareTest() {
    Frame frame = Frame.of(10);
    frame.bowl(new FallenPins(5));
    frame.bowl(new FallenPins(5));

    Assertions.assertThat(frame.checkFinished()).isFalse();
  }

  @Test
  @DisplayName("종료 확인1")
  void finishedRoundTest() {
    Frame frame = Frame.of(10);
    frame.bowl(new FallenPins(5));
    frame.bowl(new FallenPins(1));

    Assertions.assertThat(frame.checkFinished()).isTrue();
  }

  @Test
  @DisplayName("종료 확인2")
  void finishedWithStrikeTest() {
    Frame frame = Frame.of(10);
    frame.bowl(new FallenPins(10));
    frame.bowl(new FallenPins(5));

    Assertions.assertThat(frame.checkFinished()).isTrue();
  }
}