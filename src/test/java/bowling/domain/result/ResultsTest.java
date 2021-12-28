package bowling.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.Pin;
import bowling.domain.result.status.Gutter;
import bowling.domain.result.status.Spare;
import bowling.domain.result.status.Strike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultsTest {

    private Results results;

    @BeforeEach
    void init() {
        results = new Results();
    }

    @Test
    @DisplayName("파라미터 보다 작으면, true를 반환한다.")
    void sizeLessThanTest() {
        assertThat(results.sizeLessThan(-1)).isFalse();
        assertThat(results.sizeLessThan(0)).isFalse();
        assertThat(results.sizeLessThan(1)).isTrue();
    }

    @Test
    @DisplayName("파라미터 보다 작거나 같으면, true를 반환한다.")
    void sizeLessOrEqualThanTest() {
        assertThat(results.sizeLessOrEqualThan(-1)).isFalse();
        assertThat(results.sizeLessOrEqualThan(0)).isTrue();
        assertThat(results.sizeLessOrEqualThan(1)).isTrue();
    }

    @Test
    @DisplayName("Bonus Number인 Strike를 가지고 있으면, true를 반환한다.")
    void containBonusAbleStateStrikeTest() {
        assertThat(results.containBonusAbleState()).isFalse();

        results.add(new Strike(Pin.of(10)));
        assertThat(results.containBonusAbleState()).isTrue();
    }

    @Test
    @DisplayName("Bonus Number인 Spare을 가지고 있으면, true를 반환한다.")
    void containBonusAbleStateSpareTest() {
        assertThat(results.containBonusAbleState()).isFalse();

        results.add(new Gutter(Pin.of(0)));
        results.add(new Spare(Pin.of(10)));
        assertThat(results.containBonusAbleState()).isTrue();
    }

    @Test
    @DisplayName("모든 값이 Gutter 이면, true를 아니면 false를 반환한다.")
    void containAllIsGutterTest() {
        assertThat(results.containAllIsGutter()).isFalse();

        results.add(new Gutter(Pin.of(0)));
        assertThat(results.containAllIsGutter()).isTrue();

        results.add(new Spare(Pin.of(10)));
        assertThat(results.containAllIsGutter()).isFalse();
    }
}