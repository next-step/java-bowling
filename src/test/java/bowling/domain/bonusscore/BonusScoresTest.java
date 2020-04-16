package bowling.domain.bonusscore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class BonusScoresTest {
    private BonusScores bonusScores;

    @BeforeEach
    void setUp() {
        bonusScores = new BonusScores();
    }

    @DisplayName("보너스 점수 컬렉션 생성")
    @Test
    void create() {
        assertThatCode(() -> new BonusScores());
    }

    @DisplayName("점수 추가 가능한 보너스 점수 컬렉션 생성")
    @Test
    void createAddableBonusScores() {
        bonusScores.add(BonusScore.strikeBonus(0));
        bonusScores.add(BonusScore.spareBonus(1));
        bonusScores.add(BonusScore.noneBonus(2));

        BonusScores addableBonusScores = bonusScores.findAddableBonusScores();

        assertThat(addableBonusScores.isExistBonusScore(0)).isTrue();
        assertThat(addableBonusScores.isExistBonusScore(1)).isTrue();
        assertThat(addableBonusScores.isExistBonusScore(2)).isFalse();
    }

    @DisplayName("보너스 점수 추가")
    @ParameterizedTest
    @ValueSource(ints = {10, 1, 2})
    void addBonusPoint(int point) {
        bonusScores.add(BonusScore.strikeBonus(0));
        bonusScores.add(BonusScore.spareBonus(1));

        bonusScores.addBonusPoint(point);

        assertThat(bonusScores.findBonusScore(0).getTotalBonusPoint()).isEqualTo(point);
        assertThat(bonusScores.findBonusScore(1).getTotalBonusPoint()).isEqualTo(point);
    }

    @DisplayName("보너스 점수 찾기")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void findBonusScore(int frameIndex) {
        bonusScores.add(BonusScore.strikeBonus(frameIndex));
        BonusScore bonusScore = bonusScores.findBonusScore(frameIndex);

        assertThat(bonusScore.isEqualFrameIndex(frameIndex)).isTrue();
    }

    @DisplayName("보너스 점수 존재 유무 판별")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void isExistBonusScore(int frameIndex) {
        bonusScores.add(BonusScore.strikeBonus(frameIndex));

        assertThat(bonusScores.isExistBonusScore(frameIndex)).isTrue();
        assertThat(bonusScores.isExistBonusScore(frameIndex + 1)).isFalse();
    }
}
