package bowling.domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinTest {

    @DisplayName("넘어뜨린 핀 개수가 0이상 10 이하가 아니라면 예외를 던진다.")
    @ParameterizedTest(name = "[{index}] score: {0}")
    @ValueSource(ints = {-1, 11})
    void pitch_illegalRangeScore_throwsException(int hitCount) {
        assertThatThrownBy(() -> pin(hitCount))
                .isInstanceOf(IllegalRangeOfHitCountException.class);
    }

    @ParameterizedTest(name = "[{index}] {0} + {1} = {2}")
    @CsvSource({
            "1, 9, 10",
            "10, 0, 10",
            "3, 4, 7",
            "5, 1, 6",
            "3, 7, 10",
            "0, 0, 0",
    })
    void plus(int firstHitCount, int secondHitCount, int expected) {
        Pin firstPin = pin(firstHitCount);
        Pin secondPin = pin(secondHitCount);
        assertThat(firstPin.plus(secondPin)).isEqualTo(pin(expected));
        assertThat(secondPin.plus(firstPin)).isEqualTo(pin(expected));
    }

    @ParameterizedTest(name = "[{index}] hitCount: {0}, allHit: {1}")
    @CsvSource({
            "1, false",
            "10, true"
    })
    void allHit(int hitCount, boolean allHit) {
        assertThat(pin(hitCount).isAllHit()).isEqualTo(allHit);
    }

    @ParameterizedTest(name = "[{index}] hitCount: {0}, noneHit: {1}")
    @CsvSource({
            "0, true",
            "4, false"
    })
    void noneHit(int hitCount, boolean noneHit) {
        assertThat(pin(hitCount).isNoneHit()).isEqualTo(noneHit);
    }

    public Pin pin(int hitCount) {
        return Pin.from(hitCount);
    }

}
