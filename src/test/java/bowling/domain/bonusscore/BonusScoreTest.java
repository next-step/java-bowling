package bowling.domain.bonusscore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class BonusScoreTest {
    @DisplayName("스트라이크 보너스 점수 생성")
    @Test
    void createStrikeBonus() {
        assertThatCode(() -> BonusScore.strikeBonus(0));
    }

    @DisplayName("스페어 보너스 점수 생성")
    @Test
    void createSpareBonus() {
        assertThatCode(() -> BonusScore.spareBonus(0));
    }

    @DisplayName("스트라이크 보너스 점수 계산")
    @Test
    void totalStrikeBonusPoint() {
        BonusScore bonusScore = BonusScore.strikeBonus(0);
        bonusScore.add(10);
        bonusScore.add(10);

        assertThat(bonusScore.getTotalBonusPoint()).isEqualTo(20);
    }

    @DisplayName("스페어 보너스 점수 계산")
    @Test
    void totalSpareBonusPoint() {
        BonusScore bonusScore = BonusScore.spareBonus(0);
        bonusScore.add(10);

        assertThat(bonusScore.getTotalBonusPoint()).isEqualTo(10);
    }

    @DisplayName("스페어 보너스는 1구만 추가 가능")
    @Test
    void addableSpareBonus() {
        BonusScore bonusScore = BonusScore.spareBonus(0);
        assertThat(bonusScore.isAddable()).isTrue();

        bonusScore.add(10);
        assertThat(bonusScore.isAddable()).isFalse();
    }

    @DisplayName("스트라이크 보너스는 2구까지 추가 가능.")
    @Test
    void addableStrikeBonus() {
        BonusScore bonusScore = BonusScore.strikeBonus(0);
        assertThat(bonusScore.isAddable()).isTrue();

        bonusScore.add(10);
        assertThat(bonusScore.isAddable()).isTrue();

        bonusScore.add(10);
        assertThat(bonusScore.isAddable()).isFalse();
    }
}
