package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    void update() {
        Score score = new Score();

        score.update(RoundsStatus.STRIKE, 10, false);

        assertThat(score.getTotalScore()).isEqualTo(10);
        assertThat(score.getBonusAddCount()).isEqualTo(2);
    }

    @Test
    void updateBonusCount() {
        Score score = new Score();
        score.update(RoundsStatus.STRIKE, 10, false);

        score.updateBonus(10);

        assertThat(score.getTotalScore()).isEqualTo(20);
        assertThat(score.getBonusAddCount()).isEqualTo(1);
    }

    @ParameterizedTest
    @CsvSource(value = {"STRIKE:10:TRUE", "NONE:8:FALSE"}, delimiter = ':')
    void availableBonus(RoundsStatus status, int clearPinCount, boolean expected) {
        Score score = new Score();
        score.update(status, clearPinCount, false);

        boolean availableBonus = score.availableBonus();

        assertThat(availableBonus).isEqualTo(expected);
    }
}
