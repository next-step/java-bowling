package bowling.domain.state;

import bowling.domain.pin.FallenPin;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class FirstBowlingTest {

    @DisplayName("두 핀의 합이 10미만이면 미스")
    @Test
    void bowl_toMiss() {
        State state = new FirstBowling(FallenPin.of(9));

        State expected = new Miss(FallenPin.of(9), FallenPin.of(0));
        assertThat(state.bowl(FallenPin.of(0))).isEqualTo(expected);
    }

    @DisplayName("두 핀의 합이 10이면 스페어")
    @Test
    void bowl_toSpare() {
        State state = new FirstBowling(FallenPin.of(9));

        State expected = new Spare(FallenPin.of(9), FallenPin.of(1));
        assertThat(state.bowl(FallenPin.of(1))).isEqualTo(expected);
    }

    @DisplayName("두 핀의 합이 10이상이면 예외 발생")
    @Test
    void bowl_exception() {
        State state = new FirstBowling(FallenPin.of(9));

        assertThatIllegalArgumentException().isThrownBy(() -> state.bowl(FallenPin.of(2)))
                .withMessage("쓰러진 핀의 합은 10 이하여야 합니다.");
    }

    @Test
    void description() {
        State state = new FirstBowling(FallenPin.of(9));

        assertThat(state.description()).isEqualTo("9");
    }

    @Test
    void tries() {
        assertThat(new FirstBowling(FallenPin.of(9)).tries()).isEqualTo(1);
    }

    @Test
    void getScore() {
        State state = new FirstBowling(FallenPin.of(9));

        assertThat(state.getScore()).isEqualTo(new Score(9, 0));
    }

    @Test
    void addScore() {
        State state = new FirstBowling(FallenPin.of(9));
        Score previousScore = new Score(10, 1);

        assertThat(state.addScore(previousScore)).isEqualTo(new Score(19, 0));
    }
}
