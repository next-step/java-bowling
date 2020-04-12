package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpareTest {

    @Test
    @DisplayName("스페어 객체 비교")
    void compareToSpare() {
        Pin sevenFallenOfAll = new Pin(7);
        Pin threeFallenOfRemain = new Pin(3);
        State state = new Spare(sevenFallenOfAll, threeFallenOfRemain);
        State compareState = new Spare(sevenFallenOfAll, threeFallenOfRemain);

        boolean same = state.equals(compareState);

        assertThat(same).isTrue();
    }

    @Test
    @DisplayName("화면 표시")
    void display() {
        Pin sevenFallenOfAll = new Pin(7);
        Pin threeFallenOfRemain = new Pin(3);
        State spare = new Spare(sevenFallenOfAll, threeFallenOfRemain);
        assertThat(spare.display()).isEqualTo("7|/");
    }
}
