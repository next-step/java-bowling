package bowling.domain.frame;

import bowling.domain.turn.FallenPins;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinalFrameTest {

  @Test
  @DisplayName("1번 쳤을 때 스트라이크면 추가 가능")
  void validAddingWhenStrikeFrame(){
    Frame frame = new FinalFrame();
    frame.shot(new FallenPins(10));
    frame.shot(new FallenPins(1));
    Assertions.assertThat(frame.fallenPinsStatus()).isEqualTo(11);
  }

  @Test
  @DisplayName("2번 쳤을 때 합이 10이면 추가 가능")
  void validAddingOptionalFrame(){
    Frame frame = new FinalFrame();
    frame.shot(new FallenPins(9));
    frame.shot(new FallenPins(1));
    frame.shot(new FallenPins(1));
    Assertions.assertThat(frame.fallenPinsStatus()).isEqualTo(11);
  }


}