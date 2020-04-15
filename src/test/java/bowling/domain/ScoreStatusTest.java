package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreStatusTest {
    @Test
    void update() {
        int clearPinCount = 10;
        ScoreStatus scoreStatus = new ScoreStatus();

        scoreStatus.update(0, clearPinCount, clearPinCount, false);

        assertThat(scoreStatus.getScore().getTotalScore()).isEqualTo(clearPinCount);
    }

    @Test
    void updateBonus() {
        ScoreStatus scoreStatus = new ScoreStatus();

        scoreStatus.updateBonus(10);

        assertThat(scoreStatus.getScore().getTotalScore()).isEqualTo(10);
    }

    @Test
    void endCalculate_false() {
        ScoreStatus scoreStatus = new ScoreStatus();
        scoreStatus.update(0, 10, 10, false);

        assertThat(scoreStatus.endCalculate()).isFalse();
    }

    @Test
    void endCalculate_true() {
        ScoreStatus scoreStatus = new ScoreStatus();
        scoreStatus.update(0, 10, 10, false);
        scoreStatus.updateBonus(10);
        scoreStatus.updateBonus(10);

        assertThat(scoreStatus.endCalculate()).isTrue();
    }

    @Test
    void addScore() {
        ScoreStatus scoreStatus = new ScoreStatus();

        scoreStatus.addScore(10);

        assertThat(scoreStatus.getTotalScore()).isEqualTo(10);
    }

    @Test
    void isStrike() {
        int clearPinCount = 10;
        ScoreStatus scoreStatus = new ScoreStatus();
        scoreStatus.update(0, clearPinCount, clearPinCount, false);

        boolean isStrike = scoreStatus.isStrike();

        assertThat(isStrike).isTrue();
    }

    @Test
    void isSpare() {
        ScoreStatus scoreStatus = new ScoreStatus();
        scoreStatus.update(0, 9, 9, false);
        scoreStatus.update(1, 1, 10, false);

        boolean isSpare = scoreStatus.isSpare();

        assertThat(isSpare).isTrue();

    }

    @Test
    void isNone() {
        ScoreStatus scoreStatus = new ScoreStatus();
        scoreStatus.update(0, 9, 9, false);

        boolean isNone = scoreStatus.isNone();

        assertThat(isNone).isTrue();
    }
}
