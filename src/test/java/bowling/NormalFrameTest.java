package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.frame.NormalFrame;
import bowling.domain.state.Ready;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class NormalFrameTest {

  @Test
  @DisplayName("첫번째 판에 11개의 핀을 쓰러트릴 경우")
  public void play() {
    assertThatThrownBy(() -> {
      NormalFrame normalFrame = NormalFrame.createFirst();
      normalFrame.play(11);
    }).isInstanceOf(IllegalArgumentException.class);
  }

  
}
