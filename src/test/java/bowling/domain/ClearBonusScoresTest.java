package bowling.domain;

import bowling.engine.BonusScores;
import bowling.engine.Score;
import org.junit.Test;

import static bowling.domain.shot.ShotResultTest.ONE;
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
        bonus.appendBonus(ONE);
        assertThat(bonus.size()).isEqualTo(1);
        assertThat(bonus.collect()).containsExactly(BowlingScore.of(1));
    }

    @Test
    public void bonusNotApplied() {
        BonusScores bonus = ClearBonusScores.byNone();
        Score before = bonus.sum();
        bonus.appendBonus(ONE);
        assertThat(bonus.sum()).isEqualTo(before);
        assertThat(bonus.collect()).doesNotContain(BowlingScore.of(1));
    }

    @Test
    public void sum() {
        BonusScores bonus = ClearBonusScores.bySpare();
        assertThat(bonus.sum()).isEqualTo(BowlingScore.of(0));
        bonus.append(BowlingScore.of(5));
        assertThat(bonus.sum()).isEqualTo(BowlingScore.of(5));
    }

    @Test
    public void remainByNone() {
        BonusScores bonus = ClearBonusScores.byNone();
        assertThat(bonus.remain()).isFalse();
    }

    @Test
    public void remainBySpare() {
        BonusScores bonus = ClearBonusScores.bySpare();
        assertThat(bonus.remain()).isTrue();
        bonus.append(BowlingScore.of(0));
        assertThat(bonus.remain()).isFalse();
    }

    @Test
    public void remainByStrike() {
        BonusScores bonus = ClearBonusScores.byStrike();
        assertThat(bonus.remain()).isTrue();
        bonus.append(BowlingScore.of(0));
        assertThat(bonus.remain()).isTrue();
        bonus.append(BowlingScore.of(0));
        assertThat(bonus.remain()).isFalse();
    }
}
