package bowling.domain.state;

import bowling.domain.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MissTest {
    @Test
    void 입력한_두_핀의_합이_10_이_넘을경우() {
        assertThatThrownBy(() -> {
            new Miss(5,9);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 추가점수_계산_첫번째_핀에서_남은횟수가_끝남() {
        assertThat(new Miss(5,4).calculateAddScore(new Score(10,1)))
                .isEqualTo(new Score(15,0));
    }

    @Test
    void 추가점수_계산_두번째_핀에서_남은횟수가_끝남() {
        assertThat(new Miss(5,4).calculateAddScore(new Score(10,2)))
                .isEqualTo(new Score(19,0));
    }

}