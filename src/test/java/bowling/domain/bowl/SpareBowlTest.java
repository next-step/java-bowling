package bowling.domain.bowl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SpareBowlTest {

    @DisplayName("스페어가 아닌경우 예외를 던진다.")
    @ParameterizedTest(name = "[{index}] previousHitCount: {0}, hitCount: {1}")
    @CsvSource({
            "10, 0",
            "1, 8",
            "0, 0",
    })
    void pitch_spare(int firstHitCount, int secondHitCount) {
        assertThatThrownBy(() -> new SpareBowl(firstHitCount, secondHitCount))
                .isInstanceOf(CanNotPitchException.class)
                .hasMessage(SpareBowl.NOT_SPARE_MESSAGE);
    }

}
