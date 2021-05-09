package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @DisplayName("Score 인스턴스 셍성 여부 테스트")
    @Test
    void 생성() {
        // given
        int scorePoint = 10;
        BonusCount bonusCount = BonusCount.strike();

        // when
        Score score = Score.of(scorePoint, bonusCount);

        // then
        assertThat(score).isNotNull();
    }
}