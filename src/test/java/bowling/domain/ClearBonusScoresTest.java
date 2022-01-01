package bowling.domain;

import bowling.engine.BonusScores;
import bowling.engine.Score;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
    public void bonusNotApplied() {
        BonusScores bonus = ClearBonusScores.byNone();
        Score before = bonus.sum();
        Score bonusScore = FrameScore.of(1);
        bonus.appendBonus(bonusScore);
        assertThat(bonus.sum()).isEqualTo(before);
        assertThat(bonus.collect()).doesNotContain(bonusScore);
    }

    @Test
    public void sum() {
        BonusScores bonus = ClearBonusScores.bySpare();
        assertThat(bonus.sum()).isEqualTo(FrameScore.of(0));
        bonus.append(FrameScore.of(5));
        assertThat(bonus.sum()).isEqualTo(FrameScore.of(5));
    }

    @Test
    public void remainAndCompleteByNone() {
        BonusScores bonus = ClearBonusScores.byNone();
        assertThat(bonus.completed()).isTrue();
        assertThat(bonus.remain()).isFalse();
    }

    @Test
    public void remainAndCompleteBySpare() {
        BonusScores bonus = ClearBonusScores.bySpare();
        assertThat(bonus.completed()).isFalse();
        assertThat(bonus.remain()).isTrue();
        bonus.append(FrameScore.of(0));
        assertThat(bonus.completed()).isTrue();
        assertThat(bonus.remain()).isFalse();
    }

    @Test
    public void remainAndCompleteByStrike() {
        BonusScores bonus = ClearBonusScores.byStrike();
        assertThat(bonus.completed()).isFalse();
        assertThat(bonus.remain()).isTrue();
        bonus.append(FrameScore.of(0));
        assertThat(bonus.completed()).isFalse();
        assertThat(bonus.remain()).isTrue();
        bonus.append(FrameScore.of(0));
        assertThat(bonus.completed()).isTrue();
        assertThat(bonus.remain()).isFalse();
    }
}
