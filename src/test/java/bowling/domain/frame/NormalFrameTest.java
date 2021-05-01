package bowling.domain.frame;

import bowling.domain.turn.FallenPins;
import bowling.error.CannotThrowBallException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class NormalFrameTest {

  @ParameterizedTest
  @CsvSource(value = {"1,9", "2,8"})
  @DisplayName("2번 합해서 10 이내는 무조건 성공")
  void validTest(int firstShot, int secondShot){
    Frame frame = new NormalFrame(1);
    frame.shot(new FallenPins(firstShot));
    frame.shot(new FallenPins(secondShot));

    Assertions.assertThat(frame.fallenPinsStatus()).isEqualTo(firstShot+secondShot);
  }

  @ParameterizedTest
  @CsvSource(value = {"1,10", "2,9"})
  @DisplayName("2번 합해서 10 초과는 무조건 실패")
  void invalidPinsTest(int firstShot, int secondShot){
    Frame frame = new NormalFrame(1);
    frame.shot(new FallenPins(firstShot));

    Assertions.assertThatThrownBy(() -> frame.shot(new FallenPins(secondShot)))
      .isInstanceOf(CannotThrowBallException.class);
  }

  @ParameterizedTest
  @CsvSource(value = {"1,2,3", "4,3,2"})
  @DisplayName("3번은 무조건 실패")
  void invalidBallsTest(int firstShot, int secondShot, int thirdShot){
    Frame frame = new NormalFrame(1);
    frame.shot(new FallenPins(firstShot));
    frame.shot(new FallenPins(secondShot));

    Assertions.assertThatThrownBy(() -> frame.shot(new FallenPins(thirdShot)))
      .isInstanceOf(CannotThrowBallException.class);
  }

}