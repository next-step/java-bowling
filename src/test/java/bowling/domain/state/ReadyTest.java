package bowling.domain.state;

import bowling.domain.Pitch;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    @Test
    @DisplayName("스트라이크 리턴")
    void isStrike() {
        Pitch pitch = Pitch.from(10);

        Ready ready = new Ready();

        Assertions.assertThat(ready.bowl(pitch)).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("일반 투구 리턴")
    void isFirstBowling() {
        Pitch pitch = Pitch.from(9);

        Ready ready = new Ready();

        Assertions.assertThat(ready.bowl(pitch)).isInstanceOf(FirstBowl.class);
    }

}