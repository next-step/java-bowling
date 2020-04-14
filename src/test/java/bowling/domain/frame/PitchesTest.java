package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PitchesTest {
    @DisplayName("저장된 핀의 총 갯수를 구할 수 있다.")
    @Test
    void getTotal() {
        Pitches pitches = new Pitches();
        pitches.add(8);
        pitches.add(1);
        assertThat(pitches.getPinCountTotal()).isEqualTo(9);
    }

    @DisplayName("첫번째 핀 갯수를 구할 수 있다.")
    @Test
    void getFirst() {
        Pitches pitches = new Pitches();
        assertThat(pitches.getFirst().isPresent()).isFalse();
        pitches.add(1);
        assertThat(pitches.getFirst().isPresent()).isTrue();
    }
}
