package bowling.domain;

import bowling.domain.state.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class FirstPitchTest {


    private FirstPitch firstPitch;

    @BeforeEach
    void setUp() {
        firstPitch = new FirstPitch();
    }

    @Test
    @DisplayName("볼링 스트라이크")
    void bowl_strike() {
        State state = firstPitch.bowl(10);

        Strike strike = new Strike(new Pins(0, Collections.singletonList(10)));
        assertThat(state).isEqualTo(strike);
    }

    @Test
    @DisplayName("볼링 두번째 넘어가기")
    void bowl() {
        State state = firstPitch.bowl(9);

        SecondPitch secondPitch = new SecondPitch(new Pins(1, Collections.singletonList(9)));
        assertThat(state).isEqualTo(secondPitch);
    }
}