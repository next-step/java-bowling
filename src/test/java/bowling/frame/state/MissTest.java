package bowling.frame.state;

import bowling.score.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MissTest {

    @Test
    @DisplayName("첫번째, 두번째 볼링 결과 - 미스")
    void result() {
        Pin prevPins = Pin.bowl("4");
        Pin nextPins = Pin.bowl("3");
        State state = Miss.of(prevPins, nextPins);
        assertThat(state.toString()).isEqualTo("4|3");
    }
}
