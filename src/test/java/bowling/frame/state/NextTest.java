package bowling.frame.state;

import bowling.score.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NextTest {

    private Pin firstPins;
    private State secondState;

    @Test
    @DisplayName("두번째 볼링 - 스페어")
    void secondBowlIsSpare() {
        firstPins = Pin.bowl("3");
        secondState = Next.bowl(firstPins).bowl("7");
        assertThat(secondState.isFinish()).isTrue();
        assertThat(secondState instanceof Spare);
    }

    @Test
    @DisplayName("두번째 볼링 - 미스")
    void secondBowlIsMiss() {
        firstPins = Pin.bowl("5");
        secondState = Next.bowl(firstPins).bowl("2");
        assertThat(secondState.isFinish()).isTrue();
        assertThat(secondState instanceof Miss);
    }
}
