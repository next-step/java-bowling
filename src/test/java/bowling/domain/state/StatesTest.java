package bowling.domain.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StatesTest {

    private States states;

    @BeforeEach
    void setup() {
        states = States.initialize();
    }

    @Test
    @DisplayName("스트라이크 시 보너스 찬스가 두번 주어진다.")
    void hasLastBonusChance_strike() {
        states.bowl(10);

        assertThat(states.hasLastBonusChance()).isFalse();
    }

    @Test
    @DisplayName("스페어 시 보너스 찬스가 한번 주어진다.")
    void hasLastBonusChance_spare() {
        states.bowl(5);
        states.bowl(5);

        assertThat(states.hasLastBonusChance()).isTrue();
    }
}