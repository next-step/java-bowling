package bowling.domain.state;

import bowling.domain.Score;
import bowling.exception.BowlingGameException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReadyTest {
    @Test
    void 레디상태에서_점수가져오려_할_경우() {
        assertThatThrownBy(() -> {
            new Ready().getScore();
        }).isInstanceOf(BowlingGameException.class);
    }

    @Test
    void 레디상태에서_추가_점수_계산() {
        assertThatThrownBy(() -> {
            new Ready().calculateScore(new Score(1,1));
        }).isInstanceOf(BowlingGameException.class);
    }

}