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

    @Test
    @DisplayName("스페어 상태 확인")
    void checkNextReadyToSpare() {
        State state = new NextReady(7);
        State checkState = state.bowl(3);

        assertThat(checkState instanceof Spare).isTrue();
    }

    @Test
    @DisplayName("미스 상태 확인")
    void checkNextReadyToMiss() {
        State state = new NextReady(3);
        State checkState = state.bowl(2);

        assertThat(checkState instanceof Miss).isTrue();
    }

    @Test
    @DisplayName("화면 표시")
    void display() {
        State nextReady = new NextReady(3);
        assertThat(nextReady.display()).isEqualTo("3");
    }
}
