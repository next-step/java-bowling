package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StrikeTest {

    @Test
    @DisplayName("스트라이크 확인 기능")
    void isStrike() {
        Pitch pitch = Pitch.from(10);
        Strike strike = Strike.from(pitch);

        Assertions.assertThat(strike.isStrike()).isTrue();
    }

    @Test
    @DisplayName("스트라이크가 아님을 확인 기능")
    void isNotStrike() {
        Pitch pitch = Pitch.from(9);
        Strike strike = Strike.from(pitch);

        Assertions.assertThat(strike.isStrike()).isFalse();
    }

}