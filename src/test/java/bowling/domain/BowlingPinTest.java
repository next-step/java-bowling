package bowling.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BowlingPinTest {
    @Test
    @DisplayName("남아있는 볼링핀이 없는 경우, 점수 입력이 불가능하다.")
    void cannotInputScoreIfNoPins() {
        BowlingPin bowlingPin = new BowlingPin();

        bowlingPin.update(10);

        assertThatThrownBy(() -> bowlingPin.update(3))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("점수가 10을 넘을 경우, 예외를 발생시킨다.")
    void inputOverScoreTest() {
        BowlingPin bowlingPin = new BowlingPin();

        bowlingPin.update(9);

        assertThatThrownBy(() -> bowlingPin.update(2))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
