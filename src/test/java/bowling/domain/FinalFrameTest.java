package bowling.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalFrameTest {

  @Test
  @DisplayName("마지막 프레임에 스페어가 나오면 보너스 투구를 준다.")
  void isEnd_false_spare() {
    NormalFrame normalFrame = new NormalFrame();
    FinalFrame finalFrame = normalFrame.last();
    finalFrame.play(5);
    finalFrame.play(5);
    assertFalse(finalFrame.isEnd());
  }

  @Test
  @DisplayName("마지막 프레임에 스트라이크가 나오면 보너스 투구를 준다.")
  void isEnd_false_strike() {
    NormalFrame normalFrame = new NormalFrame();
    FinalFrame finalFrame = normalFrame.last();
    finalFrame.play(10);
    assertFalse(finalFrame.isEnd());
  }

  @Test
  @DisplayName("마지막 프레임에 스트라이크후에 또 스트라이크가 나오면 보너스 투구를 준다.")
  void isEnd_false_strike_strike_end() {
    NormalFrame normalFrame = new NormalFrame();
    FinalFrame finalFrame = normalFrame.last();
    finalFrame.play(10);
    assertFalse(finalFrame.isEnd());
    finalFrame.play(10);
    assertFalse(finalFrame.isEnd());
    finalFrame.play(10);
    assertTrue(finalFrame.isEnd());
  }
}