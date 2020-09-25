package bowling.domain.score.bonus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BonusScoreTest {

    @DisplayName("스페어 보너스 테스트")
    @Test
    void spareBonusTest() {
        BonusScore bonusScore = BonusScore.spareBonus(0);
        assertThat(bonusScore.isAddable()).isTrue();

        bonusScore.add(10);
        assertThat(bonusScore.isAddable()).isFalse();
    }

    @DisplayName("스트라이크 보너스 테스트")
    @Test
    void strikeBonusTest() {
        BonusScore bonusScore = BonusScore.strikeBonus(0);
        assertThat(bonusScore.isAddable()).isTrue();

        bonusScore.add(10);
        assertThat(bonusScore.isAddable()).isTrue();

        bonusScore.add(10);
        assertThat(bonusScore.isAddable()).isFalse();
    }
}