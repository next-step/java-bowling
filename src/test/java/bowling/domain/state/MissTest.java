package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MissTest {

    @Test
    @DisplayName("미스 객체 비교")
    void compareToMiss() {
        State state = new Miss(3, 2);
        State compareState = new Miss(3, 2);

        boolean same = state.equals(compareState);

        assertThat(same).isTrue();
    }
}
