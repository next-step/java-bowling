package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;
import bowling.exception.BowlingGameException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SpareTest {
    @Test
    void 스페어상태에서_bowl() {
        assertThatThrownBy(() -> {
            new Spare(new Pins(5), new Pins(5)).bowl(1);
        }).isInstanceOf(BowlingGameException.class);
    }

    @Test
    void 핀수의_합이_10_이_아닐경우() {
        assertThatThrownBy(() -> {
            new Spare(new Pins(9),new Pins(4));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 추가점수_계산_첫번째_핀에서_남은횟수가_끝남() {
        assertThat(new Spare(new Pins(5),new Pins(5)).calculateScore(new Score(10,1)))
                .isEqualTo(new Score(15,0));
    }

    @Test
    void 추가점수_계산_두번째_핀에서_남은횟수가_끝남() {
        assertThat(new Spare(new Pins(5),new Pins(5)).calculateScore(new Score(10,2)))
                .isEqualTo(new Score(20,0));
    }
}