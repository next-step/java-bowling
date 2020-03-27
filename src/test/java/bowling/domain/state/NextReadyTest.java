package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NextReadyTest {

    @Test
    @DisplayName("객체 생성 비교")
    void compareToNextReady() {
        State state = new NextReady(7);
        State state2 = new NextReady(7);

        boolean same = state.equals(state2);

        assertThat(same).isTrue();
    }
}
