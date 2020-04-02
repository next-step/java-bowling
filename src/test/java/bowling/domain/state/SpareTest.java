package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpareTest {

    @Test
    @DisplayName("스페어 객체 비교")
    void compareToSpare() {
        State state = new Spare(7, 3);
        State compareState = new Spare(7, 3);

        boolean same = state.equals(compareState);

        assertThat(same).isTrue();
    }

    @Test
    @DisplayName("화면 표시")
    void display() {
        State spare = new Spare(7, 3);
        assertThat(spare.display()).isEqualTo("7|/");
    }
}
