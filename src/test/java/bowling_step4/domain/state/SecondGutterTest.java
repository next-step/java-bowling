package bowling_step4.domain.state;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling_step4.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SecondGutterTest {

  @Test
  @DisplayName("두번째 거터에선 더이상 투구할 수 없다.")
  void play() {
    SecondGutter gutter = new SecondGutter(new Pin(3));
    assertThatThrownBy(() -> gutter.play(new Pin(4)))
        .isInstanceOf(IllegalArgumentException.class);
  }
}