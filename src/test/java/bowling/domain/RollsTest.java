package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class RollsTest {

    @DisplayName("볼링은 20번 던질 수 있다..")
    @Test
    void canCreateLottoNumbers() {
        assertThat(Rolls.generateRolls().size()).isSameAs(20);
    }
}
