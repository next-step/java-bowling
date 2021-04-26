package bowling_step4.domain.state;


import bowling_step4.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstBowlTest {

  @Test
  @DisplayName("첫번째 볼 다음 miss를 쳤을 경우 확인")
  void playMiss() {
    FirstBowl firstBowl = new FirstBowl(3);
    State state = firstBowl.play(new Pin(4));
    assertThat(state).isInstanceOf(Miss.class);
  }


  @Test
  @DisplayName("첫번째 볼 다음 spare를 쳤을 경우 확인")
  void playSpare() {
    FirstBowl firstBowl = new FirstBowl(2);
    State state = firstBowl.play(new Pin(8));
    assertThat(state).isInstanceOf(Spare.class);
  }
}