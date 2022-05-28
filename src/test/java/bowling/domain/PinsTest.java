package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinsTest {

    @DisplayName("볼링 핀을 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 10})
    void createTest(int pins) {
        new Pins(pins);
    }

    @DisplayName("볼링 핀의 개수가 음수거나 10보다 크면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void createTestFail(int pins) {
        assertThatThrownBy(() -> new Pins(pins))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("볼링 핀과 점수를 더한다.")
    @Test
    void sumScoreTest() {
        Pins pins = new Pins(5);
        assertThat(pins.sumScore(4)).isEqualTo(9);
    }

    @DisplayName("볼링 핀의 개수를 반환한다.")
    @Test
    void pinsTest() {
        Pins pins = new Pins(5);
        assertThat(pins.pins()).isEqualTo(5);
    }

    @DisplayName("볼링 핀이 Strike인지 확인한다.")
    @Test
    void isStrikeTest() {
        Pins pins = new Pins(10);
        assertThat(pins.isStrike()).isTrue();
    }

    @DisplayName("볼링 핀이 Spare인지 확인한다.")
    @Test
    void isSpareTest() {
        Pins pins = new Pins(5);
        assertThat(pins.isSpare(new Pins(5))).isTrue();
    }

    @DisplayName("두 볼링핀을 더했을 때 개수를 계산한다.")
    @Test
    void totalScoreTest() {
        Pins pins = new Pins(5);
        assertThat(pins.totalScore(new Pins(3))).isEqualTo(8);
    }
}