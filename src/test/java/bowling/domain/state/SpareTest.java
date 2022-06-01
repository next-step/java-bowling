package bowling.domain.state;

import bowling.domain.Score;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SpareTest {
    @Test
    void 핀수의_합이_10_이_아닐경우() {
        assertThatThrownBy(() -> {
            new Spare(9,4);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 추가점수_계산_첫번째_핀에서_남은횟수가_끝남() {
        assertThat(new Spare(5,5).calculateAddScore(new Score(10,1)))
                .isEqualTo(new Score(15,0));
    }

    @Test
    void 추가점수_계산_두번째_핀에서_남은횟수가_끝남() {
        assertThat(new Spare(5,5).calculateAddScore(new Score(10,2)))
                .isEqualTo(new Score(20,0));
    }

}