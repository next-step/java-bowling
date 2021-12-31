package bowling.domain;

import bowling.engine.BonusScores;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class ClearBonusScoresTest {
    @Test
    public void create() {
        assertThat(ClearBonusScores.byNone()).isInstanceOf(ClearBonusScores.class);
        assertThat(ClearBonusScores.bySpare()).isInstanceOf(ClearBonusScores.class);
        assertThat(ClearBonusScores.byStrike()).isInstanceOf(ClearBonusScores.class);
    }

    @Test
    public void bonus() {
        BonusScores bonus = ClearBonusScores.bySpare();
        bonus.appendBonus(FrameScore.of(1));
        assertThat(bonus.size()).isEqualTo(1);
        assertThat(bonus.collect()).containsExactly(FrameScore.of(1));
    }

    @Test
    public void bonusFailed() {
        BonusScores bonus = ClearBonusScores.byNone();
        assertThatIllegalStateException().isThrownBy(() -> bonus.appendBonus(FrameScore.of(1)));
    }

    @Test
    public void sum() {
        BonusScores bonus = ClearBonusScores.bySpare();
        assertThat(bonus.sum()).isEqualTo(FrameScore.of(0));
        bonus.append(FrameScore.of(5));
        assertThat(bonus.sum()).isEqualTo(FrameScore.of(5));
    }

    @Test
    public void completeByNone() {
        BonusScores bonus = ClearBonusScores.byNone();
        assertThat(bonus.completed()).isTrue();
    }

    @Test
    public void completeBySpare() {
        BonusScores bonus = ClearBonusScores.bySpare();
        assertThat(bonus.completed()).isFalse();
        bonus.append(FrameScore.of(0));
        assertThat(bonus.completed()).isTrue();
    }

    @Test
    public void completeByStrike() {
        BonusScores bonus = ClearBonusScores.byStrike();
        assertThat(bonus.completed()).isFalse();
        bonus.append(FrameScore.of(0));
        assertThat(bonus.completed()).isFalse();
        bonus.append(FrameScore.of(0));
        assertThat(bonus.completed()).isTrue();
    }
}
