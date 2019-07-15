package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class LastFallDownPinsTest {

  @Test
  void 마지막_프레임은_스트라이크면_한번더_투구할수있다() {
    assertThat(LastFallDownPins.first(10).isLastFrameFinish()).isEqualTo(false);
    assertThat(LastFallDownPins.first(10).roll(6).isLastFrameFinish()).isEqualTo(true);
  }

  @Test
  void 마지막_프레임은_스페어하면_한번더_투구할수있다() {
    assertThat(LastFallDownPins.first(3).roll(7).isLastFrameFinish()).isEqualTo(false);
    assertThat(LastFallDownPins.first(3).roll(7).roll(6).isLastFrameFinish()).isEqualTo(true);
  }

}
