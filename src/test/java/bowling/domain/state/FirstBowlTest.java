package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FirstBowlTest {

    @DisplayName("첫 번째 공과 두 번째 공의 합이 10이면 Spare, 아니면 Miss를 반환한다.")
    @Test
    void bowlTest() {
        FirstBowl firstBowl = new FirstBowl(new Pins(3));
        assertAll(
                () -> assertEquals(firstBowl.bowl(7), new Spare(new Pins(3), new Pins(7))),
                () -> assertEquals(firstBowl.bowl(2), new Miss(new Pins(3), new Pins(2)))
        );
    }

    @DisplayName("이전 점수와 더한 점수를 반환한다.")
    @Test
    void sumBeforeScoreTest() {
        FirstBowl firstBowl = new FirstBowl(new Pins(3));
        assertThat(firstBowl.sumBeforeScore(new Score(3, 1)))
                .isEqualTo(new Score(6, 0));
    }

    @DisplayName("이전 점수와 더하려 할 때 이전 점수의 남은 count가 1보다 크면 예외가 발생한다.")
    @Test
    void sumBeforeScoreTestFail() {
        FirstBowl firstBowl = new FirstBowl(new Pins(3));
        assertThatThrownBy(() -> firstBowl.sumBeforeScore(new Score(10, 2)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("어떤 점수를 입력받았을 때 계산할 수 있는지 여부를 반환한다.")
    @Test
    void canCalculateTest() {
        FirstBowl firstBowl = new FirstBowl(new Pins(3));
        assertAll(
                () -> assertFalse(firstBowl.canCalculate(new Strike().score())),
                () -> assertTrue(firstBowl.canCalculate(new Spare(new Pins(3), new Pins(7)).score()))
        );
    }
}