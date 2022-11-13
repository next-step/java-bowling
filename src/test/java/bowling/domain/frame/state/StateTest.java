package bowling.domain.frame.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.domain.dto.BowlRecord;
import bowling.domain.frame.Score;

class StateTest {
    @Test
    @DisplayName("정상적인 범위에서 보너스 합산 시에 새로운 스코어 객체를 반환한다.")
    void addBonusScore() {
        State state = new MockState();
        Score score = new Score(7, 1);
        assertThat(state.addBonusScore(score, 3))
            .isEqualTo(new Score(10, 0));

    }

    @Test
    @DisplayName("보너스 점수 계산 과정에서 더 이상 보너스를 합산 할 수 없을 시 예외를 던진다.")
    void addScoreWhenNoMoreLeft() {
        State state = new MockState();
        Score score = new Score(7, 0);
        assertThatThrownBy(() -> state.addBonusScore(score, 4))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("점수를 더 이상 합산할 수 없습니다");
    }

    static class MockState extends State {

        @Override
        public State bowl(int pins) {
            return null;
        }

        @Override
        public BowlRecord createBowlRecord() {
            return null;
        }

        @Override
        public Score getScore() {
            return null;
        }

        @Override
        public Score calculateBonusScore(Score previousScore) {
            return null;
        }

        @Override
        public boolean isFinish() {
            return false;
        }

        @Override
        public boolean canBonusBowl() {
            return false;
        }
    }
}
