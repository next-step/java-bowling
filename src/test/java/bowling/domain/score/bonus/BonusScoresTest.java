package bowling.domain.score.bonus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class BonusScoresTest {
    private BonusScores bonusScores;

    @BeforeEach
    void setUp() {
        bonusScores = new BonusScores();
    }

    @DisplayName("보너스 점수 일급컬렉션 생성 테스트")
    @Test
    void createBonusScoresTest() {
        bonusScores.add(BonusScore.strikeBonus(0));
        bonusScores.add(BonusScore.spareBonus(1));
        bonusScores.add(BonusScore.missBonus(2));
        BonusScores addableBonusScores = bonusScores.findAddableBonusScores();

        assertThat(addableBonusScores.isExistBonusScore(0)).isTrue();
        assertThat(addableBonusScores.isExistBonusScore(1)).isTrue();
        assertThat(addableBonusScores.isExistBonusScore(2)).isFalse();
    }

    @DisplayName("보너스 점수 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void isExistBonusScoreTest(int frameIndex) {
        bonusScores.add(BonusScore.strikeBonus(frameIndex));

        assertThat(bonusScores.isExistBonusScore(frameIndex)).isTrue();
        assertThat(bonusScores.isExistBonusScore(frameIndex + 1)).isFalse();
    }
}