package bowling.domain.state;

import bowling.domain.pins.Pins;
import bowling.exception.CannotCalculateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayingTest {

    @DisplayName("진행중인 상태는 점수를 계산할 수 없다.")
    @Test
    void playing_frame() {
        State firstBowl = FirstBowl.of(Pins.of(3));
        State ready = Ready.of();

        assertThatThrownBy(firstBowl::getScore).isInstanceOf(CannotCalculateException.class);
        assertThatThrownBy(ready::getScore).isInstanceOf(CannotCalculateException.class);
    }
}